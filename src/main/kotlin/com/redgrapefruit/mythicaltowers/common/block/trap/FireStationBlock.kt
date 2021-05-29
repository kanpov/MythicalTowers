package com.redgrapefruit.mythicaltowers.common.block.trap

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Every time you step on a fire station, you'll be on fire
 */
class FireStationBlock(private val type: BlockEntityType<*>) : Block(FabricBlockSettings.of(Material.STONE).hardness(2.0f)) {
    override fun onSteppedOn(world: World, pos: BlockPos, entity: Entity) {
        super.onSteppedOn(world, pos, entity)
        // Replace the top block with fire
        world.setBlockState(pos.up(), Blocks.FIRE.defaultState)
    }
}