package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.common.block.trap.CustomTntBlock
import com.redgrapefruit.mythicaltowers.common.entity.CustomTntEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.TntMinecartEntityRenderer
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper

/**
 * Due to critical constraints in the vanilla TNT system and many mixins and hacks required, the mod uses
 * a reimplementation of the TNT system.
 *
 * The renderer draws the TNT block with a slight overlay
 */
@Suppress("DEPRECATION")
abstract class CustomTntEntityRenderer<TEntity, TBlock>(dispatcher: EntityRenderDispatcher) : EntityRenderer<TEntity>(dispatcher)
        where TEntity : CustomTntEntity, TBlock : CustomTntBlock<TEntity> {

    init {
        shadowRadius = 0.5F
    }

    fun render(
        block: TBlock,
        entity: TEntity,
        yaw: Float,
        tickDelta: Float,
        matrixStack: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
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
            block.defaultState,
            matrixStack,
            vertexConsumers,
            light,
            entity.fuseValue / 5 % 2 == 0)

        matrixStack.pop()
    }

    override fun getTexture(entity: TEntity): Identifier = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE
}