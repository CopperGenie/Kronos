package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.GenieEntity;
import com.yeehawking.yeehaw.entities.LichEntity;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class UnchargedPhylactery extends Item {
    public UnchargedPhylactery() {
        super(new Properties().group(Yeehaw.SUMMON_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            ModEntityType.LICH.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);

            ItemStack itemstack = playerIn.getHeldItemMainhand();
            itemstack.shrink(1);
        }
        playerIn.playSound(SoundInit.LICH_SUMMON.get(), 0.9f, 1f);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
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
