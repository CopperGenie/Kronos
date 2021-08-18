package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.SoundInit;
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

public class Quaalude extends Item {
    public Quaalude() {
        super(new Properties()

                .group(Yeehaw.FOOD_TAB)
                .food(new Food.Builder()
                    .effect(new EffectInstance(Effects.LEVITATION, 20*2, 4), 1)
                    .effect(new EffectInstance(Effects.ABSORPTION, 20*10, 1), 1)
                    .effect(new EffectInstance(Effects.REGENERATION, 20*20, 1), 1)
                    .effect(new EffectInstance(Effects.RESISTANCE, 20*30, 1), 1)
                    .effect(new EffectInstance(Effects.NAUSEA, 20*40, 1), 1)
                    .fastToEat()
                    .setAlwaysEdible()
                    .build())

        );
    }

    public SoundEvent getEatSound() {
        return SoundInit.KRORK_HURT.get();
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.DARK_RED));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }
}
