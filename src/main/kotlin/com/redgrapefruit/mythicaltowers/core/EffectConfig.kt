package com.redgrapefruit.mythicaltowers.core

import com.redgrapefruit.mythicaltowers.util.IntRange
import net.minecraft.entity.effect.StatusEffect

/**
 * An effect config stores data about custom effects
 */
data class EffectConfig(
    /**
     * The [StatusEffect] itself
     */
    val statusEffect: StatusEffect,
    /**
     * Optional [IntRange] of durations
     */
    val durationRange: IntRange? = null,
    /**
     * Set duration value. Used when [durationRange] isn't set
     */
    val duration: Int = 0,
    /**
     * Optional [IntRange] of amplifiers
     */
    val amplifierRange: IntRange? = null,
    /**
     * Set amplifier value. Used when [amplifierRange] isn't set
     */
    val amplifier: Int = 0,
    /**
     * Is the effect always applied. Transforms [chance] into 100
     */
    val isAlwaysApplied: Boolean = false,
    /**
     * Percentage chance of the effect being applied
     */
    val chance: Int = 50,
    /**
     * [WeaponEffectTarget]. Only used in tools. Set to a placeholder value by default
     */
    val weaponEffectTarget: WeaponEffectTarget = WeaponEffectTarget.NONE
)