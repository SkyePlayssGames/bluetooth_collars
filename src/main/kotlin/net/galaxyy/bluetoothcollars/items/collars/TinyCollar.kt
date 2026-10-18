package net.galaxyy.bluetoothcollars.items.collars

import eu.pb4.trinkets.api.TrinketSlotAccess
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import java.util.function.BiConsumer

class TinyCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(properties
    .component(ModDataComponents.ACTIVE, false), key
) {
    override fun rightClick(
        player: Player,
        level: Level,
        blockPos: BlockPos?,
        collar: ItemStack,
        pet: ServerPlayer
    ): InteractionResult {
        collar.set(ModDataComponents.ACTIVE, collar.get(ModDataComponents.ACTIVE) != true)
        return InteractionResult.SUCCESS_SERVER
    }

    override fun forEachTrinketModifier(
        stack: ItemStack,
        slot: TrinketSlotAccess,
        entity: LivingEntity,
        slotIdentifier: Identifier,
        consumer: BiConsumer<Holder<Attribute>, AttributeModifier>
    ) {
        super.forEachTrinketModifier(stack, slot, entity, slotIdentifier, consumer)
        val scaleModifier: AttributeModifier = AttributeModifier(slotIdentifier.withSuffix("$MOD_ID/scale"), -0.75, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        val jumpModifier: AttributeModifier = AttributeModifier(slotIdentifier.withSuffix("$MOD_ID/jump"), -0.75, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        val speedModifier: AttributeModifier = AttributeModifier(slotIdentifier.withSuffix("$MOD_ID/speed"), -0.75, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)


        if (stack.get(ModDataComponents.ACTIVE) == true) {
            consumer.accept(Attributes.SCALE, scaleModifier)
            consumer.accept(Attributes.JUMP_STRENGTH, jumpModifier)
            consumer.accept(Attributes.MOVEMENT_SPEED, speedModifier)
        }
    }

    class TinyRemote(properties: Properties) : Remote<TinyCollar>(TinyCollar::class.java, properties)
}