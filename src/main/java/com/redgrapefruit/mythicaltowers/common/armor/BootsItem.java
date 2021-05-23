package com.redgrapefruit.mythicaltowers.common.armor;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

/**
 * Custom boots with special effects when put on
 */
public class BootsItem extends ArmorItem {
    public BootsItem(ArmorMaterial material) {
        super(material, EquipmentSlot.FEET, new Item.Settings().group(MythicalItemGroups.ARMOR));
    }
}
