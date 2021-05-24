package com.redgrapefruit.mythicaltowers.api.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;

/**
 * {@link ContainerScreenHandler} with {@link PropertyDelegate} support.<br><br>
 * A part of RedCore.Container library bundled with this mod.
 */
public abstract class ExtendedContainerScreenHandler extends ContainerScreenHandler {
    protected PropertyDelegate propertyDelegate;

    protected ExtendedContainerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate, int size, ScreenHandlerType<?> type) {
        super(syncId, playerInventory, inventory, size, type);
        // Setting up the property delegate
        this.propertyDelegate = propertyDelegate;
        addProperties(propertyDelegate);
    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }
}
