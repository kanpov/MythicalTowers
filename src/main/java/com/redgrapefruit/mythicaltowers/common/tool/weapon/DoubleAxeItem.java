package com.redgrapefruit.mythicaltowers.common.tool.weapon;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import net.minecraft.item.ToolMaterial;

import java.util.List;

/**
 * A double axe takes longer to strike, but hits MUCH harder
 */
public class DoubleAxeItem extends CustomAxeItem {
    public DoubleAxeItem(List<EffectConfig> configs, ToolMaterial material, int attackDamage, float attackSpeed) {
        super(configs, material, attackDamage, attackSpeed);
    }
}
