package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

public class ScrollOfSummonWolves extends Item {
    public ScrollOfSummonWolves() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            EntityType.WOLF.spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,2), SpawnReason.EVENT, false, true);
            EntityType.WOLF.spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,-2), SpawnReason.EVENT, false, true);
            EntityType.WOLF.spawn(serverWorld, null, null, playerIn.getPosition().add(2,2,0), SpawnReason.EVENT, false, true);
            EntityType.WOLF.spawn(serverWorld, null, null, playerIn.getPosition().add(-2,2,0), SpawnReason.EVENT, false, true);

        }
        playerIn.playSound(SoundEvents.ENTITY_WOLF_HOWL, 1.0f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
