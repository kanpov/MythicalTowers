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
    // Orbs
    val GREEN_ORB = OrbItem(EffectConfigRegistry.GREEN_ORB)

    // Amulets
    val GREEN_AMULET = AmuletItem(StatusEffects.JUMP_BOOST, 1)

    // Ingots
    val GREEN_INGOT = IngotItem()

    // Tools
    val GREEN_PICKAXE = CustomPickaxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 2, -2.3f)
    val GREEN_SHOVEL = CustomShovelItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 1, -1.5f)
    val GREEN_HOE = CustomHoeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 1, -1.25f)

    // Weapons
    val GREEN_SWORD = CustomSwordItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 3, -1.8f)
    val GREEN_AXE = CustomAxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 5, -3.1f)
    val GREEN_DOUBLE_SWORD = DoubleSwordItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 5, -1.4f)
    val GREEN_DOUBLE_AXE = DoubleAxeItem(EffectConfigRegistry.GREEN_TOOLS, ToolMaterialRegistry.GREEN, 8, -3.7f)

    // Armor
    val GREEN_HELMET = HelmetItem(ArmorMaterialRegistry.GREEN, StatusEffects.REGENERATION, 1)
    val GREEN_CHESTPLATE = ChestplateItem(ArmorMaterialRegistry.GREEN, StatusEffects.ABSORPTION, 1)
    val GREEN_LEGGINGS = LeggingsItem(ArmorMaterialRegistry.GREEN, StatusEffects.REGENERATION, 0)
    val GREEN_BOOTS = BootsItem(ArmorMaterialRegistry.GREEN, StatusEffects.ABSORPTION, 0)

    fun init() {
        register("green_orb", GREEN_ORB)
        register("green_amulet", GREEN_AMULET)
        register("green_ingot", GREEN_INGOT)
        register("green_pickaxe", GREEN_PICKAXE)
        register("green_shovel", GREEN_SHOVEL)
        register("green_hoe", GREEN_HOE)
        register("green_sword", GREEN_SWORD)
        register("green_axe", GREEN_AXE)
        register("green_double_sword", GREEN_DOUBLE_SWORD)
        register("green_double_axe", GREEN_DOUBLE_AXE)
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
}