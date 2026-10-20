package net.galaxyy.bluetoothcollars.items.collars

import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class TieDownCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(
    properties
        .component(ModDataComponents.ACTIVE, false)
        .component(ModDataComponents.ACTIVE2, false), key
) {
    override fun rightClick(player: Player, level: Level, blockPos: BlockPos?, collar: ItemStack, pet: ServerPlayer): InteractionResult {
        collar.set(ModDataComponents.ACTIVE2, false)
        val location: Pair<BlockPos, Boolean> = findLocation(level, blockPos)
        if (collar.get(ModDataComponents.LOCATION) == location.first || !location.second) {
            collar.set(ModDataComponents.LOCATION, null)
            collar.set(ModDataComponents.ACTIVE, false)
        } else {
            collar.set(ModDataComponents.LOCATION, location.first)
            collar.set(ModDataComponents.ACTIVE, true)
        }
        return InteractionResult.SUCCESS_SERVER
    }

    override fun shiftRightClick(
        player: Player,
        level: Level,
        blockPos: BlockPos?,
        collar: ItemStack,
        pet: ServerPlayer
    ): InteractionResult {
        val result: InteractionResult = rightClick(player, level, blockPos, collar, pet)
        collar.set(ModDataComponents.ACTIVE2, true)
        return result
    }

    override fun tickPlayer(collar: ItemStack, player: ServerPlayer) {
        super.tickPlayer(collar, player)

        val pos: BlockPos? = collar.get(ModDataComponents.LOCATION)
        if (checkTieDownCollarPos(pos, player, collar)) {
            pos!!
            player.teleportTo(pos.x.toDouble() + 0.5, pos.y.toDouble(), pos.z.toDouble() + 0.5)
        }
    }

    private fun findLocation(level: Level, pos: BlockPos?): Pair<BlockPos, Boolean> {
        if (pos == null) return Pair(BlockPos.ZERO, false)
        if (level.getBlockState(pos).isCollisionShapeFullBlock(level, pos)) {
            if (level.getBlockState(pos.above()).isCollisionShapeFullBlock(level, pos.above())) return Pair(BlockPos.ZERO, false)
            return Pair(pos.above(), true)
        }
        return Pair(pos, true)
    }

    private fun checkTieDownCollarPos(pos: BlockPos?, player: ServerPlayer, collar: ItemStack): Boolean {
        if (collar.get(ModDataComponents.ACTIVE) != true || pos == null) return false
        val distance = if (collar.get(ModDataComponents.ACTIVE2)!!) 3*3 else 16*16
        return player.distanceToSqr(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()) > distance
    }

    class TieDownRemote(properties: Properties) : Remote<TieDownCollar>(TieDownCollar::class.java, properties)
}