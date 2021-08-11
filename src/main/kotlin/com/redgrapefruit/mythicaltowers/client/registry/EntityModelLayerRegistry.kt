@file:Suppress("UnstableApiUsage", "DEPRECATION")

package com.redgrapefruit.mythicaltowers.client.registry

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer

/**
 * Registry for entity model layers
 */
object EntityModelLayerRegistry {
    fun init() {

    }

    /**
     * Registers a model layer
     */
    private fun register(layer: EntityModelLayer, provider: EntityModelLayerRegistry.TexturedModelDataProvider) {
        EntityModelLayerRegistry.registerModelLayer(layer, provider)
    }
}