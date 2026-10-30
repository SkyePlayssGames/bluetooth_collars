package net.galaxyy.bluetoothcollars.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.galaxyy.bluetoothcollars.datagen.villager.ModVillagers
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.PoiTypeTags
import net.minecraft.world.entity.ai.village.poi.PoiType
import java.util.concurrent.CompletableFuture

class PoiTagsDataGen(output: FabricPackOutput, registryLookupFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider<PoiType>(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture) {
    override fun addTags(registries: HolderLookup.Provider) {
        builder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
            .add(ModVillagers.PET_HANDLER_POI_KEY)
    }
}