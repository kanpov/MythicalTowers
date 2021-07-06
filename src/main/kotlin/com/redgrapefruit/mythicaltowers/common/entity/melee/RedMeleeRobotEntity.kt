package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.util.DoubleTrackedDataHandler
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

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
private const val STARTING_DURATION = 200.0

/**
 * The decreasing multiplication (decreasing multiplication - 5 * 0.5 = 2.5)
 * of [STARTING_DURATION] on every use
 */
private const val STARTING_DURATION_DECREASE = 0.7

// NBT

private const val NBT_EFFECT_ABILITY_USES = "effectAbilityUses"
private const val NBT_CURRENT_EFFECT_DURATION = "currentEffectDuration"

class RedMeleeRobotEntity(type: EntityType<RedMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    // DataTracker keys
    private lateinit var effectAbilityUsesKey: TrackedData<Int>
    private lateinit var currentEffectDurationKey: TrackedData<Double>

    /**
     * The current uses of the effect ability
     */
    private var effectAbilityUses
        get() = dataTracker.get(effectAbilityUsesKey)
        set(value) = dataTracker.set(effectAbilityUsesKey, value)

    /**
     * The current duration of each effect
     */
    private var currentEffectDuration
        get() = dataTracker.get(currentEffectDurationKey)
        set(value) = dataTracker.set(currentEffectDurationKey, value)

    override fun initDataTracker() {
        super.initDataTracker()

        effectAbilityUsesKey = DataTracker.registerData(RedMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        currentEffectDurationKey = DataTracker.registerData(RedMeleeRobotEntity::class.java, DoubleTrackedDataHandler.default)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_EFFECT_ABILITY_USES, effectAbilityUses)
        nbt.putDouble(NBT_CURRENT_EFFECT_DURATION, currentEffectDuration)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        effectAbilityUses = nbt.getInt(NBT_EFFECT_ABILITY_USES)
        currentEffectDuration = nbt.getDouble(NBT_CURRENT_EFFECT_DURATION)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 70.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.4)
            .add(EntityAttributes.GENERIC_ARMOR, 60.0)
    }
}
