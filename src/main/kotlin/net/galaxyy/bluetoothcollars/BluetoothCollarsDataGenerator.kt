package net.galaxyy.bluetoothcollars

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.galaxyy.bluetoothcollars.datagen.AdvancementDataGen
import net.galaxyy.bluetoothcollars.datagen.LanguageENUSDataGen
import net.galaxyy.bluetoothcollars.datagen.ModelDataGen
import net.galaxyy.bluetoothcollars.datagen.RecipeDataGen
import net.galaxyy.bluetoothcollars.datagen.TagDataGen

object BluetoothCollarsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()

		pack.addProvider(::RecipeDataGen)
		pack.addProvider(::ModelDataGen)
		pack.addProvider(::LanguageENUSDataGen)
		pack.addProvider(::TagDataGen)
		pack.addProvider(::AdvancementDataGen)
	}
}