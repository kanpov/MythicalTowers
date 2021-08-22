package com.redgrapefruit.mythicaltowers.client.model

import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity

class TurretRobotEntityModel<T : Entity>(private val root: ModelPart) : EntityModel<T>() {
    override fun render(
        matrices: MatrixStack?,
        vertices: VertexConsumer?,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        // Render the entire root
        root.forEachCuboid(matrices) { entry, _, _, cuboid ->
            cuboid.renderCuboid(entry, vertices, light, overlay, red, green, blue, alpha)
        }
    }

    override fun setAngles(
        entity: T,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) = Unit

    companion object Builder {
        fun create(): TexturedModelData {
            val data = ModelData()

            // Imported & converted from BlockBench
            data.root.apply {
                val main = addChild("parent", ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 1.0F, 8.0F)
                    .uv(12, 15)
                    .cuboid(-2.0F, -14.0F, 2.0F, 5.0F, 7.0F, 1.0F)
                    .uv(0, 15)
                    .cuboid(-2.0F, -14.0F, -3.0F, 5.0F, 7.0F, 1.0F)
                    .uv(0, 9)
                    .cuboid(-2.0F, -13.0F, -2.0F, 5.0F, 2.0F, 4.0F)
                    .uv(20, 19)
                    .cuboid(2.0F, -11.0F, -2.0F, 1.0F, 4.0F, 4.0F)
                    .uv(14, 9)
                    .cuboid(-1.0F, -10.0F, 1.0F, 1.0F, 3.0F, 1.0F)
                    .uv(0, 9)
                    .cuboid(-1.0F, -10.0F, -2.0F, 1.0F, 3.0F, 1.0F)
                    , ModelTransform.pivot(0.0F, 24.0F, 0.0F))

                main.addChild("child1", ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(3.0F, -4.0F, -4.0F, 2.0F, 6.0F, 2.0F)
                    .uv(0, 23)
                    .cuboid(3.0F, -4.0F, 2.0F, 2.0F, 6.0F, 2.0F)
                    , ModelTransform.rotation(0.0F, 0.0F, -0.6109F))

                main.addChild("child2", ModelPartBuilder.create()
                    .uv(8, 23)
                    .cuboid(-5.0F, -4.0F, 2.0F, 2.0F, 6.0F, 2.0F)
                    .uv(24, 0)
                    .cuboid(-5.0F, -4.0F, -4.0F, 2.0F, 6.0F, 2.0F)
                    , ModelTransform.rotation(0.0F, 0.0F, 0.6109F))
            }

            return TexturedModelData.of(data, 128, 128)
        }
    }
}