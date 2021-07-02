package com.redgrapefruit.mythicaltowers.client.model

import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotEntity
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
        main.render(matrices, vertices, light, overlay, red, green, blue, alpha)
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

            data.root.addChild("main", ModelPartBuilder.create()
                .cuboid(0f, 0f, 0f, 16f, 16f, 16f),
                ModelTransform.pivot(8f, 8f, 8f))

            return TexturedModelData.of(data, 16, 16)
        }
    }
}