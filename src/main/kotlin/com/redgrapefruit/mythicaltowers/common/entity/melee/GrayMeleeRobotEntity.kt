package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.LEVITATION_DURATION
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.MAX_LEVITATE_ABILITY_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_LEVITATE_ABILITY_USES
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

class GrayMeleeRobotEntity(type: EntityType<GrayMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var levitateAbilityUses = 0

    override fun onAttacking(target: Entity?) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (levitateAbilityUses <= MAX_LEVITATE_ABILITY_USES) {
            target.addStatusEffect(StatusEffectInstance(StatusEffects.LEVITATION, LEVITATION_DURATION, 0))

            ++levitateAbilityUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_LEVITATE_ABILITY_USES, levitateAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        levitateAbilityUses = nbt.getInt(NBT_LEVITATE_ABILITY_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.34)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 55.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0)
    }
}
