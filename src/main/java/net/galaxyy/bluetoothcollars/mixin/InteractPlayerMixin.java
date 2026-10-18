package net.galaxyy.bluetoothcollars.mixin;

import eu.pb4.trinkets.api.TrinketsApi;
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags;
import net.minecraft.client.particle.HeartParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Prediction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
abstract class InteractPlayerMixin {

    @Shadow
    public abstract double getX();

    @Shadow
    public abstract double getY();

    @Shadow
    public abstract double getZ();

    @Inject(method = "interact", at = @At("HEAD"))
    public void interactInjection(Player player, InteractionHand hand, Vec3 location, CallbackInfoReturnable<InteractionResult> cir) {
        if (((Entity) (Object) this) instanceof Player thisPlayer &&
                TrinketsApi.getAttachment(thisPlayer).isEquipped(ModItemTags.INSTANCE.getCOLLARS())
        ) {
            ItemStack item = player.getItemInHand(hand);
            if (item.is(Items.SHEARS)) {
                ItemStack headgear = TrinketsApi.getAttachment(thisPlayer).findFirst(ModItemTags.INSTANCE.getCOLLARS()).get().get();
                if (!headgear.is(ModItemTags.INSTANCE.getCOLLARS()) || player.level().isClientSide()) return;
                thisPlayer.drop(headgear, false, Prediction.PREDICTED);
                TrinketsApi.getAttachment(thisPlayer).findFirst(ModItemTags.INSTANCE.getCOLLARS())
                        .get().set(ItemStack.EMPTY);
            }
            // else if (item.get(DataComponents.FOOD) != null && item.get(DataComponents.CONSUMABLE) != null) {
            //    item.get(DataComponents.FOOD).onConsume(thisPlayer.level(), thisPlayer, item, item.get(DataComponents.CONSUMABLE));
            //}
            else if (player.getItemInHand(hand).isEmpty()) {
                if (player.level().isClientSide()) return;
                player.level().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 2, this.getZ(), 0, 0, 0);
                if (player.isCrouching() && player.getFoodData().getFoodLevel() > 6) {
                    thisPlayer.heal(2);
                    player.causeFoodExhaustion(2);
                }
            }
        }
    }
}
