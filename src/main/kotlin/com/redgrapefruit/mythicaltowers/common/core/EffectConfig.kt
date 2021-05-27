package com.redgrapefruit.mythicaltowers.common.core

import com.redgrapefruit.mythicaltowers.common.util.IntRange
import net.minecraft.entity.effect.StatusEffect
import java.util.*

/**
 * An effect config stores data about custom effects.<br></br>
 * More info on the wiki page 6
 */
class EffectConfig private constructor(
    /**
     * The [StatusEffect] itself
     */
    val statusEffect: StatusEffect?,
    /**
     * An [IntRange] of durations. Only used in ranged durations
     */
    val durationRange: Optional<IntRange>,
    /**
     * The duration of this effect. Only used in set and permanent durations
     */
    val duration: Int,
    /**
     * An [IntRange] of amplifiers. Only used in ranged amplifiers
     */
    val amplifierRange: Optional<IntRange>,
    /**
     * The amplifier of this effect. Only used in set amplifiers
     */
    val amplifier: Int,
    /**
     * Is the effect always applied. If true, the [.chance] property of the effect is ignored
     */
    val isAlwaysApplied: Boolean,
    /**
     * The chance of this effect being applied from 0% to 100%
     */
    val chance: Int,
    /**
     * The [WeaponEffectTarget] of this effect. Only used in weapons
     */
    val weaponTarget: WeaponEffectTarget?
) {

    class Builder {
        private var statusEffect: StatusEffect? = null
        private var durationRange: Optional<IntRange> = Optional.empty()
        private var duration = 0
        private var amplifierRange: Optional<IntRange> = Optional.empty()
        private var amplifier = 0
        private var isAlwaysApplied = false
        private var chance = UNDEFINED_VALUE
        private var weaponTarget: WeaponEffectTarget? = null
        fun statusEffect(statusEffect: StatusEffect?): Builder {
            this.statusEffect = statusEffect
            return this
        }

        fun rangedDuration(min: Int, max: Int): Builder {
            durationRange = Optional.of(IntRange(min, max))
            return this
        }

        fun setDuration(duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun permanentDuration(): Builder {
            duration = 999999
            return this
        }

        fun rangedAmplifier(min: Int, max: Int): Builder {
            amplifierRange = Optional.of(IntRange(min, max))
            return this
        }

        fun setAmplifier(amplifier: Int): Builder {
            this.amplifier = amplifier
            return this
        }

        fun zeroAmplifier(): Builder {
            amplifier = 0
            return this
        }

        fun isAlwaysApplied(): Builder {
            isAlwaysApplied = true
            return this
        }

        fun chance(chance: Int): Builder {
            this.chance = chance
            return this
        }

        fun weaponTarget(target: WeaponEffectTarget?): Builder {
            weaponTarget = target
            return this
        }

        fun build(): EffectConfig {
            return EffectConfig(
                statusEffect,
                durationRange,
                duration,
                amplifierRange,
                amplifier,
                isAlwaysApplied,
                chance,
                weaponTarget
            )
        }
    }

    companion object {
        const val UNDEFINED_VALUE = Int.MIN_VALUE
    }
}