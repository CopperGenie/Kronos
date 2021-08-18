package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DecryptedHomingKey extends Item {
    public DecryptedHomingKey() {
        super(new Properties().group(Yeehaw.TAB));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.AQUA));
        tooltip.add(this.text1().mergeStyle(TextFormatting.DARK_AQUA));
        tooltip.add(this.text2().mergeStyle(TextFormatting.WHITE));
        tooltip.add(this.text3().mergeStyle(TextFormatting.WHITE));
    }

    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text1() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc1");
    }
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text2() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc2");
    }
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text3() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc3");
    }
}
