package net.galaxyy.bluetoothcollars.client.collarcrafter

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import com.mojang.math.Transformation
import net.galaxyy.bluetoothcollars.BluetoothCollars
import net.galaxyy.bluetoothcollars.items.blockentities.CollarCrafterBE
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.item.ItemModelResolver
import net.minecraft.client.renderer.item.ItemStackRenderState.FoilType
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.client.resources.model.geometry.ItemQuads
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import org.joml.Matrix4fc

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
        BluetoothCollars.LOGGER.info("Rendering BE")
        poseStack.pushPose()
        poseStack.mulPose(Matrix4f().translate(0.5f, 17f/16f, 0.5f).scale(0.8f).rotate(Axis.XP.rotationDegrees(90f)))

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
        BluetoothCollars.LOGGER.info(blockEntity.theItem.toString())
        this.itemModelResolver.updateForTopItem(state.itemStackRenderState, blockEntity.theItem,
            ItemDisplayContext.FIXED, blockEntity.level, null, 0)
    }
}