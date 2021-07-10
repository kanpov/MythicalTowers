package com.redgrapefruit.mythicaltowers.mixin;

import com.redgrapefruit.mythicaltowers.common.armor.BootsItem;
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem;
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem;
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem;
import com.redgrapefruit.mythicaltowers.common.util.JavaNBT;
import com.redgrapefruit.mythicaltowers.common.util.LivingEntityMixinAccess;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin applies custom effects to the entity once the custom armor from the mod is put on.
 * <br><br>
 * Also manages stuns given by some mobs.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityMixinAccess {
    /**
     * The length of each stun
     */
    private static final int STUN_LENGTH = 60;

    private ItemStack airStack() { return new ItemStack(Items.AIR); }

    // Tracked data about previous armor stacks
    @Unique
    private ItemStack previousHelmetStack = airStack();
    @Unique
    private ItemStack previousChestplateStack = airStack();
    @Unique
    private ItemStack previousLeggingsStack = airStack();
    @Unique
    private ItemStack previousBootsStack = airStack();

    // Tracked data about stuns
    @Unique
    private boolean isStunned = false;
    @Unique
    private int stunTicks = 0;
    @Unique
    // Used to resume movement after the stun
    private float speedBeforeStun;

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow public abstract void setMovementSpeed(float movementSpeed);

    @Shadow private float movementSpeed;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        // Increment timer if needed
        if (isStunned) ++stunTicks;

        if (stunTicks >= STUN_LENGTH) {
            isStunned = false;
        }
    }

    @Override
    public void stun() {
        isStunned = true;
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        // Yeet the velocity if stunned and make sure to remember it to resume later with no loss of speed
        if (isStunned) {
            speedBeforeStun = movementSpeed;
            setMovementSpeed(0f);
        } else {
            setMovementSpeed(speedBeforeStun);
        }
    }

    /**
     * <code>setArmorInSlot</code> is called everytime an armor piece is put on/off. Main logic block
     *
     * @param slot  The {@link EquipmentSlot} of the armor
     * @param stack The {@link ItemStack} of the armor
     * @param info  Mixin {@link CallbackInfo}
     */
    @Inject(method = "setArmorInSlot", at = @At("TAIL"))
    private void setArmorInSlot(EquipmentSlot slot, ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();

        // Additional crash safety
        if (previousHelmetStack == null) previousHelmetStack = airStack();
        if (previousChestplateStack == null) previousChestplateStack = airStack();
        if (previousLeggingsStack == null) previousLeggingsStack = airStack();
        if (previousBootsStack == null) previousBootsStack = airStack();

        // How handle_X_Armor methods work:
        // 1. Check equipment slot
        // 2. Get previous and current item in the slot
        // 3. If previous is air and new is mod's armor, apply the effect using createEnablingStatusEffectInstance
        // 4. If previous is mod's armor and new is air, disable the effect using createDisablingStatusEffectInstance
        // 5. Update the previous stack to current for the next operation
        // And that for each of the 4 equipment slots

        switch (slot) {
            case HEAD -> handleHeadArmor(stack);
            case CHEST -> handleChestArmor(stack);
            case LEGS -> handleLegArmor(stack);
            case FEET -> handleFeetArmor(stack);
        }
    }

    /**
     * Handles head armor slot
     *
     * @param stack New stack
     */
    private void handleHeadArmor(ItemStack stack) {
        Item previousHelmet = previousHelmetStack.getItem();
        Item currentHelmet = stack.getItem();

        if (previousHelmet instanceof AirBlockItem && currentHelmet instanceof HelmetItem cast) {
            addStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousHelmet instanceof HelmetItem cast && currentHelmet instanceof AirBlockItem) {
            removeStatusEffect(cast.getEffect());
        }

        previousHelmetStack = new ItemStack(currentHelmet);
    }

    /**
     * Handles chest armor
     *
     * @param stack New stack
     */
    private void handleChestArmor(ItemStack stack) {
        Item previousChestplate = previousChestplateStack.getItem();
        Item currentChestplate = stack.getItem();

        if (previousChestplate instanceof AirBlockItem && currentChestplate instanceof ChestplateItem cast) {
            addStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousChestplate instanceof ChestplateItem cast && currentChestplate instanceof AirBlockItem) {
            removeStatusEffect(cast.getEffect());
        }

        previousChestplateStack = new ItemStack(currentChestplate);
    }

    /**
     * Handles leg armor
     *
     * @param stack New stack
     */
    private void handleLegArmor(ItemStack stack) {
        Item previousLeggings = previousLeggingsStack.getItem();
        Item currentLeggings = stack.getItem();

        if (previousLeggings instanceof AirBlockItem && currentLeggings instanceof LeggingsItem cast) {
            addStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousLeggings instanceof LeggingsItem cast && currentLeggings instanceof AirBlockItem) {
            removeStatusEffect(cast.getEffect());
        }

        previousLeggingsStack = new ItemStack(currentLeggings);
    }

    /**
     * Handles feet armor
     *
     * @param stack New stack
     */
    private void handleFeetArmor(ItemStack stack) {
        Item previousBoots = previousBootsStack.getItem();
        Item currentBoots = stack.getItem();

        if (previousBoots instanceof AirBlockItem && currentBoots instanceof BootsItem cast) {
            addStatusEffect(createStatusEffectInstance(cast.getEffect(), cast.getAmplifier()));
        }

        if (previousBoots instanceof BootsItem cast && currentBoots instanceof AirBlockItem) {
            removeStatusEffect(cast.getEffect());
        }

        previousBootsStack = new ItemStack(currentBoots);
    }

    /**
     * Reads tracked data from an {@link NbtCompound}
     *
     * @param nbt  The source {@link NbtCompound}
     * @param info Mixin {@link CallbackInfo}
     */
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        previousHelmetStack = JavaNBT.readItemStack(nbt, "Previous Helmet Stack");
        previousChestplateStack = JavaNBT.readItemStack(nbt, "Previous Chestplate Stack");
        previousLeggingsStack = JavaNBT.readItemStack(nbt, "Previous Leggings Stack");
        previousBootsStack = JavaNBT.readItemStack(nbt, "Previous Boots Stack");

        isStunned = nbt.getBoolean("Is Entity Stunned");
        stunTicks = nbt.getInt("Stun Ticks");
        speedBeforeStun = nbt.getFloat("Speed Before Stun");
    }

    /**
     * Writes tracked data to an {@link NbtCompound}
     *
     * @param nbt  The output {@link NbtCompound}
     * @param info Mixin {@link CallbackInfo}
     */
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToTag(NbtCompound nbt, CallbackInfo info) {
        JavaNBT.writeItemStack(nbt, "Previous Helmet Stack", previousHelmetStack);
        JavaNBT.writeItemStack(nbt, "Previous Chestplate Stack", previousChestplateStack);
        JavaNBT.writeItemStack(nbt, "Previous Leggings Stack", previousLeggingsStack);
        JavaNBT.writeItemStack(nbt, "Previous Boots Stack", previousBootsStack);

        nbt.putBoolean("Is Entity Stunned", isStunned);
        nbt.putInt("Stun Ticks", stunTicks);
        nbt.putFloat("Speed Before Stun", speedBeforeStun);
    }

    /**
     * Creates an armor {@link StatusEffectInstance}
     *
     * @param effect    The {@link StatusEffect} itself
     * @param amplifier The amplifier of the effect
     * @return Generated {@link StatusEffectInstance}
     */
    private StatusEffectInstance createStatusEffectInstance(StatusEffect effect, int amplifier) {
        return new StatusEffectInstance(
                effect,
                999999,
                amplifier
        );
    }
}
