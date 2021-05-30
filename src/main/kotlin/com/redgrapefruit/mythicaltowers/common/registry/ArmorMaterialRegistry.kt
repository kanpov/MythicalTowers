package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.registry.names.ArmorNames
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
        intArrayOf(300, 350, 400, 350), intArrayOf(4, 5, 6, 5),
        17,
        SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
        ItemRegistry.GREEN_INGOT,
        ArmorNames.GREEN_ARMOR,
        4.0f,
        0.2f
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