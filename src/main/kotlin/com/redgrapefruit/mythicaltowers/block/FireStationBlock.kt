package com.redgrapefruit.mythicaltowers.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Every time you step on a fire station, you'll be set on fire
 */
class FireStationBlock(settings: Settings) : Block(settings) {
    override fun onSteppedOn(world: World, pos: BlockPos, state: BlockState, entity: Entity) {
        super.onSteppedOn(world, pos, state, entity)
        // Replace the top block with fire
        world.setBlockState(pos.up(), Blocks.FIRE.defaultState)
    }
}