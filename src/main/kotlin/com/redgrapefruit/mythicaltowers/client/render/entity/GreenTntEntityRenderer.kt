package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.common.entity.GreenTntEntity
import com.redgrapefruit.mythicaltowers.common.init.MythicalBlocks
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.TntMinecartEntityRenderer
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper

class GreenTntEntityRenderer(dispatcher: EntityRenderDispatcher) : EntityRenderer<GreenTntEntity>(dispatcher) {
    override fun render(
        entity: GreenTntEntity,
        yaw: Float,
        tickDelta: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int
    ) {
        matrixStack.push()
        matrixStack.translate(0.0, 0.5, 0.0)
        if (entity.fuseValue.toFloat() - tickDelta + 1.0f < 10.0f) {
            var h: Float = 1.0f - (entity.fuseValue.toFloat() - tickDelta + 1.0f) / 10.0f
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
            MythicalBlocks.GREEN_TNT.defaultState,
            matrixStack,
            vertexConsumerProvider,
            light,
            entity.fuseValue / 5 % 2 == 0
        )
        matrixStack.pop()

        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light)
    }

    override fun getTexture(entity: GreenTntEntity): Identifier = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE
}