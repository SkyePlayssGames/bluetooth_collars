package net.galaxyy.bluetoothcollars.items.blockentities

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType


object ModBlockEntities {
    val COLLAR_CRAFTER_BE: BlockEntityType<CollarCrafterBE> = register(
        "collar_crafter", ::CollarCrafterBE, ModBlocks.COLLAR_CRAFTER
    )

    private fun <T : BlockEntity> register(
        name: String, entityFactory: FabricBlockEntityTypeBuilder.Factory<out T>, blocks: Block
    ): BlockEntityType<T> {
        val id: Identifier = id(name)
        return Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            id,
            FabricBlockEntityTypeBuilder.create(entityFactory, blocks).build())
    }

    fun registerBlockEntities() {
        LOGGER.info("Registering Block Entities for Bluetooth Collars")
    }
}