package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.KNOCKBACK
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.MAX_KNOCKBACK_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_KNOCKBACK_USES
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

class YellowMeleeRobotEntity(type: EntityType<YellowMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    /**
     * Maximum amount of times the knockback ability can be used
     */
    private var knockbackAbilityUses = 0

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (knockbackAbilityUses < MAX_KNOCKBACK_USES) {
            target.takeKnockback(KNOCKBACK, target.x - x, z - target.z)
            ++knockbackAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_KNOCKBACK_USES, knockbackAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        knockbackAbilityUses = nbt.getInt(NBT_KNOCKBACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.17)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 17.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
    }
}
