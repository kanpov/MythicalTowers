package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.block.trap.GreenTntBlock
import com.redgrapefruit.mythicaltowers.common.block.trap.JumpPadBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

/**
 * Stores and registers mod's blocks
 */
object BlockRegistry {
    // Traps
    val GREEN_TNT = GreenTntBlock()

    val GREEN_JUMP_PAD = JumpPadBlock(1.5, 1.1)

    fun init() {
        register("green_tnt", GREEN_TNT, ItemGroupRegistry.TRAPS)
        register("green_jump_pad", GREEN_JUMP_PAD, ItemGroupRegistry.TRAPS)
    }

    /**
     * Registers a block and a [BlockItem]
     *
     * @param name  Block name
     * @param block Block instance
     * @param group [ItemGroup] for the [BlockItem]
     */
    private fun register(name: String, block: Block, group: ItemGroup?) {
        Registry.register(Registry.BLOCK, idOf(name), block)
        Registry.register(Registry.ITEM, idOf(name), BlockItem(block, Item.Settings().group(group)))
    }
}