package net.galaxyy.bluetoothcollars.items

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab
import net.fabricmc.fabric.impl.creativetab.FabricCreativeModeTabBuilderImpl
import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object ModCreativeModeTabs {
    val COLLAR_TAB_KEY: ResourceKey<CreativeModeTab> = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(), id("collars")
    )
    val COLLAR_TAB: CreativeModeTab = FabricCreativeModeTab.builder()
        .icon { ItemStack(ModItems.TIED_DOWN_COLLAR) }
        .title(Component.translatable("collar_tab.$MOD_ID"))
        .displayItems { parameters, output ->
            output.accept(ModItems.LOCK)
            output.accept(ModItems.TIED_DOWN_COLLAR)
            output.accept(ModItems.TIED_DOWN_REMOTE)
            output.accept(ModItems.CLUMSY_COLLAR)
            output.accept(ModItems.CLUMSY_REMOTE)
            output.accept(ModItems.CRAWL_COLLAR)
            output.accept(ModItems.CRAWL_REMOTE)
            output.accept(ModItems.PAW_COLLAR)
            output.accept(ModItems.PAW_REMOTE)
            output.accept(ModItems.TINY_COLLAR)
            output.accept(ModItems.TINY_REMOTE)
            output.accept(ModItems.BLINDING_COLLAR)
            output.accept(ModItems.BLINDING_REMOTE)
            output.accept(ModItems.FROZEN_COLLAR)
            output.accept(ModItems.FROZEN_REMOTE)
        }
        .build()

    fun registerCreativeModeTabs() {
        LOGGER.info("Registering Creative Mode Tabs for Bluetooth Collars")
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, COLLAR_TAB_KEY, COLLAR_TAB);
    }
}