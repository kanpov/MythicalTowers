package com.redgrapefruit.mythicaltowers.common.core

import net.minecraft.entity.effect.StatusEffect
import com.redgrapefruit.mythicaltowers.common.util.IntRange

/**
 * An effect config stores data about custom effects
 */
data class EffectConfig(
    val statusEffect: StatusEffect,
    val durationRange: IntRange? = null,
    val duration: Int = 0,
    val amplifierRange: IntRange? = null,
    val amplifier: Int = 0,
    val isAlwaysApplied: Boolean = false,
    val chance: Int = 50,
    val weaponEffectTarget: WeaponEffectTarget = WeaponEffectTarget.NONE
)