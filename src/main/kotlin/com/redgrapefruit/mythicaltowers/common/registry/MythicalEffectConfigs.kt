package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig
import com.redgrapefruit.mythicaltowers.common.core.WeaponEffectTarget
import com.redgrapefruit.mythicaltowers.common.util.IntRange
import net.minecraft.entity.effect.StatusEffects

/**
 * Stores the mod's lists of [EffectConfig]s
 */
object MythicalEffectConfigs {
    // Orbs
    val GREEN_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.REGENERATION,
            durationRange = IntRange(300, 600),
            amplifier = 0,
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.ABSORPTION,
            durationRange = IntRange(400, 750),
            amplifierRange = IntRange(0, 1),
            chance = 70
        )
    )

    // Tools
    val GREEN_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.POISON,
            durationRange = IntRange(200, 400),
            amplifierRange = IntRange(0, 2),
            chance = 80,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.HASTE,
            durationRange = IntRange(100, 150),
            amplifier = 1,
            isAlwaysApplied = true,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )
}