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

public class ScrollOfTheAbyss extends Item {
    public ScrollOfTheAbyss() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        World world = playerIn.getEntityWorld();
        int p = playerIn.getPosition().getY();

        for (int i = -p; i <= -1; i++) {
            // Ring 1
            world.setBlockState(playerIn.getPosition().add(1, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, 0), Blocks.STONE.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, -1), Blocks.AIR.getDefaultState());
            // Ring 2
            world.setBlockState(playerIn.getPosition().add(2, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, 2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, 2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, 2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 2), Blocks.AIR.getDefaultState());
            // Ring 3
            world.setBlockState(playerIn.getPosition().add(3, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(3, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(3, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(3, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(3, i, 2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, -3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, 3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, -3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, 3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, -3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, 3), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-3, i, -2), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-3, i, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-3, i, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-3, i, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-3, i, 2), Blocks.AIR.getDefaultState());
        }
        playerIn.playSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
