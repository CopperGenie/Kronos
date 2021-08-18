package com.yeehawking.yeehaw.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class KrakenEntity extends MonsterEntity {

    public KrakenEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 80.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3d)
                .createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 4d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 14.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.5f)
                .createMutableAttribute(Attributes.ARMOR, 1.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.25f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new KrakenEntity.GoToWaterGoal(this, 1.0D)); // Doesn't work
        this.goalSelector.addGoal(1, new FindWaterGoal(this));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 24.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenieEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AlliedKnightEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));

    }

    /**
     * Static predicate for determining if the current light level and environmental conditions allow for a monster to
     * spawn.
     */
    public static boolean isValidLightLevel(IServerWorld worldIn, BlockPos pos, Random randomIn) {
        if (worldIn.getLightFor(LightType.SKY, pos) > randomIn.nextInt(32)) {
            return false;
        } else {
            int i = worldIn.getWorld().isThundering() ? worldIn.getNeighborAwareLightSubtracted(pos, 10) : worldIn.getLight(pos);
            return i <= randomIn.nextInt(8);
        }
    }

    /**
     * Static predicate for determining whether or not a monster can spawn at the provided location, incorporating a
     * check of the current light level at the location.
     */
    public static boolean canMonsterSpawnInLight(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(worldIn, pos, randomIn) && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof LivingEntity) {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100 * (int)f));
        }

        return flag;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 5 + this.world.rand.nextInt(5);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_DOLPHIN_HURT;
    }

    protected float getSoundVolume() {
        return 2.5f;
    }

    static class GoToWaterGoal extends Goal {
        private final CreatureEntity field_204730_a;
        private double field_204731_b;
        private double field_204732_c;
        private double field_204733_d;
        private final double field_204734_e;
        private final World field_204735_f;

        public GoToWaterGoal(CreatureEntity p_i48910_1_, double p_i48910_2_) {
            this.field_204730_a = p_i48910_1_;
            this.field_204734_e = p_i48910_2_;
            this.field_204735_f = p_i48910_1_.world;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!this.field_204735_f.isDaytime()) {
                return false;
            } else if (this.field_204730_a.isInWater()) {
                return false;
            } else {
                Vector3d vector3d = this.func_204729_f();
                if (vector3d == null) {
                    return false;
                } else {
                    this.field_204731_b = vector3d.x;
                    this.field_204732_c = vector3d.y;
                    this.field_204733_d = vector3d.z;
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.field_204730_a.getNavigator().noPath();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.field_204730_a.getNavigator().tryMoveToXYZ(this.field_204731_b, this.field_204732_c, this.field_204733_d, this.field_204734_e);
        }

        @Nullable
        private Vector3d func_204729_f() {
            Random random = this.field_204730_a.getRNG();
            BlockPos blockpos = this.field_204730_a.getPosition();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
                if (this.field_204735_f.getBlockState(blockpos1).matchesBlock(Blocks.WATER)) {
                    return Vector3d.copyCenteredHorizontally(blockpos1);
                }
            }

            return null;
        }
    }


}
