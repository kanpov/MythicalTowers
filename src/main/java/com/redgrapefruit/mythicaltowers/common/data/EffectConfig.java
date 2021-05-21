package com.redgrapefruit.mythicaltowers.common.data;

import com.redgrapefruit.mythicaltowers.common.util.IntRange;
import net.minecraft.entity.effect.StatusEffect;

import java.util.Optional;

/**
 * An effect config stores data about custom effects.<br>
 * More info on the wiki page 6
 */
public class EffectConfig {
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
     * The chance of this effect being applied
     */
    private final int chance;

    private EffectConfig(
            StatusEffect statusEffect,
            Optional<IntRange> durationRange,
            int duration,
            Optional<IntRange> amplifierRange,
            int amplifier,
            boolean isAlwaysApplied,
            int chance
    ) {
        this.statusEffect = statusEffect;
        this.durationRange = durationRange;
        this.duration = duration;
        this.amplifierRange = amplifierRange;
        this.amplifier = amplifier;
        this.isAlwaysApplied = isAlwaysApplied;
        this.chance = chance;
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
}
