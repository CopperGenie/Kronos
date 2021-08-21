package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPCKrorkEntity extends AnimalEntity {

    public NPCKrorkEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100000.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0d);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(6, new LookAtGoal(this, NPCGasmanEntity.class, 10.0f));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 5;
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() { return SoundInit.GASMAN_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.KRORK_HURT.get(); }

    protected float getSoundVolume() {
        return 0.7f;
    }

    private int tickTimer = 0;
    public void livingTick() {
        tickTimer ++;
        if (tickTimer == 80) {
            this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 0, false, false));
            tickTimer = 0;
        }

        super.livingTick();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCKrorkEntity.class, DataSerializers.VARINT);

    protected void registerData() {
        super.registerData();
        this.dataManager.register(QUEST_NUMBER, 0);
    }

    // Saves data to world
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("QuestNumber", this.getQuestNumber());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setQuestNumber(compound.getInt("QuestNumber"));
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setQuestNumber(0);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }



    public void setQuestNumber(int i) { this.dataManager.set(QUEST_NUMBER, i); }
    public int getQuestNumber() { return this.dataManager.get(QUEST_NUMBER); }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            Random rand = new Random();
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(14);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber(); if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    playerIn.playSound(SoundInit.KRORK_DIALOGUE_1.get(), 0.8f, 1f);
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "You don't have the new state-of-the-art biodiesel they've been rolling out?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    playerIn.playSound(SoundInit.KRORK_DIALOGUE_2.get(), 0.8f, 1f);
                    taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "Too new for our pumps. You gotta go to the city for that."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    playerIn.playSound(SoundInit.KRORK_DIALOGUE_3.get(), 0.8f, 1f);
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "That's a problem. The LED lights up when my fuel is low."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Neat tech, isn't it? Too bad it's no good if I can't get the hydrocarbons I'm looking for."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    playerIn.playSound(SoundInit.KRORK_DIALOGUE_4.get(), 0.8f, 1f);
                    taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "Not so state-of-the-art then, is it?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }
                else if (questNumber == 4) {
                    playerIn.playSound(SoundInit.KRORK_DIALOGUE_5.get(), 0.8f, 1f);
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Can I get one of those southern cuts?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(5);
                }
                else if (questNumber == 5) {
                    List<NPCGasmanEntity> gasmanEntityList = world.getEntitiesWithinAABB(NPCGasmanEntity.class, this.getBoundingBox().grow(20.0D, 10.0D, 20.0D));

                    for (NPCGasmanEntity entity : gasmanEntityList) {
                        entity.setHealth(0);
                    }
                    playerIn.playSound(SoundInit.GASMAN_DEATH.get(), 0.8f, 1f);
                    this.setQuestNumber(6);
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Hey! You. I need more biodiesel for my vehicle. Get me 5 cans of it and I'll pay you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(7);
                }
                else if (questNumber == 7) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.BIODIESEL.get()) && (itemstack.getCount() >= 5) ) {
                        itemstack.shrink(5);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.SPACE_JUNK.get());
                        taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Thanks. Here, take this space junk. It might be useful to a Terran like you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(8);
                    } else {
                        taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Get me 5 cans of biodiesel. I don't have all day."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 8) {
                    taskMessage = TextFormatting.GOLD + "[Krork] " + TextFormatting.WHITE + "Get out of here, or I'll turn you into the ultimate fuel."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                }

            }
        }

        return ActionResultType.PASS;
    }

    public boolean isImmuneToFire() {
        return true;
    }

    // No fall damage
    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        if (onGroundIn) { this.fallDistance = 0.0F; } else if (y < 0.0D) { this.fallDistance = 0.0F; }
    }
}

// SAINTE