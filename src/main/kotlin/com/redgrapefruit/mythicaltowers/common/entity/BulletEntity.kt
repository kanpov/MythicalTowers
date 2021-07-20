package com.redgrapefruit.mythicaltowers.common.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

/**
 * The base abstract bullet entity that shooter entities use.
 */
abstract class BulletEntity(private val damage: Float, type: EntityType<out BulletEntity>, world: World) : PersistentProjectileEntity(type, world) {
    override fun initDataTracker() = Unit // for some reason this needs to be implemented

    /**
     * Sets up [PersistentProjectileEntity]'s properties like damage, pierce level etc.
     */
    protected abstract fun setup()

    /**
     * An event called on the block hit _before_ the bullet is discarded
     */
    protected open fun blockHitPreDiscarded(blockHitResult: BlockHitResult) = Unit

    /**
     * An event called on the block hit _after_ the bullet is discarded
     */
    protected open fun blockHitPostDiscarded(blockHitResult: BlockHitResult) = Unit

    /**
     * An event called on the entity hit _before_ the bullet deals damage to the [LivingEntity]
     */
    protected open fun preDamageDealt(entityHitResult: EntityHitResult) = Unit

    /**
     * An event called on the entity hit _after_ the bullet deals damage to the [LivingEntity]
     */
    protected open fun postDamageDealt(entityHitResult: EntityHitResult) = Unit

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)

        blockHitPreDiscarded(blockHitResult)

        // Remove the entity on the server
        if (!world.isClient) discard()

        blockHitPostDiscarded(blockHitResult)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)

        val entity = entityHitResult.entity
        if (entity is LivingEntity) {
            preDamageDealt(entityHitResult)

            // Just damage the entity if it's a LivingEntity and then discard
            entityHitResult.entity.damage(DamageSource.mobProjectile(this, owner as? LivingEntity), damage)
            discard()

            postDamageDealt(entityHitResult)
        }
    }
}
