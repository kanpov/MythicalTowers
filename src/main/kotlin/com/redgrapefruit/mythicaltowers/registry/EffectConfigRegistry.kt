package com.redgrapefruit.mythicaltowers.registry

import com.redgrapefruit.mythicaltowers.core.EffectConfig
import com.redgrapefruit.mythicaltowers.core.WeaponEffectTarget
import com.redgrapefruit.mythicaltowers.util.IntRange
import net.minecraft.entity.effect.StatusEffects

/**
 * Stores the mod's lists of [EffectConfig]s
 */
object EffectConfigRegistry {
    // region Orbs
    val GREEN_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.CONDUIT_POWER,
            durationRange = IntRange(300, 600),
            amplifier = 0,
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.DOLPHINS_GRACE,
            durationRange = IntRange(400, 750),
            amplifierRange = IntRange(0, 1),
            chance = 70
        )
    )

    val YELLOW_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.REGENERATION,
            durationRange = IntRange(200, 600),
            amplifierRange = IntRange(1, 3),
            chance = 80
        ),
        EffectConfig(
            statusEffect = StatusEffects.ABSORPTION,
            durationRange = IntRange(500, 800),
            amplifierRange = IntRange(1, 2),
            chance = 40
        )
    )

    val ORANGE_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.INVISIBILITY,
            durationRange = IntRange(400, 750),
            amplifier = 0,
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.SPEED,
            durationRange = IntRange(500, 850),
            amplifierRange = IntRange(2, 4),
            chance = 60
        )
    )

    val RED_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.FIRE_RESISTANCE,
            durationRange = IntRange(500, 900),
            amplifier = 0,
            chance = 80
        ),
        EffectConfig(
            statusEffect = StatusEffects.LUCK,
            durationRange = IntRange(1000, 1500),
            amplifierRange = IntRange(3, 5),
            chance = 50
        )
    )

    val BLUE_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.SLOW_FALLING,
            durationRange = IntRange(400, 500),
            amplifierRange = IntRange(4, 6),
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.RESISTANCE,
            durationRange = IntRange(700, 1200),
            amplifierRange = IntRange(4, 5),
            chance = 65
        )
    )

    val PURPLE_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.WATER_BREATHING,
            durationRange = IntRange(300, 500),
            amplifier = 0,
            chance = 90
        ),
        EffectConfig(
            statusEffect = StatusEffects.SATURATION,
            durationRange = IntRange(100, 200),
            amplifierRange = IntRange(5, 7),
            chance = 55
        )
    )

    val GRAY_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.GLOWING,
            durationRange = IntRange(700, 1400),
            amplifier = 0,
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.HERO_OF_THE_VILLAGE,
            durationRange = IntRange(1000, 2000),
            amplifier = 0,
            chance = 35
        )
    )

    val BLACK_ORB: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.REGENERATION,
            durationRange = IntRange(2000, 5000),
            amplifierRange = IntRange(7, 10),
            isAlwaysApplied = true
        ),
        EffectConfig(
            statusEffect = StatusEffects.STRENGTH,
            durationRange = IntRange(3000, 6000),
            amplifierRange = IntRange(7, 12),
            chance = 90
        )
    )
    // endregion

    // region Tools
    val GREEN_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.POISON,
            durationRange = IntRange(200, 400),
            amplifierRange = IntRange(0, 2),
            chance = 80,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.HASTE,
            durationRange = IntRange(100, 150),
            amplifier = 1,
            isAlwaysApplied = true,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val YELLOW_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.SLOWNESS,
            durationRange = IntRange(300, 400),
            amplifierRange = IntRange(1, 2),
            chance = 70,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.ABSORPTION,
            durationRange = IntRange(200, 300),
            amplifier = 1,
            chance = 90,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val ORANGE_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.BLINDNESS,
            durationRange = IntRange(400, 700),
            amplifier = 0,
            isAlwaysApplied = true,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.JUMP_BOOST,
            durationRange = IntRange(300, 500),
            amplifierRange = IntRange(2, 3),
            chance = 70,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val RED_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.WITHER,
            durationRange = IntRange(300, 600),
            amplifierRange = IntRange(3, 4),
            chance = 75,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.INSTANT_HEALTH,
            durationRange = IntRange(10, 40),
            amplifier = 3,
            chance = 40,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val BLUE_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.NAUSEA,
            durationRange = IntRange(600, 800),
            amplifierRange = IntRange(4, 6),
            chance = 60,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.STRENGTH,
            durationRange = IntRange(700, 900),
            amplifierRange = IntRange(4, 7),
            chance = 85,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val PURPLE_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.WEAKNESS,
            durationRange = IntRange(500, 1000),
            amplifierRange = IntRange(5, 8),
            isAlwaysApplied = true,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.HEALTH_BOOST,
            durationRange = IntRange(1500, 3000),
            amplifierRange = IntRange(5, 10),
            chance = 65,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val GRAY_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.LEVITATION,
            durationRange = IntRange(150, 300),
            amplifierRange = IntRange(6, 8),
            chance = 75,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.SLOW_FALLING,
            durationRange = IntRange(800, 1750),
            amplifierRange = IntRange(6, 9),
            chance = 40,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )

    val BLACK_TOOLS: List<EffectConfig> = listOf(
        EffectConfig(
            statusEffect = StatusEffects.INSTANT_DAMAGE,
            durationRange = IntRange(100, 200),
            amplifierRange = IntRange(6, 7),
            isAlwaysApplied = true,
            weaponEffectTarget = WeaponEffectTarget.TARGET
        ),
        EffectConfig(
            statusEffect = StatusEffects.SPEED,
            durationRange = IntRange(500, 1000),
            amplifierRange = IntRange(6, 8),
            chance = 55,
            weaponEffectTarget = WeaponEffectTarget.ATTACKER
        )
    )
    // endregion
}