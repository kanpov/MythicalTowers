package com.redgrapefruit.mythicaltowers.armor

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.GROUP
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

/**
 * A custom chestplate with special effects when on
 */
class ChestplateItem(
    material: ArmorMaterial,
    val effect: StatusEffect,
    val amplifier: Int
) : ArmorItem(material, EquipmentSlot.CHEST, Settings().group(GROUP))