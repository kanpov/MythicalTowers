package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.entity.melee.GreenMeleeRobotEntity
import com.redgrapefruit.mythicaltowers.common.entity.melee.OrangeMeleeRobotEntity
import com.redgrapefruit.mythicaltowers.common.entity.melee.YellowMeleeRobotEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer

object EntityAttributeRegistry {
    fun init() {
        register(EntityRegistry.GREEN_MELEE_ROBOT, GreenMeleeRobotEntity.ATTRIBUTES)
        register(EntityRegistry.YELLOW_MELEE_ROBOT, YellowMeleeRobotEntity.ATTRIBUTES)
        register(EntityRegistry.ORANGE_MELEE_ROBOT, OrangeMeleeRobotEntity.ATTRIBUTES)
    }

    /**
     * Registers attributes
     */
    private fun register(type: EntityType<out LivingEntity>, attributes: DefaultAttributeContainer.Builder) {
        FabricDefaultAttributeRegistry.register(type, attributes)
    }
}