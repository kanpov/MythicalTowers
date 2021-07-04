package com.redgrapefruit.mythicaltowers.common.entity.melee

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

private const val KNOCKBACK = 3.0
private const val MAX_KNOCKBACK_USES = 5

// NBT

private const val NBT_KNOCKBACK_USES = "maxKnockbackUses"

class YellowMeleeRobotEntity(type: EntityType<YellowMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    // DataTracker keys
    private lateinit var knockbackUsesKey: TrackedData<Int>

    /**
     * Maximum amount of times the knockback ability can be used
     */
    private var knockbackUses: Int
        get() = dataTracker.get(knockbackUsesKey)
        set(value) = dataTracker.set(knockbackUsesKey, value)

    override fun initDataTracker() {
        super.initDataTracker()

        knockbackUsesKey = DataTracker.registerData(YellowMeleeRobotEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        dataTracker.startTracking(knockbackUsesKey, 0)
    }

    override fun onAttacking(target: Entity) {
        if (target !is LivingEntity) {
            super.onAttacking(target)
            return
        }

        if (knockbackUses < MAX_KNOCKBACK_USES) {
            target.takeKnockback(KNOCKBACK, target.x - x, z - target.z)
            ++knockbackUses
        }

        super.onAttacking(target)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_KNOCKBACK_USES, knockbackUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        knockbackUses = nbt.getInt(NBT_KNOCKBACK_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.17)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.9)
            .add(EntityAttributes.GENERIC_ARMOR, 20.0)
    }
}
