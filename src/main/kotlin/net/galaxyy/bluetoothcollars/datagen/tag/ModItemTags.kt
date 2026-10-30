package net.galaxyy.bluetoothcollars.datagen.tag

import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object ModItemTags {
    val COLLARS: TagKey<Item> = TagKey.create(Registries.ITEM, BluetoothCollars.id("collars"))
    val REMOTES: TagKey<Item> = TagKey.create(Registries.ITEM, BluetoothCollars.id("remotes"))
    val UPGRADE_TEMPLATES: TagKey<Item> = TagKey.create(Registries.ITEM, BluetoothCollars.id("upgrade_templaets"))

    val NECKLACES: TagKey<Item> = TagKey.create(
        Registries.ITEM, Identifier.fromNamespaceAndPath(
        BluetoothCollars.TRINKETS_MOD_ID, "chest/necklace"
    ))
}