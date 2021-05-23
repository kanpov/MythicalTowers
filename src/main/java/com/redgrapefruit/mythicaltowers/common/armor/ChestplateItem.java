package com.redgrapefruit.mythicaltowers.common.armor;

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

/**
 * A custom chestplate with special effects when on
 */
public class ChestplateItem extends ArmorItem {
    public ChestplateItem(ArmorMaterial material) {
        super(material, EquipmentSlot.CHEST, new Item.Settings().group(MythicalItemGroups.ARMOR));
    }
}
