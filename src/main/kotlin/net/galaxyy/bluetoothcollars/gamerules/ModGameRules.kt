package net.galaxyy.bluetoothcollars.gamerules

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.world.level.gamerules.GameRule
import net.minecraft.world.level.gamerules.GameRuleCategory

object ModGameRules {
    val CLICKER_RANGE: GameRule<Int> = GameRuleBuilder
        .forInteger(32)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(id("remote_range"))

    fun registerGameRules() {
        LOGGER.info("Registering Game Rules for Bluetooth Collars")
    }
}