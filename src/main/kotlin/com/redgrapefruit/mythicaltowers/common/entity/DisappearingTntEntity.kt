package com.redgrapefruit.mythicaltowers.common.entity

import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import net.minecraft.entity.*
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket
import net.minecraft.particle.ParticleTypes
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import kotlin.math.cos
import kotlin.math.sin

/**
 * Due to critical constraints in the vanilla TNT system and many mixins and hacks required, the mod uses
 * a reimplementation of the TNT system.
 *
 * The entity is spawned in the world once the TNT is primed and once the fuse timer goes out, the TNT explodes
 *
 * @param type The registered [EntityType]
 * @param world Nullable [World] instance
 */
sealed class DisappearingTntEntity(type: EntityType<*>, world: World) : Entity(type, world) {
    // region Properties & constructor

    /**
     * Fuse state [TrackedData]
     */
    private lateinit var fuseTracker: TrackedData<Int>

    /**
     * The causer of the explosion
     */
    private var causingEntity: LivingEntity? = null

    /**
     * The value of [fuseTracker]'s value
     */
    var fuseValue: Int = 0

    /**
     * The power of the caused explosion
     */
    protected var explosionPower: Float = 0.0f

    /**
     * Secondary constructor from calls withing the block
     * @param type The registered [EntityType]
     * @param world Nullable [World] instance
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param igniter The causer of this explosion
     */
    constructor(
        type: EntityType<*>,
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : this(type, world) {
        this.updatePosition(x, y, z)
        // Calculate velocity
        val d: Double = world.random.nextDouble() * 6.2831854820251465
        this.setVelocity(-sin(d) * 0.02, 0.20000000298023224, -cos(d) * 0.02)
        // Setup other variables
        prevX = x
        prevY = y
        prevZ = z
        causingEntity = igniter
    }

    // endregion

    // region Ticking

    override fun tick() {
        // Velocity management
        if (!hasNoGravity()) {
            velocity = velocity.add(0.0, -0.04, 0.0)
        }

        move(MovementType.SELF, velocity)
        velocity = velocity.multiply(0.98)

        if (onGround) {
            velocity = velocity.multiply(0.7, -0.5, 0.7)
        }

        // Fuse timer management
        --fuseValue

        // Check fuse
        if (fuseValue <= 0) {
            // Remove the block and explode on the server
            discard()
            if (!world.isClient) {
                world.createExplosion(this, x, getBodyY(0.0625), z, explosionPower, Explosion.DestructionType.BREAK)
            }
        } else {
            // Update water and draw some smoke on the client
            updateWaterState()
            if (world.isClient) {
                world.addParticle(ParticleTypes.SMOKE, x, y + 0.5, z, 0.0, 0.0, 0.0)
            }
        }
    }

    // endregion

    // region (De)Serialization

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putInt("Fuse", fuseValue)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        initFuse(nbt.getInt("Fuse"))
    }

    override fun createSpawnPacket(): Packet<*> = EntitySpawnS2CPacket(this)

    // endregion

    // region Tracking

    override fun initDataTracker() {
        fuseTracker = DataTracker.registerData(javaClass, TrackedDataHandlerRegistry.INTEGER)

        dataTracker.startTracking(fuseTracker, fuseValue)
    }

    override fun onTrackedDataSet(data: TrackedData<*>) {
        if (fuseTracker == data) {
            fuseValue = dataTracker.get(fuseTracker)
        }
    }

    // endregion

    // region Misc

    override fun collides(): Boolean = !isRemoved

    override fun getEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float = 0.15F

    // endregion

    // region API

    fun getCausingEntity(): LivingEntity? = causingEntity

    /**
     * Initializes the fuse value
     * @param fuse Fuse
     */
    fun initFuse(fuse: Int) {
        dataTracker.set(fuseTracker, fuse)
        fuseValue = fuse
    }

    // endregion
}

// region Impl-s

/**
 * The green TNT. Slightly more dangerous than usual, but the fuse timer is longer
 */
class GreenTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<GreenTntEntity>, world: World) : super(type, world) {
        initFuse(90)
        explosionPower = 5.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(90)
        explosionPower = 5.0f
    }
}

/**
 * Yellow TNT. Quite a bit more dangerous than usual.
 */
class YellowTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<YellowTntEntity>, world: World) : super(type, world) {
        initFuse(85)
        explosionPower = 8.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(85)
        explosionPower = 8.0f
    }
}

// endregion
