package com.redgrapefruit.mythicaltowers.common.init;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's items
 */
public class ModItems {
    public static void init() {

    }

    /**
     * Registers an item
     *
     * @param name Item name
     * @param item Item instance
     */
    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, idOf(name), item);
    }
}
