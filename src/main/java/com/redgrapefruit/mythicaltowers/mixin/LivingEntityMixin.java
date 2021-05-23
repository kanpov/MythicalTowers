package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;
import com.redgrapefruit.mythicaltowers.common.armor.BootsItem;
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem;
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem;
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin applies custom effects to the entity once the custom armor from the mod is put on
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void applyStatusEffect(StatusEffectInstance effect);

    // Tracked data about previous armor stacks
    @Unique
    private ItemStack previousHelmetStack;
    @Unique
    private ItemStack previousChestplateStack;
    @Unique
    private ItemStack previousLeggingsStack;
    @Unique
    private ItemStack previousBootsStack;

    // method_30122 is called everytime an armor piece is put on/off
    @Inject(method = "method_30122", at = @At("TAIL"))
    private void method_30122(EquipmentSlot slot, ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();

        // Assign previous stacks if they're null
        if (previousHelmetStack == null) previousHelmetStack = MythicalTowers.AIR_STACK;
        if (previousChestplateStack == null) previousChestplateStack = MythicalTowers.AIR_STACK;
        if (previousLeggingsStack == null) previousLeggingsStack = MythicalTowers.AIR_STACK;
        if (previousBootsStack == null) previousBootsStack = MythicalTowers.AIR_STACK;

        // How this works:
        // 1. Check equipment slot
        // 2. Get previous and current item in the slot
        // 3. If previous is air and new is mod's armor, apply the effect using createEnablingStatusEffectInstance
        // 4. If previous is mod's armor and new is air, disable the effect using createDisablingStatusEffectInstance
        // 5. Update the previous stack to current for the next operation
        // And that for EACH of the 4 equipment slots

        if (slot == EquipmentSlot.HEAD) {
            Item previousHelmet = previousHelmetStack.getItem();
            Item currentHelmet = stack.getItem();

            if (previousHelmet instanceof AirBlockItem && currentHelmet instanceof HelmetItem) {
                HelmetItem cast = (HelmetItem) currentHelmet;
                applyStatusEffect(createEnablingStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
            }

            if (previousHelmet instanceof HelmetItem && currentHelmet instanceof AirBlockItem) {
                HelmetItem cast = (HelmetItem) previousHelmet;
                applyStatusEffect(createDisablingStatusEffectInstance(cast.getEffect()));
            }

            previousHelmetStack = new ItemStack(currentHelmet);
        }

        if (slot == EquipmentSlot.CHEST) {
            Item previousChestplate = previousChestplateStack.getItem();
            Item currentChestplate = stack.getItem();

            if (previousChestplate instanceof AirBlockItem && currentChestplate instanceof ChestplateItem) {
                ChestplateItem cast = (ChestplateItem) currentChestplate;
                applyStatusEffect(createEnablingStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
            }

            if (previousChestplate instanceof ChestplateItem && currentChestplate instanceof AirBlockItem) {
                ChestplateItem cast = (ChestplateItem) previousChestplate;
                applyStatusEffect(createDisablingStatusEffectInstance(cast.getEffect()));
            }

            previousChestplateStack = new ItemStack(currentChestplate);
        }

        if (slot == EquipmentSlot.LEGS) {
            Item previousLeggings = previousLeggingsStack.getItem();
            Item currentLeggings = stack.getItem();

            if (previousLeggings instanceof AirBlockItem && currentLeggings instanceof LeggingsItem) {
                LeggingsItem cast = (LeggingsItem) currentLeggings;
                applyStatusEffect(createEnablingStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
            }

            if (previousLeggings instanceof LeggingsItem && currentLeggings instanceof AirBlockItem) {
                LeggingsItem cast = (LeggingsItem) previousLeggings;
                applyStatusEffect(createDisablingStatusEffectInstance(cast.getEffect()));
            }

            previousLeggingsStack = new ItemStack(currentLeggings);
        }

        if (slot == EquipmentSlot.FEET) {
            Item previousBoots = previousBootsStack.getItem();
            Item currentBoots = stack.getItem();

            if (previousBoots instanceof AirBlockItem && currentBoots instanceof BootsItem) {
                BootsItem cast = (BootsItem) currentBoots;
                applyStatusEffect(createEnablingStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
            }

            if (previousBoots instanceof BootsItem && currentBoots instanceof AirBlockItem) {
                BootsItem cast = (BootsItem) previousBoots;
                applyStatusEffect(createDisablingStatusEffectInstance(cast.getEffect()));
            }

            previousBootsStack = new ItemStack(currentBoots);
        }
    }

    /**
     * Creates an armor {@link StatusEffectInstance}
     * @param effect The {@link StatusEffect} itself
     * @param amplifier The amplifier of the effect
     * @return Generated {@link StatusEffectInstance}
     */
    private StatusEffectInstance createEnablingStatusEffectInstance(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(
                effect,
                999999,
                amplifier
        );
    }

    /**
     * Creates a {@link StatusEffectInstance} that disables the given effect by using duration=0
     * @param effect The {@link StatusEffect} itself
     * @return Generated {@link StatusEffectInstance}
     */
    private static StatusEffectInstance createDisablingStatusEffectInstance(StatusEffect effect) {
        return new StatusEffectInstance(
                effect,
                0,
                0
        );
    }
}
