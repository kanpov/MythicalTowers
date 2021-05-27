package com.redgrapefruit.mythicaltowers.common.armor

import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

/**
 * Custom boots with special effects when put on
 */
class BootsItem(
    material: ArmorMaterial?,
    /**
     * The [StatusEffect] applied when put on
     */
    val effect: StatusEffect,
    /**
     * The amplifier of the effect
     */
    val amplifier: Int
) : ArmorItem(material, EquipmentSlot.FEET, Settings().group(MythicalItemGroups.ARMOR))