package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfBridging extends Item {
    public ScrollOfBridging() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (playerIn.getHorizontalFacing() == Direction.NORTH) { //  -Z
            for (int i = 1; i <= 50; i++) {
                worldIn.setBlockState(playerIn.getPosition().add(1, -1, -i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, -1, -i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, -1, -i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(1, 0, -i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, 0, -i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, 0, -i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(1, 1, -i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, 1, -i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, 1, -i), Blocks.AIR.getDefaultState());
            }
        }

        else if (playerIn.getHorizontalFacing() == Direction.EAST) { //  +X
            for (int i = 1; i <= 50; i++) {
                worldIn.setBlockState(playerIn.getPosition().add(i, -1, 1), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, -1, 0), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, -1, -1), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 0, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 0, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 0, -1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 1, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(i, 1, -1), Blocks.AIR.getDefaultState());
            }
        }

        else if (playerIn.getHorizontalFacing() == Direction.SOUTH) { //  +Z
            for (int i = 1; i <= 50; i++) {
                worldIn.setBlockState(playerIn.getPosition().add(1, -1, i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, -1, i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, -1, i), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(1, 0, i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, 0, i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, 0, i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(1, 1, i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(0, 1, i), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-1, 1, i), Blocks.AIR.getDefaultState());
            }
        }

        else if (playerIn.getHorizontalFacing() == Direction.WEST) { //  -X
            for (int i = 1; i <= 50; i++) {
                worldIn.setBlockState(playerIn.getPosition().add(-i, -1, 1), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, -1, 0), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, -1, -1), Blocks.STONE_BRICKS.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 0, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 0, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 0, -1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 1, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(playerIn.getPosition().add(-i, 1, -1), Blocks.AIR.getDefaultState());
            }
        }
        playerIn.playSound(SoundEvents.BLOCK_STONE_PLACE, 0.8f, 1.0f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
