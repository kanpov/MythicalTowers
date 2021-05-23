package com.redgrapefruit.mythicaltowers.common.tool;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine;
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * A shovel with post mine and post hit effects
 */
public class CustomShovelItem extends ShovelItem {
    /**
     * The {@link EffectConfig}s linked to this axe
     */
    private final List<EffectConfig> configs;

    public CustomShovelItem(List<EffectConfig> configs, ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Item.Settings().group(MythicalItemGroups.TOOLS));

        this.configs = configs;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        EffectEngine.onPostHit(configs, target, attacker);

        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        super.postMine(stack, world, state, pos, miner);

        EffectEngine.onPostMine(configs, miner);

        return true;
    }
}
