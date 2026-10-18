package net.galaxyy.bluetoothcollars.events

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.events.TrinketEquipTrigger.TriggerInstance
import net.minecraft.advancements.predicates.DataComponentMatchers
import net.minecraft.advancements.predicates.ItemPredicate
import net.minecraft.advancements.predicates.MinMaxBounds
import net.minecraft.advancements.predicates.entity.EntityPredicate
import net.minecraft.advancements.triggers.Criterion
import net.minecraft.advancements.triggers.SimpleCriterionTrigger
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import java.util.Optional

class TrinketEquipTrigger : SimpleCriterionTrigger<TriggerInstance>() {
    override fun codec(): Codec<TriggerInstance> {
        return TriggerInstance.CODEC
    }

    fun trigger(player: ServerPlayer, item: ItemStack) {
        super.trigger(player) {t -> t.matches(item)}
    }

    data class TriggerInstance(val playerX: Optional<Holder<LootItemCondition>>, val predicates: List<ItemPredicate>)
        : SimpleInstance {
        override fun player(): Optional<Holder<LootItemCondition>> {
            return playerX
        }

        fun matches(item: ItemStack): Boolean {
            this.predicates.forEach { predicate ->
                if (predicate.test(item)) {
                    return true
                }
            }
            return false
        }

        companion object {
            val CODEC: Codec<TriggerInstance> = RecordCodecBuilder.create { i ->
                i.group(
                    LootItemCondition.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::playerX),
                    ItemPredicate.CODEC.listOf()!!.optionalFieldOf("items", listOf()).forGetter(TriggerInstance::predicates)
                ).apply(i, ::TriggerInstance)
            }

            fun hasItem(item: ItemPredicate): Criterion<TriggerInstance> {
                return ModTriggers.TRINKET_EQUIP_TRIGGER.createCriterion(
                    TriggerInstance(Optional.empty(), listOf(item))
                )
            }

            fun hasItem(item: ItemLike): Criterion<TriggerInstance> {
                return hasItem(ItemPredicate(
                    Optional.of(HolderSet.direct(item.asItem().builtInRegistryHolder())),
                    MinMaxBounds.Ints.ANY, DataComponentMatchers.ANY
                ))
            }
        }
    }
}