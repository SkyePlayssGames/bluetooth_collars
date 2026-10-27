package net.galaxyy.bluetoothcollars.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class BlockLootTableDataGen(packOutput: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricBlockLootSubProvider(packOutput, registriesFuture) {
    override fun generate() {
        dropSelf(ModBlocks.COLLAR_CRAFTER)
    }
}