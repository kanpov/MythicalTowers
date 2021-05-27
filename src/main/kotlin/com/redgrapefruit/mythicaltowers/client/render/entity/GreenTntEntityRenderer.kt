package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.common.block.trap.GreenTntBlock
import com.redgrapefruit.mythicaltowers.common.entity.GreenTntEntity
import com.redgrapefruit.mythicaltowers.common.init.MythicalBlocks
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.util.math.MatrixStack

/**
 * The green TNT. Slightly more dangerous than usual, but the fuse timer is longer
 */
class GreenTntEntityRenderer(dispatcher: EntityRenderDispatcher) : CustomTntEntityRenderer<GreenTntEntity, GreenTntBlock>(dispatcher) {
    override fun render(
        entity: GreenTntEntity,
        yaw: Float,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int
    ) {
        draw(MythicalBlocks.GREEN_TNT, entity, tickDelta, matrices, vertexConsumers, light)
    }
}