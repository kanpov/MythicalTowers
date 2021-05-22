package com.redgrapefruit.mythicaltowers.common.core;

import com.redgrapefruit.mythicaltowers.common.util.IntRange;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

import java.util.Optional;

/**
 * An effect config stores data about custom effects.<br>
 * More info on the wiki page 6
 */
public class EffectConfig {
    public static final int UNDEFINED_VALUE = Integer.MIN_VALUE;

    /**
     * The {@link StatusEffect} itself
     */
    private final StatusEffect statusEffect;
    /**
     * An {@link IntRange} of durations. Only used in ranged durations
     */
    private final Optional<IntRange> durationRange;
    /**
     * The duration of this effect. Only used in set and permanent durations
     */
    private final int duration;
    /**
     * An {@link IntRange} of amplifiers. Only used in ranged amplifiers
     */
    private final Optional<IntRange> amplifierRange;
    /**
     * The amplifier of this effect. Only used in set amplifiers
     */
    private final int amplifier;
    /**
     * Is the effect always applied. If true, the {@link #chance} property of the effect is ignored
     */
    private final boolean isAlwaysApplied;
    /**
     * The chance of this effect being applied from 0% to 100%
     */
    private final int chance;
    /**
     * The {@link WeaponEffectTarget} of this effect. Only used in weapons
     */
    private final WeaponEffectTarget weaponTarget;

    private EffectConfig(
            StatusEffect statusEffect,
            Optional<IntRange> durationRange,
            int duration,
            Optional<IntRange> amplifierRange,
            int amplifier,
            boolean isAlwaysApplied,
            int chance,
            WeaponEffectTarget weaponTarget
    ) {
        this.statusEffect = statusEffect;
        this.durationRange = durationRange;
        this.duration = duration;
        this.amplifierRange = amplifierRange;
        this.amplifier = amplifier;
        this.isAlwaysApplied = isAlwaysApplied;
        this.chance = chance;
        this.weaponTarget = weaponTarget;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public Optional<IntRange> getDurationRange() {
        return durationRange;
    }

    public int getDuration() {
        return duration;
    }

    public Optional<IntRange> getAmplifierRange() {
        return amplifierRange;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isAlwaysApplied() {
        return isAlwaysApplied;
    }

    public int getChance() {
        return chance;
    }

    public WeaponEffectTarget getWeaponTarget() {
        return weaponTarget;
    }

    public static class Builder {
        private StatusEffect statusEffect;
        private Optional<IntRange> durationRange = Optional.empty();
        private int duration;
        private Optional<IntRange> amplifierRange = Optional.empty();
        private int amplifier;
        private boolean isAlwaysApplied;
        private int chance = UNDEFINED_VALUE;
        private WeaponEffectTarget weaponTarget;

        public Builder statusEffect(StatusEffect statusEffect) {
            this.statusEffect = statusEffect;
            return this;
        }

        public Builder rangedDuration(int min, int max) {
            this.durationRange = Optional.of(new IntRange(min, max));
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder permanentDuration() {
            this.duration = 999999;
            return this;
        }

        public Builder rangedAmplifier(int min, int max) {
            this.amplifierRange = Optional.of(new IntRange(min, max));
            return this;
        }

        public Builder setAmplifier(int amplifier) {
            this.amplifier = amplifier;
            return this;
        }

        public Builder zeroAmplifier() {
            this.amplifier = 0;
            return this;
        }

        public Builder isAlwaysApplied() {
            this.isAlwaysApplied = true;
            return this;
        }

        public Builder chance(int chance) {
            this.chance = chance;
            return this;
        }

        public Builder weaponTarget(WeaponEffectTarget target) {
            this.weaponTarget = target;
            return this;
        }

        public EffectConfig build() {
            return new EffectConfig(
                    statusEffect,
                    durationRange,
                    duration,
                    amplifierRange,
                    amplifier,
                    isAlwaysApplied,
                    chance,
                    weaponTarget
            );
        }
    }
}
