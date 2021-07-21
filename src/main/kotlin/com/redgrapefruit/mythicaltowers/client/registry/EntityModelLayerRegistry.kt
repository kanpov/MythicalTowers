@file:Suppress("UnstableApiUsage", "DEPRECATION")

package com.redgrapefruit.mythicaltowers.client.registry

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.client.model.MeleeRobotEntityModel
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer

/**
 * Registry for entity model layers
 */
object EntityModelLayerRegistry {
    val MELEE_ROBOT = EntityModelLayer(idOf("melee_robot"), "1")

    fun init() {
        register(MELEE_ROBOT, MeleeRobotEntityModel.Builder::create)
    }

    /**
     * Registers a model layer
     */
    private fun register(layer: EntityModelLayer, provider: EntityModelLayerRegistry.TexturedModelDataProvider) {
        EntityModelLayerRegistry.registerModelLayer(layer, provider)
    }
}