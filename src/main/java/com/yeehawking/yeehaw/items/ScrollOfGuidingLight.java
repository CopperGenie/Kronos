package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ScrollOfGuidingLight extends Item {
    public ScrollOfGuidingLight() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            ServerWorld serverWorld = serverPlayer.server.getWorld(RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
                    new ResourceLocation("minecraft:overworld")));

            double newPosY = 0;
            for (int i = 250; i >= 0; i--) {
                if (serverWorld.getBlockState(new BlockPos(0, i - 1, 0)).isSolid()) {
                    newPosY = i;
                    break;
                }
            }

            if (serverWorld.getBlockState(new BlockPos(0, newPosY, 0)) == Blocks.WATER.getDefaultState()) {
                EntityType.BOAT.spawn(serverWorld, null, null, new BlockPos(0, newPosY + 5, 0), SpawnReason.EVENT, false, true);
            }

            serverPlayer.teleport(serverWorld, 0, newPosY, 0, serverPlayer.rotationYaw, serverPlayer.rotationPitch);
        }
        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
