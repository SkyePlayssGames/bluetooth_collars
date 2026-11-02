package net.galaxyy.bluetoothcollars.datagen.recipe

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.datagen.recipe.collarcrafter.CollarCrafterRecipeBuilder
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.collars.AbstractCollar
import net.galaxyy.bluetoothcollars.items.itemdata.AllOwnersDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.BellDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.DoubleRangeDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.datagen.tag.ModItemTags
import net.galaxyy.bluetoothcollars.items.custom.UpgradeTemplate
import net.minecraft.advancements.Advancement
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.item.enchantment.ItemEnchantments
import net.minecraft.world.level.block.Blocks
import java.util.concurrent.CompletableFuture

class RecipeDataGen(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(output, registriesFuture) {
    override fun createRecipeProvider(
        registries: HolderLookup.Provider,
        recipes: BootstrapContext<Recipe<*>>,
        advancements: BootstrapContext<Advancement>
    ): RecipeProvider {
        return object : RecipeProvider(recipes, advancements) {
            override fun buildRecipes() {
                val itemLookup: HolderLookup.RegistryLookup<Item> = registries.lookupOrThrow(Registries.ITEM)
                val enchantmentLookup: HolderLookup.RegistryLookup<Enchantment> = registries.lookupOrThrow(Registries.ENCHANTMENT)

                val collarCrafterBuilder: CollarCrafterRecipeBuilder.Companion.Builder = CollarCrafterRecipeBuilder.Companion.Builder(itemLookup)

                val curseOfBinding: ItemEnchantments.Mutable = ItemEnchantments.Mutable(ItemEnchantments.EMPTY)
                curseOfBinding.set(enchantmentLookup.getOrThrow(Enchantments.BINDING_CURSE), 1)

                makeRemoteRecipe(ModItems.TIED_DOWN_REMOTE, Items.WOOL.pink, output)
                makeRemoteRecipe(ModItems.CLUMSY_REMOTE, Items.WOOL.lightBlue, output)
                makeRemoteRecipe(ModItems.CRAWL_REMOTE, Items.WOOL.yellow, output)
                makeRemoteRecipe(ModItems.PAW_REMOTE, Items.WOOL.lime, output)
                makeRemoteRecipe(ModItems.TINY_REMOTE, Items.WOOL.purple, output)
                makeRemoteRecipe(ModItems.BLINDING_REMOTE, Items.WOOL.black, output)
                makeRemoteRecipe(ModItems.FROZEN_REMOTE, Items.WOOL.white, output)

                makeCollarRecipes(ModItems.TIED_DOWN_COLLAR, Items.WOOL.pink, output, enchantmentLookup)
                makeCollarRecipes(ModItems.CLUMSY_COLLAR, Items.WOOL.lightBlue, output, enchantmentLookup)
                makeCollarRecipes(ModItems.CRAWL_COLLAR, Items.WOOL.yellow, output, enchantmentLookup)
                makeCollarRecipes(ModItems.PAW_COLLAR, Items.WOOL.lime, output, enchantmentLookup)
                makeCollarRecipes(ModItems.TINY_COLLAR, Items.WOOL.purple, output, enchantmentLookup)
                makeCollarRecipes(ModItems.BLINDING_COLLAR, Items.WOOL.black, output, enchantmentLookup)
                makeCollarRecipes(ModItems.FROZEN_COLLAR, Items.WOOL.white, output, enchantmentLookup)

                shaped(RecipeCategory.TOOLS, ModBlocks.COLLAR_CRAFTER)
                    .pattern("srs")
                    .pattern("ici")
                    .pattern("sis")
                    .define('s', Items.SMOOTH_STONE)
                    .define('r', Items.REDSTONE)
                    .define('i', Items.IRON_INGOT)
                    .define('c', Blocks.CRAFTING_TABLE)
                    .unlockedBy(getHasName(Blocks.CRAFTING_TABLE), has(Blocks.CRAFTING_TABLE))
                    .save(output)


                UpgradeTemplate.ALL.forEach { template ->
                    if (template.patch == null) return@forEach
                    if (template.baseItem != null)
                        collarCrafterBuilder.create(RecipeCategory.MISC, template, template.baseItem!!, template.patch!!)
                            .unlockedBy(getHasName(template), has(template))
                            .save(output, "$MOD_ID:${BuiltInRegistries.ITEM.wrapAsHolder(template).unwrapKey().get().identifier().path}_${template.baseItem!!}_collar_crafting")
                    else if (template.baseTag != null)
                        collarCrafterBuilder.create(RecipeCategory.MISC, template, template.baseTag!!, template.patch!!)
                            .unlockedBy(getHasName(template), has(template))
                            .save(output, "$MOD_ID:${BuiltInRegistries.ITEM.wrapAsHolder(template).unwrapKey().get().identifier().path}_${template.baseTag!!.location.path}_collar_crafting")
                }

                collarCrafterBuilder.create(RecipeCategory.MISC, ModItems.LOCK_UP, ModItemTags.COLLARS,
                    DataComponents.ENCHANTMENTS, curseOfBinding.toImmutable()
                )
                    .unlockedBy(getHasName(ModItems.LOCK_UP), has(ModItems.LOCK_UP))
                    .save(output, "$MOD_ID:lock_collars_collar_crafting")
            }

            private fun makeRemoteRecipe(remote: Item, wool: Item, output: RecipeOutput) {
                shapeless(RecipeCategory.MISC, remote)
                    .requires(wool, 2)
                    .requires(Items.IRON_INGOT, 2)
                    .requires(Items.COPPER_INGOT, 1)
                    .requires(Items.REDSTONE, 1)
                    .unlockedBy(getHasName(wool), has(wool))
                    .save(output)
            }

            private fun makeCollarRecipes(collar: AbstractCollar, wool: Item, output: RecipeOutput, enchantmentLookup: HolderLookup.RegistryLookup<Enchantment>) {
                val curse: Holder<Enchantment> = enchantmentLookup.getOrThrow(Enchantments.BINDING_CURSE)
                val enchants: ItemEnchantments.Mutable = ItemEnchantments.Mutable(ItemEnchantments.EMPTY)
                enchants.set(curse, 1)

                shaped(RecipeCategory.MISC, collar)
                    .pattern("wcw")
                    .pattern("ili")
                    .define('w', wool)
                    .define('c', Items.COPPER_INGOT)
                    .define('i', Items.IRON_INGOT)
                    .define('l', Items.LEATHER)
                    .group(collar.key.identifier().path)
                    .unlockedBy(getHasName(wool), has(wool))
                    .save(output)
            }
        }
    }

    override fun getName(): String {
        return "RecipeProviderCOLLARS"
    }
}