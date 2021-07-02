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
 * The peak of the jump attack when it starts to decrease
 */
private const val JUMP_ATTACK_POWER_PEAK = 80

/**
 * How much the damage boost is nerfed
 */
private const val JUMP_ATTACK_DAMAGE_NERF = 0.0001

/**
 * How much the jump attack ability can be used
 */
private const val JUMP_ATTACK_USES = 2

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * Is currently jump attacking
     */
    private lateinit var isJumpAttacking: TrackedData<Boolean>

    /**
     * The current power of the jump attack
     */
    private lateinit var jumpAttackPower: TrackedData<Int>

    /**
     * Is the jump attack damage decrementing
     */
    private lateinit var jumpAttackDecrementing: TrackedData<Boolean>

    /**
     * How much the jump-attack ability has been used for
     */
    private lateinit var jumpAttackUses: TrackedData<Int>

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        // Only enable jump-attack if there are any left
        if (dataTracker.get(jumpAttackUses) < JUMP_ATTACK_USES) {
            dataTracker.set(isJumpAttacking, true)
        }
    }

    override fun initDataTracker() {
        super.initDataTracker()

        isJumpAttacking = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        jumpAttackPower = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        jumpAttackDecrementing = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        jumpAttackUses = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        dataTracker.startTracking(isJumpAttacking, false)
        dataTracker.startTracking(jumpAttackPower, 0)
        dataTracker.startTracking(jumpAttackDecrementing, false)
        dataTracker.startTracking(jumpAttackUses, 0)
    }

    override fun tick() {
        super.tick()

        // Add a damage modifier (0 if not jump-attacking)
        val power = dataTracker.get(jumpAttackPower)
        val mod = dataTracker.get(jumpAttackPower).toDouble() * JUMP_ATTACK_DAMAGE_NERF

        attributes.addTemporaryModifiers(
                ImmutableMultimap.of(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                "Jump Attack Modifier",
                dataTracker.get(jumpAttackPower).toDouble() / JUMP_ATTACK_DAMAGE_NERF,
                EntityAttributeModifier.Operation.ADDITION)
            ))

        // If jump attacking, increment if before peak or decrement after/during peak
        if (dataTracker.get(isJumpAttacking)) {
            // Set decrementing status
            if (dataTracker.get(jumpAttackPower) >= JUMP_ATTACK_POWER_PEAK) {
                dataTracker.set(jumpAttackDecrementing, true)
            }
            if (dataTracker.get(jumpAttackPower) <= 0) {
                dataTracker.set(jumpAttackDecrementing, false)
            }

            // Increment/decrement
            if (!dataTracker.get(jumpAttackDecrementing)) {
                dataTracker.set(jumpAttackPower, dataTracker.get(jumpAttackPower) + 1)
            } else {
                dataTracker.set(jumpAttackPower, dataTracker.get(jumpAttackPower) - 1)
            }

            // If the jump attack has ended, disable the flag and mark the use
            if (dataTracker.get(jumpAttackPower) <= 0) {
                dataTracker.set(isJumpAttacking, false)
                dataTracker.set(jumpAttackUses, dataTracker.get(jumpAttackUses) + 1)
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean("Is Robot Jump Attacking", dataTracker.get(isJumpAttacking))
        nbt.putInt("Jump Attack Power", dataTracker.get(jumpAttackPower))
        nbt.putBoolean("Jump Attack Decrementing", dataTracker.get(jumpAttackDecrementing))
        nbt.putInt("Jump Attack Uses", dataTracker.get(jumpAttackUses))
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        dataTracker.set(isJumpAttacking, nbt.getBoolean("Is Robot Jump Attacking"))
        dataTracker.set(jumpAttackPower, nbt.getInt("Jump Attack Power"))
        dataTracker.set(jumpAttackDecrementing, nbt.getBoolean("Jump Attack Decrementing"))
        dataTracker.set(jumpAttackUses, nbt.getInt("Jump Attack Uses"))
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.5)
            .add(EntityAttributes.GENERIC_ARMOR, 6.0)
    }
}
