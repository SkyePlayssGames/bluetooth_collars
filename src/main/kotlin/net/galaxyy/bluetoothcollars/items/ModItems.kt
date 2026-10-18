package net.galaxyy.bluetoothcollars.items

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.items.collars.AbstractCollar
import net.galaxyy.bluetoothcollars.items.collars.BlindingCollar
import net.galaxyy.bluetoothcollars.items.collars.BlindingCollar.BlindingRemote
import net.galaxyy.bluetoothcollars.items.collars.ClumsyCollar
import net.galaxyy.bluetoothcollars.items.collars.ClumsyCollar.ClumsyRemote
import net.galaxyy.bluetoothcollars.items.collars.CrawlCollar
import net.galaxyy.bluetoothcollars.items.collars.CrawlCollar.CrawlRemote
import net.galaxyy.bluetoothcollars.items.collars.FrozenCollar
import net.galaxyy.bluetoothcollars.items.collars.FrozenCollar.FrozenRemote
import net.galaxyy.bluetoothcollars.items.collars.PawCollar
import net.galaxyy.bluetoothcollars.items.collars.PawCollar.PawRemote
import net.galaxyy.bluetoothcollars.items.collars.TieDownCollar
import net.galaxyy.bluetoothcollars.items.collars.TieDownCollar.TieDownRemote
import net.galaxyy.bluetoothcollars.items.collars.TinyCollar
import net.galaxyy.bluetoothcollars.items.collars.TinyCollar.TinyRemote
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

object ModItems {
    val TIED_DOWN_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("tied_collar"))
    val TIED_DOWN_COLLAR: TieDownCollar = register(
        TIED_DOWN_COLLAR_KEY, ::TieDownCollar,
        Item.Properties()
    )
    val TIED_DOWN_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("tied_remote"))
    val TIED_DOWN_REMOTE: TieDownRemote = register(
        TIED_DOWN_REMOTE_KEY, ::TieDownRemote,
        Item.Properties()
    )

    val CLUMSY_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("clumsy_collar"))
    val CLUMSY_COLLAR: ClumsyCollar = register(
        CLUMSY_COLLAR_KEY, ::ClumsyCollar,
        Item.Properties()
    )
    val CLUMSY_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("clumsy_remote"))
    val CLUMSY_REMOTE: ClumsyRemote = register(
        CLUMSY_REMOTE_KEY, ::ClumsyRemote,
        Item.Properties()
    )

    val CRAWL_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("crawl_collar"))
    val CRAWL_COLLAR: CrawlCollar = register(
        CRAWL_COLLAR_KEY, ::CrawlCollar,
        Item.Properties()
    )
    val CRAWL_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("crawl_remote"))
    val CRAWL_REMOTE: CrawlRemote = register(
        CRAWL_REMOTE_KEY, ::CrawlRemote,
        Item.Properties()
    )

    val PAW_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("paw_collar"))
    val PAW_COLLAR: PawCollar = register(
        PAW_COLLAR_KEY, ::PawCollar,
        Item.Properties()
    )
    val PAW_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("paw_remote"))
    val PAW_REMOTE: PawRemote = register(
        PAW_REMOTE_KEY, ::PawRemote,
        Item.Properties()
    )

    val TINY_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("tiny_collar"))
    val TINY_COLLAR: TinyCollar = register(
        TINY_COLLAR_KEY, ::TinyCollar,
        Item.Properties()
    )
    val TINY_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("tiny_remote"))
    val TINY_REMOTE: TinyRemote = register(
        TINY_REMOTE_KEY, ::TinyRemote,
        Item.Properties()
    )

    val BLINDING_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("blinding_collar"))
    val BLINDING_COLLAR: BlindingCollar = register(
        BLINDING_COLLAR_KEY, ::BlindingCollar,
        Item.Properties()
    )
    val BLINDING_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("blinding_remote"))
    val BLINDING_REMOTE: BlindingRemote = register(
        BLINDING_REMOTE_KEY, ::BlindingRemote,
        Item.Properties()
    )

    val FROZEN_COLLAR_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("frozen_collar"))
    val FROZEN_COLLAR: FrozenCollar = register(
        FROZEN_COLLAR_KEY, ::FrozenCollar,
        Item.Properties()
    )
    val FROZEN_REMOTE_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("frozen_remote"))
    val FROZEN_REMOTE: FrozenRemote = register(
        FROZEN_REMOTE_KEY, ::FrozenRemote,
        Item.Properties()
    )


    val LOCK_KEY: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id("lock"))
    val LOCK: Item = register(
        LOCK_KEY, ::Item,
        Item.Properties()
    )

    private fun <T : Item> register(key: ResourceKey<Item>, factory: (Item.Properties) -> T, settings: Item.Properties): T {
        val item: T = factory(settings.setId(key))

        Registry.register(BuiltInRegistries.ITEM, key, item)
        return item
    }

    private fun <T : AbstractCollar> register(key: ResourceKey<Item>, factory: (Item.Properties, ResourceKey<Item>) -> T, settings: Item.Properties): T {
        val item: T = factory(settings.setId(key), key)

        Registry.register(BuiltInRegistries.ITEM, key, item)
        return item
    }

    fun registerItems() {
        LOGGER.info("Registering Items for Bluetooth Collars")
    }
}