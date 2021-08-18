package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.entities.DionysusEntity;
import com.yeehawking.yeehaw.entities.RoseQueenEntity;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class WiltingRose extends ModSpawnEggItem {
    public WiltingRose(RegistryObject<EntityType<RoseQueenEntity>> entityType, int primaryColor, int secondaryColor, Properties properties) {
        super(entityType, primaryColor, secondaryColor, properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (context.getPlayer().getEntityWorld().isRemote()) {
            player.playSound(SoundInit.LICH_SUMMON.get(), 1f, 0.8f);
        }
        return super.onItemUse(context);
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.func_234801_g_().mergeStyle(TextFormatting.GRAY));
    }

    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent func_234801_g_() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }
}
