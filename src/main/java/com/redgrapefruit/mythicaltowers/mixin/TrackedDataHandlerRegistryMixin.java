package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.util.TrackedDataHandlers;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * As always, vanilla {@link TrackedDataHandler} registering sucks.
 *
 * And I'm the one who needs to fix it.
 */
@Mixin(TrackedDataHandlerRegistry.class)
public class TrackedDataHandlerRegistryMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private static void extendedGet(int id, CallbackInfoReturnable<TrackedDataHandler<?>> cir) {
        if (id > TrackedDataHandlers.MIN_MODDED_HANDLER_INDEX) {
            TrackedDataHandlers.INSTANCE.getHandlers().forEach((index, handler) -> {
                if (index == id) {
                    cir.setReturnValue(handler);
                }
            });
        }
    }

    @Inject(method = "getId", at = @At("TAIL"), cancellable = true)
    private static void extendedGetId(TrackedDataHandler<?> handler, CallbackInfoReturnable<Integer> cir) {
        if (TrackedDataHandlers.INSTANCE.getHandlers().containsValue(handler)) {
            for (TrackedDataHandler<?> scanHandler : TrackedDataHandlers.INSTANCE.getHandlers().values()) {
                for (int index : TrackedDataHandlers.INSTANCE.getHandlers().keySet()) {
                    if (scanHandler == handler) {
                        cir.setReturnValue(index);
                    }
                }
            }
        }
    }
}
