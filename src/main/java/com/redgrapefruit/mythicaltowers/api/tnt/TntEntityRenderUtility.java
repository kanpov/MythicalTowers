package com.redgrapefruit.mythicaltowers.api.tnt;

import net.minecraft.block.Block;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.MathHelper;

/**
 * An utility to simplify and reduce boilerplate in the mod's {@link TntEntityRenderer}s
 */
public class TntEntityRenderUtility {
    public static void render(Block tntBlock, TntEntity tntEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        // I have no idea what's happening here tbh.

        matrixStack.push();
        matrixStack.translate(0.0D, 0.5D, 0.0D);

        if ((float) tntEntity.getFuseTimer() - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float) tntEntity.getFuseTimer() - g + 1.0F) / 10.0F;

            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float j = 1.0F + h * 0.3F;

            matrixStack.scale(j, j, j);
        }

        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));

        TntMinecartEntityRenderer.renderFlashingBlock(tntBlock.getDefaultState(), matrixStack, vertexConsumerProvider, i, tntEntity.getFuseTimer() / 5 % 2 == 0);

        matrixStack.pop();
    }
}
