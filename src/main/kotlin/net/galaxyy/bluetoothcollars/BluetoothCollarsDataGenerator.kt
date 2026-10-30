package net.galaxyy.bluetoothcollars

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.galaxyy.bluetoothcollars.datagen.AdvancementDataGen
import net.galaxyy.bluetoothcollars.datagen.BlockLootTableDataGen
import net.galaxyy.bluetoothcollars.datagen.LanguageENUSDataGen
import net.galaxyy.bluetoothcollars.datagen.ModRegistryDataDataGen
import net.galaxyy.bluetoothcollars.datagen.ModelDataGen
import net.galaxyy.bluetoothcollars.datagen.recipe.RecipeDataGen
import net.galaxyy.bluetoothcollars.datagen.tag.BlockTagDataGen
import net.galaxyy.bluetoothcollars.datagen.tag.ItemTagDataGen
import net.galaxyy.bluetoothcollars.datagen.tag.PoiTagsDataGen
import net.galaxyy.bluetoothcollars.datagen.tag.VillagerTradeTagsDataGen
import net.galaxyy.bluetoothcollars.datagen.villager.ModTradeSets
import net.galaxyy.bluetoothcollars.datagen.villager.ModVillagerTrades
import net.galaxyy.bluetoothcollars.datagen.villager.ModVillagers
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries

object BluetoothCollarsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModRegistryDataDataGen)
		pack.addProvider(::RecipeDataGen)
		pack.addProvider(::ModelDataGen)
		pack.addProvider(::LanguageENUSDataGen)
		pack.addProvider(::ItemTagDataGen)
		pack.addProvider(::BlockTagDataGen)
		pack.addProvider(::AdvancementDataGen)
		pack.addProvider(::BlockLootTableDataGen)
		pack.addProvider(::VillagerTradeTagsDataGen)
		pack.addProvider(::PoiTagsDataGen)
	}

	override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap)
		registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap)
	}
}