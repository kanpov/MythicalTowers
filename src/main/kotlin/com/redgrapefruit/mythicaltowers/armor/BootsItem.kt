package com.redgrapefruit.mythicaltowers.armor

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.GROUP
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

/**
 * Custom boots with special effects when put on
 */
class BootsItem(
    material: ArmorMaterial,
    val effect: StatusEffect,
    val amplifier: Int
) : ArmorItem(material, EquipmentSlot.FEET, Settings().group(GROUP))