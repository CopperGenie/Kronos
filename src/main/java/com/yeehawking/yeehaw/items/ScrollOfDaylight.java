package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.command.CommandSource;
import net.minecraft.command.impl.TimeCommand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IServerWorldInfo;

public class ScrollOfDaylight extends Item {
    public ScrollOfDaylight() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        LivingEntity player = playerIn;

        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            serverWorld.setDayTime(2000);
        }
        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1.0f);

        ItemStack itemstack = player.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }


}
