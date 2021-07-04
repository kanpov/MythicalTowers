package com.redgrapefruit.mythicaltowers.client.registry

import com.redgrapefruit.mythicaltowers.client.render.entity.DummyEntityRenderer
import com.redgrapefruit.mythicaltowers.client.render.entity.GreenMeleeRobotEntityRenderer
import com.redgrapefruit.mythicaltowers.client.render.entity.YellowMeleeRobotEntityRenderer
import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory

/**
 * Registers the mod's [EntityRenderer]s
 */
object EntityRendererRegistry {
    fun init() {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.GREEN_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.YELLOW_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.ORANGE_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.RED_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.BLUE_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.PURPLE_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.GRAY_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.BLACK_TNT) { context -> DummyEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.GREEN_MELEE_ROBOT) { context -> GreenMeleeRobotEntityRenderer(context) }
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.YELLOW_MELEE_ROBOT) { context -> YellowMeleeRobotEntityRenderer(context) }
    }
}