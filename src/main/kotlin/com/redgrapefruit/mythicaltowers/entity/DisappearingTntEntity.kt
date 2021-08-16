package com.redgrapefruit.mythicaltowers.entity

import com.redgrapefruit.mythicaltowers.onClient
import com.redgrapefruit.mythicaltowers.onServer
import com.redgrapefruit.mythicaltowers.registry.EntityRegistry
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
            onServer {
                world.createExplosion(this, x, getBodyY(0.0625), z, explosionPower, Explosion.DestructionType.BREAK)
            }
        } else {
            // Update water and draw some smoke on the client
            updateWaterState()
            onClient {
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

class OrangeTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<OrangeTntEntity>, world: World) : super(type, world) {
        initFuse(80)
        explosionPower = 11.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(85)
        explosionPower = 11.0f
    }
}

class RedTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<RedTntEntity>, world: World) : super(type, world) {
        initFuse(75)
        explosionPower = 14.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(75)
        explosionPower = 14.0f
    }
}

class BlueTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<BlueTntEntity>, world: World) : super(type, world) {
        initFuse(70)
        explosionPower = 19.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(70)
        explosionPower = 19.0f
    }
}

class PurpleTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<PurpleTntEntity>, world: World) : super(type, world) {
        initFuse(65)
        explosionPower = 25.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(65)
        explosionPower = 25.0f
    }
}

class GrayTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<GrayTntEntity>, world: World) : super(type, world) {
        initFuse(60)
        explosionPower = 30.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(60)
        explosionPower = 30.0f
    }
}

class BlackTntEntity : DisappearingTntEntity {
    constructor(type: EntityType<BlackTntEntity>, world: World) : super(type, world) {
        initFuse(50)
        explosionPower = 40.0f
    }

    constructor(
        world: World,
        x: Double,
        y: Double,
        z: Double,
        igniter: LivingEntity?
    ) : super(EntityRegistry.GREEN_TNT, world, x, y, z, igniter) {
        initFuse(50)
        explosionPower = 40.0f
    }
}

// endregion
