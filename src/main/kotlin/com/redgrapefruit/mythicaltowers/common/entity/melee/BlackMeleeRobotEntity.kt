package com.redgrapefruit.mythicaltowers.common.entity.melee

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.MAX_ROB_ABILITY_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.NBT_ROB_ABILITY_USES
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotStatics.ROB_CHANCE
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import kotlin.random.Random

class BlackMeleeRobotEntity(type: EntityType<BlackMeleeRobotEntity>, world: World) : MeleeRobotEntity(type, world) {
    private var robAbilityUses = 0

    override fun onAttacking(target: Entity?) {
        // The ability only works on players
        if (target !is PlayerEntity) {
            super.onAttacking(target)
            return
        }

        if (robAbilityUses <= MAX_ROB_ABILITY_USES) {
            // Check chance
            val chance = Random.nextInt(0, 101)
            if (chance <= ROB_CHANCE) {
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

        nbt.putInt(NBT_ROB_ABILITY_USES, robAbilityUses)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        robAbilityUses = nbt.getInt(NBT_ROB_ABILITY_USES)
    }

    companion object {
        val ATTRIBUTES: DefaultAttributeContainer.Builder = createHostileAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 110.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.37)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 62.5)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0)
    }
}
