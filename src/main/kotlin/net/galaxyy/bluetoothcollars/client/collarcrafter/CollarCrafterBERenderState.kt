package net.galaxyy.bluetoothcollars.client.collarcrafter

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class CollarCrafterBERenderState : BlockEntityRenderState() {
    lateinit var level: Level
    val itemStackRenderState: ItemStackRenderState = ItemStackRenderState()
}