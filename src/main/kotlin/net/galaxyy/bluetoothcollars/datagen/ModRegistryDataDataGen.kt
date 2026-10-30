package net.galaxyy.bluetoothcollars.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import java.util.concurrent.CompletableFuture

class ModRegistryDataDataGen(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        entries.addAll(registries.lookupOrThrow(Registries.TRADE_SET))
        entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE))
    }

    override fun getName(): String {
        return "Bluetooth Collars Data Provider"
    }
}