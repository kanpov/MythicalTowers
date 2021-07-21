package com.redgrapefruit.mythicaltowers.common.network

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry

/**
 * A custom spawn packet for grenades
 */
object GrenadeSpawnPacket {
    private val ID = idOf("grenade_spawn_packet")

    /**
     * Creates a new [GrenadeSpawnPacket]
     */
    fun create(entity: Entity): Packet<*> {
        // Check if the world is client-side
        if (entity.world.isClient) throw IllegalStateException("BulletSpawnPacket#create called on the client")

        // Write data
        val buf = PacketByteBufs.create()

        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(entity.type)) // entity type
        buf.writeUuid(entity.uuid) // uuid
        buf.writeVarInt(entity.id) // entity id
        buf.writeVec3D(entity.pos) // position
        buf.writeAngle(entity.pitch) // pitch angle
        buf.writeAngle(entity.yaw) // yaw angle

        // Transform the PacketByteBuf into a Packet
        return ServerPlayNetworking.createS2CPacket(ID, buf)
    }

    /**
     * Registers the client packet which receives the [GrenadeSpawnPacket]
     */
    fun register() {
        ClientPlayNetworking.registerGlobalReceiver(ID) { client, _, buf, _ ->
            // Read the data
            val type = Registry.ENTITY_TYPE.get(buf.readVarInt())
            val uuid = buf.readUuid()
            val entityId = buf.readVarInt()
            val pos = buf.readVec3D()
            val pitch = buf.readAngle()
            val yaw = buf.readAngle()

            // Execute a task on the client which creates a new entity of that type
            client.execute {
                // Check the world
                if (client.world == null) throw IllegalStateException("Tried to create an entity in a null world")
                // Create a new entity of the read type
                val entity = type.create(client.world) ?: throw IllegalStateException("Could not create entity")

                // Assign the read values to the entity
                entity.updateTrackedPosition(pos)
                entity.setPos(pos.x, pos.y, pos.z)
                entity.pitch = pitch
                entity.yaw = yaw
                entity.id = entityId
                entity.uuid = uuid

                // Add the entity to the client world
                client.world!!.addEntity(entityId, entity)
            }
        }
    }

    // Angles
    private fun PacketByteBuf.writeAngle(angle: Float) = writeFloat(angle)
    private fun PacketByteBuf.readAngle(): Float = readFloat()
    // Vec3Ds
    private fun PacketByteBuf.writeVec3D(vec3d: Vec3d) {
        writeDouble(vec3d.x)
        writeDouble(vec3d.y)
        writeDouble(vec3d.z)
    }
    private fun PacketByteBuf.readVec3D(): Vec3d = Vec3d(readDouble(), readDouble(), readDouble())
}
