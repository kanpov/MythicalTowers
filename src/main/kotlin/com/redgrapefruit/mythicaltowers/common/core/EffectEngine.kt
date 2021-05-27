package com.redgrapefruit.mythicaltowers.common.core

import com.redgrapefruit.mythicaltowers.common.MythicalTowers
import com.redgrapefruit.mythicaltowers.common.util.IntRange
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Hand

/**
 * The engine controls any operations with effects
 */
object EffectEngine {
    /**
     * The event that happens after the orb is right-clicked by a player
     *
     * @param configs The [EffectConfig]s linked to the orb
     * @param user    The user [PlayerEntity]
     * @param hand    The [Hand] with the orb
     */
    @JvmStatic
    fun onOrbUsed(configs: List<EffectConfig>, user: PlayerEntity, hand: Hand?) {
        // Iterate through each config
        for (config in configs) {
            // Get ranged duration, else use set/permanent duration
            val duration = config.durationRange
                .map { obj: IntRange? -> obj!!.pick() }
                .orElseGet { config.duration }

            // Get ranged amplifier, else use set amplifier
            val amplifier = config.amplifierRange
                .map { obj: IntRange? -> obj!!.pick() }
                .orElseGet { config.amplifier }

            // Get chance, else use 100 (always applied)
            val chance = if (config.chance == EffectConfig.UNDEFINED_VALUE) 100 else config.chance
            // Pick a number and see if it fits in the chance, then apply the effect
            if (MythicalTowers.RANDOM.nextInt(100) <= chance) {
                user.applyStatusEffect(
                    StatusEffectInstance(
                        config.statusEffect,
                        duration,
                        amplifier
                    )
                )
            }
        }

        // Decrement the stack to make the orb disappear once it is used
        user.getStackInHand(hand).decrement(1)
    }

    /**
     * The event that happens once every tick if the amulet is in a player's inventory
     *
     * @param effect    The amulet's [StatusEffect]
     * @param amplifier The effect's amplifier
     * @param entity    Holder [Entity]
     */
    @JvmStatic
    fun onAmuletTicked(effect: StatusEffect, amplifier: Int, entity: Entity?) {
        // Amulet effects only apply to players
        if (entity !is PlayerEntity) return

        // Apply the effect for 60 ticks (3 seconds) to ensure that the effect gets off the player once the amulet leaves the inventory
        // We also use addStatusEffect, not applyStatusEffect to ensure that the effect duration doesn't stack up over time
        entity.addStatusEffect(createPassiveStatusEffectInstance(effect, amplifier))
    }

    /**
     * The event that happens after an entity is hit with the tool or weapon
     *
     * @param configs  The [EffectConfig]s linked to the tool or weapon
     * @param target   The target [LivingEntity]
     * @param attacker The attacker [LivingEntity]
     */
    @JvmStatic
    fun onPostHit(configs: List<EffectConfig>, target: LivingEntity, attacker: LivingEntity) {
        for (config in configs) {
            if (checkChance(config)) {
                if (config.weaponTarget == WeaponEffectTarget.TARGET) {
                    target.applyStatusEffect(createStandardStatusEffectInstance(config))
                } else {
                    attacker.applyStatusEffect(createStandardStatusEffectInstance(config))
                }
            }
        }
    }

    /**
     * The event that happens after a block is destroyed with the tool or weapon
     *
     * @param configs The [EffectConfig]s linked to the tool or weapon
     * @param miner   The miner [LivingEntity]
     */
    @JvmStatic
    fun onPostMine(configs: List<EffectConfig>, miner: LivingEntity) {
        for (config in configs) {
            if (checkChance(config) && config.weaponTarget == WeaponEffectTarget.ATTACKER) {
                miner.applyStatusEffect(createStandardStatusEffectInstance(config))
            }
        }
    }

    /**
     * Checks your luck based on the chance calculated using the given [EffectConfig]
     *
     * @param config Source [EffectConfig]
     * @return True if you're lucky
     */
    private fun checkChance(config: EffectConfig): Boolean {
        val chance = if (config.chance == EffectConfig.UNDEFINED_VALUE) 100 else config.chance
        return MythicalTowers.RANDOM.nextInt(100) <= chance
    }

    /**
     * Creates an [EffectConfig]-based [StatusEffectInstance]
     *
     * @param config Source [EffectConfig]
     * @return Generated [StatusEffectInstance]
     */
    private fun createStandardStatusEffectInstance(config: EffectConfig): StatusEffectInstance {
        return StatusEffectInstance(
            config.statusEffect,
            config.durationRange
                .map { obj: IntRange? -> obj!!.pick() }
                .orElseGet { config.duration },
            config.amplifierRange
                .map { obj: IntRange? -> obj!!.pick() }
                .orElseGet { config.amplifier }
        )
    }

    /**
     * Creates a passive 60-tick (3-second) long [StatusEffectInstance] based on given arguments
     *
     * @param statusEffect The applied [StatusEffect]
     * @param amplifier    The effect's amplifier
     * @return Generated [StatusEffectInstance]
     */
    private fun createPassiveStatusEffectInstance(statusEffect: StatusEffect, amplifier: Int): StatusEffectInstance {
        return StatusEffectInstance(
            statusEffect,
            60,
            amplifier
        )
    }
}