package com.redgrapefruit.mythicaltowers.item

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.GROUP
import com.redgrapefruit.mythicaltowers.entity.GrenadeEntity
import com.redgrapefruit.mythicaltowers.isClient
import com.redgrapefruit.mythicaltowers.onServer
import net.minecraft.entity.EntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

/**
 * A literal grenade.
 */
sealed class GrenadeItem : Item(Settings().group(GROUP)) {
    /**
     * The factory function creating the linked entity.
     *
     * Example:
     * ```kotlin
     * override val entityFactory = { world, user -> MyGrenadeEntity(EntityRegistry.MY_GRENADE_ENTITY, world, user) }
     * ```
     */
    abstract val entityFactory: (World, PlayerEntity) -> GrenadeEntity

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        // Creep out the player
        world.playSound(user, user.x, user.y, user.z, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.HOSTILE, 1.0F, 1.0F)
        // Set a cooldown
        user.itemCooldownManager.set(this, 40)

        onServer {
            val entity = entityFactory.invoke(world, user)
            entity.setItem(stack)
            entity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 0.0F)
            world.spawnEntity(entity)
        }

        // Increment stat
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        // Decrement the grenade if not on creative
        if (!user.abilities.creativeMode) {
            stack.decrement(1)
        }

        return TypedActionResult.success(stack, isClient())
    }
}
