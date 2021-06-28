package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers
import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.UNBREAKABLE
import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.block.*
import com.redgrapefruit.mythicaltowers.common.block.entity.GateBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

/**
 * Stores and registers mod's blocks
 */
@Suppress("MemberVisibilityCanBePrivate")
object BlockRegistry {

    // Building
    val PURE_GREEN_BLOCK = Block(UNBREAKABLE)
    val PURE_YELLOW_BLOCK = Block(UNBREAKABLE)
    val PURE_ORANGE_BLOCK = Block(UNBREAKABLE)
    val PURE_RED_BLOCK = Block(UNBREAKABLE)
    val PURE_BLUE_BLOCK = Block(UNBREAKABLE)
    val PURE_PURPLE_BLOCK = Block(UNBREAKABLE)
    val PURE_GRAY_BLOCK = Block(UNBREAKABLE)
    val PURE_BLACK_BLOCK = Block(UNBREAKABLE)

    val GREEN_BRICKS = Block(UNBREAKABLE)
    val YELLOW_BRICKS = Block(UNBREAKABLE)
    val ORANGE_BRICKS = Block(UNBREAKABLE)
    val RED_BRICKS = Block(UNBREAKABLE)
    val BLUE_BRICKS = Block(UNBREAKABLE)
    val PURPLE_BRICKS = Block(UNBREAKABLE)
    val GRAY_BRICKS = Block(UNBREAKABLE)
    val BLACK_BRICKS = Block(UNBREAKABLE)

    // Transportation
    val GREEN_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.GREEN_KEY_1, GateLevel.LEVEL_1)
    val GREEN_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.GREEN_KEY_2, GateLevel.LEVEL_2)
    val GREEN_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.GREEN_KEY_3, GateLevel.LEVEL_3)
    val YELLOW_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.YELLOW_KEY_1, GateLevel.LEVEL_1)
    val YELLOW_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.YELLOW_KEY_2, GateLevel.LEVEL_2)
    val YELLOW_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.YELLOW_KEY_3, GateLevel.LEVEL_3)
    val ORANGE_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.ORANGE_KEY_1, GateLevel.LEVEL_1)
    val ORANGE_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.ORANGE_KEY_2, GateLevel.LEVEL_2)
    val ORANGE_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.ORANGE_KEY_3, GateLevel.LEVEL_3)
    val RED_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.RED_KEY_1, GateLevel.LEVEL_1)
    val RED_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.RED_KEY_2, GateLevel.LEVEL_2)
    val RED_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.RED_KEY_3, GateLevel.LEVEL_3)
    val BLUE_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.BLUE_KEY_1, GateLevel.LEVEL_1)
    val BLUE_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.BLUE_KEY_2, GateLevel.LEVEL_2)
    val BLUE_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.BLUE_KEY_3, GateLevel.LEVEL_3)
    val PURPLE_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.PURPLE_KEY_1, GateLevel.LEVEL_1)
    val PURPLE_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.PURPLE_KEY_2, GateLevel.LEVEL_2)
    val PURPLE_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.PURPLE_KEY_3, GateLevel.LEVEL_3)
    val GRAY_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.GRAY_KEY_1, GateLevel.LEVEL_1)
    val GRAY_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.GRAY_KEY_2, GateLevel.LEVEL_2)
    val GRAY_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.GRAY_KEY_3, GateLevel.LEVEL_3)
    val BLACK_GATE_1 = GateBlock(UNBREAKABLE, ItemRegistry.BLACK_KEY_1, GateLevel.LEVEL_1)
    val BLACK_GATE_2 = GateBlock(UNBREAKABLE, ItemRegistry.BLACK_KEY_2, GateLevel.LEVEL_2)
    val BLACK_GATE_3 = GateBlock(UNBREAKABLE, ItemRegistry.BLACK_KEY_3, GateLevel.LEVEL_3)

    // Traps
    val GREEN_TNT = GreenTntBlock(UNBREAKABLE)
    val YELLOW_TNT = YellowTntBlock(UNBREAKABLE)

    val GREEN_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 1.5, 1.1)

    val GREEN_FIRE_STATION = FireStationBlock(UNBREAKABLE)

    // BlockEntities
    val GATE_BLOCK_ENTITY: BlockEntityType<GateBlockEntity> = FabricBlockEntityTypeBuilder.create(::GateBlockEntity,
        GREEN_GATE_1, GREEN_GATE_2, GREEN_GATE_3,
        YELLOW_GATE_1, YELLOW_GATE_2, YELLOW_GATE_3,
        ORANGE_GATE_1, ORANGE_GATE_2, ORANGE_GATE_3,
        RED_GATE_1, RED_GATE_2, RED_GATE_3,
        BLUE_GATE_1, BLUE_GATE_2, BLUE_GATE_3,
        PURPLE_GATE_1, PURPLE_GATE_2, PURPLE_GATE_3,
        GRAY_GATE_1, GRAY_GATE_2, GRAY_GATE_3,
        BLACK_GATE_1, BLACK_GATE_2, BLACK_GATE_3).build()

    fun init() {
        register("pure_green_block", PURE_GREEN_BLOCK)
        register("pure_yellow_block", PURE_YELLOW_BLOCK)
        register("pure_orange_block", PURE_ORANGE_BLOCK)
        register("pure_red_block", PURE_RED_BLOCK)
        register("pure_blue_block", PURE_BLUE_BLOCK)
        register("pure_purple_block", PURE_PURPLE_BLOCK)
        register("pure_gray_block", PURE_GRAY_BLOCK)
        register("pure_black_block", PURE_BLACK_BLOCK)

        register("green_bricks", GREEN_BRICKS)
        register("yellow_bricks", YELLOW_BRICKS)
        register("orange_bricks", ORANGE_BRICKS)
        register("red_bricks", RED_BRICKS)
        register("blue_bricks", BLUE_BRICKS)
        register("purple_bricks", PURPLE_BRICKS)
        register("gray_bricks", GRAY_BRICKS)
        register("black_bricks", BLACK_BRICKS)

        register("green_gate_1", GREEN_GATE_1)
        register("green_gate_2", GREEN_GATE_2)
        register("green_gate_3", GREEN_GATE_3)
        register("yellow_gate_1", YELLOW_GATE_1)
        register("yellow_gate_2", YELLOW_GATE_2)
        register("yellow_gate_3", YELLOW_GATE_3)
        register("orange_gate_1", ORANGE_GATE_1)
        register("orange_gate_2", ORANGE_GATE_2)
        register("orange_gate_3", ORANGE_GATE_3)
        register("red_gate_1", RED_GATE_1)
        register("red_gate_2", RED_GATE_2)
        register("red_gate_3", RED_GATE_3)
        register("blue_gate_1", BLUE_GATE_1)
        register("blue_gate_2", BLUE_GATE_2)
        register("blue_gate_3", BLUE_GATE_3)
        register("purple_gate_1", PURPLE_GATE_1)
        register("purple_gate_2", PURPLE_GATE_2)
        register("purple_gate_3", PURPLE_GATE_3)
        register("gray_gate_1", GRAY_GATE_1)
        register("gray_gate_2", GRAY_GATE_2)
        register("gray_gate_3", GRAY_GATE_3)
        register("black_gate_1", BLACK_GATE_1)
        register("black_gate_2", BLACK_GATE_2)
        register("black_gate_3", BLACK_GATE_3)

        register("green_tnt", GREEN_TNT)
        register("yellow_tnt", YELLOW_TNT)

        register("green_jump_pad", GREEN_JUMP_PAD)

        register("green_fire_station", GREEN_FIRE_STATION)

        register("gate_block_entity", GATE_BLOCK_ENTITY)
    }

    /**
     * Registers a block and a [BlockItem]
     *
     * @param name  Block name
     * @param block Block instance
     */
    private fun register(name: String, block: Block) {
        Registry.register(Registry.BLOCK, idOf(name), block)
        Registry.register(Registry.ITEM, idOf(name), BlockItem(block, Item.Settings().group(MythicalTowers.GROUP)))
    }

    /**
     * Registers a [BlockEntity]
     *
     * @param name Block name
     * @param type Constructed [BlockEntityType]
     */
    private fun <TBlockEntity> register(name: String, type: BlockEntityType<TBlockEntity>) where TBlockEntity : BlockEntity {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, idOf(name), type)
    }
}