package com.yeehawking.yeehaw.tile_entities;

import com.yeehawking.yeehaw.init.ModTileEntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Random;

public class AnticreeperNodeTileEntity extends TileEntity implements ITickableTileEntity {
    public AnticreeperNodeTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    private int ticker = 0;

    public AnticreeperNodeTileEntity () {
        this(ModTileEntityType.ANTICREEPER_NODE_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        Random rand = new Random();
        ticker++;

        if (ticker == 40) {
            List<CreeperEntity> mobList = this.getWorld().getEntitiesWithinAABB(CreeperEntity.class, new AxisAlignedBB(this.getPos()).grow(50,50,50));
            for (MobEntity entity : mobList) {
                entity.teleportKeepLoaded(0,0,0);
                for (int i = 1; i <= 30; i++) {
                    entity.world.addParticle(ParticleTypes.PORTAL, entity.getPosXRandom(1.0D), entity.getPosYRandom(), entity.getPosZRandom(1.0D), 1 * (rand.nextDouble() - rand.nextDouble()), 1 * rand.nextDouble(), 1 * (rand.nextDouble() - rand.nextDouble()));
                }
                if (this.getWorld().getClosestPlayer(entity, 20) != null) {
                    this.getWorld().getClosestPlayer(entity, 20).playSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.AMBIENT, 0.8f, 1.0f);
                }
            }

            ticker = 0;
        }
    }
}
