package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.util.enums.ModItemTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TwilightSword extends SwordItem {
    public TwilightSword() {
        super(ModItemTier.TWILIGHT, 6, -2.4f, new Properties().group(Yeehaw.COMBAT_TAB));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.DARK_PURPLE));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }

}
