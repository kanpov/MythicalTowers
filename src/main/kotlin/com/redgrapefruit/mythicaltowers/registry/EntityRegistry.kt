package com.redgrapefruit.mythicaltowers.registry

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.entity.*
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
object EntityRegistry {
    // region TNT
    val GREEN_TNT: EntityType<GreenTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<GreenTntEntity>, world: World -> GreenTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val YELLOW_TNT: EntityType<YellowTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<YellowTntEntity>, world: World -> YellowTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val ORANGE_TNT: EntityType<OrangeTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<OrangeTntEntity>, world: World -> OrangeTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val RED_TNT: EntityType<RedTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<RedTntEntity>, world: World -> RedTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val BLUE_TNT: EntityType<BlueTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<BlueTntEntity>, world: World -> BlueTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val PURPLE_TNT: EntityType<PurpleTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<PurpleTntEntity>, world: World -> PurpleTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val GRAY_TNT: EntityType<GrayTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<GrayTntEntity>, world: World -> GrayTntEntity(type, world) }
        .fillTntAttributes()
        .build()

    val BLACK_TNT: EntityType<BlackTntEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<BlackTntEntity>, world: World -> BlackTntEntity(type, world) }
        .fillTntAttributes()
        .build()
    // endregion

    // region Melee robots
    val GREEN_MELEE_ROBOT: EntityType<GreenMeleeRobotEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC) { type: EntityType<GreenMeleeRobotEntity>, world: World -> GreenMeleeRobotEntity(type, world) }
        .dimensions(EntityDimensions.fixed(1f, 1f))
        .build()
    // endregion

    fun init() {
        // TNT
        register("green_tnt", GREEN_TNT)
        register("yellow_tnt", YELLOW_TNT)
        register("orange_tnt", ORANGE_TNT)
        register("red_tnt", RED_TNT)
        register("blue_tnt", BLUE_TNT)
        register("purple_tnt", PURPLE_TNT)
        register("gray_tnt", GRAY_TNT)
        register("black_tnt", BLACK_TNT)

        // Melee robots
        register("green_melee_robot", GREEN_MELEE_ROBOT)
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

/**
 * Fills in TNT-related attributes
 */
private fun <T> FabricEntityTypeBuilder<T>.fillTntAttributes(): FabricEntityTypeBuilder<T> where T : Entity {
    fireImmune()
    dimensions(EntityDimensions.fixed(0.98F, 0.98F))
    trackRangeBlocks(10)
    trackedUpdateRate(10)

    return this
}
