package com.redgrapefruit.mythicaltowers.common.registry

import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

/**
 * Stores all the mod's [ToolMaterial]s in a single place.<br></br>
 * Is an implementation of a [ToolMaterial] on its own
 */
enum class MythicalToolMaterials(
    private val durability: Int,
    private val miningSpeedMultiplier: Float,
    private val attackDamage: Float,
    private val miningLevel: Int,
    private val enchantability: Int,
    repairIngredient: Item?
) : ToolMaterial {
    GREEN(
        2400,
        11.0f,
        6.0f,
        4,
        17,
        MythicalItems.GREEN_INGOT
    );

    private val repairIngredient: Ingredient = Ingredient.ofItems(repairIngredient)

    override fun getDurability(): Int = durability

    override fun getMiningSpeedMultiplier(): Float = miningSpeedMultiplier

    override fun getAttackDamage(): Float = attackDamage

    override fun getMiningLevel(): Int = miningLevel

    override fun getEnchantability(): Int = enchantability

    override fun getRepairIngredient(): Ingredient = repairIngredient
}