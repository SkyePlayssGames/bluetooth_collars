package net.galaxyy.bluetoothcollars.datagen.recipe

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.collars.AbstractCollar
import net.galaxyy.bluetoothcollars.items.itemdata.AllOwnersDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.BellDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.DoubleRangeDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags
import net.minecraft.advancements.Advancement
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStackTemplate
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

                val collarHolderSet: HolderSet<Item> = itemLookup.getOrThrow(ModItemTags.COLLARS)
                val remoteHolderSet: HolderSet<Item> = itemLookup.getOrThrow(ModItemTags.REMOTES)

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

                shaped(RecipeCategory.MISC, ModItems.LOCK)
                    .pattern("ncn")
                    .pattern("c c")
                    .pattern("iii")
                    .define('n', Items.COPPER_NUGGET)
                    .define('c', Items.COPPER_CHAIN.weathering.unaffected)
                    .define('i', Items.COPPER_INGOT)
                    .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                    .save(output)

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

                CollarCrafterRecipeBuilder.create(RecipeCategory.MISC, Blocks.BELL, collarHolderSet,
                    ModDataComponents.BELL, BellDataComponent(true)
                )
                    .unlockedBy(getHasName(Blocks.BELL), has(Blocks.BELL))
                    .save(output, "$MOD_ID:bell_collar_collar_crafting")

                CollarCrafterRecipeBuilder.create(RecipeCategory.MISC, Items.DIAMOND, collarHolderSet,
                    ModDataComponents.OWNER_ALL, AllOwnersDataComponent(true)
                )
                    .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                    .save(output, "$MOD_ID:owner_all_collar_collar_crafting")

                CollarCrafterRecipeBuilder.create(RecipeCategory.MISC, Blocks.COBBLESTONE, collarHolderSet,
                    ModDataComponents.EXTRA_COLLAR, true
                )
                    .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                    .save(output, "$MOD_ID:extra_collar_collar_collar_crafting")

                CollarCrafterRecipeBuilder.create(RecipeCategory.MISC, ModItems.LOCK, collarHolderSet,
                    DataComponents.ENCHANTMENTS, curseOfBinding.toImmutable()
                )
                    .unlockedBy(getHasName(ModItems.LOCK), has(ModItems.LOCK))
                    .save(output, "$MOD_ID:lock_collar_collar_crafting")

                CollarCrafterRecipeBuilder.create(RecipeCategory.MISC, Items.IRON_INGOT, remoteHolderSet,
                    ModDataComponents.DOUBLE_RANGE, DoubleRangeDataComponent(true)
                )
                    .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                    .save(output, "$MOD_ID:double_range_remote_collar_crafting")
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