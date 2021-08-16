package com.redgrapefruit.mythicaltowers.registry

import com.redgrapefruit.mythicaltowers.entity.GreenMeleeRobotEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer

object EntityAttributeRegistry {
    fun init() {
        register(EntityRegistry.GREEN_MELEE_ROBOT, GreenMeleeRobotEntity.ATTRIBUTES)
    }

    /**
     * Registers attributes
     */
    private fun register(type: EntityType<out LivingEntity>, attributes: DefaultAttributeContainer.Builder) {
        FabricDefaultAttributeRegistry.register(type, attributes)
    }
}