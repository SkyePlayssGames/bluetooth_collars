package net.galaxyy.bluetoothcollars.items.custom

import net.minecraft.core.component.DataComponentPatch
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike

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
}