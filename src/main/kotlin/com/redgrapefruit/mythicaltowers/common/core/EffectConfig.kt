package com.redgrapefruit.mythicaltowers.common.core

import net.minecraft.entity.effect.StatusEffect
import com.redgrapefruit.mythicaltowers.common.util.IntRange
import java.util.*

/**
 * An effect config stores data about custom effects
 */
data class EffectConfig(
    val statusEffect: StatusEffect,
    val durationRange: Optional<IntRange>,
    val duration: Int,
    val amplifierRange: Optional<IntRange>,
    val amplifier: Int,
    val isAlwaysApplied: Boolean,
    val chance: Int?,
    val weaponEffectTarget: WeaponEffectTarget?
)