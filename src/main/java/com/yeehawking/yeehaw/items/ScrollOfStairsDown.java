package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfStairsDown extends Item {
    public ScrollOfStairsDown() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getHeldItemMainhand().getItem() == ModItems.SCROLL_OF_STAIRS_DOWN.get()) {
            World world = playerIn.getEntityWorld();

             if (playerIn.getHorizontalFacing() == Direction.NORTH) {
                 for (int i = 0; i >= -100; i--) {
                     world.setBlockState(playerIn.getPosition().add(0, i-1, i - 1), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                             .with(StairsBlock.FACING, Direction.SOUTH));
                     world.setBlockState(playerIn.getPosition().add(0, i-1, i - 2), Blocks.AIR.getDefaultState());
                     world.setBlockState(playerIn.getPosition().add(0, i-1, i - 3), Blocks.AIR.getDefaultState());
                     world.setBlockState(playerIn.getPosition().add(0, i-1, i - 4), Blocks.AIR.getDefaultState());
                     world.setBlockState(playerIn.getPosition().add(0, i-1, i - 5), Blocks.AIR.getDefaultState());
                 }
             }

            if (playerIn.getHorizontalFacing() == Direction.EAST) {
                for (int i = 0; i >= -100; i--) {
                    world.setBlockState(playerIn.getPosition().add(-i + 1, i-1, 0), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.WEST));
                    world.setBlockState(playerIn.getPosition().add(-i + 2, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i + 3, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i + 4, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i + 5, i-1, 0), Blocks.AIR.getDefaultState());
                }
            }

            if (playerIn.getHorizontalFacing() == Direction.SOUTH) {
                for (int i = 0; i >= -100; i--) {
                    world.setBlockState(playerIn.getPosition().add(0, i-1, -i + 1), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.NORTH));
                    world.setBlockState(playerIn.getPosition().add(0, i-1, -i + 2), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i-1, -i + 3), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i-1, -i + 4), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i-1, -i + 5), Blocks.AIR.getDefaultState());
                }
            }

            if (playerIn.getHorizontalFacing() == Direction.WEST) {
                for (int i = 0; i >= -100; i--) {
                    world.setBlockState(playerIn.getPosition().add(i - 1, i-1, 0), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.EAST));
                    world.setBlockState(playerIn.getPosition().add(i - 2, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i - 3, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i - 4, i-1, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i - 5, i-1, 0), Blocks.AIR.getDefaultState());
                }
            }
        }
        playerIn.playSound(SoundEvents.BLOCK_STONE_PLACE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
