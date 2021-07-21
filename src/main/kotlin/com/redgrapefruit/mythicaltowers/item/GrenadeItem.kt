package com.redgrapefruit.mythicaltowers.item

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.GROUP
import com.redgrapefruit.mythicaltowers.isClient
import com.redgrapefruit.mythicaltowers.onServer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

/**
 * A literal grenade.
 */
class GrenadeItem : Item(Settings().group(GROUP)) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        // Creep out the player
        world.playSound(user, user.x, user.y, user.z, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.HOSTILE, 1.0F, 1.0F)
        // Set a cooldown
        user.itemCooldownManager.set(this, 40)

        onServer {
            // TODO: Spawn entity when it's done
        }

        // Decrement the grenade if not on creative
        if (!user.abilities.creativeMode) {
            stack.decrement(1)
        }

        return TypedActionResult.success(stack, isClient())
    }
}
