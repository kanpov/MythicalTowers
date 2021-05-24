package com.redgrapefruit.mythicaltowers.common.block.trap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A fake block disappears once stepped on
 */
public class FakeBlock extends Block {
    // Fake blocks inherit settings from their real parents
    public FakeBlock(Settings parentSettings) {
        super(parentSettings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        super.onSteppedOn(world, pos, entity);

        world.removeBlock(pos, false);
    }
}
