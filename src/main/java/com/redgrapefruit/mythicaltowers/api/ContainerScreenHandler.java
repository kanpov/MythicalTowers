package com.redgrapefruit.mythicaltowers.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

/**
 * A container {@link ScreenHandler} managing slots and {@link ScreenHandlerListener}s.<br>
 * Also provides a few useful API methods for creating slots.<br><br>
 * A part of RedCore.Container library bundled with this mod.
 */
public abstract class ContainerScreenHandler extends ScreenHandler {
    // Embedded inventory
    protected final Inventory inventory;

    /**
     * Server-side screen handler constructor
     *
     * @param syncId          Synchronization ID
     * @param playerInventory Player's inventory
     * @param inventory       Embedded inventory
     * @param size            Container size
     * @param type            Registered screen handler type. Unique for each instance
     */
    protected ContainerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, int size, ScreenHandlerType<?> type) {
        super(type, syncId);

        // Setup inventory
        checkSize(inventory, size);
        this.inventory = inventory;
        this.inventory.onOpen(playerInventory.player);

        // Fire events
        onSlotInit(inventory, playerInventory);
        onListenerInit();
    }

    /**
     * An event reserved for putting slots on the screen handler
     *
     * @param inventory       Embedded inventory
     * @param playerInventory Player inventory
     */
    protected abstract void onSlotInit(Inventory inventory, PlayerInventory playerInventory);

    /**
     * An event reserved for adding screen handler listeners onto the screen handler
     */
    protected abstract void onListenerInit();

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        // Some wizardry code to transfer the selected slot once ShiftClicked

        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    // Useful API methods for creating slots because calculating pixels is very painful

    /**
     * Places a slot according to the <b>exact</b> grid (see <code>textures/gui/container/dispenser</code>)
     *
     * @param inventory Embedded inventory
     * @param index     Slot index
     * @param x         Grid X
     * @param y         Grid Y
     */
    protected void addGridSlot(Inventory inventory, int index, int x, int y) {
        addSlot(new Slot(inventory, index, 62 + x * 18, 17 + y * 18));
    }

    /**
     * Adds all the slots from the player's inventory to the screen handler
     *
     * @param playerInventory Player's inventory
     */
    protected void addPlayerInventorySlots(PlayerInventory playerInventory) {
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
    }

    /**
     * Adds all the slots from the player's hotbar to the screen handler
     *
     * @param playerInventory Player's inventory
     */
    protected void addPlayerHotbarSlots(PlayerInventory playerInventory) {
        for (int m = 0; m < 9; ++m) {
            addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }
}
