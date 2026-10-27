package net.galaxyy.bluetoothcollars.datagen.recipe

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStackTemplate
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.PlacementInfo
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeBookCategories
import net.minecraft.world.item.crafting.RecipeBookCategory
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

data class CollarCrafterRecipe(val inputPatch: Ingredient, val output: DataComponentPatch) : Recipe<CollarCrafterRecipeInput> {
    override fun matches(
        input: CollarCrafterRecipeInput,
        level: Level
    ): Boolean {
        if (level.isClientSide) return false

        return inputPatch.test(input.getItem(1))
    }

    override fun assemble(input: CollarCrafterRecipeInput): ItemStack {
        BluetoothCollars.LOGGER.info(output.toString())
        input.getItem(0).applyComponents(output)
        BluetoothCollars.LOGGER.info(input.getItem(0).components.toString())
        return input.getItem(0)
    }

    override fun showNotification(): Boolean {
        return true
    }

    override fun group(): String {
        return "Collar Crafting"
    }

    override fun getSerializer(): RecipeSerializer<out Recipe<CollarCrafterRecipeInput>> {
        return ModRecipeTypes.COLLAR_CRAFTER_SERIALISER
    }

    override fun getType(): RecipeType<out Recipe<CollarCrafterRecipeInput>> {
        return ModRecipeTypes.COLLAR_CRAFTER_TYPE
    }

    override fun placementInfo(): PlacementInfo {
        return PlacementInfo.NOT_PLACEABLE
    }

    override fun recipeBookCategory(): RecipeBookCategory {
        return RecipeBookCategories.CRAFTING_EQUIPMENT
    }

    companion object {
        val CODEC: MapCodec<CollarCrafterRecipe> = RecordCodecBuilder.mapCodec { instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CollarCrafterRecipe::inputPatch),
            DataComponentPatch.CODEC.fieldOf("output").forGetter(CollarCrafterRecipe::output)
        ).apply(instance, ::CollarCrafterRecipe) }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CollarCrafterRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CollarCrafterRecipe::inputPatch,
            DataComponentPatch.STREAM_CODEC, CollarCrafterRecipe::output,
            ::CollarCrafterRecipe
        )
    }
}