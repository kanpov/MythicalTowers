package com.redgrapefruit.mythicaltowers.client

import com.redgrapefruit.mythicaltowers.client.render.entity.GreenTntEntityRenderer
import com.redgrapefruit.mythicaltowers.common.init.MythicalEntities
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRenderDispatcher

@Environment(EnvType.CLIENT)
class MythicalTowersClient : ClientModInitializer {
    override fun onInitializeClient() {
        // Temp solution: entity renderer registry doesn't work. This's a placeholder
        EntityRendererRegistry.INSTANCE.register(MythicalEntities.GREEN_TNT) {
            dispatcher: EntityRenderDispatcher, context: EntityRendererRegistry.Context -> GreenTntEntityRenderer(dispatcher)
        }
    }
}