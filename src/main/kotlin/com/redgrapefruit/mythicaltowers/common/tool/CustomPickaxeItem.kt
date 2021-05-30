package com.redgrapefruit.mythicaltowers.common.tool

import com.redgrapefruit.mythicaltowers.common.core.EffectConfig
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine.onPostHit
import com.redgrapefruit.mythicaltowers.common.core.EffectEngine.onPostMine
import com.redgrapefruit.mythicaltowers.common.registry.ItemGroupRegistry
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * A custom pickaxe with effects post mine and post hit
 */
class CustomPickaxeItem(
    /**
     * The [EffectConfig]s linked to this axe
     */
    private val configs: List<EffectConfig>, material: ToolMaterial?, attackDamage: Int, attackSpeed: Float
) : PickaxeItem(material, attackDamage, attackSpeed, Settings().group(ItemGroupRegistry.TOOLS)) {

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