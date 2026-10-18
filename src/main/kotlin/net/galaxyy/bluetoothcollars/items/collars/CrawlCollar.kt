package net.galaxyy.bluetoothcollars.items.collars

import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class CrawlCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(properties
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
            player.pose = Pose.SWIMMING
            player.isSwimming = true
        }
    }

    class CrawlRemote(properties: Properties) : Remote<CrawlCollar>(CrawlCollar::class.java, properties)
}