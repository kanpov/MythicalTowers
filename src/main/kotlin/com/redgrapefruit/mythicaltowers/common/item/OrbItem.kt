package com.redgrapefruit.mythicaltowers.common.item

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine.onOrbUsed
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

/**
 * An orb applies effects to the player on right-click.<br></br>
 * For more info check out wiki page 6 on details about the effects
 */
class OrbItem(
    /**
     * The [EffectConfig]s linked to this orb
     */
    private val configs: List<EffectConfig>
) : Item(Settings().group(MythicalItemGroups.ORBS)) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        onOrbUsed(configs, user, hand)
        return TypedActionResult.consume(user.getStackInHand(hand))
    }
}