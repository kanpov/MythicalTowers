package com.redgrapefruit.mythicaltowers.common.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's {@link ItemGroup}s
 */
public class MythicalItemGroups {
    // Items
    public static ItemGroup ORBS;
    public static ItemGroup AMULETS;
    public static ItemGroup SUPERFOOD;
    public static ItemGroup SHIELDS;
    public static ItemGroup TOOLS;
    public static ItemGroup WEAPONS;
    public static ItemGroup ARMOR;

    // Blocks
    public static ItemGroup BUILDING;
    public static ItemGroup TRAPS;
    public static ItemGroup UTILITY;

    public static void init() {
        // TODO: Replace placeholder icons (beds) with actual mod items once these items are in place

        ORBS = register("orbs", Items.GREEN_BED);
        AMULETS = register("amulets", Items.YELLOW_BED);
        SUPERFOOD = register("superfood", Items.ORANGE_BED);
        SHIELDS = register("shields", Items.RED_BED);
        TOOLS = register("tools", Items.BLUE_BED);
        WEAPONS = register("weapons", Items.PURPLE_BED);
        ARMOR = register("armor", Items.CYAN_BED);

        BUILDING = register("building", Items.BROWN_BED);
        TRAPS = register("traps", Items.GRAY_BED);
        UTILITY = register("utility", Items.BROWN_BED);
    }

    /**
     * Registers and builds an {@link ItemGroup}
     *
     * @param name {@link ItemGroup} name
     * @param icon {@link ItemGroup} icon item
     * @return Built {@link ItemGroup}
     */
    private static ItemGroup register(String name, Item icon) {
        return FabricItemGroupBuilder
                .create(idOf(name))
                .icon(() -> new ItemStack(icon))
                .build();
    }
}
