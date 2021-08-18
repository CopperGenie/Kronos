package com.yeehawking.yeehaw.util.enums;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    SOUL_STEEL(Yeehaw.MOD_ID + ":soul_steel", 75, new int[] { 3, 5, 6, 3 }, 16,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0f, () -> { return Ingredient.fromItems(ModItems.SOUL_STEEL.get()); },
            0),
    FLAYER(Yeehaw.MOD_ID + ":flayer", 75, new int[] { 3, 5, 5, 3 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, () -> { return Ingredient.fromItems(ModItems.TATTERED_SOUL.get()); },
            0),
    CLOUDSTONE(Yeehaw.MOD_ID + ":cloudstone", 30, new int[] { 2, 0, 0, 0 }, 10,
    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, () -> { return Ingredient.fromItems(ModItems.CLOUDSTONE.get()); },
            0),
    UNDERSTEEL(Yeehaw.MOD_ID + ":understeel", 50, new int[] { 3, 6, 7, 3 }, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, () -> { return Ingredient.fromItems(ModItems.UNDERSTEEL.get()); },
            0),
    TITAN(Yeehaw.MOD_ID + ":titan", 50, new int[] { 5, 8, 8, 5 }, 15,
    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, () -> { return Ingredient.fromItems(ModItems.TITAN_INGOT.get()); },
            1),
    METEOR(Yeehaw.MOD_ID + ":meteor", 50, new int[] { 0, 0, 0, 3 }, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0f, () -> { return Ingredient.fromItems(ModItems.REFINED_METEORITE.get()); },
            0),
    YWNB(Yeehaw.MOD_ID + ":ywnb", 50, new int[] { 0, 0, 0, 3 }, 17,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, () -> { return Ingredient.fromItems(ModItems.BRIMSTONE.get()); },
            0);

/// TODO: WHAT IS MAX DAMAGE FACTOR?

    // Armor slot array: {feet, legs, chest, head}
    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 11, 16, 15, 13 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final float knockbackResistance;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                     SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial, float knockbackResistance) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
