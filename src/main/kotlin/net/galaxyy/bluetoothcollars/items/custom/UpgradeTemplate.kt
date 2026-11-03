package net.galaxyy.bluetoothcollars.items.custom

import net.galaxyy.bluetoothcollars.datagen.tag.ModItemTags
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.blockentities.CollarCrafterBE
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.dispenser.BlockSource
import net.minecraft.core.dispenser.DispenseItemBehavior
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.DispenserBlock
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

class UpgradeTemplate(properties: Properties) : Item(properties) {
    var baseItem: ItemLike? = null
    var baseTag: TagKey<Item>? = null
    var patch: DataComponentPatch? = null

    constructor(properties: Properties, base: ItemLike, patch: DataComponentPatch) : this(properties) {
        this.baseItem = base
        this.patch = patch
    }

    constructor(properties: Properties, base: TagKey<Item>, patch: DataComponentPatch) : this(properties) {
        this.baseTag = base
        this.patch = patch
    }

    init {
        ALL.add(this)
    }

    companion object {
        val ALL: ArrayList<UpgradeTemplate> = arrayListOf()
    }

    class DispenserBehaviour : OptionalDispenseItemBehavior() {
        override fun execute(
            source: BlockSource,
            dispensed: ItemStack
        ): ItemStack {
            val pos = source.pos.relative(source.state.getValue(DispenserBlock.FACING))
            if (source.level.getBlockState(pos).`is`(ModBlocks.COLLAR_CRAFTER) && source.level.getBlockEntity(pos) is CollarCrafterBE) {
                this.isSuccess = (source.level.getBlockEntity(pos) as CollarCrafterBE).tryCraft(dispensed)
                if (this.isSuccess) dispensed.count -= 1
            }
            return dispensed
        }
    }
}