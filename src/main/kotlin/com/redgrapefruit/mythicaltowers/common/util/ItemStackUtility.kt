package com.redgrapefruit.mythicaltowers.common.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag

/**
 * Utilities for reading and writing [ItemStack]s
 */
object ItemStackUtility {
    /**
     * Reads an [ItemStack] from a [CompoundTag]
     *
     * @param tag      The source [CompoundTag]
     * @param baseName The base name of the stack to differentiate since this isn't a packet, but a tag
     * @return Read [ItemStack]
     */
    @JvmStatic
    fun readItemStack(tag: CompoundTag, baseName: String): ItemStack {
        // Check if the stack is empty, if yes, return ItemStack.EMPTY
        return if (tag.getBoolean("$baseName:Is Empty")) {
            ItemStack.EMPTY
        } else {
            // Read raw identifier of the item in the stack and the count of that item
            val rawId = tag.getInt("$baseName:Raw ID")
            val count = tag.getInt("$baseName:Count")
            // Instantiate the stack and set its tag to given
            val stack = ItemStack(Item.byRawId(rawId), count)
            stack.tag = tag
            stack
        }
    }

    /**
     * Writes an [ItemStack] to a [CompoundTag]
     *
     * @param tag      The output [CompoundTag]
     * @param baseName The base name of the stack to differentiate since this isn't a packet, but a tag
     * @param input    Written [ItemStack]
     */
    @JvmStatic
    fun writeItemStack(tag: CompoundTag, baseName: String, input: ItemStack?) {
        var stack = input
        if (stack == null) stack = ItemStack.EMPTY

        // Check if the stack is empty and write it
        if (stack!!.isEmpty) {
            tag.putBoolean("$baseName:Is Empty", true)
        } else {
            tag.putBoolean("$baseName:Is Empty", false)
            // Get the item from stack and extract its raw identifier and the count of that item
            val item = stack.item
            tag.putInt("$baseName:Raw ID", Item.getRawId(item))
            tag.putInt("$baseName:Count", stack.count)
            // Read and then write the stack's CompoundTag if it's necessary
            if (item.isDamageable || item.shouldSyncTagToClient()) {
                tag.copyFrom(stack.tag)
            }
        }
    }
}