package net.galaxyy.bluetoothcollars.items.itemdata

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.TooltipProvider
import java.util.function.Consumer

data class DoubleRangeDataComponent(val bool: Boolean) : TooltipProvider {
    override fun addToTooltip(
        context: Item.TooltipContext,
        consumer: Consumer<Component>,
        flag: TooltipFlag,
        components: DataComponentGetter
    ) {
        if (bool) {
            consumer.accept(Component.translatable("$MOD_ID.tooltip.doubled_range")
                .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC))
        }
    }

    companion object {
        val CODEC: Codec<DoubleRangeDataComponent> = RecordCodecBuilder.create { builder -> builder.group(
            Codec.BOOL.fieldOf("bool").forGetter(DoubleRangeDataComponent::bool)
        ).apply(builder, ::DoubleRangeDataComponent) }
    }
}
