package com.redgrapefruit.mythicaltowers.client

import com.redgrapefruit.mythicaltowers.client.registry.EntityModelLayerRegistry
import com.redgrapefruit.mythicaltowers.client.registry.EntityRendererRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
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