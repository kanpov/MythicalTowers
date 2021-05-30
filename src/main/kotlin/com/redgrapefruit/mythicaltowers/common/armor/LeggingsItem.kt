package com.redgrapefruit.mythicaltowers.common.armor

import com.redgrapefruit.mythicaltowers.common.registry.ItemGroupRegistry
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

/**
 * Custom leggings with special effects when put on
 */
class LeggingsItem(
    material: ArmorMaterial,
    val effect: StatusEffect,
    val amplifier: Int
) : ArmorItem(material, EquipmentSlot.LEGS, Settings().group(ItemGroupRegistry.ARMOR))