package com.redgrapefruit.mythicaltowers.common.registry

import com.redgrapefruit.mythicaltowers.common.MythicalTowers.Companion.idOf
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

/**
 * Stores and registers mod's [ItemGroup]s
 */
@Suppress("MemberVisibilityCanBePrivate")
object ItemGroupRegistry {
    // Items
    lateinit var ORBS: ItemGroup
    lateinit var AMULETS: ItemGroup
    lateinit var TOOLS: ItemGroup
    lateinit var WEAPONS: ItemGroup
    lateinit var ARMOR: ItemGroup
    lateinit var INGOTS: ItemGroup

    // Blocks
    lateinit var BUILDING: ItemGroup
    lateinit var TRAPS: ItemGroup
    lateinit var UTILITY: ItemGroup

    fun init() {
        // TODO: Replace placeholder icons (beds) with actual mod items once these items are in place
        ORBS = register("orbs", ItemRegistry.GREEN_ORB)
        AMULETS = register("amulets", ItemRegistry.YELLOW_AMULET)
        TOOLS = register("tools", ItemRegistry.ORANGE_PICKAXE)
        WEAPONS = register("weapons", ItemRegistry.RED_DOUBLE_AXE)
        ARMOR = register("armor", ItemRegistry.BLUE_CHESTPLATE)
        INGOTS = register("ingots", ItemRegistry.PURPLE_INGOT)
        BUILDING = register("building", Items.BROWN_BED)
        TRAPS = register("traps", Items.GRAY_BED)
        UTILITY = register("utility", Items.BROWN_BED)
    }

    /**
     * Registers and builds an [ItemGroup]
     *
     * @param name [ItemGroup] name
     * @param icon [ItemGroup] icon item
     * @return Built [ItemGroup]
     */
    private fun register(name: String, icon: Item): ItemGroup {
        return FabricItemGroupBuilder
            .create(idOf(name))
            .icon { ItemStack(icon) }
            .build()
    }
}