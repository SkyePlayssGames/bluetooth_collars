package net.galaxyy.bluetoothcollars.datagen.villager

import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.items.ModItems
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStackTemplate
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.TradeCost
import net.minecraft.world.item.trading.VillagerTrade
import net.minecraft.world.level.block.Blocks
import java.util.Optional


object ModVillagerTrades {
    val PET_HANDLER_1_GOLD_EMERALD = createKey("pet_handler/1/gold_emerald")
    val PET_HANDLER_1_EMERALD_BELL_UP = createKey("pet_handler/1/emerald_bell_up")

    val PET_HANDLER_2_COLLAR_EMERALD = createKey("pet_handler/2/collar_emerald")

    fun bootstrap(context: BootstrapContext<VillagerTrade>) {
        context.register(PET_HANDLER_1_GOLD_EMERALD, VillagerTrade.builder(
            TradeCost(Items.GOLD_INGOT, 5),
            ItemStackTemplate(Items.EMERALD),
            8, 3, 0.05f
        ).build())
        context.register(PET_HANDLER_1_EMERALD_BELL_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 6),
            ItemStackTemplate(Items.BELL),
            4, 5, 0.05f
        ).build())

        context.register(PET_HANDLER_2_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.TIED_DOWN_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 12, 0.05f
        ).build())
    }

    private fun createKey(name: String): ResourceKey<VillagerTrade> {
        return ResourceKey.create(Registries.VILLAGER_TRADE, id(name))
    }

    fun registerVillagerTrades() {
        BluetoothCollars.LOGGER.info("Registering Villager Trades for Bluetooth Collars")
    }
}