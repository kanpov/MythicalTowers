package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.util.TntEntityMixinAccess;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * This mixin allows to modify explosion power of TNT.
 */
@Mixin(TntEntity.class)
public class TntEntityMixin implements TntEntityMixinAccess {
    @Unique
    private float explosionPower;
    @Unique
    private boolean isCustom = false;

    @ModifyVariable(method = "explode", at = @At("INVOKE_ASSIGN"))
    private float modifyExplosionPowerVariable(float originalExplosionPower) {
        return getExplosionPower();
    }

    @Override
    public float getExplosionPower() {
        // Set explosion power to vanilla if not explicitly set by the mod
        if (!isCustom) explosionPower = 4.0f;

        return explosionPower;
    }

    @Override
    public void setExplosionPower(float explosionPower) {
        this.explosionPower = explosionPower;
        this.isCustom = true;
    }
}
