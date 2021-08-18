package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ScrollOfExploration extends Item {
    public ScrollOfExploration() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        World world = playerIn.getEntityWorld();
        double newPosX = 0; double newPosY = 0; double newPosZ = 0;

        if (playerIn.getHorizontalFacing() == Direction.NORTH) { //  -Z
            newPosX = playerIn.getPosX(); newPosZ = playerIn.getPosZ() - 1000;
        }
        else if (playerIn.getHorizontalFacing() == Direction.EAST) { //  +X
            newPosX = playerIn.getPosX() + 1000; newPosZ = playerIn.getPosZ();
        }
        else if (playerIn.getHorizontalFacing() == Direction.SOUTH) { //  +Z
            newPosX = playerIn.getPosX(); newPosZ = playerIn.getPosZ() + 1000;
        }
        else if (playerIn.getHorizontalFacing() == Direction.WEST) { //  -X
            newPosX = playerIn.getPosX() - 1000; newPosZ = playerIn.getPosZ();
        }

        for (int i = 250; i >= 0; i--) {
            if (world.getBlockState(new BlockPos(newPosX, i - 1, newPosZ)).isSolid()) {
                if (world.getBlockState(new BlockPos(newPosX, i - 1, newPosZ)) == Blocks.BEDROCK.getDefaultState()) {
                    newPosY = 70;
                    world.setBlockState(new BlockPos(newPosX, newPosY - 1, newPosZ), Blocks.STONE.getDefaultState());
                    world.setBlockState(new BlockPos(newPosX, newPosY, newPosZ), Blocks.AIR.getDefaultState());
                    world.setBlockState(new BlockPos(newPosX, newPosY + 1, newPosZ), Blocks.AIR.getDefaultState());
                } else {
                    newPosY = i;
                }
                break;
            }

            if (i == 0) {
                newPosY = 70;
                world.setBlockState(new BlockPos(newPosX, newPosY - 1, newPosZ), Blocks.STONE.getDefaultState());
            }
        }

        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            serverPlayer.teleport(serverWorld, newPosX, newPosY, newPosZ, serverPlayer.rotationYaw, serverPlayer.rotationPitch);
        }

        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
