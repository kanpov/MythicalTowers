package com.redgrapefruit.mythicaltowers.common.init

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.common.armor.BootsItem
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem
import com.redgrapefruit.mythicaltowers.common.init.names.ItemNames
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
object MythicalItems {
    // Orbs
    val GREEN_ORB = OrbItem(MythicalEffectConfigs.GREEN_ORB)

    // Amulets
    val GREEN_AMULET = AmuletItem(StatusEffects.JUMP_BOOST, 1)

    // Ingots
    val GREEN_INGOT = IngotItem()

    // Tools
    val GREEN_PICKAXE = CustomPickaxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 2, -2.3f)
    val GREEN_SHOVEL = CustomShovelItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.5f)
    val GREEN_HOE = CustomHoeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.25f)

    // Weapons
    val GREEN_SWORD = CustomSwordItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 3, -1.8f)
    val GREEN_AXE = CustomAxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 5, -3.1f)
    val GREEN_DOUBLE_SWORD = DoubleSwordItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 5, -1.4f)
    val GREEN_DOUBLE_AXE = DoubleAxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 8, -3.7f)

    // Armor
    val GREEN_HELMET = HelmetItem(MythicalArmorMaterials.GREEN, StatusEffects.REGENERATION, 1)
    val GREEN_CHESTPLATE = ChestplateItem(MythicalArmorMaterials.GREEN, StatusEffects.ABSORPTION, 1)
    val GREEN_LEGGINGS = LeggingsItem(MythicalArmorMaterials.GREEN, StatusEffects.REGENERATION, 0)
    val GREEN_BOOTS = BootsItem(MythicalArmorMaterials.GREEN, StatusEffects.ABSORPTION, 0)

    fun init() {
        register(ItemNames.GREEN_ORB, GREEN_ORB)
        register(ItemNames.GREEN_AMULET, GREEN_AMULET)
        register(ItemNames.GREEN_INGOT, GREEN_INGOT)
        register(ItemNames.GREEN_PICKAXE, GREEN_PICKAXE)
        register(ItemNames.GREEN_SHOVEL, GREEN_SHOVEL)
        register(ItemNames.GREEN_HOE, GREEN_HOE)
        register(ItemNames.GREEN_SWORD, GREEN_SWORD)
        register(ItemNames.GREEN_AXE, GREEN_AXE)
        register(ItemNames.GREEN_DOUBLE_SWORD, GREEN_DOUBLE_SWORD)
        register(ItemNames.GREEN_DOUBLE_AXE, GREEN_DOUBLE_AXE)
        register(ItemNames.GREEN_HELMET, GREEN_HELMET)
        register(ItemNames.GREEN_CHESTPLATE, GREEN_CHESTPLATE)
        register(ItemNames.GREEN_LEGGINGS, GREEN_LEGGINGS)
        register(ItemNames.GREEN_BOOTS, GREEN_BOOTS)
    }

    /**
     * Registers an item
     *
     * @param name Item name
     * @param item Item instance
     */
    private fun register(name: String?, item: Item) {
        Registry.register(Registry.ITEM, idOf(name), item)
    }
}