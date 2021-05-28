package com.redgrapefruit.mythicaltowers.api

import com.redgrapefruit.mythicaltowers.common.util.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList

/**
 * A container [BlockEntity] storing, serializing & deserializing an embedded inventory.
 *
 * Also manages the creation the of connected [ScreenHandler].
 */
abstract class ContainerBlockEntity protected constructor(type: BlockEntityType<*>?) : BlockEntity(type),
    ImplementedInventory, NamedScreenHandlerFactory {

    // region Properties & Overrides

    // Embedded inventory represented through a DefaultedList
    private val inventory = DefaultedList.ofSize(containerSize, ItemStack.EMPTY)

    protected abstract val containerSize: Int

    override fun getDisplayName(): Text = TranslatableText(cachedState.block.translationKey)

    override fun markDirty() {}

    // endregion

    // region Serialization

    override fun fromTag(state: BlockState, tag: CompoundTag) {
        // Deserialization method
        super.fromTag(state, tag)
        Inventories.fromTag(tag, inventory)
    }

    override fun toTag(tag: CompoundTag): CompoundTag {
        // Serialization method
        super.toTag(tag)
        Inventories.toTag(tag, inventory)
        return tag
    }

    // endregion
}