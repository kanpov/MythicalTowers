package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.armor.BootsItem;
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem;
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem;
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem;
import com.redgrapefruit.mythicaltowers.common.util.ItemStackUtility;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin applies custom effects to the entity once the custom armor from the mod is put on.<br>
 * Also serializes and deserializes some internal data
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    // Tracked data about previous armor stacks
    @Unique
    private ItemStack previousHelmetStack;
    @Unique
    private ItemStack previousChestplateStack;
    @Unique
    private ItemStack previousLeggingsStack;
    @Unique
    private ItemStack previousBootsStack;

    @Shadow
    public abstract void applyStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    /**
     * The unmapped method_30122 is called everytime an armor piece is put on/off. Main logic block
     *
     * @param slot  The {@link EquipmentSlot} of the armor
     * @param stack The {@link ItemStack} of the armor
     * @param info  Mixin {@link CallbackInfo}
     */
    @Inject(method = "method_30122", at = @At("TAIL"))
    private void method_30122(EquipmentSlot slot, ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();

        // How handle_X_Armor methods work:
        // 1. Check equipment slot
        // 2. Get previous and current item in the slot
        // 3. If previous is air and new is mod's armor, apply the effect using createEnablingStatusEffectInstance
        // 4. If previous is mod's armor and new is air, disable the effect using createDisablingStatusEffectInstance
        // 5. Update the previous stack to current for the next operation
        // And that for each of the 4 equipment slots

        switch (slot) {
            case HEAD:
                handleHeadArmor(stack);
                break;
            case CHEST:
                handleChestArmor(stack);
                break;
            case LEGS:
                handleLegArmor(stack);
                break;
            case FEET:
                handleFeetArmor(stack);
                break;
        }
    }

    /**
     * Handles head armor slot
     *
     * @param stack New stack
     */
    private void handleHeadArmor(ItemStack stack) {
        Item previousHelmet = previousHelmetStack.getItem();
        Item currentHelmet = stack.getItem();

        if (previousHelmet instanceof AirBlockItem && currentHelmet instanceof HelmetItem) {
            HelmetItem cast = (HelmetItem) currentHelmet;
            applyStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousHelmet instanceof HelmetItem && currentHelmet instanceof AirBlockItem) {
            HelmetItem cast = (HelmetItem) previousHelmet;
            removeStatusEffect(cast.getEffect());
        }

        previousHelmetStack = new ItemStack(currentHelmet);
    }

    /**
     * Handles chest armor
     *
     * @param stack New stack
     */
    private void handleChestArmor(ItemStack stack) {
        Item previousChestplate = previousChestplateStack.getItem();
        Item currentChestplate = stack.getItem();

        if (previousChestplate instanceof AirBlockItem && currentChestplate instanceof ChestplateItem) {
            ChestplateItem cast = (ChestplateItem) currentChestplate;
            applyStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousChestplate instanceof ChestplateItem && currentChestplate instanceof AirBlockItem) {
            ChestplateItem cast = (ChestplateItem) previousChestplate;
            removeStatusEffect(cast.getEffect());
        }

        previousChestplateStack = new ItemStack(currentChestplate);
    }

    /**
     * Handles leg armor
     *
     * @param stack New stack
     */
    private void handleLegArmor(ItemStack stack) {
        Item previousLeggings = previousLeggingsStack.getItem();
        Item currentLeggings = stack.getItem();

        if (previousLeggings instanceof AirBlockItem && currentLeggings instanceof LeggingsItem) {
            LeggingsItem cast = (LeggingsItem) currentLeggings;
            applyStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousLeggings instanceof LeggingsItem && currentLeggings instanceof AirBlockItem) {
            LeggingsItem cast = (LeggingsItem) previousLeggings;
            removeStatusEffect(cast.getEffect());
        }

        previousLeggingsStack = new ItemStack(currentLeggings);
    }

    /**
     * Handles feet armor
     *
     * @param stack New stack
     */
    private void handleFeetArmor(ItemStack stack) {
        Item previousBoots = previousBootsStack.getItem();
        Item currentBoots = stack.getItem();

        if (previousBoots instanceof AirBlockItem && currentBoots instanceof BootsItem) {
            BootsItem cast = (BootsItem) currentBoots;
            applyStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousBoots instanceof BootsItem && currentBoots instanceof AirBlockItem) {
            BootsItem cast = (BootsItem) previousBoots;
            removeStatusEffect(cast.getEffect());
        }

        previousBootsStack = new ItemStack(currentBoots);
    }

    /**
     * Reads tracked data from a {@link CompoundTag}
     *
     * @param tag  The source {@link CompoundTag}
     * @param info Mixin {@link CallbackInfo}
     */
    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        previousHelmetStack = ItemStackUtility.readItemStack(tag, "Previous Helmet Stack");
        previousChestplateStack = ItemStackUtility.readItemStack(tag, "Previous Chestplate Stack");
        previousLeggingsStack = ItemStackUtility.readItemStack(tag, "Previous Leggings Stack");
        previousBootsStack = ItemStackUtility.readItemStack(tag, "Previous Boots Stack");
    }

    /**
     * Writes tracked data to a {@link CompoundTag}
     *
     * @param tag  The output {@link CompoundTag}
     * @param info Mixin {@link CallbackInfo}
     */
    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        ItemStackUtility.writeItemStack(tag, "Previous Helmet Stack", previousHelmetStack);
        ItemStackUtility.writeItemStack(tag, "Previous Chestplate Stack", previousChestplateStack);
        ItemStackUtility.writeItemStack(tag, "Previous Leggings Stack", previousLeggingsStack);
        ItemStackUtility.writeItemStack(tag, "Previous Boots Stack", previousBootsStack);
    }

    /**
     * Creates an armor {@link StatusEffectInstance}
     *
     * @param effect    The {@link StatusEffect} itself
     * @param amplifier The amplifier of the effect
     * @return Generated {@link StatusEffectInstance}
     */
    private StatusEffectInstance createStatusEffectInstance(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(
                effect,
                999999,
                amplifier
        );
    }
}
