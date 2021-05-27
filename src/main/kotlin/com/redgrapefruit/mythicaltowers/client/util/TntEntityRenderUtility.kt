package com.redgrapefruit.mythicaltowers.client.util

import net.minecraft.block.Block
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.TntEntityRenderer
import net.minecraft.client.render.entity.TntMinecartEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.entity.TntEntity
import net.minecraft.util.math.MathHelper

/**
 * An utility to simplify and reduce boilerplate in the mod's [TntEntityRenderer]s
 */
object TntEntityRenderUtility {
    fun render(
        tntBlock: Block,
        tntEntity: TntEntity,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider?,
        i: Int
    ) {
        // I have no idea what's happening here tbh.
        matrixStack.push()
        matrixStack.translate(0.0, 0.5, 0.0)

        if (tntEntity.fuseTimer.toFloat() - g + 1.0f < 10.0f) {
            var h = 1.0f - (tntEntity.fuseTimer.toFloat() - g + 1.0f) / 10.0f
            h = MathHelper.clamp(h, 0.0f, 1.0f)
            h *= h
            h *= h
            val j = 1.0f + h * 0.3f
            matrixStack.scale(j, j, j)
        }

        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0f))
        matrixStack.translate(-0.5, -0.5, 0.5)
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0f))

        TntMinecartEntityRenderer.renderFlashingBlock(
            tntBlock.defaultState,
            matrixStack,
            vertexConsumerProvider,
            i,
            tntEntity.fuseTimer / 5 % 2 == 0
        )

        matrixStack.pop()
    }
}