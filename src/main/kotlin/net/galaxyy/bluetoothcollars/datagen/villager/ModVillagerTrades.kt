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
    val PET_HANDLER_1_WHITE_WOOL_EMERALD = createKey("pet_handler/1/white_wool_emerald")
    val PET_HANDLER_1_EMERALD_BELL_UP = createKey("pet_handler/1/emerald_bell_up")

    val PET_HANDLER_2_COPPER_EMERALD = createKey("pet_handler/2/copper_emerald")
    val PET_HANDLER_2_EMERALD_LOCK_UP = createKey("pet_handler/2/emerald_lock_up")

    val PET_HANDLER_3_EMERALD_DOUBLE_RANGE_UP = createKey("pet_handler/3/emerald_double_range_up")
    val PET_HANDLER_3_EMERALD_UNI_UP = createKey("pet_handler/3/emerald_uni_up")

    val PET_HANDLER_4_BLINDING_COLLAR_EMERALD = createKey("pet_handler/4/blinding_collar_emerald")
    val PET_HANDLER_4_CLUMSY_COLLAR_EMERALD = createKey("pet_handler/4/clumsy_collar_emerald")
    val PET_HANDLER_4_CRAWL_COLLAR_EMERALD = createKey("pet_handler/4/crawl_collar_emerald")
    val PET_HANDLER_4_FROZEN_COLLAR_EMERALD = createKey("pet_handler/4/frozen_collar_emerald")
    val PET_HANDLER_4_PAW_COLLAR_EMERALD = createKey("pet_handler/4/paw_collar_emerald")
    val PET_HANDLER_4_TIED_DOWN_COLLAR_EMERALD = createKey("pet_handler/4/tied_down_collar_emerald")
    val PET_HANDLER_4_TINY_COLLAR_EMERALD = createKey("pet_handler/4/tiny_collar_emerald")
    val PET_HANDLER_4_BLINDING_REMOTE_EMERALD = createKey("pet_handler/4/blinding_remote_emerald")
    val PET_HANDLER_4_CLUMSY_REMOTE_EMERALD = createKey("pet_handler/4/clumsy_remote_emerald")
    val PET_HANDLER_4_CRAWL_REMOTE_EMERALD = createKey("pet_handler/4/crawl_remote_emerald")
    val PET_HANDLER_4_FROZEN_REMOTE_EMERALD = createKey("pet_handler/4/frozen_remote_emerald")
    val PET_HANDLER_4_PAW_REMOTE_EMERALD = createKey("pet_handler/4/paw_remote_emerald")
    val PET_HANDLER_4_TIED_DOWN_REMOTE_EMERALD = createKey("pet_handler/4/tied_down_remote_emerald")
    val PET_HANDLER_4_TINY_REMOTE_EMERALD = createKey("pet_handler/4/tiny_remote_emerald")

    val PET_HANDLER_5_EMERALD_EXTRA_COLLAR_UP = createKey("pet_handler/5/emerald_extra_collar_up")

    fun bootstrap(context: BootstrapContext<VillagerTrade>) {
        context.register(PET_HANDLER_1_WHITE_WOOL_EMERALD, VillagerTrade.builder(
            TradeCost(Blocks.WOOL.white, 5),
            ItemStackTemplate(Items.EMERALD),
            8, 2, 0.05f
        ).build())
        context.register(PET_HANDLER_1_EMERALD_BELL_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 6),
            ItemStackTemplate(ModItems.BELL_UP),
            4, 5, 0.05f
        ).build())

        context.register(PET_HANDLER_2_COPPER_EMERALD, VillagerTrade.builder(
            TradeCost(Items.COPPER_INGOT, 8),
            ItemStackTemplate(Items.EMERALD),
            8, 8, 0.05f
        ).build())
        context.register(PET_HANDLER_2_EMERALD_LOCK_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 6),
            ItemStackTemplate(ModItems.LOCK_UP),
            4, 12, 0.05f
        ).build())

        context.register(PET_HANDLER_3_EMERALD_DOUBLE_RANGE_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 5),
            ItemStackTemplate(ModItems.DOUBLE_RANGE_UP),
            4, 20, 0.05f
        ).build())
        context.register(PET_HANDLER_3_EMERALD_UNI_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 6),
            ItemStackTemplate(ModItems.UNI_UP),
            4, 20, 0.05f
        ).build())

        context.register(PET_HANDLER_4_BLINDING_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.BLINDING_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_CLUMSY_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.CLUMSY_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_CRAWL_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.CRAWL_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_FROZEN_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.FROZEN_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_PAW_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.PAW_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_TIED_DOWN_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.TIED_DOWN_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_TINY_COLLAR_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.TINY_COLLAR, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_BLINDING_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.BLINDING_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_CLUMSY_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.CLUMSY_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_CRAWL_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.CRAWL_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_FROZEN_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.FROZEN_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_PAW_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.PAW_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_TIED_DOWN_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.TIED_DOWN_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())
        context.register(PET_HANDLER_4_TINY_REMOTE_EMERALD, VillagerTrade.builder(
            TradeCost(ModItems.TINY_REMOTE, 1),
            ItemStackTemplate(Items.EMERALD, 4),
            3, 35, 0.05f
        ).build())

        context.register(PET_HANDLER_5_EMERALD_EXTRA_COLLAR_UP, VillagerTrade.builder(
            TradeCost(Items.EMERALD, 9),
            ItemStackTemplate(ModItems.EXTRA_COLLAR_UP),
            4, 40, 0.05f
        ).build())
    }

    private fun createKey(name: String): ResourceKey<VillagerTrade> {
        return ResourceKey.create(Registries.VILLAGER_TRADE, id(name))
    }

    fun registerVillagerTrades() {
        BluetoothCollars.LOGGER.info("Registering Villager Trades for Bluetooth Collars")
    }
}