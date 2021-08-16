package com.redgrapefruit.mythicaltowers.client.model

import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity

/**
 * The shared model for melee robots
 */
class MeleeRobotEntityModel<T : Entity>(private val root: ModelPart) : EntityModel<T>() {
    override fun render(
        matrices: MatrixStack,
        vertices: VertexConsumer,
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
        /**
         * Creates the model
         */
        fun create(): TexturedModelData {
            val data = ModelData()

            // Imported & converted from BlockBench
            data.root.addChild("all", ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-4.0F, -13.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                .uv(22, 16)
                .cuboid(-5.0F, -14.0F, 4.0F, 10.0F, 10.0F, 1.0F)
                .uv(0, 16)
                .cuboid(-5.0F, -14.0F, -5.0F, 10.0F, 10.0F, 1.0F)
                .uv(24, 4)
                .cuboid(1.0F, -5.0F, 1.0F, 2.0F, 2.0F, 2.0F)
                .uv(24, 0)
                .cuboid(1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 2.0F)
                .uv(0, 4)
                .cuboid(-3.0F, -5.0F, -3.0F, 2.0F, 2.0F, 2.0F)
                .uv(0, 0)
                .cuboid(-3.0F, -5.0F, 1.0F, 2.0F, 2.0F, 2.0F)
                .uv(12, 27)
                .cuboid(-2.0F, -3.0F, 2.0F, 1.0F, 1.0F, 1.0F)
                .uv(8, 27)
                .cuboid(-2.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F)
                .uv(4, 27)
                .cuboid(2.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F)
                .uv(0, 27)
                .cuboid(2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F),

                ModelTransform.pivot(0.0F, 24.0F, 0.0F))

            return TexturedModelData.of(data, 64, 64)
        }
    }
}