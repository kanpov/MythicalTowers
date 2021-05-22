package com.redgrapefruit.mythicaltowers.common.item;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine;
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * An amulet adds a passive effect everytime the amulet is inside of the player's inventory.<br>
 * After 3 seconds after the amulet leaves the inventory the effect disappears.<br>
 * Amulets don't use {@link EffectConfig}s, just constructor parameters.<br>
 * More info in wiki page 5
 */
public class AmuletItem extends Item {
    /**
     * The passive {@link StatusEffect} applied
     */
    private final StatusEffect effect;
    /**
     * The amplifier of this effect
     */
    private final int amplifier;

    public AmuletItem(StatusEffect effect, int amplifier) {
        super(new Item.Settings().group(MythicalItemGroups.AMULETS).maxCount(1));

        this.effect = effect;
        this.amplifier = amplifier;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        EffectEngine.onAmuletTicked(effect, amplifier, entity);
    }
}
