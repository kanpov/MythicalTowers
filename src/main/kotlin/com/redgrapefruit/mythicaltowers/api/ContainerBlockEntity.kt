package com.redgrapefruit.mythicaltowers.api

import com.redgrapefruit.mythicaltowers.util.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos

/**
 * A container [BlockEntity] storing, serializing & deserializing an embedded inventory.
 *
 * Also manages the creation the of connected [ScreenHandler].
 */
abstract class ContainerBlockEntity protected constructor(type: BlockEntityType<*>, pos: BlockPos, state: BlockState) :
    BlockEntity(type, pos, state),
    ImplementedInventory, NamedScreenHandlerFactory {

    // region Properties & Overrides

    // Embedded inventory represented through a DefaultedList
    private val inventory = DefaultedList.ofSize(containerSize, ItemStack.EMPTY)

    protected abstract val containerSize: Int

    override fun getDisplayName(): Text = TranslatableText(cachedState.block.translationKey)

    override fun markDirty() {}

    // endregion

    // region Serialization

    override fun readNbt(nbt: NbtCompound) {
        // Deserialization method
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        // Serialization method
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        return nbt
    }

    // endregion
}