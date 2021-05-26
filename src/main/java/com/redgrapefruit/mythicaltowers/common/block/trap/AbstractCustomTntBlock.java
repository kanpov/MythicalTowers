package com.redgrapefruit.mythicaltowers.common.block.trap;

import com.redgrapefruit.mythicaltowers.common.entity.AbstractCustomTntEntity;
import com.redgrapefruit.mythicaltowers.common.util.TntBlockMixinAccess;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
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

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        // Server-side logic
        if (world.isClient) return;

        // Create the entity and set its fuse value
        TEntity entity = createEntity(world, (double) pos.getX() + 0.5D, pos.getY(), pos.getZ(), explosion.getCausingEntity());
        entity.setFuse((short) (world.random.nextInt(entity.getFuseTimer() / 4) + entity.getFuseTimer() / 8));
        // Spawn the entity
        world.spawnEntity(entity);
    }
}
