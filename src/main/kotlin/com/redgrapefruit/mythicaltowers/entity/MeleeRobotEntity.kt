package com.redgrapefruit.mythicaltowers.entity

import com.redgrapefruit.mythicaltowers.entity.MeleeRobotStatics.NBT_IS_STUNNED
import com.redgrapefruit.mythicaltowers.entity.MeleeRobotStatics.NBT_STUN_TICKS
import com.redgrapefruit.mythicaltowers.entity.MeleeRobotStatics.STUN_VALUE
import com.redgrapefruit.mythicaltowers.registry.EntityRegistry
import net.minecraft.entity.*
import net.minecraft.entity.ai.goal.FollowTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import kotlin.random.Random

sealed class MeleeRobotEntity(type: EntityType<out HostileEntity>, world: World) : HostileEntity(type, world) {
    /**
     * Is the stun currently ongoing
     */
    private var isStunned = false

    /**
     * How many ticks have elapsed since the stun has begun
     */
    private var stunTicks = 0

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 7.0f))
        goalSelector.add(5, LookAroundGoal(this))
        targetSelector.add(1, FollowTargetGoal(this, PlayerEntity::class.java, true))
        targetSelector.add(2, FollowTargetGoal(this, VillagerEntity::class.java, true))
        targetSelector.add(3, MeleeAttackGoal(this, 2.0, false))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        // Init the stun
        isStunned = true
        stunTicks = STUN_VALUE

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()

        // Decrement the stun
        if (isStunned) {
            --stunTicks
        }
        // Check if the stun has ended
        if (stunTicks <= 0) {
            isStunned = false
            stunTicks = 0
        }
    }

    override fun tickMovement() {
        // Don't move if the stun hasn't passed yet
        if (!isStunned) super.tickMovement()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean(NBT_IS_STUNNED, isStunned)
        nbt.putInt(NBT_STUN_TICKS, stunTicks)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        isStunned = nbt.getBoolean(NBT_IS_STUNNED)
        stunTicks = nbt.getInt(NBT_STUN_TICKS)
    }

    override fun isAngryAt(player: PlayerEntity): Boolean = !isStunned
}

class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var isJumpAttacking = false
    private var jumpAttackUses = 0

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (jumpAttackUses <= MeleeRobotStatics.MAX_JUMP_ATTACK_USES) {
            isJumpAttacking = true
            jump()
            target.damage(DamageSource.mob(this), MeleeRobotStatics.JUMP_ATTACK_DAMAGE)
            ++jumpAttackUses
            isJumpAttacking = false
        }

        super.onAttacking(target)
    }

    override fun computeFallDamage(fallDistance: Float, damageMultiplier: Float): Int {
        // Suppress fall damage if jump attacking
        return if (isJumpAttacking) 0 else super.computeFallDamage(fallDistance, damageMultiplier)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean(MeleeRobotStatics.NBT_IS_JUMP_ATTACKING, isJumpAttacking)
        nbt.putInt(MeleeRobotStatics.NBT_JUMP_ATTACK_USES, jumpAttackUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        isJumpAttacking = nbt.getBoolean(MeleeRobotStatics.NBT_IS_JUMP_ATTACKING)
        jumpAttackUses = nbt.getInt(MeleeRobotStatics.NBT_JUMP_ATTACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
    }
}

class YellowMeleeRobotEntity(type: EntityType<YellowMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var knockbackAbilityUses = 0

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (knockbackAbilityUses < MeleeRobotStatics.MAX_KNOCKBACK_USES) {
            target.takeKnockback(MeleeRobotStatics.KNOCKBACK, target.x - x, z - target.z)
            ++knockbackAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_KNOCKBACK_USES, knockbackAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        knockbackAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_KNOCKBACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.17)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 17.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
    }
}

class OrangeMeleeRobotEntity(type: EntityType<OrangeMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var speedBoostTicks = 0
    private var speedBoostUses = 0
    private var isUnderSpeedBoost = false

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        if (!isUnderSpeedBoost && speedBoostUses < MeleeRobotStatics.SPEED_BOOST_MAX_USES) {
            isUnderSpeedBoost = true
        }
    }

    override fun tick() {
        super.tick()

        if (isUnderSpeedBoost) {
            ++speedBoostTicks

            movementSpeed += MeleeRobotStatics.SPEED_BOOST_VALUE

            if (speedBoostTicks >= MeleeRobotStatics.SPEED_BOOST_LENGTH) {
                speedBoostTicks = 0
                isUnderSpeedBoost = false
                movementSpeed -= MeleeRobotStatics.SPEED_BOOST_VALUE
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_SPEED_BOOST_TICKS, speedBoostTicks)
        nbt.putInt(MeleeRobotStatics.NBT_SPEED_BOOST_USES, speedBoostUses)
        nbt.putBoolean(MeleeRobotStatics.NBT_IS_UNDER_SPEED_BOOST, isUnderSpeedBoost)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        speedBoostTicks = nbt.getInt(MeleeRobotStatics.NBT_SPEED_BOOST_TICKS)
        speedBoostUses = nbt.getInt(MeleeRobotStatics.NBT_SPEED_BOOST_USES)
        isUnderSpeedBoost = nbt.getBoolean(MeleeRobotStatics.NBT_IS_UNDER_SPEED_BOOST)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 60.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.22)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 25.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0)
    }
}

class RedMeleeRobotEntity(type: EntityType<RedMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var effectAbilityUses = 0
    private var currentEffectDuration = 0.0f

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (effectAbilityUses <= MeleeRobotStatics.MAX_EFFECT_ABILITY_USES) {
            MeleeRobotStatics.EFFECT_PROBABILITIES.forEach { (effect, probability) ->
                // Use probability
                val chance = Random.nextInt(100)
                if (chance <= probability) {
                    // Make a StatusEffectInstance and apply it
                    if (currentEffectDuration == 0.0f) currentEffectDuration =
                        MeleeRobotStatics.EFFECT_STARTING_DURATION

                    val instance = StatusEffectInstance(
                        effect,
                        currentEffectDuration.toInt(),
                        Random.nextInt(0, 3)
                    )
                    target.addStatusEffect(instance)
                }
            }

            // Decrease duration using the modifier
            currentEffectDuration *= MeleeRobotStatics.EFFECT_STARTING_DURATION_INCREASE

            ++effectAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_EFFECT_ABILITY_USES, effectAbilityUses)
        nbt.putFloat(MeleeRobotStatics.NBT_CURRENT_EFFECT_DURATION, currentEffectDuration)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        effectAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_EFFECT_ABILITY_USES)
        currentEffectDuration = nbt.getFloat(MeleeRobotStatics.NBT_CURRENT_EFFECT_DURATION)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 70.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 32.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0)
    }
}

class BlueMeleeRobotEntity(type: EntityType<BlueMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var cloneAbilityUses = 0
    private var cloneAbilityCooldownState = 0
    private var isClone = false

    override fun tick() {
        super.tick()

        if (!isClone) ++cloneAbilityCooldownState
    }

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        // Check all the conditions
        if (cloneAbilityCooldownState >= MeleeRobotStatics.CLONE_ABILITY_COOLDOWN && !isClone && cloneAbilityUses <= MeleeRobotStatics.MAX_CLONE_ABILITY_USES) {
            cloneAbilityCooldownState = 0

            // Summon the clones
            for (i in 0..MeleeRobotStatics.CLONE_AMOUNT) {
                val clone = BlueMeleeRobotEntity(EntityRegistry.BLUE_MELEE_ROBOT, world)
                // Mark as clone to avoid infinite loop of clones
                clone.isClone = true
                // Spawn nearby
                clone.setPosition(pos.x + (Random.nextInt(3)), pos.y + (Random.nextInt(3)), pos.z + (Random.nextInt(3)))

                world.spawnEntity(clone)
            }

            // Count the use
            ++cloneAbilityUses
        }
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        cloneAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_CLONE_ABILITY_USES)
        cloneAbilityCooldownState = nbt.getInt(MeleeRobotStatics.NBT_CLONE_ABILITY_COOLDOWN_STATE)
        isClone = nbt.getBoolean(MeleeRobotStatics.NBT_IS_CLONE)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_CLONE_ABILITY_USES, cloneAbilityUses)
        nbt.putInt(MeleeRobotStatics.NBT_CLONE_ABILITY_COOLDOWN_STATE, cloneAbilityCooldownState)
        nbt.putBoolean(MeleeRobotStatics.NBT_IS_CLONE, isClone)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 80.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 40.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
    }
}

class PurpleMeleeRobotEntity(type: EntityType<PurpleMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var buffAbilityUses = 0
    private var currentBuffDuration = 0.0f

    override fun onAttacking(target: Entity?) {
        if (buffAbilityUses <= MeleeRobotStatics.MAX_BUFF_ABILITY_USES) {
            MeleeRobotStatics.BUFF_PROBABILITIES.forEach { (buff, probability) ->
                // Use probability
                val chance = Random.nextInt(101)
                if (chance <= probability) {
                    // Make a StatusEffectInstance and apply it
                    if (currentBuffDuration == 0.0f) currentBuffDuration = MeleeRobotStatics.BUFF_STARTING_DURATION

                    val instance = StatusEffectInstance(
                        buff,
                        currentBuffDuration.toInt(),
                        Random.nextInt(0, 2)
                    )
                    addStatusEffect(instance)
                }
            }

            // Increase duration via the modifier and count the use
            currentBuffDuration *= MeleeRobotStatics.BUFF_STARTING_DURATION_INCREASE

            ++buffAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_BUFF_ABILITY_USES, buffAbilityUses)
        nbt.putFloat(MeleeRobotStatics.NBT_CURRENT_BUFF_DURATION, currentBuffDuration)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        buffAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_BUFF_ABILITY_USES)
        currentBuffDuration = nbt.getFloat(MeleeRobotStatics.NBT_CURRENT_BUFF_DURATION)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 90.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.31)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 47.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
    }
}

class GrayMeleeRobotEntity(type: EntityType<GrayMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var levitateAbilityUses = 0

    override fun onAttacking(target: Entity?) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (levitateAbilityUses <= MeleeRobotStatics.MAX_LEVITATE_ABILITY_USES) {
            target.addStatusEffect(StatusEffectInstance(
                StatusEffects.LEVITATION,
                MeleeRobotStatics.LEVITATION_DURATION, 0))

            ++levitateAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_LEVITATE_ABILITY_USES, levitateAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        levitateAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_LEVITATE_ABILITY_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.34)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 55.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0)
    }
}

class BlackMeleeRobotEntity(type: EntityType<BlackMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var robAbilityUses = 0

    override fun onAttacking(target: Entity?) {
        // The ability only works on players
        if (target !is PlayerEntity) {
            super.onAttacking(target)
            return
        }

        if (robAbilityUses <= MeleeRobotStatics.MAX_ROB_ABILITY_USES) {
            // Check chance
            val chance = Random.nextInt(0, 101)
            if (chance <= MeleeRobotStatics.ROB_CHANCE) {
                var targetSlotIndex = Random.nextInt(0, target.inventory.size())
                // Pick a slot until it's not empty
                while (target.inventory.getStack(targetSlotIndex).isEmpty) {
                    targetSlotIndex = Random.nextInt(0, target.inventory.size())
                }
                // Empty (rob) that slot and put paper in it just to troll the player
                target.inventory.setStack(targetSlotIndex, ItemStack(Items.PAPER))
            }
            ++robAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(MeleeRobotStatics.NBT_ROB_ABILITY_USES, robAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        robAbilityUses = nbt.getInt(MeleeRobotStatics.NBT_ROB_ABILITY_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 110.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.37)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 62.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0)
    }
}