package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.client.model.MeleeRobotEntityModel
import com.redgrapefruit.mythicaltowers.client.registry.EntityModelLayerRegistry
import com.redgrapefruit.mythicaltowers.entity.GreenMeleeRobotEntity
import com.redgrapefruit.mythicaltowers.entity.MeleeRobotEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier

sealed class MeleeRobotEntityRenderer<T : MeleeRobotEntity>(
    context: EntityRendererFactory.Context,
    private val texture: Identifier
) : MobEntityRenderer<T, MeleeRobotEntityModel<T>>(context, MeleeRobotEntityModel(context.getPart(EntityModelLayerRegistry.MELEE_ROBOT)), 0.1f) {
    override fun getTexture(entity: T): Identifier = texture
}

class GreenMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<GreenMeleeRobotEntity>(context, idOf("textures/entity/green_melee_robot.png"))
