package com.redgrapefruit.mythicaltowers.client.init

import com.redgrapefruit.mythicaltowers.client.render.entity.GreenTntEntityRenderer
import com.redgrapefruit.mythicaltowers.common.init.MythicalEntities
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

/**
 * Registers the mod's [EntityRenderer]s
 */
object MythicalEntityRenderers {
    fun init() {
        register(MythicalEntities.GREEN_TNT) { dispatcher: EntityRenderDispatcher, _: EntityRendererRegistry.Context ->
            GreenTntEntityRenderer(dispatcher)
        }
    }

    /**
     * Registers an [EntityRenderer]
     * @param type The [EntityType] of the rendered entity
     * @param factory The lambda factory
     */
    private fun <TEntity> register(type: EntityType<TEntity>, factory: EntityRendererRegistry.Factory) where TEntity: Entity {
        EntityRendererRegistry.INSTANCE.register(type, factory)
    }
}