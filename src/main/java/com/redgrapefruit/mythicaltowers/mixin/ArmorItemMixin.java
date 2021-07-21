package com.redgrapefruit.mythicaltowers.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.redgrapefruit.mythicaltowers.registry.ArmorMaterialRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * This mixin applies knockback resistance to mod armor since Mojang decided to make knockback resistance immutable.<br>
 * Inspired by Fabric Wiki page about adding armor to the game
 */
@Mixin(ArmorItem.class)
public class ArmorItemMixin {
    @Shadow
    @Final
    private static UUID[] MODIFIERS;
    @Shadow
    @Final
    protected float knockbackResistance;
    @Shadow
    @Final
    @Mutable
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    /**
     * During constructor apply the knockback resistance {@link EntityAttributeModifier} if the {@link ArmorMaterial} is from the mod
     *
     * @param material The {@link ArmorMaterial}
     * @param slot     The {@link EquipmentSlot} with this armor piece
     * @param settings General {@link Item.Settings}
     * @param info     Mixin {@link CallbackInfo}
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void constructor(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings, CallbackInfo info) {
        // Get the UUID
        UUID uuid = MODIFIERS[slot.getEntitySlotId()];

        // Check if the ArmorMaterial is from the mod
        if (material instanceof ArmorMaterialRegistry) {
            // Make a builder
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            // Add all attribute modifiers to the builder
            attributeModifiers.forEach(builder::put);
            // Add our knockback resistance
            builder.put(
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(
                            "Armor knockback resistance",
                            knockbackResistance,
                            EntityAttributeModifier.Operation.ADDITION
                    )
            );
            // Assign the attribute modifiers
            attributeModifiers = builder.build();
        }
    }
}
