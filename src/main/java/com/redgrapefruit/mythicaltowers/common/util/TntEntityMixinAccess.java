package com.redgrapefruit.mythicaltowers.common.util;

import com.redgrapefruit.mythicaltowers.mixin.TntEntityMixin;

/**
 * Duck interface for {@link TntEntityMixin}
 */
public interface TntEntityMixinAccess {
    float getExplosionPower();
    void setExplosionPower(float explosionPower);
}
