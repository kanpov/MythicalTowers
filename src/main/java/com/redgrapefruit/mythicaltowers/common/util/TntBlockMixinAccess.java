package com.redgrapefruit.mythicaltowers.common.util;

import com.redgrapefruit.mythicaltowers.common.entity.AbstractCustomTntEntity;
import com.redgrapefruit.mythicaltowers.mixin.TntBlockMixin;

import java.util.function.Function;

/**
 * The duck interface for {@link TntBlockMixin}
 */
public interface TntBlockMixinAccess {
    void setCustom();

    // The instantiator lambda for creating entities
    void setInstantiator(Function<Void, ? extends AbstractCustomTntEntity> instantiator);
}
