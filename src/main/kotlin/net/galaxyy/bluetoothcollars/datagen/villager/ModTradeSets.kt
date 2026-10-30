package net.galaxyy.bluetoothcollars.datagen.villager

import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.datagen.tag.ModVillagerTradeTags
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.IntProviders
import net.minecraft.world.item.trading.TradeSet
import net.minecraft.world.item.trading.VillagerTrade
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders
import java.util.Optional

object ModTradeSets {
    val PET_HANDLER_1: ResourceKey<TradeSet> = create("pet_handler/level_1")
    val PET_HANDLER_2: ResourceKey<TradeSet> = create("pet_handler/level_2")

    fun bootstrap(context: BootstrapContext<TradeSet>) {
        register(context, PET_HANDLER_1, ModVillagerTradeTags.PET_HANDLER_1)
        register(context, PET_HANDLER_2, ModVillagerTradeTags.PET_HANDLER_2)
    }

    private fun create(id: String): ResourceKey<TradeSet> {
        return ResourceKey.create(Registries.TRADE_SET, id(id))
    }

    fun register(context: BootstrapContext<TradeSet>, key: ResourceKey<TradeSet>, tagKey: TagKey<VillagerTrade>): Holder.Reference<TradeSet> {
        return register(context, key, tagKey, ContextIntProviders.exactly(2))
    }

    fun register(context: BootstrapContext<TradeSet>, key: ResourceKey<TradeSet>, tagKey: TagKey<VillagerTrade>, number: Holder<ContextIntProvider>): Holder.Reference<TradeSet> {
        return context.register(key, TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tagKey),
            number, false, Optional.of(key.identifier().withPrefix("trade_set/"))))
    }
}