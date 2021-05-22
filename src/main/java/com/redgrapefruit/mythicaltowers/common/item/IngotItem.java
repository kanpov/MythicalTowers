package com.redgrapefruit.mythicaltowers.common.item;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.item.Item;

/**
 * A ingot of a certain custom material
 */
public class IngotItem extends Item {
    public IngotItem() {
        super(new Item.Settings().group(MythicalItemGroups.INGOTS));
    }
}
