package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfFire extends Item {
    public ScrollOfFire() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        LivingEntity player = playerIn;
        if (player.getHeldItemMainhand().getItem() == ModItems.SCROLL_OF_FIRE.get()) {
            World world = player.getEntityWorld();

            for (int i = 10; i>=-10; i--) {
                for (int j = 10; j>=-10; j--) {
                    int a = -2;

                    for (int k = 1; k <= 6; k++) {
                        if (world.getBlockState(player.getPosition().add(i, a, j)) != Blocks.FIRE.getDefaultState()) {
                            if (world.getBlockState(player.getPosition().add(i, a, j)) == Blocks.AIR.getDefaultState()) {
                                world.setBlockState(player.getPosition().add(i, a, j), Blocks.FIRE.getDefaultState());
                            }
                            else {
                                a++;
                            }
                        }
                    }

                    world.setBlockState(player.getPosition().add(i, a, j), Blocks.FIRE.getDefaultState());
                }
            }

            playerIn.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 0.8f, 1f);

            ItemStack itemstack = player.getHeldItemMainhand();
            itemstack.shrink(1);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }


}
