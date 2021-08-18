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

public class ScrollOfFlameWall extends Item {
    public ScrollOfFlameWall() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        LivingEntity player = playerIn;
        if (player.getHeldItemMainhand().getItem() == ModItems.SCROLL_OF_FLAME_WALL.get()) {
            World world = player.getEntityWorld();

            if (player.getHorizontalFacing() == Direction.NORTH) { // -Z
                for (int j = -10; j <= 10; j++) {
                    world.setBlockState(player.getPosition().add(j, -1, -2), Blocks.MAGMA_BLOCK.getDefaultState());
                    world.setBlockState(player.getPosition().add(j, 0, -2), Blocks.FIRE.getDefaultState());
                }
            }

            if (player.getHorizontalFacing() == Direction.EAST) { // +X
                for (int j = -10; j <= 10; j++) {
                    world.setBlockState(player.getPosition().add(2, 0, j), Blocks.MAGMA_BLOCK.getDefaultState());
                    world.setBlockState(player.getPosition().add(2, 1, j), Blocks.FIRE.getDefaultState());
                }
            }

            if (player.getHorizontalFacing() == Direction.SOUTH) { // +Z
                for (int j = -10; j <= 10; j++) {
                    world.setBlockState(player.getPosition().add(j, 0, 2), Blocks.MAGMA_BLOCK.getDefaultState());
                    world.setBlockState(player.getPosition().add(j, 1, 2), Blocks.FIRE.getDefaultState());
                }
            }

            if (player.getHorizontalFacing() == Direction.WEST) { // -X
                for (int j = -10; j <= 10; j++) {
                    world.setBlockState(player.getPosition().add(-2, 0, j), Blocks.MAGMA_BLOCK.getDefaultState());
                    world.setBlockState(player.getPosition().add(-2, 1, j), Blocks.FIRE.getDefaultState());
                }
            }
            playerIn.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 0.8f, 1f);

            ItemStack itemstack = player.getHeldItemMainhand();
            itemstack.shrink(1);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }


}
