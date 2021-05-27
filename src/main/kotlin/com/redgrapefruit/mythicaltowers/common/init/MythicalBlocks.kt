package com.redgrapefruit.mythicaltowers.common.init

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

/**
 * Stores and registers mod's blocks
 */
object MythicalBlocks {
    fun init() {}

    /**
     * Registers a block and a [BlockItem]
     *
     * @param name  Block name
     * @param block Block instance
     * @param group [ItemGroup] for the [BlockItem]
     */
    private fun register(name: String, block: Block, group: ItemGroup) {
        Registry.register(Registry.BLOCK, idOf(name), block)
        Registry.register(Registry.ITEM, idOf(name), BlockItem(block, Item.Settings().group(group)))
    }
}