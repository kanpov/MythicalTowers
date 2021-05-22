package com.redgrapefruit.mythicaltowers.common.core;

import net.minecraft.entity.LivingEntity;

/**
 * The target of weapon effects, the target {@link LivingEntity} or the attacker {@link LivingEntity}
 */
public enum WeaponEffectTarget {
    /**
     * The target of the attack
     */
    TARGET,
    /**
     * The attacker of the attack OR the miner
     */
    ATTACKER
}
