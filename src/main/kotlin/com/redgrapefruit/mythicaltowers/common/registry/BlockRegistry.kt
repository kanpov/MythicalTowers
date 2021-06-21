package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.blockSettings
import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.block.building.CustomBricksBlock
import com.redgrapefruit.mythicaltowers.common.block.building.PureMaterialBlock
import com.redgrapefruit.mythicaltowers.common.block.trap.FireStationBlock
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
@Suppress("MemberVisibilityCanBePrivate")
object BlockRegistry {

    // Building
    val PURE_GREEN_BLOCK = PureMaterialBlock(blockSettings(3.0f))
    val PURE_YELLOW_BLOCK = PureMaterialBlock(blockSettings(4.5f))
    val PURE_ORANGE_BLOCK = PureMaterialBlock(blockSettings(6.0f))
    val PURE_RED_BLOCK = PureMaterialBlock(blockSettings(7.5f))
    val PURE_BLUE_BLOCK = PureMaterialBlock(blockSettings(9.0f))
    val PURE_PURPLE_BLOCK = PureMaterialBlock(blockSettings(10.5f))
    val PURE_GRAY_BLOCK = PureMaterialBlock(blockSettings(12.0f))
    val PURE_BLACK_BLOCK = PureMaterialBlock(blockSettings(13.5f))

    val GREEN_BRICKS = CustomBricksBlock(blockSettings(3.5f))
    val YELLOW_BRICKS = CustomBricksBlock(blockSettings(4.5f))

    // Traps
    val GREEN_TNT = GreenTntBlock()

    val GREEN_JUMP_PAD = JumpPadBlock(1.5, 1.1)

    val GREEN_FIRE_STATION = FireStationBlock(blockSettings(2.0f))

    fun init() {
        register("pure_green_block", PURE_GREEN_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_yellow_block", PURE_YELLOW_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_orange_block", PURE_ORANGE_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_red_block", PURE_RED_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_blue_block", PURE_BLUE_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_purple_block", PURE_PURPLE_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_gray_block", PURE_GRAY_BLOCK, ItemGroupRegistry.BUILDING)
        register("pure_black_block", PURE_BLACK_BLOCK, ItemGroupRegistry.BUILDING)

        register("green_bricks", GREEN_BRICKS, ItemGroupRegistry.BUILDING)
        register("yellow_bricks", YELLOW_BRICKS, ItemGroupRegistry.BUILDING)

        register("green_tnt", GREEN_TNT, ItemGroupRegistry.TRAPS)

        register("green_jump_pad", GREEN_JUMP_PAD, ItemGroupRegistry.TRAPS)

        register("green_fire_station", GREEN_FIRE_STATION, ItemGroupRegistry.TRAPS)
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