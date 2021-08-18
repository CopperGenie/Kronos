package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfBigBomb extends Item {
    public ScrollOfBigBomb() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        LivingEntity player = playerIn;

        World world = player.getEntityWorld();

        world.setBlockState(player.getPosition().add(2, 0, 0), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(player.getPosition().add(1, -1, 0), Blocks.GRASS_BLOCK.getDefaultState());
        world.setBlockState(player.getPosition().add(1, 0, 0), Blocks.REDSTONE_TORCH.getDefaultState());

        for (int i = 2; i <= 6; i++) {
            // 3x3
            world.setBlockState(player.getPosition().add(1, i, 1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(1, i, 0), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(1, i, -1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(0, i, 1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(0, i, 0), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(0, i, -1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, i, 1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, i, 0), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, i, -1), Blocks.TNT.getDefaultState());
            // 5x5 ring
            world.setBlockState(player.getPosition().add(2, i, -2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(2, i, -1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(2, i, 0), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(2, i, 1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(2, i, 2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(1, i, -2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(1, i, 2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(0, i, -2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(0, i, 2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, i, -2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, i, 2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-2, i, -2), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-2, i, -1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-2, i, 0), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-2, i, 1), Blocks.TNT.getDefaultState());
            world.setBlockState(player.getPosition().add(-2, i, 2), Blocks.TNT.getDefaultState());
        }
        playerIn.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 0.8f, 1.0f);

        ItemStack itemstack = player.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
