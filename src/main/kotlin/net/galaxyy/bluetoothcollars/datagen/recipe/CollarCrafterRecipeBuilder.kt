package net.galaxyy.bluetoothcollars.datagen.recipe

import net.minecraft.advancements.triggers.Criterion
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.component.DataComponentType
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.level.ItemLike

class CollarCrafterRecipeBuilder(
    private val recipeCategory: RecipeCategory,
    private val ingredient: Ingredient,
    private val result: DataComponentPatch
) : RecipeBuilder {
    private val recipeUnlockAdvancementBuilder: RecipeUnlockAdvancementBuilder = RecipeUnlockAdvancementBuilder()
    private var group: String? = null

    override fun unlockedBy(
        name: String,
        criterion: Criterion<*>
    ): RecipeBuilder {
        recipeUnlockAdvancementBuilder.unlockedBy(name, criterion)
        return this
    }

    override fun group(group: String?): RecipeBuilder {
        this.group = group
        return this
    }

    override fun defaultId(): ResourceKey<Recipe<*>>? {
        return null
    }

    override fun save(
        output: RecipeOutput,
        location: ResourceKey<Recipe<*>>
    ) {
        val recipe = CollarCrafterRecipe(ingredient, result)
        output.accept(location, recipe, recipeUnlockAdvancementBuilder.build(output, location, recipeCategory))
    }

    companion object {
        fun <T : Any> create(recipeCategory: RecipeCategory, ingredient: ItemLike, type: DataComponentType<T>, value: T): CollarCrafterRecipeBuilder =
            CollarCrafterRecipeBuilder(recipeCategory, Ingredient.of(ingredient),
                DataComponentPatch.builder().set(type, value).build()
            )
    }
}