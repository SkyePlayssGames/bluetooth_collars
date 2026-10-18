package net.galaxyy.bluetoothcollars.items.collars

import eu.pb4.trinkets.api.SlotAttributes
import eu.pb4.trinkets.api.TrinketSlotAccess
import eu.pb4.trinkets.api.TrinketsApi
import eu.pb4.trinkets.api.callback.TrinketCallback
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.events.ModTriggers
import net.galaxyy.bluetoothcollars.gamerules.ModGameRules
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.items.itemdata.OwnerDataComponent
import net.galaxyy.bluetoothcollars.sounds.ModSounds
import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import java.util.UUID
import java.util.function.BiConsumer

abstract class AbstractCollar(properties: Properties, val key: ResourceKey<Item>) : Item(
    properties
        .component(ModDataComponents.OWNER, OwnerDataComponent(listOf()))
        .stacksTo(1)
), TrinketCallback {
    val necklaceSlotModifier: Holder<Attribute> = SlotAttributes.createAttributeForSlot("chest/necklace")

    abstract fun rightClick(player: Player, level: Level, blockPos: BlockPos?, collar: ItemStack, pet: ServerPlayer): InteractionResult

    open fun shiftRightClick(player: Player, level: Level, blockPos: BlockPos?, collar: ItemStack, pet: ServerPlayer): InteractionResult {
        return rightClick(player, level, blockPos, collar, pet)
    }

    open fun tickPlayer(collar: ItemStack, player: ServerPlayer) {
        if (collar.get(ModDataComponents.BELL)?.bell == true &&
            player.random.nextInt(60_000_000) / 1_000_000 < player.knownSpeed.length() * 5
        ) {
            player.level().playSound(null, player.blockPosition(),
                ModSounds.BELL, SoundSource.PLAYERS, 0.7f, 1f
            )
        }
    }

    override fun onCraftedBy(itemStack: ItemStack, player: Player) {
        super.onCraftedBy(itemStack, player)
        itemStack.set(ModDataComponents.OWNER, OwnerDataComponent(listOf(player.uuid)))
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        if (level.isClientSide) return super.use(level, player, hand)
        if (!player.isShiftKeyDown) return super.use(level, player, hand)
        val itemStack: ItemStack = player.getItemInHand(hand)
        val offhandItem: ItemStack = player.getItemInHand(offHand(hand))
        if (offhandItem.`is`(ModItems.LOCK)) {
            val result = useLock(level, player, itemStack, hand, offhandItem)
            if (result == InteractionResult.SUCCESS_SERVER) return result
        }
        if (itemStack.get(ModDataComponents.OWNER_ALL)?.bool == true) return InteractionResult.PASS
        val ownersList: List<UUID> = itemStack.get(ModDataComponents.OWNER)!!.list
        if (!ownersList.contains(player.uuid)) {
            val owners: ArrayList<UUID> = if (ownersList.isEmpty()) ArrayList() else ArrayList(ownersList)
            owners.add(player.uuid)
            itemStack.set(ModDataComponents.OWNER, OwnerDataComponent(owners.distinct()))
            return InteractionResult.SUCCESS_SERVER
        } else {
            return InteractionResult.PASS
        }
    }

    override fun forEachTrinketModifier(
        stack: ItemStack,
        slot: TrinketSlotAccess,
        entity: LivingEntity,
        slotIdentifier: Identifier,
        consumer: BiConsumer<Holder<Attribute>, AttributeModifier>
    ) {
        if (stack.get(ModDataComponents.EXTRA_COLLAR) == true) consumer.accept(this.necklaceSlotModifier, AttributeModifier(
            slotIdentifier.withSuffix("bluetooth_collars/collar"), 1.0, AttributeModifier.Operation.ADD_VALUE
        ))
    }

    abstract class Remote<T : AbstractCollar>(val collar: Class<T>, properties: Properties) : Item(properties
        .stacksTo(1)
    ) {
        var runningRemoteClick: Boolean = false

        override fun useOn(context: UseOnContext): InteractionResult {
            return if (!runningRemoteClick) runRemoteCode(context.level, context.player, context.hand, context.clickedPos) else InteractionResult.PASS
        }

        override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
            return if (!runningRemoteClick) runRemoteCode(level, player, hand, null) else InteractionResult.PASS
        }

        private fun runRemoteCode(level: Level, player: Player?, hand: InteractionHand, blockPos: BlockPos?): InteractionResult {
            runningRemoteClick = true
            if (level.isClientSide) return InteractionResult.PASS
            var result: InteractionResult = InteractionResult.PASS

            findCollars(level as ServerLevel, player?.blockPosition(), player).forEach { pair ->
                if (player!!.isShiftKeyDown) result = if ((pair.key.item as AbstractCollar).shiftRightClick(
                        player, level, blockPos,
                        pair.key, pair.value
                    ) == InteractionResult.SUCCESS_SERVER) InteractionResult.SUCCESS_SERVER else result
                else result = if ((pair.key.item as AbstractCollar).rightClick(player, level, blockPos, pair.key, pair.value) == InteractionResult.SUCCESS_SERVER) InteractionResult.SUCCESS_SERVER else result
            }
            return result
        }

        fun findCollars(level: ServerLevel, blockPos: BlockPos?, player: Player?): HashMap<ItemStack, ServerPlayer> {
            val clickerRange: Double = level.gameRules.get(ModGameRules.CLICKER_RANGE).toDouble()
            val result: HashMap<ItemStack, ServerPlayer> = HashMap()

            if (blockPos == null || player == null) return result

            level.getEntitiesOfClass(Player::class.java, AABB(blockPos).inflate(clickerRange)).forEach { pet ->
                TrinketsApi.getAttachment(pet).forEach { _, item ->
                    if (item.item.javaClass == collar && (item.get(ModDataComponents.OWNER)?.list?.contains(player.uuid) == true || item.get(ModDataComponents.OWNER_ALL)?.bool == true)) {
                        result[item] = pet as ServerPlayer
                        pet.sendOverlayMessage(Component.translatable("$MOD_ID.overlay.click"))
                        level.playSound(null, pet.blockPosition(), ModSounds.CLICK, SoundSource.PLAYERS, 1f, 1f)
                        ModTriggers.COLLAR_ACTIVATE_TRIGGER.trigger(pet, item)
                    }
                }
            }
            return result
        }

        override fun inventoryTick(itemStack: ItemStack, level: ServerLevel, owner: Entity, slot: EquipmentSlot?) {
            runningRemoteClick = false
            super.inventoryTick(itemStack, level, owner, slot)
        }
    }

    private fun useLock(level: Level, player: Player, itemStack: ItemStack, hand: InteractionHand, offhandItem: ItemStack): InteractionResult {
        val curseOfBinding: Holder<Enchantment> = level.registryAccess().get(Enchantments.BINDING_CURSE).get()
        if (itemStack.enchantments.getLevel(curseOfBinding) != 0) return InteractionResult.PASS

        itemStack.enchant(curseOfBinding, 1)
        player.setItemInHand(hand, itemStack)

        if (!player.isCreative) {
            offhandItem.count--
            player.setItemInHand(offHand(hand), offhandItem)
        }

        return InteractionResult.SUCCESS_SERVER
    }

    fun offHand(hand: InteractionHand): InteractionHand
    = if (hand == InteractionHand.MAIN_HAND) InteractionHand.OFF_HAND else InteractionHand.MAIN_HAND
}