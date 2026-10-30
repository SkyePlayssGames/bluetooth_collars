package net.galaxyy.bluetoothcollars.datagen.tag

import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.trading.VillagerTrade

object ModVillagerTradeTags {
    val PET_HANDLER_1: TagKey<VillagerTrade> = TagKey.create(Registries.VILLAGER_TRADE, id("pet_handler/level_1"))
    val PET_HANDLER_2: TagKey<VillagerTrade> = TagKey.create(Registries.VILLAGER_TRADE, id("pet_handler/level_2"))
    val PET_HANDLER_3: TagKey<VillagerTrade> = TagKey.create(Registries.VILLAGER_TRADE, id("pet_handler/level_3"))
    val PET_HANDLER_4: TagKey<VillagerTrade> = TagKey.create(Registries.VILLAGER_TRADE, id("pet_handler/level_4"))
    val PET_HANDLER_5: TagKey<VillagerTrade> = TagKey.create(Registries.VILLAGER_TRADE, id("pet_handler/level_5"))
}