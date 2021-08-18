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

public class ScrollOfEarthBarrier extends Item {
    public ScrollOfEarthBarrier() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        World world = playerIn.getEntityWorld();

        world.setBlockState(playerIn.getPosition().add(2, 0, -2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 0, -1), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 0, 0), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 0, 1), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 0, 2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 0, -2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 0, 2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 0, -2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 0, 2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 0, -2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 0, 2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 0, -2), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 0, -1), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 0, 0), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 0, 1), Blocks.DIRT.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 0, 2), Blocks.DIRT.getDefaultState());

        world.setBlockState(playerIn.getPosition().add(2, 1, -2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 1, -1), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 1, 0), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 1, 1), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(2, 1, 2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 1, -2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 1, 2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 1, -2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 1, 2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 1, -2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 1, 2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 1, -2), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 1, -1), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 1, 0), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 1, 1), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-2, 1, 2), Blocks.GRASS_BLOCK.getDefaultState());

        playerIn.playSound(SoundEvents.BLOCK_GRASS_PLACE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
