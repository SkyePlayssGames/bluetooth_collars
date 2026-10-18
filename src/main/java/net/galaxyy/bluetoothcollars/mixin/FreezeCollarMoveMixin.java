package net.galaxyy.bluetoothcollars.mixin;

import eu.pb4.trinkets.api.TrinketsApi;
import net.galaxyy.bluetoothcollars.items.ModItems;
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
abstract class FreezeCollarMoveMixin {
    @Inject(at = @At("HEAD"), method = "move", cancellable = true)
    void moveInject(MoverType moverType, Vec3 delta, CallbackInfo ci) {
        if ((Entity) (Object) this instanceof Player player) {
            if (!TrinketsApi.getAttachment(player).isEquipped(ModItems.INSTANCE.getFROZEN_COLLAR())) return;
            if (Boolean.FALSE.equals(TrinketsApi.getAttachment(player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                    .get().get().get(ModDataComponents.INSTANCE.getACTIVE()))) return;
            if (moverType != MoverType.PISTON) ci.cancel();
            else TrinketsApi.getAttachment(player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                    .get().get().set(ModDataComponents.INSTANCE.getACTIVE2(), true);
        }
    }

    @Inject(method = "turn", at = @At("HEAD"), cancellable = true)
    void turnInject(double xo, double yo, CallbackInfo ci) {
        if ((Entity) (Object) this instanceof Player player) {
            if (!TrinketsApi.getAttachment(player).isEquipped(ModItems.INSTANCE.getFROZEN_COLLAR())) return;
            if (Boolean.TRUE.equals(TrinketsApi.getAttachment(player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                    .get().get().get(ModDataComponents.INSTANCE.getACTIVE()))) ci.cancel();
        }
    }

    @Inject(method = "setPose", at = @At("HEAD"), cancellable = true)
    void setPoseInject(Pose pose, CallbackInfo ci) {
        if ((Entity) (Object) this instanceof Player player) {
            if (!TrinketsApi.getAttachment(player).isEquipped(ModItems.INSTANCE.getFROZEN_COLLAR())) return;
            if (Boolean.TRUE.equals(TrinketsApi.getAttachment(player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                    .get().get().get(ModDataComponents.INSTANCE.getACTIVE()))) ci.cancel();
        }
    }
}

@Mixin(Camera.class)
abstract class FreezeCollarCameraMixin {
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    void updateInject(DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Minecraft.getInstance().player == null) return;
        if (!TrinketsApi.getAttachment(Minecraft.getInstance().player).isEquipped(ModItems.INSTANCE.getFROZEN_COLLAR())) return;
        if (Boolean.FALSE.equals(TrinketsApi.getAttachment(Minecraft.getInstance().player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                .get().get().get(ModDataComponents.INSTANCE.getACTIVE()))) return;
        if (Boolean.FALSE.equals(TrinketsApi.getAttachment(Minecraft.getInstance().player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                .get().get().get(ModDataComponents.INSTANCE.getACTIVE2()))) { ci.cancel(); return; }
        TrinketsApi.getAttachment(Minecraft.getInstance().player).findFirst(ModItems.INSTANCE.getFROZEN_COLLAR())
                .get().get().set(ModDataComponents.INSTANCE.getACTIVE2(), false);
    }
}