package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class x0026gEntity extends MonsterEntity {

    public x0026gEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.27d)
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
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        //this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 22.0f));
        //this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(
                this, ThePoliceEntity.class, 6f, 1.0d, 1.22d));

    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20;
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() { return SoundInit.X0026G_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { if (this.rand.nextInt(4) == 0) {return SoundInit.X0026G_HURT.get();} else {return null;} }

    protected float getSoundVolume() {
        return 0.65f;
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItemMainhand();
        String taskMessage;
        if (handIn.equals(Hand.MAIN_HAND)) {
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.QUAALUDE.get()) {
                if (itemstack.getCount() >= 20) {
                    itemstack.shrink(20);
                    playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                    taskMessage = TextFormatting.YELLOW + "[Car] " + TextFormatting.WHITE + "Far out man. Here, take this, and watch out for the cops!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.TOTALLY_TUBULAR_GLOVE.get());
                } else {
                    taskMessage = TextFormatting.YELLOW + "[Car] " + TextFormatting.WHITE + "We need more than that, dude!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                }
            } else {
                taskMessage = TextFormatting.YELLOW + "[Car] " + TextFormatting.WHITE + "Hey you! Got any Quaaludes?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
            }
        }
            return ActionResultType.PASS;
    }

    int i = 0;

    @Override
    public void livingTick() {
        this.i++;
        if (this.rand.nextInt(6) == 0) {
            this.i++;
        }
        if (this.i == 20*7) {
            this.getEntityWorld().playSound(this.getEntityWorld().getClosestPlayer(this, 7), this.getPosition(), SoundInit.X0026G_AMBIENT.get(), SoundCategory.HOSTILE, 0.65F, 1F);
            this.i = 0;
        }
        super.livingTick();
    }
}