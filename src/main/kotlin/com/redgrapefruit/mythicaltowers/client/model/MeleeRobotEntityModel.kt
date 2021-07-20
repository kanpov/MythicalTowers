package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.client.renderPart
import com.redgrapefruit.mythicaltowers.common.entity.MeleeRobotEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack

/**
 * A model for the melee robot
 */
class MeleeRobotEntityModel(private val root: ModelPart) : EntityModel<MeleeRobotEntity>() {
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
        entity: MeleeRobotEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) = Unit

    companion object {
        fun texturedModelData(): TexturedModelData {
            val data = ModelData()

            data.root.apply {
                addChild("1", ModelPartBuilder.create()
                    .uv(0, 24)
                    .cuboid(6.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F)
                    .cuboid(-8.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F)
                    .cuboid(-8.0F, -16.0F, -8.0F, 2.0F, 2.0F, 16.0F)
                    .cuboid(6.0F, -16.0F, -8.0F, 2.0F, 2.0F, 16.0F)
                    , ModelTransform.pivot(0.0F, 24.0F, 0.0F))

                addChild("2", ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-6.0F, -14.0F, -6.0F, 12.0F, 12.0F, 12.0F)
                    , ModelTransform.pivot(0.0F, 24.0F, 0.0F))
            }

            return TexturedModelData.of(data, 48, 48)
        }
    }
}