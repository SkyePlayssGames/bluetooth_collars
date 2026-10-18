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
import net.minecraft.world.phys.Vec3

class FrozenCollar(properties: Properties, key: ResourceKey<Item>) : AbstractCollar(properties
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
        collar.set(ModDataComponents.ACTIVE2, false)
        pet.isNoGravity = collar.get(ModDataComponents.ACTIVE) == true
        pet.deltaMovement = Vec3.ZERO
        return InteractionResult.SUCCESS_SERVER
    }

    class FrozenRemote(properties: Properties) : Remote<FrozenCollar>(FrozenCollar::class.java, properties)
}