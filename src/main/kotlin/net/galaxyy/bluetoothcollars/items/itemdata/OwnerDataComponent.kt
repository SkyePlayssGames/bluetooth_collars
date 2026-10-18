package net.galaxyy.bluetoothcollars.items.itemdata

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.core.UUIDUtil
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextColor
import net.minecraft.world.item.Item
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.TooltipProvider
import java.util.UUID
import java.util.function.Consumer

data class OwnerDataComponent(val list: List<UUID>) : TooltipProvider {
    override fun addToTooltip(
        context: Item.TooltipContext,
        consumer: Consumer<Component>,
        flag: TooltipFlag,
        components: DataComponentGetter
    ) {
        if (components.get(ModDataComponents.OWNER_ALL)?.bool == true) return

        if (list.isEmpty()) { consumer.accept(Component.translatable("$MOD_ID.tooltip.owners_none")); return }

        if (list.size == 1 ) consumer.accept(Component.translatable("$MOD_ID.tooltip.owners_singular"))
        else consumer.accept(Component.translatable("$MOD_ID.tooltip.owners_plural"))

        var ownersShown: Int = 0
        Minecraft.getInstance().connection?.onlinePlayers?.forEach { playerInfo ->
            if (list.contains(playerInfo.profile.id)) {
                consumer.accept(Component.translatable("$MOD_ID.tooltip.owner", playerInfo.profile.name))
                ownersShown++
            }
        }

        if (ownersShown < list.size) {
            if (list.size - ownersShown == 1)
                consumer.accept(Component.translatable("$MOD_ID.tooltip.owners_offline_singular")
                    .withColor(TextColor.GRAY).withStyle(ChatFormatting.ITALIC))
            else consumer.accept(Component.translatable("$MOD_ID.tooltip.owners_offline_plural", list.size - ownersShown)
                    .withColor(TextColor.GRAY).withStyle(ChatFormatting.ITALIC))
        }
    }

    companion object {
        val CODEC: Codec<OwnerDataComponent> = RecordCodecBuilder.create{ builder ->
            builder.group(
                Codec.list(UUIDUtil.CODEC).fieldOf("list").forGetter(OwnerDataComponent::list)
            ).apply(builder, ::OwnerDataComponent)
        }
    }
}