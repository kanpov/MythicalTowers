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
    val ORBS: ItemGroup = register("orbs", ItemRegistry.GREEN_ORB)
    val AMULETS: ItemGroup = register("amulets", ItemRegistry.YELLOW_AMULET)
    val TOOLS: ItemGroup = register("tools", ItemRegistry.ORANGE_PICKAXE)
    val WEAPONS: ItemGroup = register("weapons", ItemRegistry.RED_DOUBLE_AXE)
    val ARMOR: ItemGroup = register("armor", ItemRegistry.BLUE_CHESTPLATE)
    val INGOTS: ItemGroup = register("ingots", ItemRegistry.PURPLE_INGOT)

    // Blocks
    val BUILDING: ItemGroup = register("building", Items.BROWN_BED)
    val TRAPS: ItemGroup = register("traps", Items.GRAY_BED)
    val UTILITY: ItemGroup = register("utility", Items.BROWN_BED)

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