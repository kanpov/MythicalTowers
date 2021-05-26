package com.redgrapefruit.mythicaltowers.mixin;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Allows to modify fuse timer of TNT
 */
@Mixin(TntEntity.class)
public interface TntEntityAccessor {
    @Accessor("fuseTimer")
    void setFuseTimer(int fuseTimer);
}
