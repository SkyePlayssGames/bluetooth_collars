package net.galaxyy.bluetoothcollars.datagen

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.ModItems
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TexturedModel

class ModelDataGen(output: FabricPackOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockModelGenerators: BlockModelGenerators) {
        blockModelGenerators.createTrivialBlock(ModBlocks.COLLAR_CRAFTER, TexturedModel.CUBE_BOTTOM_TOP)
    }

    override fun generateItemModels(itemModelGenerators: ItemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.TIED_DOWN_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.TIED_DOWN_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.CLUMSY_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.CLUMSY_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.CRAWL_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.CRAWL_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.PAW_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.PAW_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.TINY_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.TINY_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.BLINDING_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.BLINDING_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.FROZEN_COLLAR, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.FROZEN_REMOTE, ModelTemplates.FLAT_ITEM)
        itemModelGenerators.generateFlatItem(ModItems.LOCK, ModelTemplates.FLAT_ITEM)
    }
}