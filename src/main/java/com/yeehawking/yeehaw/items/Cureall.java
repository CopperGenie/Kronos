package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Cureall extends Item {
    public Cureall() {
        super(new Properties().group(Yeehaw.FOOD_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.removeActivePotionEffect(Effects.POISON);
        playerIn.removeActivePotionEffect(Effects.SLOWNESS);
        playerIn.removeActivePotionEffect(Effects.WITHER);
        playerIn.removeActivePotionEffect(Effects.WEAKNESS);
        playerIn.removeActivePotionEffect(Effects.NAUSEA);
        playerIn.removeActivePotionEffect(Effects.BLINDNESS);
        playerIn.removeActivePotionEffect(Effects.HUNGER);
        playerIn.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.8f, 1f);
        playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 5, 1, false, false));

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
