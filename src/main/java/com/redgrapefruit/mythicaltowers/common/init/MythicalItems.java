package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.init.names.ItemNames;
import com.redgrapefruit.mythicaltowers.common.item.AmuletItem;
import com.redgrapefruit.mythicaltowers.common.item.IngotItem;
import com.redgrapefruit.mythicaltowers.common.item.OrbItem;
import com.redgrapefruit.mythicaltowers.common.tool.*;
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

    // Ingots
    public static final IngotItem GREEN_INGOT = new IngotItem();

    // Tools
    public static final CustomSwordItem GREEN_SWORD = new CustomSwordItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 3, -1.8f);
    public static final CustomPickaxeItem GREEN_PICKAXE = new CustomPickaxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 2, -2.3f);
    public static final CustomAxeItem GREEN_AXE = new CustomAxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 5, -3.1f);
    public static final CustomShovelItem GREEN_SHOVEL = new CustomShovelItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.5f);
    public static final CustomHoeItem GREEN_HOE = new CustomHoeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.25f);

    public static void init() {
        register(ItemNames.GREEN_ORB, GREEN_ORB);
        register(ItemNames.GREEN_AMULET, GREEN_AMULET);
        register(ItemNames.GREEN_INGOT, GREEN_INGOT);
        register(ItemNames.GREEN_SWORD, GREEN_SWORD);
        register(ItemNames.GREEN_PICKAXE, GREEN_PICKAXE);
        register(ItemNames.GREEN_AXE, GREEN_AXE);
        register(ItemNames.GREEN_SHOVEL, GREEN_SHOVEL);
        register(ItemNames.GREEN_HOE, GREEN_HOE);
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
