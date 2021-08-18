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

public class ScrollOfLavaLake extends Item {
    public ScrollOfLavaLake() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // Ring 1
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, 0), Blocks.STONE.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, -1), Blocks.LAVA.getDefaultState());
        // Ring 2
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, 2), Blocks.LAVA.getDefaultState());
        // Ring 3
        worldIn.setBlockState(playerIn.getPosition().add(3, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(3, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(3, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(3, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(3, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, -3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(1, -1, 3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, -3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(0, -1, 3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, -3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-1, -1, 3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-3, -1, -2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-3, -1, -1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-3, -1, 0), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-3, -1, 1), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-3, -1, 2), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, -3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(-2, -1, 3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, 3), Blocks.LAVA.getDefaultState());
        worldIn.setBlockState(playerIn.getPosition().add(2, -1, -3), Blocks.LAVA.getDefaultState());

        playerIn.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
