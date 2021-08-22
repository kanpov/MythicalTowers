package com.redgrapefruit.mythicaltowers.client.registry

import com.redgrapefruit.mythicaltowers.client.render.entity.*
import com.redgrapefruit.mythicaltowers.registry.EntityRegistry
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
        // TNT
        register(EntityRegistry.GREEN_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.YELLOW_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.ORANGE_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.RED_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.BLUE_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.PURPLE_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.GRAY_TNT) { context -> DummyEntityRenderer(context) }
        register(EntityRegistry.BLACK_TNT) { context -> DummyEntityRenderer(context) }

        // Melee robot
        register(EntityRegistry.GREEN_MELEE_ROBOT) { context -> GreenMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.YELLOW_MELEE_ROBOT) { context -> YellowMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.ORANGE_MELEE_ROBOT) { context -> OrangeMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.RED_MELEE_ROBOT) { context -> RedMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.BLUE_MELEE_ROBOT) { context -> BlueMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.PURPLE_MELEE_ROBOT) { context -> PurpleMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.GRAY_MELEE_ROBOT) { context -> GrayMeleeRobotEntityRenderer(context) }
        register(EntityRegistry.BLACK_MELEE_ROBOT) { context -> BlackMeleeRobotEntityRenderer(context) }
    }

    /**
     * Registers an [EntityRenderer] for [type]'s [Entity] with the given [factory]
     */
    private fun <E : Entity> register(type: EntityType<E>, factory: EntityRendererFactory<E>) {
        EntityRendererRegistry.INSTANCE.register(type, factory)
    }
}