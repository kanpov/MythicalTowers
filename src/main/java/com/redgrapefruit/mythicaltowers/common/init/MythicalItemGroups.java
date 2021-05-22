package com.redgrapefruit.mythicaltowers.common.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's {@link ItemGroup}s
 */
public class MythicalItemGroups {
    public static void init() {

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
