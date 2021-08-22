package com.redgrapefruit.mythicaltowers.entity

import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.FollowTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import kotlin.random.Random

sealed class TurretRobotEntity(type: EntityType<out HostileEntity>, world: World) : HostileEntity(type, world) {
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 11.0f))
        goalSelector.add(5, LookAroundGoal(this))
        goalSelector.add(5, WanderAroundFarGoal(this, 1.0))
        targetSelector.add(1, FollowTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, FollowTargetGoal(this, WolfEntity::class.java, true))
    }

    override fun initialize(
        world: ServerWorldAccess?,
        difficulty: LocalDifficulty?,
        spawnReason: SpawnReason?,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        // Add slowness & weakness status effects
        addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, Random.nextInt(100, 200)))
        addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, Random.nextInt(70, 140)))

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }
}
