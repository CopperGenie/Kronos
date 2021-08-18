package com.yeehawking.yeehaw.tile_entities;

import com.yeehawking.yeehaw.init.ModTileEntityType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Random;

public class GlowingAirBlockTileEntity extends TileEntity implements ITickableTileEntity {
    public GlowingAirBlockTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    private int ticker = 0;

    public GlowingAirBlockTileEntity() {
        this(ModTileEntityType.GLOWING_AIR_BLOCK_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        ticker++;

        if (ticker == 20) {
            if (this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2, false) == null) {
                this.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
            } else {
                if (this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2, false).getHeldItemMainhand().getItem() != Items.TORCH &&
                        this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2, false).getHeldItemOffhand().getItem() != Items.TORCH) {
                    this.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
            ticker = 0;
        }
    }
}
