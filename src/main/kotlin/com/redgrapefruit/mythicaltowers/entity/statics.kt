package com.redgrapefruit.mythicaltowers.entity

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffects

/**
 * Statics data for melee robots
 */
object MeleeRobotStatics {
    const val STUN_VALUE = 40

    const val JUMP_ATTACK_DAMAGE = 10f
    const val MAX_JUMP_ATTACK_USES = 3

    const val KNOCKBACK = 3.0
    const val MAX_KNOCKBACK_USES = 5

    const val SPEED_BOOST_VALUE = 3.0f
    const val SPEED_BOOST_LENGTH = 80
    const val SPEED_BOOST_MAX_USES = 4

    const val MAX_EFFECT_ABILITY_USES = 4
    val EFFECT_PROBABILITIES: Map<StatusEffect, Int> = mapOf(
        StatusEffects.POISON to 70,
        StatusEffects.WEAKNESS to 80,
        StatusEffects.BLINDNESS to 50,
        StatusEffects.NAUSEA to 30
    )
    const val EFFECT_STARTING_DURATION = 200.0f
    const val EFFECT_STARTING_DURATION_INCREASE = 0.7f

    const val MAX_CLONE_ABILITY_USES = 3
    const val CLONE_AMOUNT = 2
    const val CLONE_ABILITY_COOLDOWN = 100

    const val MAX_BUFF_ABILITY_USES = 4
    val BUFF_PROBABILITIES: Map<StatusEffect, Int> = mapOf(
        StatusEffects.SPEED to 60,
        StatusEffects.REGENERATION to 85,
        StatusEffects.ABSORPTION to 50,
        StatusEffects.RESISTANCE to 15
    )
    const val BUFF_STARTING_DURATION = 100.0f
    const val BUFF_STARTING_DURATION_INCREASE = 1.5f

    const val MAX_LEVITATE_ABILITY_USES = 2
    const val LEVITATION_DURATION = 200

    const val MAX_ROB_ABILITY_USES = 3
    const val ROB_CHANCE = 25

    // NBT

    const val NBT_IS_STUNNED = "isStunned"
    const val NBT_STUN_TICKS = "stunTicks"
    const val NBT_IS_JUMP_ATTACKING = "isJumpAttacking"
    const val NBT_JUMP_ATTACK_USES = "jumpAttackUses"
    const val NBT_KNOCKBACK_USES = "maxKnockbackUses"
    const val NBT_SPEED_BOOST_TICKS = "speedBoostTicks"
    const val NBT_SPEED_BOOST_USES = "speedBoostUses"
    const val NBT_IS_UNDER_SPEED_BOOST = "isUnderSpeedBoost"
    const val NBT_EFFECT_ABILITY_USES = "effectAbilityUses"
    const val NBT_CURRENT_EFFECT_DURATION = "currentEffectDuration"
    const val NBT_BUFF_ABILITY_USES = "buffAbilityUses"
    const val NBT_CURRENT_BUFF_DURATION = "currentBuffDuration"
    const val NBT_CLONE_ABILITY_USES = "cloneAbilityUses"
    const val NBT_CLONE_ABILITY_COOLDOWN_STATE = "cloneAbilityCooldownState"
    const val NBT_IS_CLONE = "isClone"
    const val NBT_LEVITATE_ABILITY_USES = "levitateAbilityUses"
    const val NBT_ROB_ABILITY_USES = "robAbilityUses"
}
