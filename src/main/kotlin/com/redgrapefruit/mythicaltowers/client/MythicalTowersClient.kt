package com.redgrapefruit.mythicaltowers.client

import com.redgrapefruit.mythicaltowers.client.registry.EntityModelLayerRegistry
import com.redgrapefruit.mythicaltowers.client.registry.EntityRendererRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText

@Environment(EnvType.CLIENT)
class MythicalTowersClient : ClientModInitializer {
    override fun onInitializeClient() {
        // Thank the player for using the MythicalTowers mod in the chat
        ClientPlayConnectionEvents.JOIN.register { _, _, client ->
            client.player?.sendMessage(LiteralText("Thank you for using the MythicalTowers mod!"), false)
        }

        EntityRendererRegistry.init()
        EntityModelLayerRegistry.init()
    }
}

// Some misc extensions

/**
 * This utility simplifies the rendering process of a primitive [ModelPart] and just renders all cuboids in it.
 */
fun renderPart(
    part: ModelPart,
    matrices: MatrixStack,
    vertices: VertexConsumer,
    light: Int,
    overlay: Int,
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float): Unit = matrices.use {

    // Render all cuboids using a few handy functions provided from vanilla
    part.forEachCuboid(this) { entry, _, _, cuboid ->
        cuboid.renderCuboid(entry, vertices, light, overlay, red, green, blue, alpha)
    }
}

/**
 * This extension is a simple wrapper around `push` and `pop` methods to reduce boilerplate related to them
 */
inline fun MatrixStack.use(action: MatrixStack.() -> Unit) {
    push()
    action.invoke(this)
    pop()
}
