package com.redgrapefruit.mythicaltowers.api

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerListener
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot

/**
 * A container [ScreenHandler] managing slots and [ScreenHandlerListener]s.
 *
 * Also provides a few useful API methods for creating slots
 */
abstract class ContainerScreenHandler protected constructor(
    syncId: Int,
    playerInventory: PlayerInventory,
    inventory: Inventory,
    size: Int,
    type: ScreenHandlerType<*>
) : ScreenHandler(type, syncId) {

    // region Properties & constructor

    // Embedded inventory
    private var inventory: Inventory

    /**
     * Server-side screen handler constructor
     */
    init {

        // Setup inventory
        checkSize(inventory, size)
        this.inventory = inventory
        this.inventory.onOpen(playerInventory.player)

        // Fire events
        onSlotInit(inventory, playerInventory)
        onListenerInit()
    }

    // endregion

    // region Events

    /**
     * An event reserved for putting slots on the screen handler
     *
     * @param inventory       Embedded inventory
     * @param playerInventory Player inventory
     */
    protected abstract fun onSlotInit(inventory: Inventory?, playerInventory: PlayerInventory?)

    /**
     * An event reserved for adding screen handler listeners onto the screen handler
     */
    protected abstract fun onListenerInit()

    // endregion

    // region Implementation

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        // Some wizardry code to transfer the selected slot once ShiftClicked
        var newStack = ItemStack.EMPTY
        val slot = slots[invSlot]
        if (slot != null && slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }
            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }
        return newStack
    }

    // endregion

    // region API

    /**
     * Places a slot according to the **exact** grid (see `textures/gui/container/dispenser`)
     *
     * @param inventory Embedded inventory
     * @param index     Slot index
     * @param x         Grid X
     * @param y         Grid Y
     */
    protected fun addGridSlot(inventory: Inventory?, index: Int, x: Int, y: Int) {
        addSlot(Slot(inventory, index, 62 + x * 18, 17 + y * 18))
    }

    /**
     * Adds all the slots from the player's inventory to the screen handler
     *
     * @param playerInventory Player's inventory
     */
    protected fun addPlayerInventorySlots(playerInventory: PlayerInventory?) {
        for (m in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18))
            }
        }
    }

    /**
     * Adds all the slots from the player's hotbar to the screen handler
     *
     * @param playerInventory Player's inventory
     */
    protected fun addPlayerHotbarSlots(playerInventory: PlayerInventory?) {
        for (m in 0..8) {
            addSlot(Slot(playerInventory, m, 8 + m * 18, 142))
        }
    }

    // endregion
}