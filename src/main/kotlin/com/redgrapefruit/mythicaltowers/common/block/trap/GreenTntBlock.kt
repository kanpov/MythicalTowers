package com.redgrapefruit.mythicaltowers.common.block.trap

import com.redgrapefruit.mythicaltowers.common.entity.GreenTntEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

/**
 * Green TNT. Slightly more dangerous than usual, but the fuse time is longer
 */
class GreenTntBlock() : CustomTntBlock<GreenTntEntity>() {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): GreenTntEntity {
        return GreenTntEntity(world, x, y, z, igniter)
    }
}