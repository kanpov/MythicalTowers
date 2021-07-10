package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import java.util.*

/**
 * Maximum amount of times the enemy-stun ability can be used
 */
private const val MAX_ENEMY_STUN_ABILITY_USES = 2

/**
 * The cooldown for using the enemy-stun ability
 */
private const val ENEMY_STUN_COOLDOWN = 40

// NBT

private const val NBT_ENEMY_STUN_ABILITY_USES = "enemyStunAbilityUses"
private const val NBT_ENEMY_STUN_COOLDOWN_STATE = "enemyStunCooldownState"

// UUID

private val UUID_SPEED_MODIFIER = UUID.fromString("ad593ae7-d328-48ee-9d11-df5783fc3fba")

class GrayMeleeRobotEntity(type: EntityType<GrayMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * How many times the enemy-stun ability has been used
     */
    private var enemyStunAbilityUses = 0

    /**
     * The state of the enemy-stun ability cooldown
     */
    private var enemyStunCooldownState = 0

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        val instance = target.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)

        if (instance == null) {
            super.onAttacking(target)
            return
        }

        if (enemyStunCooldownState >= ENEMY_STUN_COOLDOWN && enemyStunAbilityUses <= MAX_ENEMY_STUN_ABILITY_USES) {
            if (instance.getModifier(UUID_SPEED_MODIFIER) != null) {
                instance.removeModifier(UUID_SPEED_MODIFIER)
            }

            instance.addTemporaryModifier(
                EntityAttributeModifier(
                    UUID_SPEED_MODIFIER,
                    "Enemy stun slow",
                    0.0000000001,
                    EntityAttributeModifier.Operation.MULTIPLY_TOTAL))

            ++enemyStunAbilityUses
            enemyStunCooldownState = 0

            // The last time, remove the modifier for good
            if (enemyStunAbilityUses == MAX_ENEMY_STUN_ABILITY_USES) {
                instance.removeModifier(UUID_SPEED_MODIFIER)
            }
        }

        super.onAttacking(target)
    }

    override fun tick() {
        super.tick()

        // Increase cooldown
        ++enemyStunCooldownState
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_ENEMY_STUN_ABILITY_USES, enemyStunAbilityUses)
        nbt.putInt(NBT_ENEMY_STUN_COOLDOWN_STATE, enemyStunCooldownState)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        enemyStunAbilityUses = nbt.getInt(NBT_ENEMY_STUN_ABILITY_USES)
        enemyStunCooldownState = nbt.getInt(NBT_ENEMY_STUN_COOLDOWN_STATE)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.34)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 150.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0)
    }
}
