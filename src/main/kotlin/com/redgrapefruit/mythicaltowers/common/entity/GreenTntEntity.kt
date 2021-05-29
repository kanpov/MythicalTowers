package com.redgrapefruit.mythicaltowers.common.entity

import com.redgrapefruit.mythicaltowers.common.init.MythicalEntities
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

/**
 * The green TNT. Slightly more dangerous than usual, but the fuse timer is longer
 */
class GreenTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<GreenTntEntity>, world: World) : super(type, world) {
        initFuse(90)
        explosionPower = 5.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(MythicalEntities.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(90)
        explosionPower = 5.0f
    }
}