package net.galaxyy.bluetoothcollars.items

import net.galaxyy.bluetoothcollars.items.blockentities.CollarCrafterBE
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class CollarCrafter(properties: Properties) : BaseEntityBlock(properties) {
    override fun newBlockEntity(
        worldPosition: BlockPos,
        blockState: BlockState
    ): BlockEntity {
        return CollarCrafterBE(worldPosition, blockState)
    }
}