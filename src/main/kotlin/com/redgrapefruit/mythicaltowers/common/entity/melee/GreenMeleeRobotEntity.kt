package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.5)
            .add(EntityAttributes.GENERIC_ARMOR, 6.0)
    }
}
