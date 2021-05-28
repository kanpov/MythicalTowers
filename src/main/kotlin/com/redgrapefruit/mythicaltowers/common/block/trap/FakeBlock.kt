package com.redgrapefruit.mythicaltowers.common.block.trap

import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * A fake block disappears once stepped on
 */
class FakeBlock  // Fake blocks inherit settings from their real parents
    (parentSettings: Settings) : Block(parentSettings) {
    override fun onSteppedOn(world: World, pos: BlockPos, entity: Entity) {
        super.onSteppedOn(world, pos, entity)
        world.removeBlock(pos, false)
    }
}