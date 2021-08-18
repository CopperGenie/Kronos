package com.yeehawking.yeehaw.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class LichEntity extends MonsterEntity {

    public LichEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 150.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.237d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 9.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .createMutableAttribute(Attributes.ARMOR, 2.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.25f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 34.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.goalSelector.addGoal(9, new LichEntity.FireballAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class,
                10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getPosY() - this.getPosY()) <= 4.0D; }));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenieEntity.class,
                10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getPosY() - this.getPosY()) <= 4.0D; }));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AlliedKnightEntity.class,
                10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getPosY() - this.getPosY()) <= 4.0D; }));
    }


    private int tickTimer = 0;
    public void livingTick() {
        tickTimer ++;
        if (tickTimer == 100) {
            this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 0, false, false));
            tickTimer = 0;
        }

        super.livingTick();
    }

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(LichEntity.class,
            DataSerializers.BOOLEAN);
    private int explosionStrength = 2;

    @OnlyIn(Dist.CLIENT)
    public boolean isAttacking() {
        return this.dataManager.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.dataManager.set(ATTACKING, attacking);
    }

    public int getFireballStrength() {
        return this.explosionStrength;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(ATTACKING, false);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("ExplosionPower", this.explosionStrength);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("ExplosionPower", 99)) {
            this.explosionStrength = compound.getInt("ExplosionPower");
        }

    }

    static class FireballAttackGoal extends Goal {
        private final LichEntity parentEntity;
        public int attackTimer;

        public FireballAttackGoal(LichEntity ghast) {
            this.parentEntity = ghast;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.parentEntity.getAttackTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.attackTimer = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.parentEntity.setAttacking(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.parentEntity.getAttackTarget();
            double d0 = 64.0D;
            if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(livingentity)) {
                World world = this.parentEntity.world;
                ++this.attackTimer;
                /*if (this.attackTimer == 110 && !this.parentEntity.isSilent()) {
                    world.playEvent((PlayerEntity)null, 1015, this.parentEntity.getPosition(), 0);
                }*/

                if (this.attackTimer == 100) {
                    double d1 = 4.0D;
                    Vector3d vector3d = this.parentEntity.getLook(1.0F);
                    double d2 = livingentity.getPosX() - (this.parentEntity.getPosX() + vector3d.x * 4.0D);
                    double d3 = livingentity.getPosYHeight(0.5D) - (0.5D + this.parentEntity.getPosYHeight(0.5D));
                    double d4 = livingentity.getPosZ() - (this.parentEntity.getPosZ() + vector3d.z * 4.0D);
                    if (!this.parentEntity.isSilent()) {
                        world.playEvent((PlayerEntity)null, 1016, this.parentEntity.getPosition(), 0);
                    }

                    FireballEntity fireballentity = new FireballEntity(world, this.parentEntity, d2, d3, d4);
                    fireballentity.explosionPower = this.parentEntity.getFireballStrength();
                    fireballentity.setPosition(this.parentEntity.getPosX() + vector3d.x * 4.0D, this.parentEntity.getPosYHeight(0.5D) + 0.5D, fireballentity.getPosZ() + vector3d.z * 4.0D);
                    world.addEntity(fireballentity);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }

            this.parentEntity.setAttacking(this.attackTimer > 100);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 140 * (int)f, 0, false, true));
        }
        if (flag && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60 * (int)f, 0, false, true));
        }

        return flag;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 100 + this.world.rand.nextInt(10);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_STRAY_AMBIENT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GHAST_DEATH;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_STRAY_HURT;
    }

    protected float getSoundVolume() {
        return 2.5f;
    }

    public boolean isImmuneToFire() {
        return this.getType().isImmuneToFire();
    }
}
