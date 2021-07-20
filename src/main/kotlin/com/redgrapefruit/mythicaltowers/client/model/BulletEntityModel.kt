package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.client.renderPart
import com.redgrapefruit.mythicaltowers.common.entity.BulletEntity
import net.minecraft.client.model.*
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

    companion object Builder {
        fun create(): TexturedModelData {
            val data = ModelData()

            data.root.apply {
                val main = addChild("main", ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-1.0F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                    .cuboid(-1.0F, -12.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                    .uv(0, 10)
                    .cuboid(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                    , ModelTransform.pivot(0.0F, 24.0F, 0.0F))

                main.addChild("rotated_1", ModelPartBuilder.create()
                    .uv(0, 4)
                    .cuboid(-1.0F, 1.0F, -3.0F, 2.0F, 1.0F, 2.0F)
                    .uv(6, 2)
                    .cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F)
                    , ModelTransform.of(0.0F, -8.0F, 0.0F, -1.5708F, 1.5708F, 0.0F))

                main.addChild("rotated_2", ModelPartBuilder.create()
                    .uv(6, 5)
                    .cuboid(-1.0F, 1.0F, -3.0F, 2.0F, 1.0F, 2.0F)
                    .uv(0, 7)
                    .cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F)
                    , ModelTransform.of(0.0F, -8.0F, 0.0F, -1.5708F, 0.0F, 0.0F))
            }

            return TexturedModelData.of(data, 16, 16)
        }
    }
}
