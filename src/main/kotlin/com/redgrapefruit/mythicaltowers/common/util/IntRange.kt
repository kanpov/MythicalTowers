package com.redgrapefruit.mythicaltowers.common.util

import com.redgrapefruit.mythicaltowers.common.MythicalTowers

/**
 * A range of integers to randomly pick from
 */
class IntRange(private val min: Int, private val max: Int) {
    /**
     * Picks a random number from this range
     *
     * @return Random number
     */
    fun pick(): Int {
        val initial = max - min
        return MythicalTowers.RANDOM.nextInt(initial) + min
    }
}