package com.redgrapefruit.mythicaltowers.common.core;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;
import com.redgrapefruit.mythicaltowers.common.util.IntRange;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.List;

/**
 * The engine controls any operations with effects
 */
public class EffectEngine {
    /**
     * The event that happens after an entity is hit with the tool or weapon.
     * @param configs The {@link EffectConfig}s linked to the tool or weapon
     * @param target The target {@link LivingEntity}
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
     * @param configs The {@link EffectConfig}s linked to the tool or weapon
     * @param miner The miner {@link LivingEntity}
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
     * @param statusEffect The applied {@link StatusEffect}
     * @param amplifier The effect's amplifier
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
