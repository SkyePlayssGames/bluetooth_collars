package net.galaxyy.bluetoothcollars.items.itemdata

import com.mojang.serialization.Codec
import net.galaxyy.bluetoothcollars.BluetoothCollars.LOGGER
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.UUIDUtil
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import java.util.UUID

object ModDataComponents {
    @Deprecated(
        "Replaced with OWNER (owner_new)",
        ReplaceWith("OWNER"),
        DeprecationLevel.WARNING
    )
    val OWNER_OLD: DataComponentType<UUID> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("owner"),
        DataComponentType.builder<UUID>().persistent(UUIDUtil.CODEC).build()
    )

    val OWNER: DataComponentType<OwnerDataComponent> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("owner_new"),
        DataComponentType.builder<OwnerDataComponent>().persistent(OwnerDataComponent.CODEC).build()
    )

    val OWNER_ALL: DataComponentType<AllOwnersDataComponent> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("owner_all"),
        DataComponentType.builder<AllOwnersDataComponent>().persistent(AllOwnersDataComponent.CODEC).build()
    )

    val LOCATION: DataComponentType<BlockPos> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("location"),
        DataComponentType.builder<BlockPos>().persistent(BlockPos.CODEC).build()
    )

    val ACTIVE: DataComponentType<Boolean> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("active"),
        DataComponentType.builder<Boolean>().persistent(Codec.BOOL).build()
    )

    val ACTIVE2: DataComponentType<Boolean> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("active2"),
        DataComponentType.builder<Boolean>().persistent(Codec.BOOL).build()
    )

    val BELL: DataComponentType<BellDataComponent> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("bell"),
        DataComponentType.builder<BellDataComponent>().persistent(BellDataComponent.CODEC).build()
    )

    val EXTRA_COLLAR: DataComponentType<Boolean> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("extra_collar"),
        DataComponentType.builder<Boolean>().persistent(Codec.BOOL).build()
    )

    val DOUBLE_RANGE: DataComponentType<DoubleRangeDataComponent> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        id("double_range"),
        DataComponentType.builder<DoubleRangeDataComponent>().persistent(DoubleRangeDataComponent.CODEC).build()
    )

    fun registerDataComponents() {
        LOGGER.info("Registering Data Components for Bluetooth Collars")
    }
}