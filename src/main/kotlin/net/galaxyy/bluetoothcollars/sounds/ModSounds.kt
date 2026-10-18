package net.galaxyy.bluetoothcollars.sounds

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvent

object ModSounds {
    val CLICK: SoundEvent = register("click")
    val BELL: SoundEvent = register("bell")

    private fun register(name: String): SoundEvent {
        val id: Identifier = id(name)
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id,
            SoundEvent.createFixedRangeEvent(id, 30f)
        )
    }

    fun registerSounds() {
        LOGGER.info("Registering Sounds for Bluetooth Collars")
    }
}