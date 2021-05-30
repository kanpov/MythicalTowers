package com.redgrapefruit.mythicaltowers.common

import com.redgrapefruit.mythicaltowers.common.registry.BlockRegistry
import com.redgrapefruit.mythicaltowers.common.registry.EntityRegistry
import com.redgrapefruit.mythicaltowers.common.registry.ItemGroupRegistry
import com.redgrapefruit.mythicaltowers.common.registry.ItemRegistry
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import java.util.*

class MythicalTowers : ModInitializer {
    override fun onInitialize() {
        ItemGroupRegistry.init()
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
    }
}