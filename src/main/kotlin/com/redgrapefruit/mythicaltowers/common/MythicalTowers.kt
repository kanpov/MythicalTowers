package com.redgrapefruit.mythicaltowers.common

import com.redgrapefruit.mythicaltowers.common.registry.BlockRegistry
import com.redgrapefruit.mythicaltowers.common.registry.EntityAttributeRegistry
import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import com.redgrapefruit.mythicaltowers.common.registry.ItemRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Material
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import java.util.*

class MythicalTowers : ModInitializer {
    override fun onInitialize() {
        // Thank the player for using the MythicalTowers mod in the chat
        ClientPlayConnectionEvents.JOIN.register { _, _, client ->
            client.player?.sendMessage(LiteralText("Thank you for using the MythicalTowers mod!"), false)
        }

        ItemRegistry.init()
        BlockRegistry.init()
        EntityRegistry.init()
        EntityAttributeRegistry.init()
    }

    companion object {
        val RANDOM = Random()
        val GROUP: ItemGroup = FabricItemGroupBuilder.build(idOf("group")) { ItemStack(ItemRegistry.GREEN_ORB) }

        /**
         * Returns the [Identifier] of given name
         *
         * @param name Name
         * @return Generated [Identifier]
         */
        fun idOf(name: String): Identifier {
            return Identifier("mythicaltowers", name)
        }

        val UNBREAKABLE: AbstractBlock.Settings = FabricBlockSettings
                .of(Material.METAL)
                .collidable(true)
                .breakByHand(false)
                .breakByTool(FabricToolTags.PICKAXES, 4)
                .hardness(Float.MAX_VALUE)
    }
}