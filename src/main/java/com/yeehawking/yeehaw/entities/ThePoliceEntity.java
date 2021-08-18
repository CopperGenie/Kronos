package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class ThePoliceEntity extends MonsterEntity {

    public ThePoliceEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.27d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.3f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.19f)
                .createMutableAttribute(Attributes.ARMOR, 2);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        //this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 22.0f));
        //this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.18D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, x0026gEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20;
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() { return SoundInit.THE_POLICE_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { if (this.rand.nextInt(4) == 0) {return SoundInit.THE_POLICE_HURT.get();} else {return null;} }

    protected float getSoundVolume() {
        return 0.6f;
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItemMainhand();
        String taskMessage;
        if (handIn.equals(Hand.MAIN_HAND)) {
            if (playerIn.getHeldItemMainhand().getItem() == Items.BUCKET) {
                itemstack.shrink(1);
                this.entityDropItem(ModItems.BIODIESEL.get());
                this.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20*8, 7, false, false));
                taskMessage = TextFormatting.RED + "[The Police] " + TextFormatting.WHITE + "Someone siphoned our fuel! We need backup!";
            }
            else if (playerIn.getHeldItemMainhand().getItem() == ModItems.QUAALUDE.get()) {
                int itemCount = itemstack.getCount();
                itemstack.shrink(Math.min(itemCount, 5));
                taskMessage = TextFormatting.RED + "[The Police] " + TextFormatting.WHITE + "Calling all available units! We found the perp!";
                if (playerIn.getEntityWorld() instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                    ModEntityType.THE_POLICE.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0),
                            SpawnReason.EVENT, false, true);
                    ModEntityType.THE_POLICE.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0),
                            SpawnReason.EVENT, false, true);
                }
            } else {
                taskMessage = TextFormatting.RED + "[The Police] " + TextFormatting.WHITE + "Get down on the ground! Get down on the ground!";
            }
            if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
        }
        return ActionResultType.PASS;
    }

    private int i = 0;

    @Override
    public void livingTick() {
        this.i++;
        if (this.i == 40) {
            this.getEntityWorld().playSound(this.getEntityWorld().getClosestPlayer(this, 7), this.getPosition(), SoundInit.POLICE_BUZZ.get(), SoundCategory.HOSTILE, 0.5F, 1F);
            this.i = 0;
        }
        super.livingTick();
    }
}