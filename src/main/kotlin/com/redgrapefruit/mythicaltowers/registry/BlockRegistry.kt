package com.redgrapefruit.mythicaltowers.registry

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.GROUP
import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.UNBREAKABLE
import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.block.*
import com.redgrapefruit.mythicaltowers.block.entity.GateBlockEntity
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

    val GREEN_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val GREEN_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val YELLOW_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val YELLOW_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val ORANGE_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val ORANGE_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val RED_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val RED_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val BLUE_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val BLUE_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val PURPLE_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val PURPLE_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val GRAY_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val GRAY_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)
    val BLACK_UP_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.UP)
    val BLACK_DOWN_LIFT = LiftBlock(UNBREAKABLE, LiftDestination.DOWN)

    // Traps
    val GREEN_TNT = GreenTntBlock(UNBREAKABLE)
    val YELLOW_TNT = YellowTntBlock(UNBREAKABLE)
    val ORANGE_TNT = OrangeTntBlock(UNBREAKABLE)
    val RED_TNT = RedTntBlock(UNBREAKABLE)
    val BLUE_TNT = BlueTntBlock(UNBREAKABLE)
    val PURPLE_TNT = PurpleTntBlock(UNBREAKABLE)
    val GRAY_TNT = GrayTntBlock(UNBREAKABLE)
    val BLACK_TNT = BlackTntBlock(UNBREAKABLE)

    val GREEN_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 2.0)
    val YELLOW_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 2.25)
    val ORANGE_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 2.5)
    val RED_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 2.75)
    val BLUE_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 3.0)
    val PURPLE_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 3.25)
    val GRAY_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 3.5)
    val BLACK_JUMP_PAD = JumpPadBlock(UNBREAKABLE, 3.75)

    val GREEN_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val YELLOW_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val ORANGE_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val RED_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val BLUE_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val PURPLE_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val GRAY_FIRE_STATION = FireStationBlock(UNBREAKABLE)
    val BLACK_FIRE_STATION = FireStationBlock(UNBREAKABLE)

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

        register("green_up_lift", GREEN_UP_LIFT)
        register("green_down_lift", GREEN_DOWN_LIFT)
        register("yellow_up_lift", YELLOW_UP_LIFT)
        register("yellow_down_lift", YELLOW_DOWN_LIFT)
        register("orange_up_lift", ORANGE_UP_LIFT)
        register("orange_down_lift", ORANGE_DOWN_LIFT)
        register("red_up_lift", RED_UP_LIFT)
        register("red_down_lift", RED_DOWN_LIFT)
        register("blue_up_lift", BLUE_UP_LIFT)
        register("blue_down_lift", BLUE_DOWN_LIFT)
        register("purple_up_lift", PURPLE_UP_LIFT)
        register("purple_down_lift", PURPLE_DOWN_LIFT)
        register("gray_up_lift", GRAY_UP_LIFT)
        register("gray_down_lift", GRAY_DOWN_LIFT)
        register("black_up_lift", BLACK_UP_LIFT)
        register("black_down_lift", BLACK_DOWN_LIFT)

        register("green_tnt", GREEN_TNT)
        register("yellow_tnt", YELLOW_TNT)
        register("orange_tnt", ORANGE_TNT)
        register("red_tnt", RED_TNT)
        register("blue_tnt", BLUE_TNT)
        register("purple_tnt", PURPLE_TNT)
        register("gray_tnt", GRAY_TNT)
        register("black_tnt", BLACK_TNT)

        register("green_jump_pad", GREEN_JUMP_PAD)
        register("yellow_jump_pad", YELLOW_JUMP_PAD)
        register("orange_jump_pad", ORANGE_JUMP_PAD)
        register("red_jump_pad", RED_JUMP_PAD)
        register("blue_jump_pad", BLUE_JUMP_PAD)
        register("purple_jump_pad", PURPLE_JUMP_PAD)
        register("gray_jump_pad", GRAY_JUMP_PAD)
        register("black_jump_pad", BLACK_JUMP_PAD)

        register("green_fire_station", GREEN_FIRE_STATION)
        register("yellow_fire_station", YELLOW_FIRE_STATION)
        register("orange_fire_station", ORANGE_FIRE_STATION)
        register("red_fire_station", RED_FIRE_STATION)
        register("blue_fire_station", BLUE_FIRE_STATION)
        register("purple_fire_station", PURPLE_FIRE_STATION)
        register("gray_fire_station", GRAY_FIRE_STATION)
        register("black_fire_station", BLACK_FIRE_STATION)

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
        Registry.register(Registry.ITEM, idOf(name), BlockItem(block, Item.Settings().group(GROUP)))
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