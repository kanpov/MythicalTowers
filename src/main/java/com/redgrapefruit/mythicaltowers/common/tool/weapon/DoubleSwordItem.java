package com.redgrapefruit.mythicaltowers.common.tool.weapon;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import net.minecraft.item.ToolMaterial;

import java.util.List;

/**
 * A double sword takes shorter to strike and hits harder
 */
public class DoubleSwordItem extends CustomSwordItem {
    public DoubleSwordItem(List<EffectConfig> configs, ToolMaterial material, int attackDamage, float attackSpeed) {
        super(configs, material, attackDamage, attackSpeed);
    }
}
