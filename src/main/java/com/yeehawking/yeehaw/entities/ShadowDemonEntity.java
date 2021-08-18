package com.yeehawking.yeehaw.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class ShadowDemonEntity extends MonsterEntity {

    public ShadowDemonEntity(EntityType<? extends MonsterEntity> type, World worldIn) { super(type, worldIn); }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 300.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.22f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 34.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, SheepEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenieEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AlliedKnightEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));

    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && entityIn instanceof LivingEntity && rand.nextInt(4) == 1) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100 * (int)f, 0, false, true));
        }
        if (flag && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 80 * (int)f, 0, false, true));
        }

        return flag;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 170 + this.world.rand.nextInt(30);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GHAST_HURT;
    }

    protected float getSoundVolume() {
        return 1.8f;
    }


    // Boss bar

    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }

    }

    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    protected void updateAITasks() {
        super.updateAITasks();

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    // End boss bar

    public boolean isImmuneToFire() {
        return true;
    }}
