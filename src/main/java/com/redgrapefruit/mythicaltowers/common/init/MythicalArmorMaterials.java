package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.init.names.ArmorNames;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Stores all the mod's {@link ArmorMaterial}s in a single place.<br>
 * Is an implementation of an {@link ArmorMaterial} on its own
 */
public enum MythicalArmorMaterials implements ArmorMaterial {
    GREEN(
            new int[]{300, 350, 400, 350},
            new int[]{4, 5, 6, 5},
            17,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            MythicalItems.GREEN_INGOT,
            ArmorNames.GREEN_ARMOR,
            4.0F,
            0.2F
    );

    private final int[] durabilityValues;
    private final int[] protectionValues;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Ingredient repairIngredient;
    private final String name;
    private final float toughness;
    private final float knockbackResistance;

    MythicalArmorMaterials(
            int[] durabilityValues,
            int[] protectionValues,
            int enchantability,
            SoundEvent equipSound,
            Item repairIngredient,
            String name,
            float toughness,
            float knockbackResistance
    ) {
        this.durabilityValues = durabilityValues;
        this.protectionValues = protectionValues;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.repairIngredient = Ingredient.ofItems(repairIngredient);
        this.name = name;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return durabilityValues[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return protectionValues[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
