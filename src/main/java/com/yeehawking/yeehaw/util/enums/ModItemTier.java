package com.yeehawking.yeehaw.util.enums;

import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {

    SOUL_STEEL(2, 2500, 7.0f, 0, 16, () -> {
        return Ingredient.fromItems(ModItems.SOUL_STEEL.get());
    }),

    UNDERSTEEL(3, 1300, 7.0f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.UNDERSTEEL.get());
    }),

    BUSTER(2, 2000, 7.0f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.TITAN_INGOT.get());
    }),

    KRAKEN(2, 1000, 5.7f, 0, 13, () -> {
        return Ingredient.fromItems(ModItems.TATTERED_SOUL.get());
    }),
    SUNSET(2, 1000, 5.7f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.CUBIC_ROSEUM.get());
    }),
    PLAGUE_ROD(0, 5, 1f, 0, 0, () -> null),
    ANGEL_TABLET(0, 20, 1f, 0, 0, () -> null),
    TWILIGHT(2, 1200, 6f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.TWILIGHT_ROSE_ITEM.get());
    }),
    CUTLASS(2, 1300, 6f, 0, 12, () -> {
        return Ingredient.fromItems(Items.IRON_INGOT);
    }),
    HARVESTER(2, 1350, 6f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.TATTERED_SOUL.get());
    }),
    YOUNGLING_SLAYER(2, 1250, 6f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.CRYSTAL_SHARD.get());
    }),
    AGARIC_CLEAVER(2, 1500, 6f, 0, 12, () -> {
        return Ingredient.fromItems(ModItems.FUNGAL_TITANITE.get());
    });



    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability,
                Supplier<Ingredient> repairMaterial)
    {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
