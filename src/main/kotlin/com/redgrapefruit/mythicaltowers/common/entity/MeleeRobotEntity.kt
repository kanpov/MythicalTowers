package com.redgrapefruit.mythicaltowers.common.entity

import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.FollowTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World

/**
 * After how many ticks is the stun going to end
 */
private const val STUN_VALUE = 40

/**
 * A melee robot spawns in, has a stun for 2 seconds and performs melee attacks
 */
abstract class MeleeRobotEntity(type: EntityType<MeleeRobotEntity>, world: World) : HostileEntity(type, world) {
    /**
     * Is the robot currently stunned
     */
    private val isStunned: TrackedData<Boolean> =
        DataTracker.registerData(MeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

    /**
     * How much ticks are left until the stun is taken off
     */
    private val stunTicks: TrackedData<Int> =
        DataTracker.registerData(MeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 7.0f))
        goalSelector.add(5, LookAroundGoal(this))
        targetSelector.add(1, FollowTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, FollowTargetGoal(this, VillagerEntity::class.java, true))
        targetSelector.add(3, MeleeAttackGoal(this, 2.0, false))
    }

    override fun initDataTracker() {
        super.initDataTracker()

        dataTracker.startTracking(isStunned, false)
        dataTracker.startTracking(stunTicks, 0)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        // Init the stun
        dataTracker.set(isStunned, true)
        dataTracker.set(stunTicks, STUN_VALUE)

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()

        // Decrement the stun
        if (dataTracker.get(isStunned)) {
            dataTracker.set(stunTicks, dataTracker.get(stunTicks) - 1)
        }
        // Check if the stun has ended
        if (dataTracker.get(stunTicks) <= 0) {
            dataTracker.set(isStunned, false)
            dataTracker.set(stunTicks, 0)
        }
    }

    override fun tickMovement() {
        // Don't move if the stun hasn't passed yet
        if (!dataTracker.get(isStunned)) super.tickMovement()
    }
}