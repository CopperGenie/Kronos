package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ForbiddenFruit extends BlockItem {

    public ForbiddenFruit(Block block) {
        super(block, new Item.Properties().group(Yeehaw.TAB));
    }
}


