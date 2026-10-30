package net.galaxyy.bluetoothcollars.datagen.villager

import com.google.common.collect.ImmutableSet
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import net.fabricmc.fabric.api.`object`.builder.v1.world.poi.PoiHelper
import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.villager.VillagerProfession
import net.minecraft.world.item.trading.TradeSet

object ModVillagers {
    val PET_HANDLER_POI_KEY: ResourceKey<PoiType> = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, id("pet_handler_poi"))
    val PET_HANDLER_POI: PoiType = PoiHelper.register(id("pet_handler_poi"), 1, 1, ModBlocks.COLLAR_CRAFTER)

    val PET_HANDLER: VillagerProfession = registerVillagerProfession(
        "pet_handler", "Pet Handler", PET_HANDLER_POI_KEY, SoundEvents.NOTE_BLOCK_BELL.value(), Int2ObjectMap.ofEntries(
            Int2ObjectMap.entry(1, ModTradeSets.PET_HANDLER_1),
            Int2ObjectMap.entry(2, ModTradeSets.PET_HANDLER_2),
            Int2ObjectMap.entry(3, ModTradeSets.PET_HANDLER_3),
            Int2ObjectMap.entry(4, ModTradeSets.PET_HANDLER_4),
            Int2ObjectMap.entry(5, ModTradeSets.PET_HANDLER_5)
        )
    )

    private fun registerVillagerProfession(name: String, title: String, poi: ResourceKey<PoiType>, sound: SoundEvent, map: Int2ObjectMap<ResourceKey<TradeSet>>): VillagerProfession {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, id(name), VillagerProfession(
            Component.literal(title), {holder -> holder.`is`(poi)}, {holder -> holder.`is`(poi)},
            ImmutableSet.of(), ImmutableSet.of(), sound, map
        ))
    }

    fun registerVillagers() {
        BluetoothCollars.LOGGER.info("Registering Villagers for Bluetooth Collars")
    }
}