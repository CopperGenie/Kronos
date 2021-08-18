package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
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

public class WorldKeyTheDungeon extends Item {
    public WorldKeyTheDungeon() {
        super(new Properties().group(Yeehaw.KEY_TAB));
    }

    Vector3d outsidePos;
    ServerWorld outsideWorld;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld targetWorld = (ServerWorld) playerIn.getEntityWorld();
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;

            if (playerIn.getHealth() >= 19.5) {

                if (playerIn.world.getDimensionKey().equals(Yeehaw.THE_DUNGEON)) {
                    // ensure destination chunk is loaded before we put the player in it
                    outsideWorld.getChunk(new BlockPos(outsidePos));
                    serverPlayer.teleport(outsideWorld, outsidePos.getX(), outsidePos.getY(), outsidePos.getZ(), serverPlayer.rotationYaw,
                            serverPlayer.rotationPitch);
                }

                else {
                    outsidePos = new Vector3d(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                    outsideWorld = (ServerWorld) playerIn.getEntityWorld();
                    targetWorld = serverPlayer.server.getWorld(Yeehaw.THE_DUNGEON);
                    Vector3d targetVec = new Vector3d(0, 100, 0);

                    // ensure destination chunk is loaded before we put the player in it
                    targetWorld.getChunk(new BlockPos(targetVec));
                    serverPlayer.teleport(targetWorld, targetVec.getX(), targetVec.getY(), targetVec.getZ(), serverPlayer.rotationYaw,
                            serverPlayer.rotationPitch);
                }
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
        tooltip.add(this.text0().mergeStyle(TextFormatting.DARK_GRAY));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }

}
