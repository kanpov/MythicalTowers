package com.redgrapefruit.mythicaltowers.common.item

import com.redgrapefruit.mythicaltowers.common.registry.MythicalItemGroups
import net.minecraft.item.Item

/**
 * A ingot of a certain custom material
 */
class IngotItem : Item(Settings().group(MythicalItemGroups.INGOTS))