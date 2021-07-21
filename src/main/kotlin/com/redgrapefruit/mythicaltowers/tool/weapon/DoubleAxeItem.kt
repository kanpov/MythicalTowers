package com.redgrapefruit.mythicaltowers.tool.weapon

import com.redgrapefruit.mythicaltowers.core.EffectConfig
import net.minecraft.item.ToolMaterial

/**
 * A double axe takes longer to strike, but hits MUCH harder
 */
class DoubleAxeItem(configs: List<EffectConfig>, material: ToolMaterial?, attackDamage: Int, attackSpeed: Float) :
    CustomAxeItem(configs, material, attackDamage, attackSpeed)