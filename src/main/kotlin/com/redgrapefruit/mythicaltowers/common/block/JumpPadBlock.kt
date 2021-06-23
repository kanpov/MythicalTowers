package com.redgrapefruit.mythicaltowers.common.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World
import kotlin.math.abs

/**
 * Base jump force
 */
private const val JUMP_FORCE: Float = 1.8f

/**
 * A jump pad acts as a slime block
 */
class JumpPadBlock(
    settings: Settings,
    private val livingBoostMultiplier: Double,
    private val standardBoostMultiplier: Double
) : Block(settings) {

    override fun onLandedUpon(world: World, state: BlockState, pos: BlockPos, entity: Entity, distance: Float) {
        // Do standard damage if the entity bypasses landing effects, else suppress the damage
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, distance)
        } else {
            entity.handleFallDamage(distance, 0.0F, DamageSource.FALL)
        }
    }

    override fun onEntityLand(world: BlockView, entity: Entity) {
        // Don't do anything if the entity bypasses landing effects, else bounce upwards
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity)
        } else {
            launch(entity)
        }
    }

    override fun onSteppedOn(world: World, pos: BlockPos, state: BlockState, entity: Entity) {
        val d: Double = abs(entity.velocity.y)

        if (d < 0.1 && !entity.bypassesLandingEffects()) {
            val e: Double = 0.4 + d * 0.2
            entity.velocity = entity.velocity.multiply(e, 1.0, e)
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    /**
     * Launches upwards
     * @param entity The launched entity
     */
    private fun launch(entity: Entity) {
        val velocity: Vec3d = entity.velocity
        // Additional jump force depending on the entity
        val modifier: Double = if (entity is LivingEntity) livingBoostMultiplier else standardBoostMultiplier

        entity.setVelocity(velocity.x, (velocity.y + JUMP_FORCE) * modifier, velocity.z)
    }
}