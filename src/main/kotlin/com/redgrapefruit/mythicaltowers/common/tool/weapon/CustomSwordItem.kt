package com.redgrapefruit.mythicaltowers.common.tool.weapon

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine.onPostHit
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine.onPostMine
import com.redgrapefruit.mythicaltowers.common.init.MythicalItemGroups
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * A custom sword with effects post mine and post hit
 */
open class CustomSwordItem(
    /**
     * The [EffectConfig]s linked to this axe
     */
    private val configs: List<EffectConfig>, material: ToolMaterial?, attackDamage: Int, attackSpeed: Float
) : SwordItem(material, attackDamage, attackSpeed, Settings().group(MythicalItemGroups.WEAPONS)) {
    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        super.postHit(stack, target, attacker)
        onPostHit(configs, target, attacker)
        return true
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        super.postMine(stack, world, state, pos, miner)
        onPostMine(configs, miner)
        return true
    }
}