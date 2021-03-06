package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import com.yeehawking.yeehaw.entities.TreasureFairyEntity;
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

public class FairyStone extends Item {
    public FairyStone() {
        super(new Properties().group(Yeehaw.SUMMON_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            ModEntityType.TREASURE_FAIRY.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);

            ItemStack itemstack = playerIn.getHeldItemMainhand();
            itemstack.shrink(1);
        }
        playerIn.playSound(SoundInit.TREASURE_FAIRY_SUMMON.get(), 0.8f, 1.0f);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.AQUA));
        tooltip.add(this.text1().mergeStyle(TextFormatting.DARK_AQUA));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }
    public IFormattableTextComponent text1() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc1"); }

}
