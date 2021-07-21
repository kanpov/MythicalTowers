package com.redgrapefruit.mythicaltowers.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound

/**
 * Utilities for reading and writing into NBTs intended for use in Java code.
 */
object JavaNBT {

    // ItemStacks

    @JvmStatic
    fun readItemStack(nbt: NbtCompound, baseName: String): ItemStack {
        // Check if the stack is empty, if yes, return ItemStack.EMPTY
        return if (nbt.getBoolean("$baseName:Is Empty")) {
            ItemStack.EMPTY
        } else {
            // Read raw identifier of the item in the stack and the count of that item
            val rawId = nbt.getInt("$baseName:Raw ID")
            val count = nbt.getInt("$baseName:Count")
            // Instantiate the stack and set its tag to given
            val stack = ItemStack(Item.byRawId(rawId), count)
            stack.nbt = nbt
            stack
        }
    }

    @JvmStatic
    fun writeItemStack(nbt: NbtCompound, baseName: String, input: ItemStack?) {
        var stack = input
        if (stack == null) stack = ItemStack.EMPTY

        // Check if the stack is empty and write it
        if (stack!!.isEmpty) {
            nbt.putBoolean("$baseName:Is Empty", true)
        } else {
            nbt.putBoolean("$baseName:Is Empty", false)
            // Get the item from stack and extract its raw identifier and the count of that item
            val item = stack.item
            nbt.putInt("$baseName:Raw ID", Item.getRawId(item))
            nbt.putInt("$baseName:Count", stack.count)
            // Read and then write the stack's CompoundTag if it's necessary
            if (item.isDamageable || item.isNbtSynced) {
                nbt.copyFrom(stack.nbt)
            }
        }
    }
}