package com.redgrapefruit.mythicaltowers.block

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.RANDOM
import com.redgrapefruit.mythicaltowers.entity.*
import com.redgrapefruit.mythicaltowers.isClient
import com.redgrapefruit.mythicaltowers.onServer
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Items
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion

/**
 * Due to critical constraints in the vanilla TNT system and many mixins and hacks required, the mod uses
 * a reimplementation of the TNT system.
 *
 * The block is placed by the player/entity and turns into an entity once primed
 */
sealed class DisappearingTntBlock<TEntity>(settings: Settings) :
    Block(settings) where TEntity : DisappearingTntEntity {

    // region Properties, constructor and abstract

    /**
     * The blockstate unstable property. If true, the TNT can be lit up
     */
    private lateinit var unstableProperty: BooleanProperty

    init {
        // Setup properties
        defaultState = defaultState.with(unstableProperty, false)
    }

    abstract fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): TEntity

    // endregion

    // region Implementation

    override fun onBlockAdded(
        state: BlockState,
        world: World,
        pos: BlockPos,
        oldState: BlockState,
        notify: Boolean
    ) {
        // If the block has changed and is connected to redstone, explode it and remove it
        if (!oldState.isOf(state.block) && world.isReceivingRedstonePower(pos)) {
            primeTnt(world, pos)
            world.removeBlock(pos, false)
        }
    }

    override fun neighborUpdate(
        state: BlockState,
        world: World,
        pos: BlockPos,
        block: Block,
        fromPos: BlockPos,
        notify: Boolean
    ) {
        // Do roughly the same thing as in onBlockAdded
        if (world.isReceivingRedstonePower(pos)) {
            primeTnt(world, pos)
            world.removeBlock(pos, false)
        }
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        onServer {
            if (!player.isCreative && state.get(unstableProperty)) {
                primeTnt(world, pos)
            }
        }

        super.onBreak(world, pos, state, player)
    }

    override fun onDestroyedByExplosion(world: World, pos: BlockPos, explosion: Explosion) {
        onServer {
            // Create the entity and set its fuse
            val entity: TEntity =
                createEntity(world, pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, explosion.causingEntity)
            entity.initFuse(world.random.nextInt(entity.fuseValue / 4) + entity.fuseValue / 8)
            // Spawn the entity
            world.spawnEntity(entity)
        }
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        onServer {
            val stack = player.getStackInHand(hand)
            val item = stack.item
            return if (item !== Items.FLINT_AND_STEEL && item !== Items.FIRE_CHARGE) {
                super.onUse(state, world, pos, player, hand, hit)
            } else {
                primeTnt(world, pos, player)
                world.setBlockState(pos, Blocks.AIR.defaultState, 11)
                if (!player.isCreative) {
                    if (item === Items.FLINT_AND_STEEL) {
                        stack.damage(1, RANDOM, player as ServerPlayerEntity)
                    } else {
                        stack.decrement(1)
                    }
                }
                ActionResult.success(isClient())
            }
        }
        return ActionResult.SUCCESS
    }

    override fun onProjectileHit(
        world: World,
        state: BlockState,
        hit: BlockHitResult,
        projectile: ProjectileEntity
    ) {
        onServer {
            val entity: Entity? = projectile.owner

            if (projectile.isOnFire) {
                val pos: BlockPos = hit.blockPos

                // Prime the TNT and destroy the block
                primeTnt(world, pos, if (entity is LivingEntity) entity else null)
                world.removeBlock(pos, false)
            }
        }
    }

    override fun shouldDropItemsOnExplosion(explosion: Explosion): Boolean = false

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        unstableProperty = BooleanProperty.of("unstable")

        builder.add(unstableProperty)
    }

    // endregion

    // region Priming TNT

    private fun primeTnt(world: World, pos: BlockPos) = primeTnt(world, pos, null)

    private fun primeTnt(world: World, pos: BlockPos, igniter: LivingEntity?) {
        onServer {
            // Create the entity and spawn it
            val entity = createEntity(world, pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, igniter)
            world.spawnEntity(entity)
            // Play the vanilla prime sound
            world.playSound(
                null,
                entity.x,
                entity.y,
                entity.z,
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F
            )
        }
    }

    // endregion
}

// region Impl-s

class GreenTntBlock(settings: Settings) : DisappearingTntBlock<GreenTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): GreenTntEntity {
        return GreenTntEntity(world, x, y, z, igniter)
    }
}

class YellowTntBlock(settings: Settings) : DisappearingTntBlock<YellowTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): YellowTntEntity {
        return YellowTntEntity(world, x, y, z, igniter)
    }
}

class OrangeTntBlock(settings: Settings) : DisappearingTntBlock<OrangeTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): OrangeTntEntity {
        return OrangeTntEntity(world, x, y, z, igniter)
    }
}

class RedTntBlock(settings: Settings) : DisappearingTntBlock<RedTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): RedTntEntity {
        return RedTntEntity(world, x, y, z, igniter)
    }
}

class BlueTntBlock(settings: Settings) : DisappearingTntBlock<BlueTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): BlueTntEntity {
        return BlueTntEntity(world, x, y, z, igniter)
    }
}

class PurpleTntBlock(settings: Settings) : DisappearingTntBlock<PurpleTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): PurpleTntEntity {
        return PurpleTntEntity(world, x, y, z, igniter)
    }
}

class GrayTntBlock(settings: Settings) : DisappearingTntBlock<GrayTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): GrayTntEntity {
        return GrayTntEntity(world, x, y, z, igniter)
    }
}

class BlackTntBlock(settings: Settings) : DisappearingTntBlock<BlackTntEntity>(settings) {
    override fun createEntity(world: World, x: Double, y: Double, z: Double, igniter: LivingEntity?): BlackTntEntity {
        return BlackTntEntity(world, x, y, z, igniter)
    }
}

// endregion
