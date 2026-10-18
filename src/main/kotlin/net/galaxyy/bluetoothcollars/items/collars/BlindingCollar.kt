package net.galaxyy.bluetoothcollars.items.collars

import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class BlindingCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(properties
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

    override fun tickPlayer(collar: ItemStack, player: ServerPlayer) {
        super.tickPlayer(collar, player)

        if (collar.get(ModDataComponents.ACTIVE) == true) {
            player.addEffect(MobEffectInstance(MobEffects.BLINDNESS, -1))
        }
    }

    class BlindingRemote(properties: Properties) : Remote<BlindingCollar>(BlindingCollar::class.java, properties)
}