package com.redgrapefruit.mythicaltowers.common.item.shield;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * A lightweight shield with less max use time, but gives you speed
 */
public class LightweightShieldItem extends CustomShieldItem {
    public LightweightShieldItem(int maxUseTime) {
        super(maxUseTime);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        // The effect is restricted to players-only
        if (!(entity instanceof PlayerEntity)) return;

        ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(
                StatusEffects.SPEED,
                60,
                0
        ));
    }
}
