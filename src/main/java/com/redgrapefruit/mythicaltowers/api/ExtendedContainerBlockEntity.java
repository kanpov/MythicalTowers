package com.redgrapefruit.mythicaltowers.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.Tickable;

/**
 * A {@link ContainerBlockEntity} with {@link PropertyDelegate} support and out-of-the-box {@link Tickable#tick()} event attached.<br><br>
 * A part of RedCore.Container library bundled with this mod.
 */
public abstract class ExtendedContainerBlockEntity extends ContainerBlockEntity implements Tickable {
    protected PropertyDelegate propertyDelegate;

    protected ExtendedContainerBlockEntity(BlockEntityType<?> type) {
        super(type);
        // Construct property delegate
        propertyDelegate = constructDelegate();
    }

    /**
     * Constructs the {@link PropertyDelegate}
     *
     * @return Created {@link PropertyDelegate}
     */
    protected abstract PropertyDelegate constructDelegate();

    /**
     * Deserialize {@link PropertyDelegate} properties from a {@link CompoundTag}
     *
     * @param tag Input {@link CompoundTag}
     */
    protected abstract void deserialize(CompoundTag tag);

    /**
     * Serializes {@link PropertyDelegate} properties into a {@link CompoundTag}
     *
     * @param tag Output {@link CompoundTag}
     */
    protected abstract void serialize(CompoundTag tag);

    // S&D overrides

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        deserialize(tag); // Fire event
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        serialize(tag); // Fire event
        return tag;
    }
}
