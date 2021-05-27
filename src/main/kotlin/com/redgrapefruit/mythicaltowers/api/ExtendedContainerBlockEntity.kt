package com.redgrapefruit.mythicaltowers.api

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.nbt.CompoundTag
import net.minecraft.screen.PropertyDelegate
import net.minecraft.util.Tickable

/**
 * A [ContainerBlockEntity] with [PropertyDelegate] support and out-of-the-box [Tickable.tick] event attached.<br></br><br></br>
 * A part of RedCore.Container library bundled with this mod.
 */
abstract class ExtendedContainerBlockEntity protected constructor(type: BlockEntityType<*>?) :
    ContainerBlockEntity(type), Tickable {
    private var propertyDelegate: PropertyDelegate

    /**
     * Constructs the [PropertyDelegate]
     *
     * @return Created [PropertyDelegate]
     */
    protected abstract fun constructDelegate(): PropertyDelegate

    /**
     * Deserialize [PropertyDelegate] properties from a [CompoundTag]
     *
     * @param tag Input [CompoundTag]
     */
    protected abstract fun deserialize(tag: CompoundTag?)

    /**
     * Serializes [PropertyDelegate] properties into a [CompoundTag]
     *
     * @param tag Output [CompoundTag]
     */
    protected abstract fun serialize(tag: CompoundTag?)

    // S&D overrides
    override fun fromTag(state: BlockState, tag: CompoundTag) {
        super.fromTag(state, tag)
        deserialize(tag) // Fire event
    }

    override fun toTag(tag: CompoundTag): CompoundTag {
        super.toTag(tag)
        serialize(tag) // Fire event
        return tag
    }

    init {
        // Construct property delegate
        propertyDelegate = constructDelegate()
    }
}