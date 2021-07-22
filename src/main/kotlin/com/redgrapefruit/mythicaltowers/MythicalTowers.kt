package com.redgrapefruit.mythicaltowers

import com.redgrapefruit.mythicaltowers.registry.BlockRegistry
import com.redgrapefruit.mythicaltowers.registry.EntityAttributeRegistry
import com.redgrapefruit.mythicaltowers.registry.EntityRegistry
import com.redgrapefruit.mythicaltowers.registry.ItemRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Material
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import java.util.*

class MythicalTowers : ModInitializer {
    override fun onInitialize() {
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

// Client/server manipulations

/**
 * Runs the [code] if the current environment is client
 */
inline fun onClient(code: () -> Unit) {
    if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) code.invoke()
}

/**
 * Runs the [code] if the current environment is server
 */
inline fun onServer(code: () -> Unit) {
    if (FabricLoader.getInstance().environmentType == EnvType.SERVER)  code.invoke()
}

/**
 * Checks if the current environment is client
 */
fun isClient() = FabricLoader.getInstance().environmentType == EnvType.CLIENT

/**
 * Checks if the current environment is server
 */
fun isServer() = FabricLoader.getInstance().environmentType == EnvType.SERVER
