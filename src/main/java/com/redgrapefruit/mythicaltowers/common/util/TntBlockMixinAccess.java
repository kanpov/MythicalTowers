package com.redgrapefruit.mythicaltowers.common.util;

import com.mojang.datafixers.util.Function5;
import com.redgrapefruit.mythicaltowers.common.entity.AbstractCustomTntEntity;
import com.redgrapefruit.mythicaltowers.mixin.TntBlockMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * The duck interface for {@link TntBlockMixin}
 */
public interface TntBlockMixinAccess {
    void setCustom();

    // The instantiator lambda for creating entities
    void setInstantiator(Function5<World, Double, Double, Double, @Nullable LivingEntity, ? extends AbstractCustomTntEntity> instantiator);
}
