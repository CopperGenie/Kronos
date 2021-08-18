package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfMight extends Item {
    public ScrollOfMight() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20*40, 1, false, false));

        playerIn.playSound(SoundInit.HUMAN_HURT.get(), 0.8f, 0.8f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
