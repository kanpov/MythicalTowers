package com.redgrapefruit.mythicaltowers.common

import com.redgrapefruit.mythicaltowers.common.init.MythicalBlocks
import com.redgrapefruit.mythicaltowers.common.init.MythicalEntities
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups
import com.redgrapefruit.mythicaltowers.common.init.MythicalItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import java.util.*

class MythicalTowers : ModInitializer {
    override fun onInitialize() {
        MythicalItemGroups.init()
        MythicalItems.init()
        MythicalBlocks.init()
        MythicalEntities.init()
    }

    companion object {
        private const val MOD_ID = "mythicaltowers"

        @JvmField
        val RANDOM = Random()

        /**
         * Returns the [Identifier] of given name
         *
         * @param name Name
         * @return Generated [Identifier]
         */
        @JvmStatic
        fun idOf(name: String?): Identifier {
            return Identifier(MOD_ID, name)
        }
    }
}