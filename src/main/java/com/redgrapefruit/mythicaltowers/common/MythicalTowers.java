package com.redgrapefruit.mythicaltowers.common;

import com.redgrapefruit.mythicaltowers.common.init.ModBlocks;
import com.redgrapefruit.mythicaltowers.common.init.ModItemGroups;
import com.redgrapefruit.mythicaltowers.common.init.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import java.util.Random;

public class MythicalTowers implements ModInitializer {
    public static final String MOD_ID = "mythicaltowers";

    public static final Random RANDOM = new Random();

    /**
     * Returns the {@link Identifier} of given name
     *
     * @param name Name
     * @return Generated {@link Identifier}
     */
    public static Identifier idOf(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        ModItemGroups.init();
        ModItems.init();
        ModBlocks.init();
    }
}
