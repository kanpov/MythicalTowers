package com.redgrapefruit.mythicaltowers.common.util;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;

/**
 * A range of integers to randomly pick from
 */
public class IntRange {
    private final int min;
    private final int max;

    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Picks a random number from this range
     *
     * @return Random number
     */
    public int pick() {
        int initial = max - min;

        return MythicalTowers.RANDOM.nextInt(initial) + min;
    }
}
