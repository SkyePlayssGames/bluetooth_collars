package net.galaxyy.bluetoothcollars.datagen.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeInput

data class CollarCrafterRecipeInput(val inputPatch: ItemStack, val inputCollar: ItemStack) : RecipeInput {
    override fun getItem(index: Int): ItemStack {
        return when (index) {
            0 -> inputCollar
            else -> inputPatch
        }
    }

    override fun size(): Int {
        return 2
    }
}