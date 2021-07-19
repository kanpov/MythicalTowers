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
 * Maximum amount of times the self-buff ability can be used
 */
private const val MAX_BUFF_ABILITY_USES = 4

/**
 * A map of probabilities of every self-buff [StatusEffect].
 *
 * Counted in percentages.
 */
private val BUFF_PROBABILITIES: Map<StatusEffect, Int> = mapOf(
    StatusEffects.SPEED to 60,
    StatusEffects.REGENERATION to 85,
    StatusEffects.ABSORPTION to 50,
    StatusEffects.RESISTANCE to 15
)

/**
 * The starting duration of every effect.
 *
 * Is increased on every use of the ability
 */
private const val STARTING_DURATION = 100.0f

/**
 * The multiplier of the increase of [STARTING_DURATION]
 */
private const val STARTING_DURATION_INCREASE = 1.5f

// NBT

private const val NBT_BUFF_ABILITY_USES = "buffAbilityUses"
private const val NBT_CURRENT_BUFF_DURATION = "currentBuffDuration"

class PurpleMeleeRobotEntity(type: EntityType<PurpleMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * The amount of times the buff ability has been used
     */
    private var buffAbilityUses = 0

    /**
     * The duration of the buff effects
     */
    private var currentBuffDuration = 0.0f

    override fun onAttacking(target: Entity?) {
        if (buffAbilityUses <= MAX_BUFF_ABILITY_USES) {
            BUFF_PROBABILITIES.forEach { (buff, probability) ->
                // Use probability
                val chance = Random.nextInt(101)
                if (chance <= probability) {
                    // Make a StatusEffectInstance and apply it
                    if (currentBuffDuration == 0.0f) currentBuffDuration = STARTING_DURATION

                    val instance = StatusEffectInstance(
                        buff,
                        currentBuffDuration.toInt(),
                        Random.nextInt(0, 2)
                    )
                    addStatusEffect(instance)
                }
            }

            // Increase duration via the modifier and count the use
            currentBuffDuration *= STARTING_DURATION_INCREASE

            ++buffAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_BUFF_ABILITY_USES, buffAbilityUses)
        nbt.putFloat(NBT_CURRENT_BUFF_DURATION, currentBuffDuration)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        buffAbilityUses = nbt.getInt(NBT_BUFF_ABILITY_USES)
        currentBuffDuration = nbt.getFloat(NBT_CURRENT_BUFF_DURATION)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 90.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.31)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 47.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
    }
}
