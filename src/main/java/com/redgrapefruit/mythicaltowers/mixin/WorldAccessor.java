package com.redgrapefruit.mythicaltowers.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Allows modifying {@link World#isClient} for a workaround solution
 */
@Mixin(World.class)
public interface WorldAccessor {
    @Accessor("isClient")
    void setClient(boolean isClient);
}
