package com.redgrapefruit.mythicaltowers.common.util

import net.minecraft.entity.data.TrackedDataHandler
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.network.PacketByteBuf

/**
 * A [TrackedDataHandler] for tracked double values since [TrackedDataHandlerRegistry] doesn't have one
 */
class DoubleTrackedDataHandler : TrackedDataHandler<Double> {
    override fun write(buf: PacketByteBuf, value: Double) {
        buf.writeDouble(value)
    }

    override fun read(buf: PacketByteBuf): Double {
        return buf.readDouble()
    }

    override fun copy(value: Double): Double {
        return value
    }

    companion object {
        val default = DoubleTrackedDataHandler()
    }
}
