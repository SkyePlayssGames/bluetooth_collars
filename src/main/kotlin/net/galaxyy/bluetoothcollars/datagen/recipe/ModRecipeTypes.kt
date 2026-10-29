package net.galaxyy.bluetoothcollars.datagen.recipe

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.datagen.recipe.collarcrafter.CollarCrafterRecipe
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType

object ModRecipeTypes {
    val COLLAR_CRAFTER_SERIALISER: RecipeSerializer<CollarCrafterRecipe> = Registry.register(
        BuiltInRegistries.RECIPE_SERIALIZER, id("collar_crafting"),
        RecipeSerializer(CollarCrafterRecipe.CODEC, CollarCrafterRecipe.STREAM_CODEC)
    )

    val COLLAR_CRAFTER_TYPE: RecipeType<CollarCrafterRecipe> = Registry.register(
        BuiltInRegistries.RECIPE_TYPE, id("collar_crafting"),
        object : RecipeType<CollarCrafterRecipe> {
            override fun toString(): String {
                return "collar_crafting"
            }
        }
    )

    fun registerRecipes() {
        LOGGER.info("Registering Recipes for Bluetooth Collars")
    }
}