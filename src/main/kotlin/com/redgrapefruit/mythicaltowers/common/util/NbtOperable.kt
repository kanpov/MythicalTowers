package com.redgrapefruit.mythicaltowers.common.util

import net.minecraft.nbt.NbtCompound

/**
 * A data type that can be read/written into NBT using the built-in methods
 */
class NbtOperable<T> private constructor(
    /**
     * A reference to a function from the [NbtCompound] class that writes the operable into the NBT
     */
    private val writer: (NbtCompound, String, T) -> Unit,
    /**
     * A reference to a function from the [NbtCompound] class that reads the operable from NBT and returns it
     */
    private val reader: (NbtCompound, String) -> T) {

    /**
     * Invokes the [writer]
     */
    fun write(nbt: NbtCompound, key: String, value: T) {
        writer.invoke(nbt, key, value)
    }

    /**
     * Invokes the [reader]
     */
    fun read(nbt: NbtCompound, key: String): T {
        return reader.invoke(nbt, key)
    }

    companion object {
        val BYTE = NbtOperable(NbtCompound::putByte, NbtCompound::getByte)
        val SHORT = NbtOperable(NbtCompound::putShort, NbtCompound::getShort)
        val INT = NbtOperable(NbtCompound::putInt, NbtCompound::getInt)
        val LONG = NbtOperable(NbtCompound::putLong, NbtCompound::getLong)
        val UUID = NbtOperable(NbtCompound::putUuid, NbtCompound::getUuid)
        val FLOAT = NbtOperable(NbtCompound::putFloat, NbtCompound::getFloat)
        val DOUBLE = NbtOperable(NbtCompound::putDouble, NbtCompound::getDouble)
        val STRING = NbtOperable(NbtCompound::putString, NbtCompound::getString)
        val BOOLEAN = NbtOperable(NbtCompound::putBoolean, NbtCompound::getBoolean)
    }
}
