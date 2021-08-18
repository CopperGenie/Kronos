package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ScrollOfOrigin extends Item {
    public ScrollOfOrigin() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        double newPosY = 0;
        for (int i = 250; i >= 0; i--) {
            if (worldIn.getBlockState(new BlockPos(0, i - 1, 0)).isSolid()) {
                if (worldIn.getBlockState(new BlockPos(0, i - 1, 0)) == Blocks.BEDROCK.getDefaultState()) {
                    newPosY = 70;
                    worldIn.setBlockState(new BlockPos(0, newPosY - 1, 0), Blocks.STONE.getDefaultState());
                    worldIn.setBlockState(new BlockPos(0, newPosY, 0), Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(new BlockPos(0, newPosY + 1, 0), Blocks.AIR.getDefaultState());
                } else {
                    newPosY = i;
                }
                break;
            }

            if (i == 0) {
                newPosY = 70;
                worldIn.setBlockState(new BlockPos(0, newPosY - 1, 0), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(1, newPosY - 1, 0), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(-1, newPosY - 1, 0), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(0, newPosY - 1, 1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(1, newPosY - 1, 1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(-1, newPosY - 1, 1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(0, newPosY - 1, -1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(1, newPosY - 1, -1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(-1, newPosY - 1, -1), Blocks.STONE.getDefaultState());
            }
        }

        if (worldIn.getBlockState(new BlockPos(0, newPosY, 0)) == Blocks.WATER.getDefaultState()) {
            if (playerIn.getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                EntityType.BOAT.spawn(serverWorld, null, null, new BlockPos(0, newPosY, 0), SpawnReason.EVENT, false, true);
            }
        }

        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            serverPlayer.teleport(serverWorld, 0, newPosY, 0, serverPlayer.rotationYaw, serverPlayer.rotationPitch);
        }
        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
