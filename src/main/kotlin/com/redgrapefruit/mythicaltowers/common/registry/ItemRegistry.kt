package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.armor.BootsItem
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem
import com.redgrapefruit.mythicaltowers.common.item.AmuletItem
import com.redgrapefruit.mythicaltowers.common.item.IngotItem
import com.redgrapefruit.mythicaltowers.common.item.OrbItem
import com.redgrapefruit.mythicaltowers.common.tool.CustomHoeItem
import com.redgrapefruit.mythicaltowers.common.tool.CustomPickaxeItem
import com.redgrapefruit.mythicaltowers.common.tool.CustomShovelItem
import com.redgrapefruit.mythicaltowers.common.tool.weapon.CustomAxeItem
import com.redgrapefruit.mythicaltowers.common.tool.weapon.CustomSwordItem
import com.redgrapefruit.mythicaltowers.common.tool.weapon.DoubleAxeItem
import com.redgrapefruit.mythicaltowers.common.tool.weapon.DoubleSwordItem
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

/**
 * Stores and registers mod's items
 */
@Suppress("MemberVisibilityCanBePrivate")
object ItemRegistry {
    // region Orbs
    val GREEN_ORB = OrbItem(EffectConfigRegistry.GREEN_ORB)
    val YELLOW_ORB = OrbItem(EffectConfigRegistry.YELLOW_ORB)
    val ORANGE_ORB = OrbItem(EffectConfigRegistry.ORANGE_ORB)
    val RED_ORB = OrbItem(EffectConfigRegistry.RED_ORB)
    val BLUE_ORB = OrbItem(EffectConfigRegistry.BLUE_ORB)
    val PURPLE_ORB = OrbItem(EffectConfigRegistry.PURPLE_ORB)
    val GRAY_ORB = OrbItem(EffectConfigRegistry.GRAY_ORB)
    val BLACK_ORB = OrbItem(EffectConfigRegistry.BLACK_ORB)
    // endregion

    // region Amulets
    val GREEN_AMULET = AmuletItem(StatusEffects.JUMP_BOOST, 1)
    val YELLOW_AMULET = AmuletItem(StatusEffects.REGENERATION, 0)
    val ORANGE_AMULET = AmuletItem(StatusEffects.SPEED, 2)
    val RED_AMULET = AmuletItem(StatusEffects.FIRE_RESISTANCE, 0)
    val BLUE_AMULET = AmuletItem(StatusEffects.SLOW_FALLING, 3)
    val PURPLE_AMULET = AmuletItem(StatusEffects.SATURATION, 5)
    val GRAY_AMULET = AmuletItem(StatusEffects.HERO_OF_THE_VILLAGE, 0)
    val BLACK_AMULET = AmuletItem(StatusEffects.STRENGTH, 7)
    // endregion

    // region Ingots
    val GREEN_INGOT = IngotItem()
    val YELLOW_INGOT = IngotItem()
    val ORANGE_INGOT = IngotItem()
    val RED_INGOT = IngotItem()
    val BLUE_INGOT = IngotItem()
    val PURPLE_INGOT = IngotItem()
    val GRAY_INGOT = IngotItem()
    val BLACK_INGOT = IngotItem()
    // endregion

    // region Tools
    val GREEN_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 2, -2.3f)
    val YELLOW_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 4, -2.2f)
    val ORANGE_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 6, -2.1f)
    val RED_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 8, -2.0f)
    val BLUE_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 10, -1.9f)
    val PURPLE_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 12, -1.8f)
    val GRAY_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 14, -1.7f)
    val BLACK_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 16, -1.5f)
    val GREEN_SHOVEL = CustomShovelItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 1, -1.5f)
    val YELLOW_SHOVEL = CustomShovelItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 2, -1.45f)
    val ORANGE_SHOVEL = CustomShovelItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 3, -1.4f)
    val RED_SHOVEL = CustomShovelItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 4, -1.35f)
    val BLUE_SHOVEL = CustomShovelItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 5, -1.3f)
    val PURPLE_SHOVEL = CustomShovelItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 6, -1.25f)
    val GRAY_SHOVEL = CustomShovelItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 7, -1.2f)
    val BLACK_SHOVEL = CustomShovelItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 8, -1.15f)
    val GREEN_HOE = CustomHoeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 1, -1.25f)
    val YELLOW_HOE = CustomHoeItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 2, -1.2f)
    val ORANGE_HOE = CustomHoeItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 3, -1.25f)
    val RED_HOE = CustomHoeItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 4, -1.2f)
    val BLUE_HOE = CustomHoeItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 5, -1.25f)
    val PURPLE_HOE = CustomHoeItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 6, -1.2f)
    val GRAY_HOE = CustomHoeItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 7, -1.25f)
    val BLACK_HOE = CustomHoeItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 8, -1.1f)
    // endregion

    // region Weapons
    val GREEN_SWORD = CustomSwordItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 3, -1.8f)
    val YELLOW_SWORD = CustomSwordItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 8, -1.75f)
    val ORANGE_SWORD = CustomSwordItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 13, -1.7f)
    val RED_SWORD = CustomSwordItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 18, -1.65f)
    val BLUE_SWORD = CustomSwordItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 23, -1.6f)
    val PURPLE_SWORD = CustomSwordItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 28, -1.65f)
    val GRAY_SWORD = CustomSwordItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 33, -1.7f)
    val BLACK_SWORD = CustomSwordItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 38, -1.75f)
    val GREEN_AXE = CustomAxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 5, -3.1f)
    val YELLOW_AXE = CustomAxeItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 12, -3.05f)
    val ORANGE_AXE = CustomAxeItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 19, -3.0f)
    val RED_AXE = CustomAxeItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 26, -2.95f)
    val BLUE_AXE = CustomAxeItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 33, -2.9f)
    val PURPLE_AXE = CustomAxeItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 40, -2.85f)
    val GRAY_AXE = CustomAxeItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 48, -2.8f)
    val BLACK_AXE = CustomAxeItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 56, -2.75f)
    val GREEN_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 6, -2.5f)
    val YELLOW_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 16, -2.45f)
    val ORANGE_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 26, -2.4f)
    val RED_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 36, -2.35f)
    val BLUE_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 46, -2.3f)
    val PURPLE_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 56, -2.25f)
    val GRAY_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 66, -2.2f)
    val BLACK_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 76, -2.15f)
    val GREEN_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 10, -4.3f)
    val YELLOW_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.YELLOW_TOOLS, ToolMaterialRegistry.YELLOW, 24, -4.25f)
    val ORANGE_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.ORANGE_TOOLS, ToolMaterialRegistry.ORANGE, 38, -4.2f)
    val RED_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.RED_TOOLS, ToolMaterialRegistry.RED, 52, -4.15f)
    val BLUE_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.BLUE_TOOLS, ToolMaterialRegistry.BLUE, 66, -4.1f)
    val PURPLE_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.PURPLE_TOOLS, ToolMaterialRegistry.PURPLE, 80, -4.05f)
    val GRAY_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.GRAY_TOOLS, ToolMaterialRegistry.GRAY, 96, -4.0f)
    val BLACK_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.BLACK_TOOLS, ToolMaterialRegistry.BLACK, 112, -3.95f)
    // endregion

    // region Armor
    val GREEN_HELMET = HelmetItem(ArmorMaterialRegistry.GREEN, StatusEffects.REGENERATION, 0)
    val GREEN_CHESTPLATE = ChestplateItem(ArmorMaterialRegistry.GREEN, StatusEffects.ABSORPTION, 0)
    val GREEN_LEGGINGS = LeggingsItem(ArmorMaterialRegistry.GREEN, StatusEffects.STRENGTH, 0)
    val GREEN_BOOTS = BootsItem(ArmorMaterialRegistry.GREEN, StatusEffects.RESISTANCE, 0)
    // endregion

    // region Registration
    fun init() {
        register("green_orb", GREEN_ORB)
        register("yellow_orb", YELLOW_ORB)
        register("orange_orb", ORANGE_ORB)
        register("red_orb", RED_ORB)
        register("blue_orb", BLUE_ORB)
        register("purple_orb", PURPLE_ORB)
        register("gray_orb", GRAY_ORB)
        register("black_orb", BLACK_ORB)

        register("green_amulet", GREEN_AMULET)
        register("yellow_amulet", YELLOW_AMULET)
        register("orange_amulet", ORANGE_AMULET)
        register("red_amulet", RED_AMULET)
        register("blue_amulet", BLUE_AMULET)
        register("purple_amulet", PURPLE_AMULET)
        register("gray_amulet", GRAY_AMULET)
        register("black_amulet", BLACK_AMULET)

        register("green_ingot", GREEN_INGOT)
        register("yellow_ingot", YELLOW_INGOT)
        register("orange_ingot", ORANGE_INGOT)
        register("red_ingot", RED_INGOT)
        register("blue_ingot", BLUE_INGOT)
        register("purple_ingot", PURPLE_INGOT)
        register("gray_ingot", GRAY_INGOT)
        register("black_ingot", BLACK_INGOT)

        register("green_pickaxe", GREEN_PICKAXE)
        register("yellow_pickaxe", YELLOW_PICKAXE)
        register("orange_pickaxe", ORANGE_PICKAXE)
        register("red_pickaxe", RED_PICKAXE)
        register("blue_pickaxe", BLUE_PICKAXE)
        register("purple_pickaxe", PURPLE_PICKAXE)
        register("gray_pickaxe", GRAY_PICKAXE)
        register("black_pickaxe", BLACK_PICKAXE)
        register("green_shovel", GREEN_SHOVEL)
        register("yellow_shovel", YELLOW_SHOVEL)
        register("orange_shovel", ORANGE_SHOVEL)
        register("red_shovel", RED_SHOVEL)
        register("blue_shovel", BLUE_SHOVEL)
        register("purple_shovel", PURPLE_SHOVEL)
        register("gray_shovel", GRAY_SHOVEL)
        register("black_shovel", BLACK_SHOVEL)
        register("green_hoe", GREEN_HOE)
        register("yellow_hoe", YELLOW_HOE)
        register("orange_hoe", ORANGE_HOE)
        register("red_hoe", RED_HOE)
        register("blue_hoe", BLUE_HOE)
        register("purple_hoe", PURPLE_HOE)
        register("gray_hoe", GRAY_HOE)
        register("black_hoe", BLACK_HOE)

        register("green_sword", GREEN_SWORD)
        register("yellow_sword", YELLOW_SWORD)
        register("orange_sword", ORANGE_SWORD)
        register("red_sword", RED_SWORD)
        register("blue_sword", BLUE_SWORD)
        register("purple_sword", PURPLE_SWORD)
        register("gray_sword", GRAY_SWORD)
        register("black_sword", BLACK_SWORD)
        register("green_axe", GREEN_AXE)
        register("yellow_axe", YELLOW_AXE)
        register("orange_axe", ORANGE_AXE)
        register("red_axe", RED_AXE)
        register("blue_axe", BLUE_AXE)
        register("purple_axe", PURPLE_AXE)
        register("gray_axe", GRAY_AXE)
        register("black_axe", BLACK_AXE)
        register("green_double_sword", GREEN_DOUBLE_SWORD)
        register("yellow_double_sword", YELLOW_DOUBLE_SWORD)
        register("orange_double_sword", ORANGE_DOUBLE_SWORD)
        register("red_double_sword", RED_DOUBLE_SWORD)
        register("blue_double_sword", BLUE_DOUBLE_SWORD)
        register("purple_double_sword", PURPLE_DOUBLE_SWORD)
        register("gray_double_sword", GRAY_DOUBLE_SWORD)
        register("black_double_sword", BLACK_DOUBLE_SWORD)
        register("green_double_axe", GREEN_DOUBLE_AXE)
        register("yellow_double_axe", YELLOW_DOUBLE_AXE)
        register("orange_double_axe", ORANGE_DOUBLE_AXE)
        register("red_double_axe", RED_DOUBLE_AXE)
        register("blue_double_axe", BLUE_DOUBLE_AXE)
        register("purple_double_axe", PURPLE_DOUBLE_AXE)
        register("gray_double_axe", GRAY_DOUBLE_AXE)
        register("black_double_axe", BLACK_DOUBLE_AXE)

        register("green_helmet", GREEN_HELMET)
        register("green_chestplate", GREEN_CHESTPLATE)
        register("green_leggings", GREEN_LEGGINGS)
        register("green_boots", GREEN_BOOTS)
    }

    /**
     * Registers an item
     *
     * @param name Item name
     * @param item Item instance
     */
    private fun register(name: String, item: Item) {
        Registry.register(Registry.ITEM, idOf(name), item)
    }
    // endregion
}