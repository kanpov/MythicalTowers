package com.redgrapefruit.mythicaltowers.common.core;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;
import com.redgrapefruit.mythicaltowers.common.util.IntRange;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.List;

/**
 * The engine controls any operations with effects
 */
public class EffectEngine {
    /**
     * The event that happens after the orb is right-clicked by a player
     *
     * @param configs The {@link EffectConfig}s linked to the orb
     * @param user    The user {@link PlayerEntity}
     * @param hand    The {@link Hand} with the orb
     */
    public static void onOrbUsed(List<EffectConfig> configs, PlayerEntity user, Hand hand) {
        // Iterate through each config
        for (EffectConfig config : configs) {
            // Get ranged duration, else use set/permanent duration
            int duration = config.getDurationRange()
                    .map(IntRange::pick)
                    .orElseGet(config::getDuration);

            // Get ranged amplifier, else use set amplifier
            int amplifier = config.getAmplifierRange()
                    .map(IntRange::pick)
                    .orElseGet(config::getAmplifier);

            // Get chance, else use 100 (always applied)
            int chance = config.getChance() == EffectConfig.UNDEFINED_VALUE ? 100 : config.getChance();
            // Pick a number and see if it fits in the chance, then apply the effect
            if (MythicalTowers.RANDOM.nextInt(100) <= chance) {
                user.applyStatusEffect(new StatusEffectInstance(
                        config.getStatusEffect(),
                        duration,
                        amplifier
                ));
            }
        }

        // Decrement the stack to make the orb disappear once it is used
        user.getStackInHand(hand).decrement(1);
    }

    /**
     * The event that happens once every tick if the amulet is in a player's inventory
     *
     * @param effect    The amulet's {@link StatusEffect}
     * @param amplifier The effect's amplifier
     * @param entity    Holder {@link Entity}
     */
    public static void onAmuletTicked(StatusEffect effect, int amplifier, Entity entity) {
        // Amulet effects only apply to players
        if (!(entity instanceof PlayerEntity)) return;

        // Apply the effect for 60 ticks (3 seconds) to ensure that the effect gets off the player once the amulet leaves the inventory
        // We also use addStatusEffect, not applyStatusEffect to ensure that the effect duration doesn't stack up over time
        ((PlayerEntity) entity).addStatusEffect(createPassiveStatusEffectInstance(effect, amplifier));
    }

    /**
     * The event that happens after an entity is hit with the tool or weapon
     *
     * @param configs  The {@link EffectConfig}s linked to the tool or weapon
     * @param target   The target {@link LivingEntity}
     * @param attacker The attacker {@link LivingEntity}
     */
    public static void onPostHit(List<EffectConfig> configs, LivingEntity target, LivingEntity attacker) {
        for (EffectConfig config : configs) {
            if (checkChance(config)) {
                if (config.getWeaponTarget() == WeaponEffectTarget.TARGET) {
                    target.applyStatusEffect(createStandardStatusEffectInstance(config));
                } else {
                    attacker.applyStatusEffect(createStandardStatusEffectInstance(config));
                }
            }
        }
    }

    /**
     * The event that happens after a block is destroyed with the tool or weapon
     *
     * @param configs The {@link EffectConfig}s linked to the tool or weapon
     * @param miner   The miner {@link LivingEntity}
     */
    public static void onPostMine(List<EffectConfig> configs, LivingEntity miner) {
        for (EffectConfig config : configs) {
            if (checkChance(config) && config.getWeaponTarget() == WeaponEffectTarget.ATTACKER) {
                miner.applyStatusEffect(createStandardStatusEffectInstance(config));
            }
        }
    }

    /**
     * Checks your luck based on the chance calculated using the given {@link EffectConfig}
     *
     * @param config Source {@link EffectConfig}
     * @return True if you're lucky
     */
    private static boolean checkChance(EffectConfig config) {
        int chance =
                config.getChance() == EffectConfig.UNDEFINED_VALUE
                        ? 100
                        : config.getChance();

        return MythicalTowers.RANDOM.nextInt(100) <= chance;
    }

    /**
     * Creates an {@link EffectConfig}-based {@link StatusEffectInstance}
     *
     * @param config Source {@link EffectConfig}
     * @return Generated {@link StatusEffectInstance}
     */
    private static StatusEffectInstance createStandardStatusEffectInstance(EffectConfig config) {
        return new StatusEffectInstance(
                config.getStatusEffect(),

                config.getDurationRange()
                        .map(IntRange::pick)
                        .orElseGet(config::getDuration),

                config.getAmplifierRange()
                        .map(IntRange::pick)
                        .orElseGet(config::getAmplifier)
        );
    }

    /**
     * Creates a passive 60-tick (3-second) long {@link StatusEffectInstance} based on given arguments
     *
     * @param statusEffect The applied {@link StatusEffect}
     * @param amplifier    The effect's amplifier
     * @return Generated {@link StatusEffectInstance}
     */
    private static StatusEffectInstance createPassiveStatusEffectInstance(StatusEffect statusEffect, int amplifier) {
        return new StatusEffectInstance(
                statusEffect,
                60,
                amplifier
        );
    }
}
