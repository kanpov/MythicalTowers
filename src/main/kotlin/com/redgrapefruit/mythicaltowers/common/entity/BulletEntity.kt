package com.redgrapefruit.mythicaltowers.common.entity

import com.redgrapefruit.mythicaltowers.client.MythicalTowersClient
import com.redgrapefruit.mythicaltowers.common.registry.ItemRegistry
import com.redgrapefruit.mythicaltowers.common.util.BulletSpawnPacket
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.Packet
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

/**
 * The base abstract bullet entity that shooter entities use.
 */
abstract class BulletEntity(private val item: Item, type: EntityType<out BulletEntity>, world: World) : PersistentProjectileEntity(type, world) {
    override fun initDataTracker() = Unit // for some reason this needs to be implemented

    /**
     * An event called on the block hit _before_ the bullet is discarded
     */
    protected open fun hitBlockBeforeDiscarded(blockHitResult: BlockHitResult) = Unit

    /**
     * An event called on the block hit _after_ the bullet is discarded
     */
    protected open fun hitBlockAfterDiscarded(blockHitResult: BlockHitResult) = Unit

    /**
     * An event called on the entity hit _before_ the bullet deals damage to the [LivingEntity]
     */
    protected open fun hitTarget(entityHitResult: EntityHitResult) = Unit

    override fun asItemStack(): ItemStack = ItemStack(item)

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)

        hitBlockBeforeDiscarded(blockHitResult)
        // Remove the entity on the server
        if (!world.isClient) discard()
        hitBlockAfterDiscarded(blockHitResult)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)

        val entity = entityHitResult.entity
        if (entity is LivingEntity) {
            hitTarget(entityHitResult)
            discard()
        }
    }

    override fun createSpawnPacket(): Packet<*> {
        return BulletSpawnPacket.create(this, MythicalTowersClient.BULLET_SPAWN_PACKET)
    }
}

class GreenBulletEntity(type: EntityType<GreenBulletEntity>, world: World) : BulletEntity(ItemRegistry.GREEN_BULLET, type, world) {

}
