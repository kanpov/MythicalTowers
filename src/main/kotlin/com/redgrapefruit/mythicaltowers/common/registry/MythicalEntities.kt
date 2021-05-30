package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.entity.GreenTntEntity
import com.redgrapefruit.mythicaltowers.common.registry.names.EntityNames
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

/**
 * Stores and registers the mod's [EntityType]s
 */
object MythicalEntities {
    val GREEN_TNT: EntityType<GreenTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<GreenTntEntity>, world: World -> GreenTntEntity(type, world) }
        .fireImmune()
        .dimensions(EntityDimensions.fixed(0.98F, 0.98F))
        .trackRangeBlocks(10)
        .trackedUpdateRate(10)
        .build()

    fun init() {
        register(EntityNames.GREEN_TNT, GREEN_TNT)
    }

    /**
     * Registers an [EntityType]
     * @param name Entity name
     * @param type Built [EntityType] using [FabricEntityTypeBuilder]
     * @param TEntity The entity that the [EntityType] belongs to
     */
    private fun <TEntity> register(name: String, type: EntityType<TEntity>) where TEntity : Entity {
        Registry.register(Registry.ENTITY_TYPE, idOf(name), type)
    }
}