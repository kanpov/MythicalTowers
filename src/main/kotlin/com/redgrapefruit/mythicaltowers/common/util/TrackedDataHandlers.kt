package com.redgrapefruit.mythicaltowers.common.util

import com.redgrapefruit.mythicaltowers.mixin.TrackedDataHandlerRegistryMixin
import net.minecraft.entity.data.TrackedDataHandler

/**
 * Util exposing [TrackedDataHandlerRegistryMixin] to outer access
 */
object TrackedDataHandlers {
    const val MIN_MODDED_HANDLER_INDEX = Int.MAX_VALUE / 2

    val handlers: MutableMap<Int, TrackedDataHandler<*>> = mutableMapOf()

    // Set the lastHandlerIndex far to support a lot other handlers and a lot of custom handlers
    private var lastHandlerIndex: Int = MIN_MODDED_HANDLER_INDEX

    /**
     * Registers the [handler]
     */
    fun <T> register(handler: TrackedDataHandler<T>) {
        ++lastHandlerIndex
        handlers[lastHandlerIndex] = handler
    }

    init {
        register(DoubleTrackedDataHandler.default)
    }
}