package net.galaxyy.bluetoothcollars.mixin;

import eu.pb4.trinkets.api.TrinketsApi;
import net.galaxyy.bluetoothcollars.items.ModItems;
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class PawCollarMixin {
	@Shadow
	public static Minecraft getInstance() {
		throw new UnsupportedOperationException("Implemented via mixin");
	}

	@Shadow
	@Final
	public Options options;

	@Inject(method = "handleKeybinds", at = @At("HEAD"))
	public void handleKeybindsInjection(CallbackInfo ci) {
		if (!TrinketsApi.getAttachment(getInstance().player).isEquipped(ModItems.INSTANCE.getPAW_COLLAR())) return;
		ItemStack collar = TrinketsApi.getAttachment(getInstance().player).findFirst(
				ModItems.INSTANCE.getPAW_COLLAR(), false
		).get().get();
		while (collar.get(ModDataComponents.INSTANCE.getACTIVE2()) && this.options.keyAttack.consumeClick()) {
			this.options.keyAttack.setDown(false);
		}
		while (collar.get(ModDataComponents.INSTANCE.getACTIVE()) && this.options.keyUse.consumeClick()) {
			this.options.keyUse.setDown(false);
		}
	}
}