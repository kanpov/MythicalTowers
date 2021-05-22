package com.redgrapefruit.mythicaltowers.common.item;

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig;
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine;
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

/**
 * An orb applies effects to the player on right-click.<br>
 * For more info check out wiki page 6 on details about the effects
 */
public class OrbItem extends Item {
    /**
     * The {@link EffectConfig}s linked to this orb
     */
    private final List<EffectConfig> configs;

    public OrbItem(List<EffectConfig> configs) {
        super(new Item.Settings().group(MythicalItemGroups.ORBS));

        this.configs = configs;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        EffectEngine.onOrbUsed(configs, user, hand);

        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
