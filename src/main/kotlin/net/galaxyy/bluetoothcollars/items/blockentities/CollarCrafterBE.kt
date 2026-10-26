package net.galaxyy.bluetoothcollars.items.blockentities

import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

class CollarCrafterBE(worldPosition: BlockPos, blockState: BlockState) : BlockEntity(ModBlockEntities.COLLAR_CRAFTER_BE, worldPosition, blockState) {
    private var collar: ItemStack = ItemStack.EMPTY

    fun rightClick(player: ServerPlayer, hand: InteractionHand): InteractionResult {
        if (player.getItemInHand(hand).`is`(ModItemTags.COLLARS)) {
            return if (trySetCollarStack(player, hand)) InteractionResult.SUCCESS_SERVER else InteractionResult.PASS
        } else if (player.getItemInHand(hand).isEmpty) {
            return if (tryTakeAwayCollarStack(player, hand)) InteractionResult.SUCCESS_SERVER else InteractionResult.PASS
        }
        return InteractionResult.PASS
    }

    private fun trySetCollarStack(player: ServerPlayer, hand: InteractionHand): Boolean {
        if (!collar.isEmpty) return false
        collar = player.getItemInHand(hand)
        player.setItemInHand(hand, ItemStack.EMPTY)
        this.setChanged()
        return true
    }

    private fun tryTakeAwayCollarStack(player: ServerPlayer, hand: InteractionHand): Boolean {
        if (collar.isEmpty) return false
        player.setItemInHand(hand, collar)
        collar = ItemStack.EMPTY
        this.setChanged()
        return true
    }

    override fun saveAdditional(output: ValueOutput) {
        output.storeNullable("collar", ItemStack.CODEC, if (collar.isEmpty) null else collar)

        super.saveAdditional(output)
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)

        this.collar = input.read("collar", ItemStack.CODEC).orElse(ItemStack.EMPTY) ?: ItemStack.EMPTY
    }
}