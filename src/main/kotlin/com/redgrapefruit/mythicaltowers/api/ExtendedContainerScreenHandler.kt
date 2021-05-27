package com.redgrapefruit.mythicaltowers.api

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType

/**
 * [ContainerScreenHandler] with [PropertyDelegate] support.<br></br><br></br>
 * A part of RedCore.Container library bundled with this mod.
 */
abstract class ExtendedContainerScreenHandler protected constructor(
    syncId: Int,
    playerInventory: PlayerInventory,
    inventory: Inventory,
    var propertyDelegate: PropertyDelegate,
    size: Int,
    type: ScreenHandlerType<*>?
) : ContainerScreenHandler(syncId, playerInventory, inventory, size, type) {
    init {
        // Setting up the property delegate
        addProperties(propertyDelegate)
    }
}