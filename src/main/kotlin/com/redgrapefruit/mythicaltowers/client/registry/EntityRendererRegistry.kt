package com.redgrapefruit.mythicaltowers.client.registry

import com.redgrapefruit.mythicaltowers.client.render.entity.DummyEntityRenderer
import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

/**
 * Registers the mod's [EntityRenderer]s
 */
object EntityRendererRegistry {
    fun init() {
        register(EntityRegistry.GREEN_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.YELLOW_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.ORANGE_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.RED_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.BLUE_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.PURPLE_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.GRAY_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
        register(EntityRegistry.BLACK_TNT) { context: EntityRendererFactory.Context -> DummyEntityRenderer(context) }
    }

    /**
     * Registers an [EntityRenderer]
     * @param type The [EntityType] of the entity that the [EntityRenderer] belongs to
     * @param factory Lambda factory that instantiates the [EntityRenderer]
     */
    private fun <TEntity> register(
        type: EntityType<TEntity>,
        factory: EntityRendererFactory<TEntity>
    ) where TEntity : Entity {
        EntityRendererRegistry.INSTANCE.register(type, factory)
    }
}