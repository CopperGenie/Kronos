package com.yeehawking.yeehaw.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BigShaqEntity extends TNTEntity {
    public BigShaqEntity(EntityType<? extends TNTEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void explode() {
        float f = 4.0F;
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 100.0F, Explosion.Mode.BREAK);
    }

}
