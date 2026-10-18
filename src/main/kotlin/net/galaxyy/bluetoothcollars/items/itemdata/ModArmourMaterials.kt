package net.galaxyy.bluetoothcollars.items.itemdata

import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets

object ModArmourMaterials {
    @Deprecated("This is unused and will be deleted", ReplaceWith(""))
    private val COLLAR_MATERIAL_KEY: ResourceKey<EquipmentAsset> = ResourceKey.create(
        EquipmentAssets.ROOT_ID, id("collar")
    )

    @Deprecated("This is unused and will be deleted", ReplaceWith(""), DeprecationLevel.ERROR)
    val COLLAR_MATERIAL: ArmorMaterial = ArmorMaterial(
        10,
        mapOf(
            Pair(ArmorType.HELMET, 0)
        ),
        1,
        SoundEvents.ARMOR_EQUIP_WOLF,
        0f,
        0f,
        ItemTags.REPAIRS_LEATHER_ARMOR,
        COLLAR_MATERIAL_KEY
    )
}