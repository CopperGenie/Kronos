package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ScrollOfThePlains extends Item {
    public ScrollOfThePlains() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        for (int i = -25; i <= 25; i++) {
            for (int j = -25; j <= 25; j++) {
                worldIn.setBlockState(playerIn.getPosition().add(i, -1, j), Blocks.GRASS_BLOCK.getDefaultState());

                for (int k = -2; k >= -50; k--) {
                    worldIn.setBlockState(playerIn.getPosition().add(i, k, j), Blocks.DIRT.getDefaultState());
                }
            }
        }
        for (int i = -25; i <= 25; i++) {
            for (int j = -25; j <= 25; j++) {
                for (int k = 0; k <= 30; k++) {
                    worldIn.setBlockState(playerIn.getPosition().add(i, k, j), Blocks.AIR.getDefaultState());
                }
            }
        }
        playerIn.playSound(SoundEvents.BLOCK_GRASS_PLACE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
