package net.galaxyy.bluetoothcollars.items.blockentities

import net.galaxyy.bluetoothcollars.datagen.recipe.CollarCrafterRecipeInput
import net.galaxyy.bluetoothcollars.datagen.recipe.ModRecipeTypes
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags
import net.galaxyy.bluetoothcollars.stats.ModStats
import net.minecraft.client.telemetry.TelemetryProperty.GameMode
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.ContainerHelper
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.minecraft.world.ticks.ContainerSingleItem

class CollarCrafterBE(worldPosition: BlockPos, blockState: BlockState) : BlockEntity(ModBlockEntities.COLLAR_CRAFTER_BE, worldPosition, blockState), ContainerSingleItem {
     var inventory: NonNullList<ItemStack> = NonNullList.withSize(1, ItemStack.EMPTY)

    fun rightClick(player: ServerPlayer, hand: InteractionHand): InteractionResult {
        return if (isEmpty) {
            if (trySetCollarStack(player, hand)) InteractionResult.SUCCESS_SERVER else InteractionResult.CONSUME
        } else if (player.getItemInHand(hand).isEmpty) {
            if (tryTakeAwayCollarStack(player, hand)) InteractionResult.SUCCESS_SERVER else InteractionResult.CONSUME
        } else {
            if (tryCraftCollar(player, hand)) InteractionResult.SUCCESS_SERVER else InteractionResult.CONSUME
        }
    }

    private fun tryCraftCollar(player: ServerPlayer, hand: InteractionHand): Boolean {
        if (isEmpty) return false
        val input = CollarCrafterRecipeInput(
            player.getItemInHand(hand), theItem
        )
        val recipe = (level as ServerLevel).recipeAccess().getRecipeFor(
            ModRecipeTypes.COLLAR_CRAFTER_TYPE, input, level!!
        )
        if (recipe.isEmpty) return false
        theItem = recipe.get().value.assemble(input)
        if (!player.gameMode().isCreative) player.setItemInHand(hand, player.getItemInHand(hand).copyWithCount(player.getItemInHand(hand).count - 1))
        this.setChanged()
        player.awardStat(ModStats.COLLAR_CRAFTER_CRAFTS)
        return true
    }

    private fun trySetCollarStack(player: ServerPlayer, hand: InteractionHand): Boolean {
        if (!isEmpty) return false
        theItem = player.getItemInHand(hand).copyWithCount(1)
        player.getItemInHand(hand).shrink(1)
        this.setChanged()
        return true
    }

    private fun tryTakeAwayCollarStack(player: ServerPlayer, hand: InteractionHand): Boolean {
        if (isEmpty) return false
        player.setItemInHand(hand, theItem.copy())
        clearContent()
        this.setChanged()
        return true
    }

    override fun saveAdditional(output: ValueOutput) {
        super.saveAdditional(output)

        ContainerHelper.saveAllItems(output, this.inventory)
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        this.inventory.clear()
        ContainerHelper.loadAllItems(input, this.inventory)
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener> {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(registries)
    }

    override fun setChanged() {
        super.setChanged()

        val state = blockState
        level?.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL)
    }

    override fun stillValid(player: Player): Boolean {
        return true
    }

    override fun getTheItem(): ItemStack {
        return inventory.first()
    }

    override fun setTheItem(itemStack: ItemStack) {
        inventory[0] = itemStack.copyWithCount(1)
        setChanged()
    }

    override fun clearContent() {
        inventory[0] = ItemStack.EMPTY
        setChanged()
    }
}