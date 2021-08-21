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

public class RunRum extends Item {
    public RunRum() {
        super(new Properties()
            .group(Yeehaw.FOOD_TAB)
            .maxStackSize(8)
            .food(new Food.Builder()
                .effect(new EffectInstance(Effects.SPEED, 20*120, 1), 1)
                .effect(new EffectInstance(Effects.WEAKNESS, 20*120, 9), 1)
                .effect(new EffectInstance(Effects.RESISTANCE, 20*120, 9), 1)
                .hunger(8)
                .saturation(2)
                .setAlwaysEdible()
                .fastToEat()
                .build())
        );
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_RAVAGER_ROAR;
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.BLUE));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }

}