package net.galaxyy.bluetoothcollars.client.collarcrafter

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class CollarCrafterBERenderState : BlockEntityRenderState() {
    lateinit var level: Level
    lateinit var facing: Direction
    val itemStackRenderState: ItemStackRenderState = ItemStackRenderState()
}