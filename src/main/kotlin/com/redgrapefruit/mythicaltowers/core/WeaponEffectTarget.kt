package com.redgrapefruit.mythicaltowers.core

import net.minecraft.entity.LivingEntity

/**
 * The target of weapon effects, the target [LivingEntity] or the attacker [LivingEntity]
 */
enum class WeaponEffectTarget {
    /**
     * The target of the attack
     */
    TARGET,

    /**
     * The attacker of the attack OR the miner
     */
    ATTACKER,

    /**
     * Placeholder value
     */
    NONE
}