package com.redgrapefruit.mythicaltowers.common.block.trap;

import com.redgrapefruit.mythicaltowers.common.entity.AbstractCustomTntEntity;
import com.redgrapefruit.mythicaltowers.common.util.TntBlockMixinAccess;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Custom TNT trap
 */
public abstract class AbstractCustomTntBlock<TEntity extends AbstractCustomTntEntity> extends TntBlock {
    public AbstractCustomTntBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TNT));

        TntBlockMixinAccess access = (TntBlockMixinAccess) this;

        access.setCustom();
        access.setInstantiator(this::createEntity);
    }

    protected abstract TEntity createEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter);

}
