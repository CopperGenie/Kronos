package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tomato extends Item {
    public Tomato() {
        super(new Properties()

                .group(Yeehaw.FOOD_TAB)
                .food(new Food.Builder()
                    .hunger(6)
                    .saturation(3.0f)
                    .build())

        );
    }
}
