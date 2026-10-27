package net.galaxyy.bluetoothcollars.stats

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.stats.StatFormatter
import net.minecraft.stats.Stats

object ModStats {
    val COLLAR_CRAFTER_CRAFTS = register("collar_crafter_crafts", StatFormatter.DEFAULT)

    private fun register(name: String, formatter: StatFormatter): Identifier {
        val id = id(name)
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, id)
        Stats.CUSTOM.get(id, formatter)
        return id
    }

    fun registerStats() {
        LOGGER.info("Registering Stats for Bluetooth Collars")
    }
}