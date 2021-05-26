package com.redgrapefruit.mythicaltowers.common.block.trap;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;

/**
 * Custom TNT trap
 */
public abstract class AbstractCustomTntBlock extends TntBlock {
    public AbstractCustomTntBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TNT));
    }
}
