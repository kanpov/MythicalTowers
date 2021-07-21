package com.redgrapefruit.mythicaltowers.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Teleports you to the [LiftDestination] when shifting
 */
class LiftBlock(settings: Settings, private val destination: LiftDestination) : Block(settings) {
    override fun onSteppedOn(world: World, pos: BlockPos, state: BlockState, entity: Entity) {
        super.onSteppedOn(world, pos, state, entity)

        // If the entity is sneaking (shifting), teleport it
        if (entity.isInSneakingPose) {
            entity.teleport(entity.pos.x, entity.pos.y + destination.offset, entity.pos.z)
        }
    }
}

/**
 * The destination of the teleport
 */
enum class LiftDestination(internal val offset: Double) {
    UP(5.0),
    DOWN(-5.0)
}
