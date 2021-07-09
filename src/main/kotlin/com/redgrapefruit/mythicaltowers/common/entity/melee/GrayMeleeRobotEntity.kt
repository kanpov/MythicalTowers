package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class GrayMeleeRobotEntity(type: EntityType<GrayMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.34)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 150.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0)
    }
}
