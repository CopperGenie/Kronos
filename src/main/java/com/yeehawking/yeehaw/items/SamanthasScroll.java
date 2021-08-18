package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SamanthasScroll extends Item {
    public SamanthasScroll() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        LivingEntity player = playerIn;
        if (player.getHeldItemMainhand().getItem() == ModItems.SAMANTHAS_SCROLL.get()) {
            World world = player.getEntityWorld();
            for (int i = -3; i <= 3; i++) {
                for (int j = -3; j <= 3; j++) {
                    world.setBlockState(player.getPosition().add(i, -1, j), Blocks.GRASS_BLOCK.getDefaultState());
                }
            }
            for (int k = 1; k <= 4; k++) {
                double x = Math.random() * 6 - 3;
                double z = Math.random() * 6 - 3;

                if (world.getBlockState(player.getPosition().add(x, 0, z)) == Blocks.AIR.getDefaultState()) {
                    world.setBlockState(player.getPosition().add(x, 0, z), Blocks.BLUE_ORCHID.getDefaultState());
                }
            }

            ItemStack itemstack = player.getHeldItemMainhand();
            itemstack.shrink(1);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
