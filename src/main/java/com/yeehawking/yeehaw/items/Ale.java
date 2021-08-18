package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class Ale extends Item {
    public Ale() {
        super(new Properties()
            .group(Yeehaw.FOOD_TAB)
            .maxStackSize(8)
            .food(new Food.Builder()
                .effect(new EffectInstance(Effects.NAUSEA, 20*15, 0), 1)
                .effect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0), 1)
                .hunger(6)
                .saturation(2)
                .setAlwaysEdible()
                .build())

        );
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

}