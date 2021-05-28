package com.redgrapefruit.mythicaltowers.common.block.trap

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World
import kotlin.math.abs

class JumpPadBlock(lbm: Double, sbm: Double) : Block(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK)) {
    private var livingBoostMultiplier: Double = 1.0
    private var standardBoostMultiplier: Double = 0.8
    
    init {
        this.livingBoostMultiplier = sbm
        this.standardBoostMultiplier = lbm
    }

    override fun onLandedUpon(world: World, pos: BlockPos, entity: Entity, distance: Float) {
        // Do standard damage if the entity bypasses landing effects, else suppress the damage
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, pos, entity, distance)
        } else {
            entity.handleFallDamage(distance, 0.0F)
        }
    }

    override fun onEntityLand(world: BlockView, entity: Entity) {
        // Don't do anything if the entity bypasses landing effects, else bounce upwards
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity)
        } else {
            bounce(entity)
        }
    }

    override fun onSteppedOn(world: World, pos: BlockPos, entity: Entity) {
        val d: Double = abs(entity.velocity.y)

        if (d < 0.1 && !entity.bypassesLandingEffects()) {
            val e: Double = 0.4 + d * 0.2
            entity.velocity = entity.velocity.multiply(e, 1.0, e)
        }

        super.onSteppedOn(world, pos, entity)
    }

    /**
     * Bounces up
     * @param entity The bouncing entity
     */
    private fun bounce(entity: Entity) {
        val velocity: Vec3d = entity.velocity
        // If the velocity is negative, accelerate on the Y axis using the calculated modifier
        if (velocity.y < 0.0) {
            val modifier: Double = if (entity is LivingEntity) livingBoostMultiplier else standardBoostMultiplier
            entity.setVelocity(velocity.x, -velocity.y * modifier, velocity.z)
        }
    }
}