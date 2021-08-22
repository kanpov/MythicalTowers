package com.redgrapefruit.mythicaltowers.client.render.entity

import com.redgrapefruit.mythicaltowers.MythicalTowers.Companion.idOf
import com.redgrapefruit.mythicaltowers.client.model.MeleeRobotEntityModel
import com.redgrapefruit.mythicaltowers.client.registry.EntityModelLayerRegistry
import com.redgrapefruit.mythicaltowers.entity.*
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

class YellowMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<YellowMeleeRobotEntity>(context, idOf("textures/entity/yellow_melee_robot.png"))

class OrangeMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<OrangeMeleeRobotEntity>(context, idOf("textures/entity/orange_melee_robot.png"))

class RedMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<RedMeleeRobotEntity>(context, idOf("textures/entity/red_melee_robot.png"))

class BlueMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<BlueMeleeRobotEntity>(context, idOf("textures/entity/blue_melee_robot.png"))

class PurpleMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<PurpleMeleeRobotEntity>(context, idOf("textures/entity/purple_melee_robot.png"))

class GrayMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<GrayMeleeRobotEntity>(context, idOf("textures/entity/gray_melee_robot.png"))

class BlackMeleeRobotEntityRenderer(context: EntityRendererFactory.Context)
    : MeleeRobotEntityRenderer<BlackMeleeRobotEntity>(context, idOf("textures/entity/black_melee_robot.png"))
