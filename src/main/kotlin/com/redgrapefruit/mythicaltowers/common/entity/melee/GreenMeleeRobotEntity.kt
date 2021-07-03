package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.google.common.collect.ImmutableMultimap
import com.redgrapefruit.mythicaltowers.common.util.trackedData
import com.redgrapefruit.mythicaltowers.common.util.trackedDataEntry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import kotlin.reflect.jvm.isAccessible

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
    /**
     * Is the robot currently jump-attacking the enemy
     */
    var isJumpAttacking: Boolean by trackedData(dataTracker, TrackedDataHandlerRegistry.BOOLEAN)

    /**
     * The current power of the jump-attack
     */
    var jumpAttackPower: Int by trackedData(dataTracker, TrackedDataHandlerRegistry.INTEGER)

    init {
        ::isJumpAttacking.isAccessible = true
        ::jumpAttackPower.isAccessible = true
    }

    override fun initDataTracker() {
        super.initDataTracker()

        dataTracker.startTracking(trackedDataEntry(::isJumpAttacking), false)
        dataTracker.startTracking(trackedDataEntry(::jumpAttackPower), 0)
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
