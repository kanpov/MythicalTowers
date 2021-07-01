package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.google.common.collect.ImmutableMultimap
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

private const val JUMP_ATTACK_POWER_PEAK = 4

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * Is currently jump attacking
     */
    private val isJumpAttacking: TrackedData<Boolean> =
        DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

    /**
     * The current power of the jump attack
     */
    private val jumpAttackPower: TrackedData<Int> =
        DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        dataTracker[isJumpAttacking] = true
    }

    override fun initDataTracker() {
        super.initDataTracker()

        dataTracker.startTracking(isJumpAttacking, false)
        dataTracker.startTracking(jumpAttackPower, 0)
    }

    override fun tick() {
        super.tick()

        // Add a damage modifier (0 if not jump-attacking)
        attributes.addTemporaryModifiers(ImmutableMultimap.of(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                "Jump Attack Modifier",
                dataTracker[jumpAttackPower].toDouble(),
                EntityAttributeModifier.Operation.ADDITION)))

        // If jump attacking, increment if before peak or decrement after/during peak
        if (dataTracker.get(isJumpAttacking)) {
            if (dataTracker.get(jumpAttackPower) < JUMP_ATTACK_POWER_PEAK) {
                dataTracker.set(jumpAttackPower, dataTracker.get(jumpAttackPower) + 1)
            } else {
                dataTracker.set(jumpAttackPower, dataTracker.get(jumpAttackPower) - 1)
            }

            // If the jump attack has ended, disable the flag
            if (dataTracker.get(jumpAttackPower) <= 0) {
                dataTracker.set(isJumpAttacking, false)
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean("Is Robot Jump Attacking", dataTracker.get(isJumpAttacking))
        nbt.putInt("Jump Attack Power", dataTracker.get(jumpAttackPower))
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        dataTracker.set(isJumpAttacking, nbt.getBoolean("Is Robot Jump Attacking"))
        dataTracker.set(jumpAttackPower, nbt.getInt("Jump Attack Power"))
    }

    companion object {
        val ATTRIBUTES = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
            .add(EntityAttributes.GENERIC_ARMOR, 4.0)
    }
}
