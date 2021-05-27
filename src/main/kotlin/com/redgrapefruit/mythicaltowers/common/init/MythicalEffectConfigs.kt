package com.redgrapefruit.mythicaltowers.common.init

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig
import com.redgrapefruit.mythicaltowers.common.core.WeaponEffectTarget
import net.minecraft.entity.effect.StatusEffects
import java.util.*

/**
 * Stores the mod's lists of [EffectConfig]s
 */
object MythicalEffectConfigs {
    // Orbs
    val GREEN_ORB = Arrays.asList(
        EffectConfig.Builder()
            .statusEffect(StatusEffects.REGENERATION)
            .rangedDuration(300, 600)
            .setAmplifier(0)
            .isAlwaysApplied()
            .build(),
        EffectConfig.Builder()
            .statusEffect(StatusEffects.ABSORPTION)
            .rangedDuration(400, 750)
            .rangedAmplifier(0, 1)
            .chance(70)
            .build()
    )

    // Tools
    val GREEN_TOOLS = Arrays.asList(
        EffectConfig.Builder()
            .statusEffect(StatusEffects.POISON)
            .rangedDuration(200, 400)
            .rangedAmplifier(0, 2)
            .chance(80)
            .weaponTarget(WeaponEffectTarget.TARGET)
            .build(),
        EffectConfig.Builder()
            .statusEffect(StatusEffects.HASTE)
            .rangedDuration(100, 150)
            .setAmplifier(1)
            .isAlwaysApplied()
            .weaponTarget(WeaponEffectTarget.ATTACKER)
            .build()
    )
}