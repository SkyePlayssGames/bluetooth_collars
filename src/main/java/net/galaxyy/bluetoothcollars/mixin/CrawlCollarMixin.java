package net.galaxyy.bluetoothcollars.mixin;

import eu.pb4.trinkets.api.TrinketsApi;
import net.galaxyy.bluetoothcollars.items.ModItems;
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents;
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
abstract class CrawlCollarMixin {
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    public void updateSwimmingInjection(CallbackInfo ci) {
        if (((Entity) (Object) this) instanceof Player &&
                TrinketsApi.getAttachment((LivingEntity) (Object) this).isEquipped(ModItems.INSTANCE.getCRAWL_COLLAR()) &&
                TrinketsApi.getAttachment((LivingEntity) (Object) this).findFirst(ModItems.INSTANCE.getCRAWL_COLLAR(), false)
                        .get().get().get(ModDataComponents.INSTANCE.getACTIVE())
        ) {
            ci.cancel();
        }
    }
}
