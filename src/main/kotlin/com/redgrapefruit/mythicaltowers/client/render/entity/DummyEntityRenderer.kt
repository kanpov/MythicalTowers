package com.redgrapefruit.mythicaltowers.client.render.entity

import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier

/**
 * An [EntityRenderer] which does absolutely nothing. Don't ask questions
 */
class DummyEntityRenderer<TEntity>(context: EntityRendererFactory.Context) :
    EntityRenderer<TEntity>(context) where TEntity : Entity {
    override fun getTexture(entity: TEntity): Identifier = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE
}