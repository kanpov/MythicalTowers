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
private const val MAX_JUMP_ATTACK_POWER = 20

/**
 * The conversion rate from power in ticks to power in attribute modifier value
 */
private const val TICK_TO_MODIFIER_RATE = 1.0

/**
 * The maximum amount of times the jump-attack can be used
 */
private const val MAX_JUMP_ATTACK_USES = 2

// NBT

private const val NBT_IS_JUMP_ATTACKING = "isJumpAttacking"
private const val NBT_JUMP_ATTACK_POWER = "jumpAttackPower"
private const val NBT_JUMP_ATTACK_USES = "jumpAttackUses"

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    // DataTracker keys
    private lateinit var isJumpAttackingKey: TrackedData<Boolean>
    private lateinit var jumpAttackPowerKey: TrackedData<Int>
    private lateinit var jumpAttackUsesKey: TrackedData<Int>

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

    /**
     * The amount of times the jump-attack ability has been used
     */
    private var jumpAttackUses
        get() = dataTracker.get(jumpAttackUsesKey)
        set(value) = dataTracker.set(jumpAttackUsesKey, value)

    override fun initDataTracker() {
        super.initDataTracker()

        isJumpAttackingKey = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        jumpAttackPowerKey = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        jumpAttackUsesKey = DataTracker.registerData(GreenMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        dataTracker.startTracking(isJumpAttackingKey, false)
        dataTracker.startTracking(jumpAttackPowerKey, MAX_JUMP_ATTACK_POWER)
        dataTracker.startTracking(jumpAttackUsesKey, 0)
    }

    override fun onAttacking(target: Entity) {
        super.onAttacking(target)

        val uses = jumpAttackUses
        val power = jumpAttackPower
        val `is` = isJumpAttacking

        if (jumpAttackUses <= MAX_JUMP_ATTACK_USES && jumpAttackPower >= MAX_JUMP_ATTACK_POWER) {
            jumpAttackPower = 0
            isJumpAttacking = true
        }
    }

    override fun tick() {
        super.tick()

        val modifierMultimap = ImmutableMultimap.of(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                "jump_attack_damage_boost",
                jumpAttackPower * TICK_TO_MODIFIER_RATE,
                EntityAttributeModifier.Operation.ADDITION
            )
        )

        if (isJumpAttacking) {
            ++jumpAttackPower

            attributes.addTemporaryModifiers(modifierMultimap)

            if (jumpAttackPower >= MAX_JUMP_ATTACK_POWER) {
                isJumpAttacking = false
                jumpAttackPower = 0
                ++jumpAttackUses
                attributes.removeModifiers(modifierMultimap)
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

//        nbt.putBoolean(NBT_IS_JUMP_ATTACKING, isJumpAttacking)
//        nbt.putInt(NBT_JUMP_ATTACK_POWER, jumpAttackPower)
//        nbt.putInt(NBT_JUMP_ATTACK_USES, jumpAttackUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

//        isJumpAttacking = nbt.getBoolean(NBT_IS_JUMP_ATTACKING)
//        jumpAttackPower = nbt.getInt(NBT_JUMP_ATTACK_POWER)
//        jumpAttackUses = nbt.getInt(NBT_JUMP_ATTACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
            .add(EntityAttributes.GENERIC_ARMOR, 6.0)
    }
}
