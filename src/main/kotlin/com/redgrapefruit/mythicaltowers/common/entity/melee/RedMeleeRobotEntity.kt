package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.EFFECT_PROBABILITIES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.EFFECT_STARTING_DURATION
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.EFFECT_STARTING_DURATION_INCREASE
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.MAX_EFFECT_ABILITY_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_CURRENT_EFFECT_DURATION
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_EFFECT_ABILITY_USES
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import kotlin.random.Random

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
                    if (currentEffectDuration == 0.0f) currentEffectDuration = EFFECT_STARTING_DURATION

                    val instance = StatusEffectInstance(
                        effect,
                        currentEffectDuration.toInt(),
                        Random.nextInt(0, 3)
                    )
                    target.addStatusEffect(instance)
                }
            }

            // Decrease duration using the modifier
            currentEffectDuration *= EFFECT_STARTING_DURATION_INCREASE

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
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 32.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0)
    }
}
