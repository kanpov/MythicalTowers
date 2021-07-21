package com.redgrapefruit.mythicaltowers.registry

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

/**
 * Stores all the mod's [ArmorMaterial]s in a single place.<br></br>
 * Is an implementation of an [ArmorMaterial] on its own
 */
enum class ArmorMaterialRegistry(
    private val durabilityValues: IntArray,
    private val protectionValues: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    repairIngredient: Item,
    private val armorName: String,
    private val toughness: Float,
    private val knockbackResistance: Float
) : ArmorMaterial {

    GREEN(
        intArrayOf(300, 350, 400, 350),
        intArrayOf(4, 5, 6, 5),
        17,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.GREEN_INGOT,
        "green",
        4.0f,
        0.2f
    ),
    YELLOW(
        intArrayOf(600, 650, 700, 650),
        intArrayOf(7, 8, 9, 8),
        20,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.YELLOW_INGOT,
        "yellow",
        6.0f,
        0.3f
    ),
    ORANGE(
        intArrayOf(900, 950, 1000, 950),
        intArrayOf(10, 11, 12, 11),
        23,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.ORANGE_INGOT,
        "orange",
        8.0f,
        0.4f
    ),
    RED(
        intArrayOf(1200, 1250, 1300, 1250),
        intArrayOf(13, 14, 15, 14),
        26,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.RED_INGOT,
        "red",
        10.0f,
        0.5f
    ),
    BLUE(
        intArrayOf(1500, 1550, 1600, 1550),
        intArrayOf(16, 17, 18, 17),
        29,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.BLUE_INGOT,
        "blue",
        12.0f,
        0.6f
    ),
    PURPLE(
        intArrayOf(1800, 1850, 1900, 1850),
        intArrayOf(19, 20, 21, 20),
        32,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.PURPLE_INGOT,
        "purple",
        14.0f,
        0.7f
    ),
    GRAY(
        intArrayOf(2100, 2150, 2200, 2150),
        intArrayOf(22, 23, 24, 23),
        35,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.GRAY_INGOT,
        "gray",
        16.0f,
        0.8f
    ),
    BLACK(
        intArrayOf(2400, 2450, 2500, 2450),
        intArrayOf(25, 26, 27, 26),
        38,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.BLACK_INGOT,
        "black",
        18.0f,
        0.9f
    );

    private val repairIngredient: Ingredient = Ingredient.ofItems(repairIngredient)

    override fun getDurability(slot: EquipmentSlot): Int {
        return durabilityValues[slot.entitySlotId]
    }

    override fun getProtectionAmount(slot: EquipmentSlot): Int {
        return protectionValues[slot.entitySlotId]
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return equipSound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient
    }

    override fun getName(): String {
        return armorName
    }

    override fun getToughness(): Float {
        return toughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }
}