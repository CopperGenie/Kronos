package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Cheese extends Item {
    public Cheese() {
        super(new Properties()

                .group(Yeehaw.FOOD_TAB)
                .food(new Food.Builder()
                    .hunger(6)
                    .saturation(3.0f)
                    .build())

        );
    }
}
