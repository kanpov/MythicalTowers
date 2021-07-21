package com.redgrapefruit.mythicaltowers.block

import com.redgrapefruit.mythicaltowers.block.entity.GateBlockEntity
import com.redgrapefruit.mythicaltowers.block.entity.TICK_COUNTER_MAX
import com.redgrapefruit.mythicaltowers.item.KeyItem
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

/**
 * Custom gate that allows you to enter the tower with a [KeyItem] to open.
 *
 * Also has an opening animation.
 */
class GateBlock(settings: Settings, private val key: KeyItem, private val level: GateLevel) : Block(settings), BlockEntityProvider {
    /**
     * The opening animation blockstate
     */
    private lateinit var openProperty: IntProperty

    init {
        createOpenPropertyIfNull()
        defaultState = stateManager.defaultState.with(openProperty, 0)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return GateBlockEntity(pos, state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)

        createOpenPropertyIfNull()
        builder.add(openProperty)
    }

    override fun <T : BlockEntity> getTicker(
        world: World,
        stateOuter: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T> {
        return BlockEntityTicker { _, pos, state, rawEntity ->
            // Just increment the counter
            val entity = rawEntity as GateBlockEntity
            entity.counter++

            // If the counter has gone off, move onto the next blockstate and reset the counter
            if (entity.isDisappearing && entity.counter >= TICK_COUNTER_MAX) {
                val currentOpen = state.get(openProperty) as Int

                if (currentOpen < 6) {
                    world.setBlockState(pos, state.with(openProperty, currentOpen + 1))
                    entity.counter = 0
                } else {
                    // If the animation has been fully passed, remove the door
                    world.removeBlock(pos, false)
                }
            }
        }
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        // There are 3 levels with 6 opening states each, in total - 18 different VoxelShapes
        when (level) {
            GateLevel.LEVEL_1 -> {
                when (state.get(openProperty)) {
                    0 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 14.0, 10.0),
                        createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    1 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 12.0, 10.0),
                        createCuboidShape(0.0, 12.0, 0.0, 16.0, 14.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    2 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 10.0, 10.0),
                        createCuboidShape(0.0, 10.0, 0.0, 16.0, 12.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    3 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 8.0, 10.0),
                        createCuboidShape(0.0, 8.0, 0.0, 16.0, 10.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    4 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 6.0, 10.0),
                        createCuboidShape(0.0, 6.0, 0.0, 16.0, 8.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    5 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 2.0, 6.0, 10.0, 4.0, 10.0),
                        createCuboidShape(0.0, 4.0, 0.0, 16.0, 6.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                    else -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(6.0, 0.0, 6.0, 10.0, 1.0, 10.0),
                        createCuboidShape(0.0, 2.0, 0.0, 16.0, 4.0, 16.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR); }
                }
            }
            GateLevel.LEVEL_2 -> {
                when (state.get(openProperty)) {
                    0 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 14.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 14.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    1 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 12.0, 0.0, 16.0, 14.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 12.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 12.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    2 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 10.0, 0.0, 16.0, 12.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 10.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 10.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    3 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 8.0, 0.0, 16.0, 10.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 8.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 8.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    4 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 6.0, 0.0, 16.0, 8.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 6.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 6.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    5 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 4.0, 0.0, 16.0, 6.0, 16.0),
                        createCuboidShape(9.0, 2.0, 9.0, 13.0, 4.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 4.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    else -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 2.0, 0.0, 16.0, 4.0, 16.0),
                        createCuboidShape(9.0, 0.0, 9.0, 13.0, 1.0, 13.0),
                        createCuboidShape(3.0, 0.0, 3.0, 7.0, 1.0, 7.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                }
            }
            else -> {
                when (state.get(openProperty)) {
                    0 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 14.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 14.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 14.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    1 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 12.0, 0.0, 16.0, 14.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 12.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 12.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 12.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    2 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 10.0, 0.0, 16.0, 12.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 10.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 10.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 10.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    3 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 8.0, 0.0, 16.0, 10.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 8.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 8.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 8.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    4 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 6.0, 0.0, 16.0, 8.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 6.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 6.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 6.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    5 -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 4.0, 0.0, 16.0, 6.0, 16.0),
                        createCuboidShape(3.0, 2.0, 9.0, 7.0, 4.0, 13.0),
                        createCuboidShape(3.0, 2.0, 3.0, 7.0, 4.0, 7.0),
                        createCuboidShape(9.0, 2.0, 6.0, 13.0, 4.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                    else -> return listOf(
                        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                        createCuboidShape(0.0, 2.0, 0.0, 16.0, 4.0, 16.0),
                        createCuboidShape(3.0, 0.0, 9.0, 7.0, 1.0, 13.0),
                        createCuboidShape(3.0, 0.0, 3.0, 7.0, 1.0, 7.0),
                        createCuboidShape(9.0, 0.0, 6.0, 13.0, 1.0, 10.0)
                    ).reduce { v1, v2 -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR) }
                }
            }
        }
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val stack = player.getStackInHand(hand)
        val entity = world.getBlockEntity(pos) as GateBlockEntity

        // Check if the door has been right-clicked with the linked key
        entity.isDisappearing = stack.item == key

        return ActionResult.SUCCESS
    }

    // Hack
    private fun createOpenPropertyIfNull() {
        if (!::openProperty.isInitialized)
            openProperty = IntProperty.of("open", 0, 6)
    }
}

/**
 * Determines the level of security of the gate. Used for generating [VoxelShape]s
 */
enum class GateLevel {
    LEVEL_1,
    LEVEL_2,
    LEVEL_3
}
