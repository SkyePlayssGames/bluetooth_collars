package net.galaxyy.bluetoothcollars.items

import net.galaxyy.bluetoothcollars.items.blockentities.CollarCrafterBE
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class CollarCrafter(properties: Properties) : BaseEntityBlock(properties) {
    override fun newBlockEntity(
        worldPosition: BlockPos,
        blockState: BlockState
    ): BlockEntity {
        return CollarCrafterBE(worldPosition, blockState)
    }

    override fun useItemOn(
        itemStack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (level.isClientSide || hand == InteractionHand.OFF_HAND || level.getBlockEntity(pos) !is CollarCrafterBE) return InteractionResult.CONSUME
        return (level.getBlockEntity(pos)!! as CollarCrafterBE).rightClick(player as ServerPlayer, hand)
    }
}