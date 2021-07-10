package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.util.LivingEntityMixinAccess
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

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

        if (enemyStunCooldownState >= ENEMY_STUN_COOLDOWN && enemyStunAbilityUses <= MAX_ENEMY_STUN_ABILITY_USES) {
            if (target is LivingEntityMixinAccess) {
                target.stun()
            } else {
                LOGGER.error("Critical failure in the mod's mixins. target !is LivingEntityMixinAccess")
            }

            ++enemyStunAbilityUses
            enemyStunCooldownState = 0
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
