package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.client.model.MeleeRobotEntityModel
import com.redgrapefruit.mythicaltowers.common.entity.melee.MeleeRobotEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier

/**
 * A renderer for the melee robot
 */
class MeleeRobotEntityRenderer(
    /**
     * The context obtained from registering
     */
    context: EntityRendererFactory.Context,
    /**
     * Registered model for the layer
     */
    layer: EntityModelLayer,
    /**
     * The linked texture for rendering the model
     */
    private val texture: Identifier)
    : MobEntityRenderer<MeleeRobotEntity, MeleeRobotEntityModel>(context, MeleeRobotEntityModel(context.getPart(layer)), 1f) {

    override fun getTexture(entity: MeleeRobotEntity?): Identifier {
        return texture
    }
}