package net.galaxyy.bluetoothcollars.items.collars

import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class PawCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(properties
    .component(ModDataComponents.ACTIVE,  false)
    .component(ModDataComponents.ACTIVE2, false), key
) {
    override fun rightClick(
        player: Player,
        level: Level,
        blockPos: BlockPos?,
        collar: ItemStack,
        pet: ServerPlayer
    ): InteractionResult {
        collar.set(ModDataComponents.ACTIVE, collar.get(ModDataComponents.ACTIVE) == false)
        return InteractionResult.SUCCESS_SERVER
    }

    override fun shiftRightClick(
        player: Player,
        level: Level,
        blockPos: BlockPos?,
        collar: ItemStack,
        pet: ServerPlayer
    ): InteractionResult {
        collar.set(ModDataComponents.ACTIVE2, collar.get(ModDataComponents.ACTIVE2) == false)
        return InteractionResult.SUCCESS_SERVER
    }


    class PawRemote(properties: Properties) : Remote<PawCollar>(PawCollar::class.java, properties)
}