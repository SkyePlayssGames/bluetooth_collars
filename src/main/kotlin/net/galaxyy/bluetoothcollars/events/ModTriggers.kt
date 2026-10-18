package net.galaxyy.bluetoothcollars.events

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries

object ModTriggers {
    val TRINKET_EQUIP_TRIGGER: TrinketEquipTrigger = Registry.register(
        BuiltInRegistries.TRIGGER_TYPES,
        "trinket_equip", TrinketEquipTrigger()
    )
    val COLLAR_ACTIVATE_TRIGGER: CollarActivateTrigger = Registry.register(
        BuiltInRegistries.TRIGGER_TYPES,
        "collar_activate", CollarActivateTrigger()
    )

    fun registerTriggers() = LOGGER.info("Registering Advancement Triggers for Bluetooth Collars")
}