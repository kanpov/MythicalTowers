package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.entity.AbstractCustomTntEntity;
import com.redgrapefruit.mythicaltowers.common.util.TntBlockMixinAccess;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

/**
 * This mixin modifies the private primeTnt method as it isn't overridable
 */
@Mixin(TntBlock.class)
public class TntBlockMixin implements TntBlockMixinAccess {
    @Unique
    private static boolean isCustom = false;
    @Unique
    private static Function<?, ? extends AbstractCustomTntEntity> instantiator;
    @Unique
    private static boolean isWorldClient = false;

    @ModifyArg(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At("INVOKE_ASSIGN"), index = 0)
    private static World modifyWorld(World originalWorld) {
        // Do not modify if the TNT isn't from the mod
        if (!isCustom) return originalWorld;

        // Check if the world is ACTUALLY client and store that data for later
        isWorldClient = originalWorld.isClient();
        // Use a workaround solution: make isClient true so the if statement doesn't pass and we can do our own code
        ((WorldAccessor) originalWorld).setClient(true);

        return originalWorld;
    }

    /**
     * Custom logic for the private <code>primeTnt</code>
     * @param world {@link World} instance
     * @param pos TNT {@link BlockPos}
     * @param igniter TNT igniter
     * @param info Mixin {@link CallbackInfo}
     */
    @Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At("TAIL"))
    private static void primeTnt(World world, BlockPos pos, LivingEntity igniter, CallbackInfo info) {
        // Do not do anything if the TNT isn't from the mod and if the world is client-side
        if (!isCustom || isWorldClient) return;

        // Create the entity using the instantiator and spawn it in the world
        AbstractCustomTntEntity entity = instantiator.apply(null);
        world.spawnEntity(entity);
        // Play the vanilla explosion sound
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    public void setCustom() {
        isCustom = true;
    }

    @Override
    public void setInstantiator(Function<Void, ? extends AbstractCustomTntEntity> instantiatorValue) {
        instantiator = instantiatorValue;
    }
}
