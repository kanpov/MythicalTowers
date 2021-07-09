package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

private const val JUMP_ATTACK_DAMAGE = 10f
private const val MAX_JUMP_ATTACK_USES = 3

// NBT

private const val NBT_IS_JUMP_ATTACKING = "isJumpAttacking"
private const val NBT_JUMP_ATTACK_USES = "jumpAttackUses"

/**
 * The green melee robot with speed boost and jump attacks
 */
class GreenMeleeRobotEntity(type: EntityType<GreenMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * Is the robot currently jump-attacking the enemy
     */
    private var isJumpAttacking = false

    /**
     * The amount of times the jump-attack ability has been used
     */
    private var jumpAttackUses = 0

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (jumpAttackUses <= MAX_JUMP_ATTACK_USES) {
            isJumpAttacking = true
            jump()
            target.damage(DamageSource.mob(this), JUMP_ATTACK_DAMAGE)
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

        nbt.putBoolean(NBT_IS_JUMP_ATTACKING, isJumpAttacking)
        nbt.putInt(NBT_JUMP_ATTACK_USES, jumpAttackUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        isJumpAttacking = nbt.getBoolean(NBT_IS_JUMP_ATTACKING)
        jumpAttackUses = nbt.getInt(NBT_JUMP_ATTACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
    }
}
