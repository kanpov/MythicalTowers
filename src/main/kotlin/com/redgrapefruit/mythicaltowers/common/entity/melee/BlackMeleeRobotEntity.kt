package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class BlackMeleeRobotEntity(type: EntityType<BlackMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 110.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.37)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 62.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0)
    }
}
