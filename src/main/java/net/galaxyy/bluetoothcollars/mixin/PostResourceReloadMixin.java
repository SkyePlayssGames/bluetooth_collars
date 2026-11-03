package net.galaxyy.bluetoothcollars.mixin;

import com.mojang.serialization.Dynamic;
import net.galaxyy.bluetoothcollars.BluetoothCollars;
import net.galaxyy.bluetoothcollars.datagen.tag.ModItemTags;
import net.galaxyy.bluetoothcollars.items.custom.UpgradeTemplate;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

abstract class PostResourceReloadMixin {
    @Mixin(MinecraftServer.class)
    static abstract class MinecraftServerMixin {
        @Inject(method = "reloadResources", at = @At("TAIL"))
        void reloadResourcesInjection(Collection<String> packsToEnable, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
            BuiltInRegistries.ITEM.get(ModItemTags.INSTANCE.getUPGRADE_TEMPLATES()).get().forEach(item -> {
                DispenserBlock.registerBehavior(item.value(), new UpgradeTemplate.DispenserBehaviour());
            });
        }
    }

    @Mixin(WorldOpenFlows.class)
    static abstract class WorldOpenFlowsMixin {
        @Inject(method = "openWorldLoadLevelStem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/worldselection/WorldOpenFlows;openWorldCheckWorldStemCompatibility(Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lnet/minecraft/server/WorldStem;Lnet/minecraft/server/packs/repository/PackRepository;Ljava/lang/Runnable;)V"))
        void OWLLSInjection(LevelStorageSource.LevelStorageAccess worldAccess, Dynamic<?> levelDataTag, boolean safeMode, Runnable onCancel, CallbackInfo ci) {
            BuiltInRegistries.ITEM.get(ModItemTags.INSTANCE.getUPGRADE_TEMPLATES()).get().forEach(item -> {
                DispenserBlock.registerBehavior(item.value(), new UpgradeTemplate.DispenserBehaviour());
            });
        }
    }
}
