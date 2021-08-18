package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ScrollOfEscape extends Item {
    public ScrollOfEscape() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        Random rand = new Random();
        World world = playerIn.getEntityWorld();

        double newPosX = playerIn.getPosX() - 20 + (40 * rand.nextInt(2));
        double newPosZ = playerIn.getPosZ() - 20 + (40 * rand.nextInt(2));

        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            serverPlayer.teleport(serverWorld, newPosX, serverPlayer.getPosY(), newPosZ, serverPlayer.rotationYaw, serverPlayer.rotationPitch);
        }

        world.setBlockState(playerIn.getPosition().add(0, 1, 0), Blocks.AIR.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 0, 0), Blocks.AIR.getDefaultState());
        if (!world.getBlockState(playerIn.getPosition().add(0, -1, 0)).isSolid()) {
            world.setBlockState(playerIn.getPosition().add(0, -1, 0), Blocks.STONE.getDefaultState());
        }
        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
