package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.List;
import java.util.Random;

public class FeloniousBolusEntity extends PhantomEntity {

    public FeloniousBolusEntity(EntityType<? extends PhantomEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 54.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 33.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.25f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 40.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AlliedKnightEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GenieEntity.class, true));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20 + this.world.rand.nextInt(10);
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.FB_DEATH.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.FB_HURT.get();
    }

    protected float getSoundVolume() {
        return 0.8f;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return null;
    }

    public boolean isImmuneToFire() {
        return true;
    }

    private int i = 0;

    @Override
    public void livingTick() {
        this.i++;
        if (this.rand.nextInt(10) == 0) {
            this.i++;
        }
        if (this.i == 20*15) {
            this.getEntityWorld().playSound(this.getEntityWorld().getClosestPlayer(this, 7), this.getPosition(), SoundInit.FB_AMBIENT.get(), SoundCategory.HOSTILE, 0.6F, 1F);
            this.i = 0;
        }
        super.livingTick();
    }
}
