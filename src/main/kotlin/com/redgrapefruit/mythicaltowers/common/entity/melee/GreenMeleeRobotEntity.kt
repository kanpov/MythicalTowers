package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.google.common.collect.ImmutableMultimap
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

/**
 * The maximum jump attack power
 */
private const val MAX_JUMP_ATTACK_POWER = 80

/**
 * The conversion rate from power in ticks to power in attribute modifier value
 */
private const val TICK_TO_MODIFIER_RATE = 0.00333

// NBT

private const val NBT_IS_JUMP_ATTACKING = "isJumpAttacking"
private const val NBT_JUMP_ATTACK_POWER = "jumpAttackPower"

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    // DataTracker keys
    private val isJumpAttackingKey: TrackedData<Boolean> =
        DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    private val jumpAttackPowerKey: TrackedData<Int> =
        DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

    /**
     * Is the robot currently jump-attacking the enemy
     */
    private var isJumpAttacking
        get() = dataTracker.get(isJumpAttackingKey)
        set(value) = dataTracker.set(isJumpAttackingKey, value)

    /**
     * The current power of the jump-attack
     */
    private var jumpAttackPower
        get() = dataTracker.get(jumpAttackPowerKey)
        set(value) = dataTracker.set(jumpAttackPowerKey, value)

    override fun initDataTracker() {
        super.initDataTracker()

        dataTracker.startTracking(isJumpAttackingKey, false)
        dataTracker.startTracking(jumpAttackPowerKey, 0)
    }

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        if (jumpAttackPower >= MAX_JUMP_ATTACK_POWER) {
            isJumpAttacking = true
            jumpAttackPower = 0
        }
    }

    override fun tick() {
        super.tick()

        attributes.addTemporaryModifiers(ImmutableMultimap.of(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                "jump_attack_damage_boost",
                jumpAttackPower / TICK_TO_MODIFIER_RATE,
                EntityAttributeModifier.Operation.ADDITION
            )
        ))

        if (isJumpAttacking) {
            ++jumpAttackPower

            if (jumpAttackPower >= MAX_JUMP_ATTACK_POWER) {
                isJumpAttacking = false
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean(NBT_IS_JUMP_ATTACKING, isJumpAttacking)
        nbt.putInt(NBT_JUMP_ATTACK_POWER, jumpAttackPower)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        isJumpAttacking = nbt.getBoolean(NBT_IS_JUMP_ATTACKING)
        jumpAttackPower = nbt.getInt(NBT_JUMP_ATTACK_POWER)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
            .add(EntityAttributes.GENERIC_ARMOR, 6.0)
    }
}
