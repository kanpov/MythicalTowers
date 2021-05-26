package com.redgrapefruit.mythicaltowers.common.entity;

import com.redgrapefruit.mythicaltowers.common.util.TntEntityMixinAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * A custom {@link TntEntity} with the ability to customize the fuse timer and explosion power
 */
public abstract class AbstractCustomTntEntity extends TntEntity {
    public AbstractCustomTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public AbstractCustomTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
    }

    protected void setFuseTimer(int fuse) {
        setFuse(fuse);
    }

    protected void setExplosionPower(float explosionPower) {
        ((TntEntityMixinAccess) this).setExplosionPower(explosionPower);
    }
}
