package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotEntity
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
    ) {
        matrices.push()
        // Render every cuboid
        root.forEachCuboid(matrices) { entry, _, _, cuboid ->
            cuboid.renderCuboid(entry, vertices, light, overlay, red, green, blue, alpha)
        }
        matrices.pop()
    }

    override fun setAngles(
        entity: MeleeRobotEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {

    }

    companion object {
        fun texturedModelData(): TexturedModelData {
            val data = ModelData()

            data.root.apply {
                addChild(
                    "center", ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(2f, 2f, 2f, 12f, 12f, 12f),
                    ModelTransform.NONE
                )

                addChild(
                    "front_1", ModelPartBuilder.create()
                        .uv(12, 0)
                        .cuboid(0f, 0f, 0f, 16f, 2f, 2f),
                    ModelTransform.NONE
                )

                addChild(
                    "front_2", ModelPartBuilder.create()
                        .uv(12, 0)
                        .cuboid(0f, 14f, 0f, 16f, 2f, 2f),
                    ModelTransform.NONE
                )

                addChild(
                    "back_1", ModelPartBuilder.create()
                        .uv(12, 0)
                        .cuboid(0f, 0f, 14f, 16f, 2f, 2f),
                    ModelTransform.NONE
                )

                addChild("back_2", ModelPartBuilder.create()
                    .uv(12, 0)
                    .cuboid(0f, 14f, 14f, 16f, 2f, 2f),
                    ModelTransform.NONE
                )

                addChild("right_1", ModelPartBuilder.create()
                    .uv(12, 0)
                    .cuboid(14f, 0f, 0f, 2f, 2f, 16f),
                    ModelTransform.NONE
                )

                addChild("right_2", ModelPartBuilder.create()
                    .uv(12, 0)
                    .cuboid(14f, 14f, 0f, 2f, 2f, 16f),
                    ModelTransform.NONE
                )

                addChild("left_1", ModelPartBuilder.create()
                    .uv(12, 0)
                    .cuboid(0f, 0f, 0f, 2f, 2f, 16f),
                    ModelTransform.NONE
                )

                addChild("left_2", ModelPartBuilder.create()
                    .uv(12, 0)
                    .cuboid(0f, 14f, 0f, 2f, 2f, 16f),
                    ModelTransform.NONE
                )
            }

            return TexturedModelData.of(data, 16, 16)
        }
    }
}