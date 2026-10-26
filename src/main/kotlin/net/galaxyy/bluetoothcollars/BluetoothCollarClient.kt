package net.galaxyy.bluetoothcollars

import net.fabricmc.api.ClientModInitializer
import net.galaxyy.bluetoothcollars.client.collarcrafter.CollarCrafterBERenderer
import net.galaxyy.bluetoothcollars.items.blockentities.ModBlockEntities
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers

object BluetoothCollarClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.COLLAR_CRAFTER_BE, ::CollarCrafterBERenderer)
    }
}