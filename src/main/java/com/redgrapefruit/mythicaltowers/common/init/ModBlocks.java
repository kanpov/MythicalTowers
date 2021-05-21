package com.redgrapefruit.mythicaltowers.common.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's blocks
 */
public class ModBlocks {
    public static void init() {

    }

    /**
     * Registers a block and a {@link BlockItem}
     * @param name Block name
     * @param block Block instance
     * @param group {@link ItemGroup} for the {@link BlockItem}
     */
    private static void register(String name, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, idOf(name), block);
        Registry.register(Registry.ITEM, idOf(name), new BlockItem(block, new Item.Settings().group(group)));
    }
}
