package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

private const val SPEED_BOOST_VALUE = 3.0f

/**
 * The length of the speed boost in ticks
 */
private const val SPEED_BOOST_LENGTH = 80

/**
 * The maximum amount of times the ability can be used
 */
private const val SPEED_BOOST_MAX_USES = 4

// NBT

private const val NBT_SPEED_BOOST_TICKS = "speedBoostTicks"
private const val NBT_SPEED_BOOST_USES = "speedBoostUses"
private const val NBT_IS_UNDER_SPEED_BOOST = "isUnderSpeedBoost"

/**
 * An orange melee robot with temporary speed boost
 */
class OrangeMeleeRobotEntity(type: EntityType<OrangeMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * The amount of ticks the ability has been going for
     */
    private var speedBoostTicks = 0

    /**
     * The current amount of times the ability has been used
     */
    private var speedBoostUses = 0

    /**
     * Is the ability ongoing
     */
    private var isUnderSpeedBoost = false

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        if (!isUnderSpeedBoost && speedBoostUses < SPEED_BOOST_MAX_USES) {
            isUnderSpeedBoost = true
        }
    }

    override fun tick() {
        super.tick()

        if (isUnderSpeedBoost) {
            ++speedBoostTicks

            movementSpeed += SPEED_BOOST_VALUE

            if (speedBoostTicks >= SPEED_BOOST_LENGTH) {
                speedBoostTicks = 0
                isUnderSpeedBoost = false
                movementSpeed -= SPEED_BOOST_VALUE
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_SPEED_BOOST_TICKS, speedBoostTicks)
        nbt.putInt(NBT_SPEED_BOOST_USES, speedBoostUses)
        nbt.putBoolean(NBT_IS_UNDER_SPEED_BOOST, isUnderSpeedBoost)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        speedBoostTicks = nbt.getInt(NBT_SPEED_BOOST_TICKS)
        speedBoostUses = nbt.getInt(NBT_SPEED_BOOST_USES)
        isUnderSpeedBoost = nbt.getBoolean(NBT_IS_UNDER_SPEED_BOOST)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 60.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.22)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.7)
            .add(EntityAttributes.GENERIC_ARMOR, 45.0)
    }
}
