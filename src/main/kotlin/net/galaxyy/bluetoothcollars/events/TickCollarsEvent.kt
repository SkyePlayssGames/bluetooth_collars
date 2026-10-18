package net.galaxyy.bluetoothcollars.events

import eu.pb4.trinkets.api.TrinketsApi
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.collars.AbstractCollar
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags
import net.galaxyy.bluetoothcollars.items.itemdata.OwnerDataComponent
import net.minecraft.resources.Identifier
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.ItemStack

class TickCollarsEvent : ServerTickEvents.StartTick {
    override fun onStartTick(server: MinecraftServer) {
        server.playerList.players.forEach { player ->
            fixItems(player)
            deblind(player)
            activateGravity(player)

            TrinketsApi.getAttachment(player).forEach { _, collar ->
                if (collar.item !is AbstractCollar) return@forEach


                fixCollarOwnership(collar)
                checkGetCollaredFunction(player, collar)
                (collar.item as AbstractCollar).tickPlayer(collar, player)
            }
        }
    }

    private fun fixItems(player: ServerPlayer) {
        player.inventory.forEach { itemStack ->
            fixActiveComponents(itemStack)
        }
    }

    private fun fixCollarOwnership(itemStack: ItemStack) {
        if (itemStack.get(ModDataComponents.OWNER_OLD) != null) {
            itemStack.set(
                ModDataComponents.OWNER,
                OwnerDataComponent(listOf(itemStack.get(ModDataComponents.OWNER_OLD)!!))
            )
            itemStack.set(ModDataComponents.OWNER_OLD, null)
        }
    }

    private fun fixActiveComponents(itemStack: ItemStack) {
        if (itemStack.get(ModDataComponents.ACTIVE) == true) itemStack.set(ModDataComponents.ACTIVE, false)
        if (itemStack.get(ModDataComponents.ACTIVE2) == true) itemStack.set(ModDataComponents.ACTIVE2, false)
        if (!itemStack.`is`(ModItemTags.COLLARS)) itemStack.remove(ModDataComponents.LOCATION)
    }

    private fun deblind(player: ServerPlayer) {
        if (player.getEffect(MobEffects.BLINDNESS)?.isInfiniteDuration == true && (
                    !TrinketsApi.getAttachment(player).isEquipped(ModItems.BLINDING_COLLAR) ||
                            TrinketsApi.getAttachment(player).equipped(ModItems.BLINDING_COLLAR, false).first()
                                .get().get(ModDataComponents.ACTIVE) != true
                    )) {
            player.removeEffect(MobEffects.BLINDNESS)
        }
    }

    private fun activateGravity(player: ServerPlayer) {
        if (!TrinketsApi.getAttachment(player).isEquipped(ModItems.FROZEN_COLLAR))
            player.isNoGravity = false
    }

    private fun checkGetCollaredFunction(player: ServerPlayer, collar: ItemStack) {
        ModTriggers.TRINKET_EQUIP_TRIGGER.trigger(player, collar)
    }
}