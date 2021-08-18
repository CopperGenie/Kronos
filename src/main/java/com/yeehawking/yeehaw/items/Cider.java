package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Cider extends Item {
    public Cider() {
        super(new Properties()
            .group(Yeehaw.FOOD_TAB)
            .maxStackSize(8)
            .food(new Food.Builder()
            .effect(new EffectInstance(Effects.NAUSEA, 20*15, 0), 1)
            .effect(new EffectInstance(Effects.HASTE, 20*30, 0), 1)
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