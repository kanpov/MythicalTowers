package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.common.entity.MeleeRobotEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack

/**
 * A model for the melee robot
 */
class MeleeRobotEntityModel(root: ModelPart) : EntityModel<MeleeRobotEntity>() {
    private val main: ModelPart = root.getChild("main")

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
        main.render(matrices, vertices, light, overlay, red, green, blue, alpha)
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

            data.root.addChild("main", ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(0f, 0f, 0f, 16f, 16f, 16f), ModelTransform.NONE)

            return TexturedModelData.of(data, 16, 16)
        }
    }
}