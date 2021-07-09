package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class PurpleMeleeRobotEntity(type: EntityType<PurpleMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 90.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.31)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 125.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
    }
}
