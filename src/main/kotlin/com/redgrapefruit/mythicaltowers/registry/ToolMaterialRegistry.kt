package com.redgrapefruit.mythicaltowers.registry

import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

/**
 * Stores all the mod's [ToolMaterial]s in a single place.<br></br>
 * Is an implementation of a [ToolMaterial] on its own
 */
enum class ToolMaterialRegistry(
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
        ItemRegistry.GREEN_INGOT
    ),
    YELLOW(
        2900,
        13.0f,
        8.0f,
        4,
        19,
        ItemRegistry.YELLOW_INGOT
    ),
    ORANGE(
        3400,
        15.0f,
        10.0f,
        4,
        21,
        ItemRegistry.ORANGE_INGOT
    ),
    RED(
        3900,
        17.0f,
        12.0f,
        4,
        23,
        ItemRegistry.RED_INGOT
    ),
    BLUE(
        4500,
        20.0f,
        15.0f,
        4,
        26,
        ItemRegistry.BLUE_INGOT
    ),
    PURPLE(
        5100,
        23.0f,
        18.0f,
        4,
        29,
        ItemRegistry.PURPLE_INGOT
    ),
    GRAY(
        5700,
        26.0f,
        21.0f,
        4,
        32,
        ItemRegistry.GRAY_INGOT
    ),
    BLACK(
        7000,
        30.0f,
        25.0f,
        4,
        40,
        ItemRegistry.BLACK_INGOT
    );

    private val repairIngredient: Ingredient = Ingredient.ofItems(repairIngredient)

    override fun getDurability(): Int = durability

    override fun getMiningSpeedMultiplier(): Float = miningSpeedMultiplier

    override fun getAttackDamage(): Float = attackDamage

    override fun getMiningLevel(): Int = miningLevel

    override fun getEnchantability(): Int = enchantability

    override fun getRepairIngredient(): Ingredient = repairIngredient
}