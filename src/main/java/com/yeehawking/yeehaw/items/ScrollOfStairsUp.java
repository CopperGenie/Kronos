package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfStairsUp extends Item {
    public ScrollOfStairsUp() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getHeldItemMainhand().getItem() == ModItems.SCROLL_OF_STAIRS_UP.get()) {
            World world = playerIn.getEntityWorld();

            if (playerIn.getHorizontalFacing() == Direction.NORTH) {
                for (int i = 0; i <= 150; i++) {
                    world.setBlockState(playerIn.getPosition().add(0, i, -i), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, -i-1), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, -i-2), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, -i-3), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, -i-4), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.NORTH));

                    if ((world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.GRASS_BLOCK.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.SAND.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.SPRUCE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.DARK_OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.ACACIA_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.BIRCH_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.JUNGLE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, -i-4)) == Blocks.AIR.getDefaultState())) {
                        break;
                    }
                }
            }

            if (playerIn.getHorizontalFacing() == Direction.EAST) {
                for (int i = 0; i <= 150; i++) {
                    world.setBlockState(playerIn.getPosition().add(i, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i+1, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i+2, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i+3, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(i+4, i, 0), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.EAST));

                    if ((world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.GRASS_BLOCK.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.SAND.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.SPRUCE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.DARK_OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.ACACIA_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.BIRCH_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.JUNGLE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(i+4, i-1, 0)) == Blocks.AIR.getDefaultState())) {
                        break;
                    }
                }
            }

            if (playerIn.getHorizontalFacing() == Direction.SOUTH) {
                for (int i = 0; i <= 150; i++) {
                    world.setBlockState(playerIn.getPosition().add(0, i, i), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, i+1), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, i+2), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, i+3), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(0, i, i+4), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.SOUTH));

                    if ((world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.GRASS_BLOCK.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.SAND.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.SPRUCE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.DARK_OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.ACACIA_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.BIRCH_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.JUNGLE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(0, i-1, i+4)) == Blocks.AIR.getDefaultState())) {
                        break;
                    }
                }
            }

            if (playerIn.getHorizontalFacing() == Direction.WEST) {
                for (int i = 0; i <= 110; i++) {
                    world.setBlockState(playerIn.getPosition().add(i, -i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i-1, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i-2, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i-3, i, 0), Blocks.AIR.getDefaultState());
                    world.setBlockState(playerIn.getPosition().add(-i-4, i, 0), Blocks.STONE_BRICK_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, Direction.WEST));

                    if ((world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.GRASS_BLOCK.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.SAND.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.SPRUCE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.DARK_OAK_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.ACACIA_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.BIRCH_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.JUNGLE_LOG.getDefaultState()) ||
                            (world.getBlockState(playerIn.getPosition().add(-i-4, i-1, 0)) == Blocks.AIR.getDefaultState())) {
                        break;
                    }
                }
            }
        }
        playerIn.playSound(SoundEvents.BLOCK_STONE_PLACE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }


}
