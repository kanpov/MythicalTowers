package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_IS_UNDER_SPEED_BOOST
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_SPEED_BOOST_TICKS
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_SPEED_BOOST_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.SPEED_BOOST_LENGTH
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.SPEED_BOOST_MAX_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.SPEED_BOOST_VALUE
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

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
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 25.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0)
    }
}
