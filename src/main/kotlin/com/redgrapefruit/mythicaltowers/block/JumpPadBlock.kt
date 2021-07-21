package com.redgrapefruit.mythicaltowers.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World

/**
 * A jump pad acts as a slime block
 */
class JumpPadBlock(
    settings: Settings,
    private val force: Double
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
        launch(entity)

        super.onSteppedOn(world, pos, state, entity)
    }

    /**
     * Launches upwards
     * @param entity The launched entity
     */
    private fun launch(entity: Entity) {
        val velocity: Vec3d = entity.velocity

        entity.setVelocity(velocity.x, velocity.y + force, velocity.z)
    }
}