package com.redgrapefruit.mythicaltowers.common.armor;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

/**
 * A custom helmet with special effects when on
 */
public class HelmetItem extends ArmorItem {
    public HelmetItem(ArmorMaterial material) {
        super(material, EquipmentSlot.HEAD, new Item.Settings().group(MythicalItemGroups.ARMOR));
    }
}
