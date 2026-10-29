package net.galaxyy.bluetoothcollars.events

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.events.CollarCrafterCraftTrigger.TriggerInstance
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

class CollarCrafterCraftTrigger : SimpleCriterionTrigger<TriggerInstance>() {
    override fun codec(): Codec<TriggerInstance> {
        return TriggerInstance.CODEC
    }

    fun trigger(player: ServerPlayer) {
        super.trigger(player) {_ -> true}
    }

    data class TriggerInstance(val playerX: Optional<Holder<LootItemCondition>>)
        : SimpleInstance {
        override fun player(): Optional<Holder<LootItemCondition>> {
            return playerX
        }

        companion object {
            val CODEC: Codec<TriggerInstance> = RecordCodecBuilder.create { i ->
                i.group(
                    LootItemCondition.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::playerX)
                ).apply(i, ::TriggerInstance)
            }

            fun create(): Criterion<TriggerInstance> {
                return ModTriggers.COLLAR_CRAFTER_CRAFT_TRIGGER.createCriterion(
                    TriggerInstance(Optional.empty())
                )
            }
        }
    }
}