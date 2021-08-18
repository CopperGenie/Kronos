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

public class Pathwinder extends Item {
    public Pathwinder() {
        super(new Properties().group(Yeehaw.TOOL_TAB));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.func_234801_g_().mergeStyle(TextFormatting.DARK_GREEN));
    }

    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent func_234801_g_() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }
}
