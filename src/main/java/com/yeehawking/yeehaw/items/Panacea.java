package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.List;

public class Panacea extends Item {
    public Panacea() {
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
        playerIn.playSound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK, 0.8f, 1f);
        playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 2, false, false));
        playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 10, 2, false, false));
        playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 10, 2, false, false));
        playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20 * 2, 0, false, false));

        List<MobEntity> mobList = worldIn.getEntitiesWithinAABB(MobEntity.class, playerIn.getBoundingBox().grow(50.0D, 50.0D, 50.0D));
        for (MobEntity entity : mobList) {
            entity.addPotionEffect(new EffectInstance(Effects.GLOWING, 20 * 30, 0, false, false));
            entity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20 * 2, 0, false, false));
        }

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
