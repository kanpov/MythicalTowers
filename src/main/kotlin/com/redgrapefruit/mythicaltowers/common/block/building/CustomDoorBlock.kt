package com.redgrapefruit.mythicaltowers.common.block.building

import com.redgrapefruit.mythicaltowers.common.item.KeyItem
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Custom door that requires a [KeyItem] to open.
 */
class CustomDoorBlock(private val key: KeyItem, settings: Settings) : Block(settings) {
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val stack = player.getStackInHand(hand)

        // Check if the door has been right-clicked with the linked key
        if (stack.item == key) {
            // Remove the door
            world.removeBlock(pos, false)
            // Decrement the stack with the key because a key can only be used once
            stack.decrement(1)
        }

        return ActionResult.SUCCESS
    }
}