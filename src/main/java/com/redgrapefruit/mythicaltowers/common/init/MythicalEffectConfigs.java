package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import com.redgrapefruit.mythicaltowers.common.core.WeaponEffectTarget;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Arrays;
import java.util.List;

/**
 * Stores the mod's lists of {@link EffectConfig}s
 */
public class MythicalEffectConfigs {
    // Orbs
    public static final List<EffectConfig> GREEN_ORB = Arrays.asList(
            new EffectConfig.Builder()
                .statusEffect(StatusEffects.REGENERATION)
                .rangedDuration(300, 600)
                .setAmplifier(0)
                .isAlwaysApplied()
                .build(),
            new EffectConfig.Builder()
                .statusEffect(StatusEffects.ABSORPTION)
                .rangedDuration(400, 750)
                .rangedAmplifier(0, 1)
                .chance(70)
                .build()
    );

    // Tools
    public static final List<EffectConfig> GREEN_TOOLS = Arrays.asList(
            new EffectConfig.Builder()
                .statusEffect(StatusEffects.POISON)
                .rangedDuration(200, 400)
                .rangedAmplifier(0, 2)
                .chance(80)
                .weaponTarget(WeaponEffectTarget.TARGET)
                .build(),
            new EffectConfig.Builder()
                .statusEffect(StatusEffects.HASTE)
                .rangedDuration(100, 150)
                .setAmplifier(1)
                .isAlwaysApplied()
                .weaponTarget(WeaponEffectTarget.ATTACKER)
                .build()
    );
}
