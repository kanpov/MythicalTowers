package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import kotlin.random.Random

/**
 * Max amount of ability uses
 */
private const val MAX_EFFECT_ABILITY_USES = 4

/**
 * A map of effect occur probabilities.
 *
 * The probabilities are calculates in percentages.
 */
private val EFFECT_PROBABILITIES: Map<StatusEffect, Int> = mapOf(
    StatusEffects.POISON to 70,
    StatusEffects.WEAKNESS to 80,
    StatusEffects.BLINDNESS to 50,
    StatusEffects.NAUSEA to 30
)

/**
 * The starting duration of the effects.
 *
 * Is decreased on each use of the ability
 */
private const val STARTING_DURATION = 200.0f

/**
 * The decreasing multiplication (decreasing multiplication - 5 * 0.5 = 2.5)
 * of [STARTING_DURATION] on every use
 */
private const val STARTING_DURATION_DECREASE = 0.7f

// NBT

private const val NBT_EFFECT_ABILITY_USES = "effectAbilityUses"
private const val NBT_CURRENT_EFFECT_DURATION = "currentEffectDuration"

class RedMeleeRobotEntity(type: EntityType<RedMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * The amount of times the effect ability has been used
     */
    private var effectAbilityUses = 0

    /**
     * The current duration of each effect
     */
    private var currentEffectDuration = 0.0f

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (effectAbilityUses <= MAX_EFFECT_ABILITY_USES) {
            EFFECT_PROBABILITIES.forEach { (effect, probability) ->
                // Use probability
                val chance = Random.nextInt(100)
                if (chance <= probability) {
                    // Make a StatusEffectInstance and apply it
                    if (currentEffectDuration == 0.0f) currentEffectDuration = STARTING_DURATION

                    val instance = StatusEffectInstance(
                        effect,
                        currentEffectDuration.toInt(),
                        Random.nextInt(0, 3)
                    )
                    target.addStatusEffect(instance)
                }
            }

            // Decrease duration using the modifier
            currentEffectDuration *= STARTING_DURATION_DECREASE

            ++effectAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_EFFECT_ABILITY_USES, effectAbilityUses)
        nbt.putFloat(NBT_CURRENT_EFFECT_DURATION, currentEffectDuration)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        effectAbilityUses = nbt.getInt(NBT_EFFECT_ABILITY_USES)
        currentEffectDuration = nbt.getFloat(NBT_CURRENT_EFFECT_DURATION)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 70.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 75.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0)
    }
}
