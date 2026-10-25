package net.galaxyy.bluetoothcollars.items

import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.references.BlockItemId
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

object ModBlocks {
    val COLLAR_CRAFTER_KEY: BlockItemId = create("collar_crafter")
    val COLLAR_CRAFTER: CollarCrafter = register(
        COLLAR_CRAFTER_KEY, ::CollarCrafter,
        BlockBehaviour.Properties.of()
    )


    private fun <T : Block> register(id: BlockItemId, factory: (BlockBehaviour.Properties) -> T, properties: BlockBehaviour.Properties): T {
        val block: T = register(id.block, factory, properties)

        val blockItem: BlockItem = BlockItem(block, Item.Properties().useBlockDescriptionPrefix().setId(id.item))
        Registry.register(BuiltInRegistries.ITEM, id.item, blockItem)

        return block
    }

    private fun <T : Block> register(id: ResourceKey<Block>, factory: (BlockBehaviour.Properties) -> T, properties: BlockBehaviour.Properties): T {
        val block: T = factory(properties.setId(id))

        return Registry.register(BuiltInRegistries.BLOCK, id, block)
    }

    private fun create(name: String): BlockItemId {
        val id: Identifier = id(name)
        return BlockItemId.create(id, id)
    }

    fun registerBlocks() {
        LOGGER.info("Registering Blocks for Bluetooth Collars")
    }
}