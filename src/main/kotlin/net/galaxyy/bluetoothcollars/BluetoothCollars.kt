package net.galaxyy.bluetoothcollars

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry
import net.galaxyy.bluetoothcollars.events.ModTriggers
import net.galaxyy.bluetoothcollars.events.TickCollarsEvent
import net.galaxyy.bluetoothcollars.gamerules.ModGameRules
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.ModCreativeModeTabs
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.blockentities.ModBlockEntities
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.sounds.ModSounds
import net.minecraft.core.component.DataComponents
import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

object BluetoothCollars : ModInitializer {
	const val MOD_ID: String = "bluetooth_collars"
	const val TRINKETS_MOD_ID: String = "trinkets"

	val LOGGER = LoggerFactory.getLogger(MOD_ID)!!

	override fun onInitialize() {
		ModItems.registerItems()
		ModBlocks.registerBlocks()
		ModBlockEntities.registerBlockEntities()
		ModDataComponents.registerDataComponents()
		ModCreativeModeTabs.registerCreativeModeTabs()
		ModSounds.registerSounds()
		ModTriggers.registerTriggers()
		ModGameRules.registerGameRules()

		ServerTickEvents.START_SERVER_TICK.register(TickCollarsEvent())

		ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DAMAGE, ModDataComponents.BELL)
		ItemComponentTooltipProviderRegistry.addAfter(ModDataComponents.BELL, ModDataComponents.OWNER)
		ItemComponentTooltipProviderRegistry.addAfter(ModDataComponents.OWNER, ModDataComponents.OWNER_ALL)
	}

	fun id(path: String): Identifier
		= Identifier.fromNamespaceAndPath(MOD_ID, path)
}
