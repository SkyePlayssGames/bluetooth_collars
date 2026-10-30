package net.galaxyy.bluetoothcollars.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.galaxyy.bluetoothcollars.datagen.villager.ModVillagerTrades
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.trading.VillagerTrade
import java.util.concurrent.CompletableFuture

class VillagerTradeTagsDataGen(output: FabricPackOutput, registryLookupFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider<VillagerTrade>(output, Registries.VILLAGER_TRADE, registryLookupFuture) {
    override fun addTags(registries: HolderLookup.Provider) {
        builder(ModVillagerTradeTags.PET_HANDLER_1)
            .add(ModVillagerTrades.PET_HANDLER_1_EMERALD_BELL_UP)
            .add(ModVillagerTrades.PET_HANDLER_1_GOLD_EMERALD)
        builder(ModVillagerTradeTags.PET_HANDLER_2)
            .add(ModVillagerTrades.PET_HANDLER_2_COLLAR_EMERALD)
    }
}