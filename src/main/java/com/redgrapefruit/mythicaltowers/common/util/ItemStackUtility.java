package com.redgrapefruit.mythicaltowers.common.util;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * Utilities for reading and writing {@link ItemStack}s
 */
public class ItemStackUtility {
    /**
     * Reads an {@link ItemStack} from a {@link CompoundTag}
     *
     * @param tag      The source {@link CompoundTag}
     * @param baseName The base name of the stack to differentiate since this isn't a packet, but a tag
     * @return Read {@link ItemStack}
     */
    public static ItemStack readItemStack(CompoundTag tag, String baseName) {
        // Check if the stack is empty, if yes, return ItemStack.EMPTY
        if (tag.getBoolean(baseName + ":Is Empty")) {
            return ItemStack.EMPTY;
        } else {
            // Read raw identifier of the item in the stack and the count of that item
            int rawId = tag.getInt(baseName + ":Raw ID");
            int count = tag.getInt(baseName + ":Count");
            // Instantiate the stack and set its tag to given
            ItemStack stack = new ItemStack(Item.byRawId(rawId), count);
            stack.setTag(tag);

            return stack;
        }
    }

    /**
     * Writes an {@link ItemStack} to a {@link CompoundTag}
     *
     * @param tag      The output {@link CompoundTag}
     * @param baseName The base name of the stack to differentiate since this isn't a packet, but a tag
     * @param stack    Written {@link ItemStack}
     */
    public static void writeItemStack(CompoundTag tag, String baseName, ItemStack stack) {
        if (stack == null) stack = ItemStack.EMPTY;

        // Check if the stack is empty and write it
        if (stack.isEmpty()) {
            tag.putBoolean(baseName + ":Is Empty", true);
        } else {
            tag.putBoolean(baseName + ":Is Empty", false);
            // Get the item from stack and extract its raw identifier and the count of that item
            Item item = stack.getItem();
            tag.putInt(baseName + ":Raw ID", Item.getRawId(item));
            tag.putInt(baseName + ":Count", stack.getCount());
            // Read and then write the stack's CompoundTag if it's necessary
            if (item.isDamageable() || item.shouldSyncTagToClient()) {
                tag.copyFrom(stack.getTag());
            }
        }
    }
}
