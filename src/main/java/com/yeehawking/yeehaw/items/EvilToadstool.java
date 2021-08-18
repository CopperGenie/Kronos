package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import com.yeehawking.yeehaw.entities.ToadFatherEntity;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class EvilToadstool extends Item {
    public EvilToadstool() {
        super(new Properties().group(Yeehaw.SUMMON_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        double posX = 0; double posY = 0; double posZ = 0;

        if (playerIn.getHorizontalFacing() == Direction.NORTH) { //  -Z
            posX = playerIn.getPosX();
            posZ = playerIn.getPosZ() - 6;
        }
        else if (playerIn.getHorizontalFacing() == Direction.EAST) { //  +X
            posX = playerIn.getPosX() + 6;
            posZ = playerIn.getPosZ();
        }
        else if (playerIn.getHorizontalFacing() == Direction.SOUTH) { //  +Z
            posX = playerIn.getPosX();
            posZ = playerIn.getPosZ() + 6;
        }
        else if (playerIn.getHorizontalFacing() == Direction.WEST) { //  -X
            posX = playerIn.getPosX() - 6;
            posZ = playerIn.getPosZ();
        }

        for (int i = 250; i >= 0; i--) {
            if ( (worldIn.getBlockState(new BlockPos(posX, i - 1, posZ)) != Blocks.AIR.getDefaultState()) &&
                    (worldIn.getBlockState(new BlockPos(posX, i - 1, posZ)) != Blocks.CAVE_AIR.getDefaultState()) &&
                    (worldIn.getBlockState(new BlockPos(posX, i - 1, posZ)) != Blocks.VOID_AIR.getDefaultState())) {

                posY = i;
                break;
            }

            if (i == 0) {
                posY = 70;
                worldIn.setBlockState(new BlockPos(posX, posY - 1, posZ), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX+1, posY - 1, posZ), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX-1, posY - 1, posZ), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX, posY - 1, posZ+1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX+1, posY - 1, posZ+1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX-1, posY - 1, posZ+1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX, posY - 1, posZ-1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX+1, posY - 1, posZ-1), Blocks.STONE.getDefaultState());
                worldIn.setBlockState(new BlockPos(posX-1, posY - 1, posZ-1), Blocks.STONE.getDefaultState());
            }
        }

        if (worldIn.getBlockState(new BlockPos(posX, posY, posZ)) == Blocks.BEDROCK.getDefaultState()) {
            if (!playerIn.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent("Toad Father can't find you here!"), playerIn.getUniqueID()); }
        } else {
            if (worldIn instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                ModEntityType.TOAD_FATHER.get().spawn(serverWorld, null, null, new BlockPos(posX,posY,posZ), SpawnReason.EVENT, false, true);

                ItemStack itemstack = playerIn.getHeldItemMainhand();
                itemstack.shrink(1);
            }

            playerIn.playSound(SoundInit.TOAD_FATHER_SUMMON.get(), 0.8f, 0.8f);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.func_234801_g_().mergeStyle(TextFormatting.GRAY));
    }

    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent func_234801_g_() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }
}
