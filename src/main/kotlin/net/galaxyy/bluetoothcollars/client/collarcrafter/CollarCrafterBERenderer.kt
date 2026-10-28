package net.galaxyy.bluetoothcollars.client.collarcrafter

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.galaxyy.bluetoothcollars.items.CollarCrafter
import net.galaxyy.bluetoothcollars.items.blockentities.CollarCrafterBE
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.item.ItemModelResolver
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f

class CollarCrafterBERenderer(context: BlockEntityRendererProvider.Context) : BlockEntityRenderer<CollarCrafterBE, CollarCrafterBERenderState> {
    val itemModelResolver: ItemModelResolver = context.itemModelResolver

    override fun createRenderState(): CollarCrafterBERenderState {
        return CollarCrafterBERenderState()
    }

    override fun submit(
        state: CollarCrafterBERenderState,
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        camera: CameraRenderState
    ) {
        poseStack.pushPose()
        poseStack.mulPose(Matrix4f()
            .translate(0.5f, 17f/16f, 0.5f)
            .scale(0.8f)
            .rotate(Axis.XP.rotationDegrees(90f))
            .rotate(Axis.ZP.rotationDegrees(when (state.facing) {
                Direction.NORTH -> 0f
                Direction.EAST -> 90f
                Direction.SOUTH -> 180f
                Direction.WEST -> 270f
                else -> 0f
            }))
        )

        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0)

        poseStack.popPose()
    }

    override fun extractRenderState(
        blockEntity: CollarCrafterBE,
        state: CollarCrafterBERenderState,
        partialTicks: Float,
        cameraPosition: Vec3,
        breakProgress: ModelFeatureRenderer.CrumblingOverlay?
    ) {
        super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress)
        state.level = blockEntity.level!!
        state.facing = blockEntity.blockState.getValue(CollarCrafter.FACING)
        this.itemModelResolver.updateForTopItem(state.itemStackRenderState, blockEntity.theItem,
            ItemDisplayContext.FIXED, blockEntity.level, null, 0)
    }
}