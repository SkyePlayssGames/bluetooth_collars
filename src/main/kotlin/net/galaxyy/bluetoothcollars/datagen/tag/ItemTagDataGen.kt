package net.galaxyy.bluetoothcollars.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.galaxyy.bluetoothcollars.items.ModItems
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import java.util.concurrent.CompletableFuture

class ItemTagDataGen(output: FabricPackOutput, lookup: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.ItemTagsProvider(output, lookup)
{
    override fun addTags(registries: HolderLookup.Provider) {
        builder(ModItemTags.COLLARS)
            .add(ModItems.PAW_COLLAR_KEY)
            .add(ModItems.CLUMSY_COLLAR_KEY)
            .add(ModItems.CRAWL_COLLAR_KEY)
            .add(ModItems.TIED_DOWN_COLLAR_KEY)
            .add(ModItems.TINY_COLLAR_KEY)
            .add(ModItems.BLINDING_COLLAR_KEY)
            .add(ModItems.FROZEN_COLLAR_KEY)

        builder(ModItemTags.REMOTES)
            .add(ModItems.PAW_REMOTE_KEY)
            .add(ModItems.CRAWL_REMOTE_KEY)
            .add(ModItems.CLUMSY_REMOTE_KEY)
            .add(ModItems.TIED_DOWN_REMOTE_KEY)
            .add(ModItems.TINY_REMOTE_KEY)
            .add(ModItems.BLINDING_REMOTE_KEY)
            .add(ModItems.FROZEN_REMOTE_KEY)

        builder(ModItemTags.UPGRADE_TEMPLATES)
            .add(ModItems.LOCK_UP_KEY)
            .add(ModItems.BELL_UP_KEY)
            .add(ModItems.EXTRA_COLLAR_UP_KEY)
            .add(ModItems.UNI_UP_KEY)
            .add(ModItems.DOUBLE_RANGE_UP_KEY)

        builder(ModItemTags.NECKLACES)
            .addTag(ModItemTags.COLLARS)

        builder(ItemTags.EQUIPPABLE_ENCHANTABLE)
            .addTag(ModItemTags.COLLARS)

        builder(ItemTags.VANISHING_ENCHANTABLE)
            .addTag(ModItemTags.COLLARS)
    }
}