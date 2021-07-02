package com.redgrapefruit.mythicaltowers.client

import com.redgrapefruit.mythicaltowers.client.registry.EntityModelLayerRegistry
import com.redgrapefruit.mythicaltowers.client.registry.EntityRendererRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
class MythicalTowersClient : ClientModInitializer {
    override fun onInitializeClient() {
        EntityRendererRegistry.init()
        EntityModelLayerRegistry.init()
    }
}