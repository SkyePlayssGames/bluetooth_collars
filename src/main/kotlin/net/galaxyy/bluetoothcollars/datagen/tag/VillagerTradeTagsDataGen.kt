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
            .add(ModVillagerTrades.PET_HANDLER_1_WHITE_WOOL_EMERALD)

        builder(ModVillagerTradeTags.PET_HANDLER_2)
            .add(ModVillagerTrades.PET_HANDLER_2_COPPER_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_2_EMERALD_LOCK_UP)

        builder(ModVillagerTradeTags.PET_HANDLER_3)
            .add(ModVillagerTrades.PET_HANDLER_3_EMERALD_UNI_UP)
            .add(ModVillagerTrades.PET_HANDLER_3_EMERALD_DOUBLE_RANGE_UP)

        builder(ModVillagerTradeTags.PET_HANDLER_4)
            .add(ModVillagerTrades.PET_HANDLER_4_BLINDING_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_CRAWL_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_CLUMSY_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_FROZEN_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_PAW_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_TIED_DOWN_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_TINY_COLLAR_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_BLINDING_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_CRAWL_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_CLUMSY_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_FROZEN_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_PAW_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_TIED_DOWN_REMOTE_EMERALD)
            .add(ModVillagerTrades.PET_HANDLER_4_TINY_REMOTE_EMERALD)

        builder(ModVillagerTradeTags.PET_HANDLER_5)
            .add(ModVillagerTrades.PET_HANDLER_5_EMERALD_EXTRA_COLLAR_UP)
    }
}