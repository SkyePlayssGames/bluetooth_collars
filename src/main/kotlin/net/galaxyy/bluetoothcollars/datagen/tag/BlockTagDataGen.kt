package net.galaxyy.bluetoothcollars.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagDataGen(output: FabricPackOutput, registryLookupFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.BlockTagsProvider(output, registryLookupFuture) {
    override fun addTags(registries: HolderLookup.Provider) {
        builder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.COLLAR_CRAFTER_KEY)
    }
}