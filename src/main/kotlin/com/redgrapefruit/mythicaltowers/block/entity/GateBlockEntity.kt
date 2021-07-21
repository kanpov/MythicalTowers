package com.redgrapefruit.mythicaltowers.block.entity

import com.redgrapefruit.mythicaltowers.registry.BlockRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos

internal const val TICK_COUNTER_MAX: Int = 20

/**
 * Contains the info for the disappearing animation
 */
class GateBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(BlockRegistry.GATE_BLOCK_ENTITY, pos, state) {
    /**
     * Is the gate currently disappearing
     */
    var isDisappearing: Boolean = false

    /**
     * A utility tick counter
     */
    var counter: Int = 0

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        isDisappearing = nbt.getBoolean("Is Gate Disappearing")
        counter = nbt.getInt("Ticking Counter")
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        super.writeNbt(nbt)
        nbt.putBoolean("Is Gate Disappearing", isDisappearing)
        nbt.putInt("Ticking Counter", counter)
        return nbt
    }
}