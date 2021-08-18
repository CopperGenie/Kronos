package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModBlocks;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Random;

public class HerobrineEntity extends MonsterEntity {

    public HerobrineEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1000.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.50d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 200.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 12.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.28f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 34.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AlliedKnightEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GenieEntity.class, true));
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && entityIn instanceof LivingEntity && rand.nextInt(4) == 1) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.LEVITATION, 2, 20, false, true));
        }
        if (flag && entityIn instanceof LivingEntity && rand.nextInt(4) == 1) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.LEVITATION, 20 * 5, 140, false, true));
        }
        if (flag && entityIn instanceof LivingEntity && rand.nextInt(10) == 1) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SPEED, 20 * 2, 100, false, true));
        }
        if (flag && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 80, 0, false, true));
        }

        return flag;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 1000 + this.world.rand.nextInt(100);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ENDERMAN_HURT;
    }

    protected float getSoundVolume() {
        return 2.5f;
    }

    private int tickTimer = 0;
    public void livingTick() {
        tickTimer ++;
        if (tickTimer == 80) {
            this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 0, false, false));
            tickTimer = 0;
        }

        if (this.world.isRemote) {
            Random rand = new Random();
            if (rand.nextInt(5) == 0) {
                this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }

        super.livingTick();
    }

    public boolean isImmuneToFire() {
        return true;
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
}

