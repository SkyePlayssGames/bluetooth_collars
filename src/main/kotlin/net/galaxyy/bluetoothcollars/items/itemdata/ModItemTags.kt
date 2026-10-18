package net.galaxyy.bluetoothcollars.items.itemdata

import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object ModItemTags {
    val COLLARS: TagKey<Item> = TagKey.create(Registries.ITEM, id("collars"))
    val REMOTES: TagKey<Item> = TagKey.create(Registries.ITEM, id("remotes"))

    val NECKLACES: TagKey<Item> = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
        BluetoothCollars.TRINKETS_MOD_ID, "chest/necklace"
    ))
}