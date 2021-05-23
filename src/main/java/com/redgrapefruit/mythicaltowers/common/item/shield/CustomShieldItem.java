package com.redgrapefruit.mythicaltowers.common.item.shield;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

/**
 * A standard shield added by the mod
 */
public class CustomShieldItem extends ShieldItem {
    /**
     * The max time to hold a shield
     */
    private final int maxUseTime;

    public CustomShieldItem(int maxUseTime) {
        super(new Item.Settings().group(MythicalItemGroups.SHIELDS));

        this.maxUseTime = maxUseTime;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return maxUseTime;
    }
}
