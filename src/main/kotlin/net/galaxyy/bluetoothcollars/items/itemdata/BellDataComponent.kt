package net.galaxyy.bluetoothcollars.items.itemdata

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.minecraft.core.UUIDUtil
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextColor
import net.minecraft.world.item.Item
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.TooltipProvider
import java.util.function.Consumer

data class BellDataComponent(val bell: Boolean) : TooltipProvider {
    override fun addToTooltip(
        context: Item.TooltipContext,
        consumer: Consumer<Component>,
        flag: TooltipFlag,
        components: DataComponentGetter
    ) {
        if (bell) {
            consumer.accept(Component.translatable("$MOD_ID.tooltip.bell").withColor(TextColor.YELLOW))
        }
    }

    companion object {
        val CODEC: Codec<BellDataComponent> = RecordCodecBuilder.create { builder ->
            builder.group(
                Codec.BOOL.fieldOf("bell").forGetter(BellDataComponent::bell)
            ).apply(builder, ::BellDataComponent)
        }
    }
}
