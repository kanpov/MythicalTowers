package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.init.names.ItemNames;
import com.redgrapefruit.mythicaltowers.common.item.OrbItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's items
 */
public class MythicalItems {
    // Orbs
    public static final OrbItem GREEN_ORB = new OrbItem(MythicalEffectConfigs.GREEN_ORB);

    // Amulets

    public static void init() {
        register(ItemNames.GREEN_ORB, GREEN_ORB);
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
