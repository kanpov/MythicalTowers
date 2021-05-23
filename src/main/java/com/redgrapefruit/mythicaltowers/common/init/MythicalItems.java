package com.redgrapefruit.mythicaltowers.common.init;

import com.redgrapefruit.mythicaltowers.common.armor.BootsItem;
import com.redgrapefruit.mythicaltowers.common.armor.ChestplateItem;
import com.redgrapefruit.mythicaltowers.common.armor.HelmetItem;
import com.redgrapefruit.mythicaltowers.common.armor.LeggingsItem;
import com.redgrapefruit.mythicaltowers.common.init.names.ItemNames;
import com.redgrapefruit.mythicaltowers.common.item.AmuletItem;
import com.redgrapefruit.mythicaltowers.common.item.IngotItem;
import com.redgrapefruit.mythicaltowers.common.item.OrbItem;
import com.redgrapefruit.mythicaltowers.common.tool.*;
import com.redgrapefruit.mythicaltowers.common.tool.weapon.CustomAxeItem;
import com.redgrapefruit.mythicaltowers.common.tool.weapon.CustomSwordItem;
import com.redgrapefruit.mythicaltowers.common.tool.weapon.DoubleAxeItem;
import com.redgrapefruit.mythicaltowers.common.tool.weapon.DoubleSwordItem;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.redgrapefruit.mythicaltowers.common.MythicalTowers.idOf;

/**
 * Stores and registers mod's items
 */
public class MythicalItems {
    // Orbs
    public static final OrbItem GREEN_ORB = new OrbItem(MythicalEffectConfigs.GREEN_ORB);

    // Amulets
    public static final AmuletItem GREEN_AMULET = new AmuletItem(StatusEffects.JUMP_BOOST, 1);

    // Ingots
    public static final IngotItem GREEN_INGOT = new IngotItem();

    // Tools
    public static final CustomPickaxeItem GREEN_PICKAXE = new CustomPickaxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 2, -2.3f);
    public static final CustomShovelItem GREEN_SHOVEL = new CustomShovelItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.5f);
    public static final CustomHoeItem GREEN_HOE = new CustomHoeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 1, -1.25f);

    // Weapons
    public static final CustomSwordItem GREEN_SWORD = new CustomSwordItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 3, -1.8f);
    public static final CustomAxeItem GREEN_AXE = new CustomAxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 5, -3.1f);
    public static final DoubleSwordItem GREEN_DOUBLE_SWORD = new DoubleSwordItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 5, -1.4f);
    public static final DoubleAxeItem GREEN_DOUBLE_AXE = new DoubleAxeItem(MythicalEffectConfigs.GREEN_TOOLS, MythicalToolMaterials.GREEN, 8, -3.7f);

    // Armor
    public static final HelmetItem GREEN_HELMET = new HelmetItem(MythicalArmorMaterials.GREEN, StatusEffects.REGENERATION, 1);
    public static final ChestplateItem GREEN_CHESTPLATE = new ChestplateItem(MythicalArmorMaterials.GREEN, StatusEffects.ABSORPTION, 1);
    public static final LeggingsItem GREEN_LEGGINGS = new LeggingsItem(MythicalArmorMaterials.GREEN, StatusEffects.REGENERATION, 0);
    public static final BootsItem GREEN_BOOTS = new BootsItem(MythicalArmorMaterials.GREEN, StatusEffects.ABSORPTION, 0);

    public static void init() {
        register(ItemNames.GREEN_ORB, GREEN_ORB);
        register(ItemNames.GREEN_AMULET, GREEN_AMULET);
        register(ItemNames.GREEN_INGOT, GREEN_INGOT);
        register(ItemNames.GREEN_PICKAXE, GREEN_PICKAXE);
        register(ItemNames.GREEN_SHOVEL, GREEN_SHOVEL);
        register(ItemNames.GREEN_HOE, GREEN_HOE);
        register(ItemNames.GREEN_SWORD, GREEN_SWORD);
        register(ItemNames.GREEN_AXE, GREEN_AXE);
        register(ItemNames.GREEN_DOUBLE_SWORD, GREEN_DOUBLE_SWORD);
        register(ItemNames.GREEN_DOUBLE_AXE, GREEN_DOUBLE_AXE);
        register(ItemNames.GREEN_HELMET, GREEN_HELMET);
        register(ItemNames.GREEN_CHESTPLATE, GREEN_CHESTPLATE);
        register(ItemNames.GREEN_LEGGINGS, GREEN_LEGGINGS);
        register(ItemNames.GREEN_BOOTS, GREEN_BOOTS);
    }

    /**
     * Registers an item
     *
     * @param name Item name
     * @param item Item instance
     */
    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, idOf(name), item);
    }
}
