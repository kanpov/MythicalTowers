package com.redgrapefruit.mythicaltowers.common

import com.redgrapefruit.mythicaltowers.common.registry.BlockRegistry
import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import com.redgrapefruit.mythicaltowers.common.registry.ItemRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Material
import net.minecraft.util.Identifier
import java.util.*

class MythicalTowers : ModInitializer {
    override fun onInitialize() {
        ItemRegistry.init()
        BlockRegistry.init()
        EntityRegistry.init()
    }

    companion object {
        val RANDOM = Random()

        /**
         * Returns the [Identifier] of given name
         *
         * @param name Name
         * @return Generated [Identifier]
         */
        fun idOf(name: String): Identifier {
            return Identifier("mythicaltowers", name)
        }

        /**
         * Creates standard block settings
         *
         * @param hardness Block's hardness
         */
        fun blockSettings(hardness: Float): FabricBlockSettings {
            return FabricBlockSettings
                .of(Material.METAL)
                .collidable(true)
                .breakByHand(false)
                .breakByTool(FabricToolTags.PICKAXES, 4)
                .hardness(hardness)
        }
    }
}