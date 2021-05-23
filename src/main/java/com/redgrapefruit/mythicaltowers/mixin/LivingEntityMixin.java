package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.armor.BootsItem;
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem;
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem;
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    // method_30122 is called everytime an armor piece is put on/off
    @Inject(method = "method_30122", at = @At("TAIL"))
    private void method_30122(EquipmentSlot slot, ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();

        // Check the EquipmentSlot and the item, then apply the effect. Stacking of durations is allowed

        if (slot == EquipmentSlot.HEAD && item instanceof HelmetItem) {
            HelmetItem helmet = (HelmetItem) item;
            applyStatusEffect(createStatusEffectInstance(helmet.getEffect(), helmet.getAmplifier()));
        }

        if (slot == EquipmentSlot.CHEST && item instanceof ChestplateItem) {
            ChestplateItem chestplate = (ChestplateItem) item;
            applyStatusEffect(createStatusEffectInstance(chestplate.getEffect(), chestplate.getAmplifier()));
        }

        if (slot == EquipmentSlot.LEGS && item instanceof LeggingsItem) {
            LeggingsItem leggings = (LeggingsItem) item;
            applyStatusEffect(createStatusEffectInstance(leggings.getEffect(), leggings.getAmplifier()));
        }

        if (slot == EquipmentSlot.FEET && item instanceof BootsItem) {
            BootsItem boots = (BootsItem) item;
            applyStatusEffect(createStatusEffectInstance(boots.getEffect(), boots.getAmplifier()));
        }
    }

    /**
     * Creates an armor {@link StatusEffectInstance}
     * @param effect The {@link StatusEffect} itself
     * @param amplifier The amplifier of the effect
     * @return Generated {@link StatusEffectInstance}
     */
    private StatusEffectInstance createStatusEffectInstance(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(
                effect,
                100,
                amplifier
        );
    }
}
