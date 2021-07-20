package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.client.renderPart
import com.redgrapefruit.mythicaltowers.common.entity.BulletEntity
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack

/**
 * A model for the bullets used by shooter robots
 */
class BulletEntityModel(private val root: ModelPart) : EntityModel<BulletEntity>() {
    override fun render(
        matrices: MatrixStack,
        vertices: VertexConsumer,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ): Unit = renderPart(root, matrices, vertices, light, overlay, red, green, blue, alpha)

    override fun setAngles(
        entity: BulletEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) = Unit
}
