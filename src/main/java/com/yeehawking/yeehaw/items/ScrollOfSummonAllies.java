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

public class ScrollOfSummonAllies extends Item {
    public ScrollOfSummonAllies() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,2), SpawnReason.EVENT, false, true);
            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,-2), SpawnReason.EVENT, false, true);
            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(2,2,2), SpawnReason.EVENT, false, true);
            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(2,2,-2), SpawnReason.EVENT, false, true);
            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(-2,2,2), SpawnReason.EVENT, false, true);
            ModEntityType.ALLIED_KNIGHT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(-2,2,-2), SpawnReason.EVENT, false, true);

        }
        playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
