package net.galaxyy.bluetoothcollars.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.BluetoothCollars.id
import net.galaxyy.bluetoothcollars.events.CollarActivateTrigger
import net.galaxyy.bluetoothcollars.events.TrinketEquipTrigger
import net.galaxyy.bluetoothcollars.items.ModItems
import net.galaxyy.bluetoothcollars.items.itemdata.AllOwnersDataComponent
import net.galaxyy.bluetoothcollars.items.itemdata.ModDataComponents
import net.galaxyy.bluetoothcollars.items.itemdata.ModItemTags
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.predicates.DataComponentMatchers
import net.minecraft.advancements.predicates.EnchantmentPredicate
import net.minecraft.advancements.predicates.ItemPredicate
import net.minecraft.advancements.predicates.MinMaxBounds
import net.minecraft.advancements.triggers.InventoryChangeTrigger
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponentExactPredicate
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.component.DataComponents
import net.minecraft.core.component.TypedDataComponent
import net.minecraft.core.component.predicates.DataComponentPredicates
import net.minecraft.core.component.predicates.EnchantmentsPredicate
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.ItemEnchantments
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementDataGen(output: FabricPackOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) : FabricAdvancementProvider(output, registryLookup) {
    override fun generateAdvancement(
        registryLookup: HolderLookup.Provider,
        consumer: Consumer<AdvancementHolder>
    ) {
        val itemLookup: HolderLookup.RegistryLookup<Item> = registryLookup.lookupOrThrow(Registries.ITEM)
        val enchantmentLookup: HolderLookup.RegistryLookup<Enchantment> = registryLookup.lookupOrThrow(Registries.ENCHANTMENT)

        val collarSomeone: AdvancementHolder = Advancement.Builder.advancement()
            .display(
                ModItems.TIED_DOWN_REMOTE,
                Component.translatable("advancement.$MOD_ID.collar_someone.name"),
                Component.translatable("advancement.$MOD_ID.collar_someone.description"),
                AdvancementType.TASK,
                true, true, false
            )
            .addCriterion("collar_someone", InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(itemLookup, ModItemTags.REMOTES).build())
            )
            .parent(createPlaceholder(Identifier.withDefaultNamespace("husbandry/root")))
            .save(consumer, id("collar_someone"))


        val getCollared: AdvancementHolder = Advancement.Builder.advancement()
            .display(
                ModItems.TIED_DOWN_COLLAR,
                Component.translatable("advancement.$MOD_ID.get_collared.name"),
                Component.translatable("advancement.$MOD_ID.get_collared.description"),
                AdvancementType.TASK,
                true, true, false
            )
            .addCriterion("get_collared", TrinketEquipTrigger.TriggerInstance.hasItem(
                ItemPredicate.Builder.item().of(itemLookup, ModItemTags.COLLARS).build())
            )
            .parent(createPlaceholder(Identifier.withDefaultNamespace("husbandry/root")))
            .save(consumer, id("get_collared"))


        val experimental: AdvancementHolder = Advancement.Builder.advancement()
            .display(
                ModItems.TINY_COLLAR,
                Component.translatable("advancement.$MOD_ID.experimental.name"),
                Component.translatable("advancement.$MOD_ID.experimental.description"),
                AdvancementType.TASK,
                true, true, false
            )
            .addCriterion("tied_down_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.TIED_DOWN_COLLAR
            ))
            .addCriterion("clumsy_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.CLUMSY_COLLAR
            ))
            .addCriterion("crawl_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.CRAWL_COLLAR
            ))
            .addCriterion("paw_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.PAW_COLLAR
            ))
            .addCriterion("tiny_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.TINY_COLLAR
            ))
            .addCriterion("blinding_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.BLINDING_COLLAR
            ))
            .addCriterion("frozen_collar", CollarActivateTrigger.TriggerInstance.ofItem(
                ModItems.FROZEN_COLLAR
            ))
            .parent(getCollared)
            .save(consumer, id("experimental"))
    }
}