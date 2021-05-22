package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.init.names.ItemNames;
import com.redgrapefruit.mythicaltowers.common.item.AmuletItem;
import com.redgrapefruit.mythicaltowers.common.item.OrbItem;
import net.minecraft.entity.effect.StatusEffects;
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
    public static final AmuletItem GREEN_AMULET = new AmuletItem(StatusEffects.JUMP_BOOST, 1);

    public static void init() {
        register(ItemNames.GREEN_ORB, GREEN_ORB);
        register(ItemNames.GREEN_AMULET, GREEN_AMULET);
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
