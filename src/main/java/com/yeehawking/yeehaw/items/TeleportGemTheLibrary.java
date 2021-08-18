package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.NPCWynnbrimEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
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
import java.util.Random;

public class TeleportGemTheLibrary extends Item {
    public TeleportGemTheLibrary() {
        super(new Properties().group(Yeehaw.GEM_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld targetWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;

            if (playerIn.getHealth() >= 20) {
                targetWorld = serverPlayer.server.getWorld(Yeehaw.SKYPIERCERS);

                AxisAlignedBB centerAABB = new AxisAlignedBB(BlockPos.ZERO.add(40000, 100, 0));

                // Loads all chunks within the BB
                for (int x = 39800; x <= 40200; x += 16) {
                    for (int z = -200; z <= 200; z += 16) {
                        targetWorld.getChunk(new BlockPos(x, 100, z));
                    }
                }

                List<NPCWynnbrimEntity> entityList = targetWorld.getEntitiesWithinAABB(NPCWynnbrimEntity.class, centerAABB.grow(200.0D, 200.0D, 200.0D));

                for (NPCWynnbrimEntity entity : entityList) {
                    BlockPos entityPos = entity.getPosition();

                    // ensure destination chunk is loaded before we put the player in it
                    targetWorld.getChunk(new BlockPos(entityPos));
                    serverPlayer.teleport(targetWorld, entityPos.getX(), entityPos.getY(), entityPos.getZ(), serverPlayer.rotationYaw,
                            serverPlayer.rotationPitch);

                    break;
                }
            }

            else {
                if (!playerIn.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent("You are too weak to use the gem!"), playerIn.getUniqueID()); }
            }
        }
        playerIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.BLUE));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }

}
