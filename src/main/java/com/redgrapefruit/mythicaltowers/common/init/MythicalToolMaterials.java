package com.redgrapefruit.mythicaltowers.common.init;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

/**
 * Stores all the mod's {@link ToolMaterial}s in a single place.<br>
 * Is an implementation of a {@link ToolMaterial} on its own
 */
public enum MythicalToolMaterials implements ToolMaterial {
    GREEN(
            2400,
            11.0F,
            6.0F,
            4,
            17,
            MythicalItems.GREEN_INGOT
    );

    private final int durability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int miningLevel;
    private final int enchantability;
    private final Ingredient repairIngredient;

    MythicalToolMaterials(
            int durability,
            float miningSpeedMultiplier,
            float attackDamage,
            int miningLevel,
            int enchantability,
            Item repairIngredient
    ) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.repairIngredient = Ingredient.ofItems(repairIngredient);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
