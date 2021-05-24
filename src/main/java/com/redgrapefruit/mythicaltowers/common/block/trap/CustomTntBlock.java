package com.redgrapefruit.mythicaltowers.common.block.trap;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;

/**
 * Custom TNT trap
 */
public class CustomTntBlock extends TntBlock {
    public CustomTntBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TNT));
    }
}
