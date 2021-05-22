package com.redgrapefruit.mythicaltowers.common.item;

import com.redgrapefruit.mythicaltowers.common.MythicalTowers;
import com.redgrapefruit.mythicaltowers.common.data.EffectConfig;
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups;
import com.redgrapefruit.mythicaltowers.common.util.IntRange;
import net.minecraft.entity.effect.StatusEffectInstance;
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
        // Iterate through each config
        for (EffectConfig config : configs) {
            // Get ranged duration, else use set/permanent duration
            int duration = config.getDurationRange()
                    .map(IntRange::pick)
                    .orElseGet(config::getDuration);

            // Get ranged amplifier, else use set amplifier
            int amplifier = config.getAmplifierRange()
                    .map(IntRange::pick)
                    .orElseGet(config::getAmplifier);

            // Get chance, else use 100 (always applied)
            int chance = config.getChance() == EffectConfig.UNDEFINED_VALUE ? 100 : config.getChance();
            // Pick a number and see if it fits in the chance, then apply the effect
            if (MythicalTowers.RANDOM.nextInt(100) <= chance) {
                user.applyStatusEffect(new StatusEffectInstance(
                        config.getStatusEffect(),
                        duration,
                        amplifier
                ));
            }
        }

        // Decrement the stack to make the orb disappear once it is used
        user.getStackInHand(hand).decrement(1);

        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
