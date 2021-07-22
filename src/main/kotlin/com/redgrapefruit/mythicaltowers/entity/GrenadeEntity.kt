package com.redgrapefruit.mythicaltowers.entity

import com.redgrapefruit.mythicaltowers.entity.GrenadeStatics.NBT_GRENADE_TIMER
import com.redgrapefruit.mythicaltowers.entity.GrenadeStatics.TIMER_UNTIL_EXPLOSION
import com.redgrapefruit.mythicaltowers.network.GrenadeSpawnPacket
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion

sealed class GrenadeEntity : ThrownItemEntity {
    private var timer = 0

    constructor(type: EntityType<GrenadeEntity>, world: World) : super(type, world)

    constructor(type: EntityType<GrenadeEntity>, world: World, owner: LivingEntity) : super(type, owner, world)

    constructor(type: EntityType<GrenadeEntity>, world: World, x: Double, y: Double, z: Double) : super(type, x, y, z, world)

    /**
     * The grenade item associated
     */
    abstract val item: Item

    /**
     * The power of the explosion
     */
    abstract val power: Float

    override fun getDefaultItem(): Item = item

    override fun tick() {
        super.tick()

        ++timer

        // Explode and remove/discard
        if (timer >= TIMER_UNTIL_EXPLOSION) {
            world.createExplosion(null, x, y, z, power, true, Explosion.DestructionType.BREAK)
            discard()
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putInt(NBT_GRENADE_TIMER, timer)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        timer = nbt.getInt(NBT_GRENADE_TIMER)
    }

    override fun createSpawnPacket(): Packet<*> = GrenadeSpawnPacket.create(this)
}
