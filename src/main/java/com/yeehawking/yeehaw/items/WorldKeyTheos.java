package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class WorldKeyTheos extends Item {
    public WorldKeyTheos() {
        super(new Properties().group(Yeehaw.KEY_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld targetWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;

            if (playerIn.getHealth() >= 19.5) {
                if (playerIn.world.getDimensionKey().equals(Yeehaw.THEOS)) {
                    targetWorld = serverPlayer.server.getWorld(RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
                            new ResourceLocation("minecraft:overworld")));
                }

                else {
                    targetWorld = serverPlayer.server.getWorld(Yeehaw.THEOS);
                }

                double newPosY = 0;
                for (int i = 250; i >= 0; i--) {
                    if ( (targetWorld.getBlockState(new BlockPos(playerIn.getPosX(), i - 1, playerIn.getPosZ())) != Blocks.AIR.getDefaultState()) &&
                            (targetWorld.getBlockState(new BlockPos(playerIn.getPosX(), i - 1, playerIn.getPosZ())) != Blocks.CAVE_AIR.getDefaultState()) &&
                            (targetWorld.getBlockState(new BlockPos(playerIn.getPosX(), i - 1, playerIn.getPosZ())) != Blocks.VOID_AIR.getDefaultState())) {

                        if (targetWorld.getBlockState(new BlockPos(playerIn.getPosX(), i - 1, playerIn.getPosZ())) == Blocks.BEDROCK.getDefaultState()) {
                            newPosY = 70;
                            targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY - 1, playerIn.getPosZ()), Blocks.STONE.getDefaultState());
                            targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY, playerIn.getPosZ()), Blocks.TORCH.getDefaultState());
                            targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY + 1, playerIn.getPosZ()), Blocks.AIR.getDefaultState());
                        } else {
                            newPosY = i;
                        }
                        break;
                    }

                    if (i == 0) {
                        newPosY = 70;
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY - 1, playerIn.getPosZ()), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()+1, newPosY - 1, playerIn.getPosZ()), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()-1, newPosY - 1, playerIn.getPosZ()), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY - 1, playerIn.getPosZ()+1), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()+1, newPosY - 1, playerIn.getPosZ()+1), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()-1, newPosY - 1, playerIn.getPosZ()+1), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX(), newPosY - 1, playerIn.getPosZ()-1), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()+1, newPosY - 1, playerIn.getPosZ()-1), Blocks.STONE.getDefaultState());
                        targetWorld.setBlockState(new BlockPos(playerIn.getPosX()-1, newPosY - 1, playerIn.getPosZ()-1), Blocks.STONE.getDefaultState());
                    }
                }

                if (targetWorld.getBlockState(new BlockPos(playerIn.getPosX(), newPosY - 1, playerIn.getPosZ())) == Blocks.WATER.getDefaultState()) {
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        EntityType.BOAT.spawn(targetWorld, null, null, new BlockPos(playerIn.getPosX(), newPosY + 2, playerIn.getPosZ()), SpawnReason.EVENT, false, true);
                    }
                }

                Vector3d targetVec = new Vector3d(playerIn.getPosX(), newPosY, playerIn.getPosZ());

                // ensure destination chunk is loaded before we put the player in it
                targetWorld.getChunk(new BlockPos(targetVec));
                serverPlayer.teleport(targetWorld, targetVec.getX(), targetVec.getY(), targetVec.getZ(), serverPlayer.rotationYaw,
                        serverPlayer.rotationPitch);

            }

            else {
                if (!playerIn.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent("You are too weak to use the key!"), playerIn.getUniqueID()); }
            }
        }

        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.DARK_AQUA));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }

}
