package com.redgrapefruit.mythicaltowers.common.armor;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

/**
 * Custom leggings with special effects when put on
 */
public class LeggingsItem extends ArmorItem {
    public LeggingsItem(ArmorMaterial material) {
        super(material, EquipmentSlot.LEGS, new Item.Settings().group(MythicalItemGroups.ARMOR));
    }
}
