package com.redgrapefruit.mythicaltowers.common.armor

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

/**
 * A custom chestplate with special effects when on
 */
class ChestplateItem(
    material: ArmorMaterial?,
    /**
     * The [StatusEffect] applied when put on
     */
    val effect: StatusEffect,
    /**
     * The amplifier of the effect
     */
    val amplifier: Int
) : ArmorItem(material, EquipmentSlot.CHEST, Settings().group(MythicalItemGroups.ARMOR))