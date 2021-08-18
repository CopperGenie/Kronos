package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class KnightEntity extends AnimalEntity {

    public KnightEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 30d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 15.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.3f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.22f)
                .createMutableAttribute(Attributes.ARMOR, 2);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 22.0f));
        //this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, GenieEntity.class, true));

    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20;
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() { return SoundInit.HUMAN_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.HUMAN_HURT.get(); }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            Random rand = new Random();
            if (rand.nextInt(100) == 0) {
                String taskMessage = TextFormatting.RED + "[Knight] " + TextFormatting.WHITE + "Eat my shorts!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                this.entityDropItem(Items.LEATHER_LEGGINGS);
            } else {
                String taskMessage = TextFormatting.RED + "[Knight] " + TextFormatting.WHITE + "RAAAAAAHH!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
            }
        }
            return ActionResultType.PASS;
    }
}