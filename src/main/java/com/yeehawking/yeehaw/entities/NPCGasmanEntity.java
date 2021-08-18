package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
import java.util.Random;

public class NPCGasmanEntity extends AnimalEntity {

    public NPCGasmanEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    private int tickTimer = 0;
    public void livingTick() {
        tickTimer ++;
        if (tickTimer == 120) {
            this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 0, false, false));
            tickTimer = 0;
        }

        super.livingTick();
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
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.GASMAN_HURT.get(); }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCAelymoreEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCKrorkEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUARTZ_COUNT = EntityDataManager.createKey(NPCKrorkEntity.class, DataSerializers.VARINT);

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TEXTURE, 0);
        this.dataManager.register(QUEST_NUMBER, 0);
        this.dataManager.register(QUARTZ_COUNT, 0);
    }

    public static class NPCData extends AgeableData {
        public final int typeData;

        public NPCData(int type) {
            super(1.0F);
            this.typeData = type;
        }
    }

    // Saves data to world
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Texture", this.getTexture());
        compound.putInt("QuestNumber", this.getQuestNumber());
        compound.putInt("QuartzCount", this.getQuartzCount());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setTexture(compound.getInt("Texture"));
        this.setQuestNumber(compound.getInt("QuestNumber"));
        this.setQuartzCount(compound.getInt("QuartzCount"));
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = 0;
        if (spawnDataIn instanceof NPCAelymoreEntity.NPCData) {
            i = ((NPCAelymoreEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCAelymoreEntity.NPCData(i); }
        this.setTexture(i);

        this.setQuestNumber(0);
        this.setQuartzCount(0);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void setTexture(int i) { this.dataManager.set(TEXTURE, i); }
    public int getTexture() { return this.dataManager.get(TEXTURE); }
    public void setQuestNumber(int i) { this.dataManager.set(QUEST_NUMBER, i); }
    public int getQuestNumber() { return this.dataManager.get(QUEST_NUMBER); }
    public void setQuartzCount(int i) { this.dataManager.set(QUARTZ_COUNT, i); }
    public int getQuartzCount() { return this.dataManager.get(QUARTZ_COUNT); }

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
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "What can I do for you?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "I need a raise. Got any spare quartz?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.QUARTZ) && (this.getQuartzCount() < 500) ) {
                        this.setQuartzCount(this.getQuartzCount() + itemstack.getCount());
                        itemstack.shrink(itemstack.getCount());
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        if (this.getQuartzCount() < 500) {
                            taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "Thanks a ton. Got any more?";
                        } else {
                            taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "I'm rich! 500 quartz... I can't believe it!";
                        }
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if (this.getQuartzCount() >= 500) {
                        taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "I don't think I'll be needing this anymore."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(3);
                        this.setTexture(1);
                        this.entityDropItem(ModItems.TIME_CARD.get());
                    } else {
                        taskMessage = TextFormatting.GREEN + "[Gasman] " + TextFormatting.WHITE + "Got any spare quartz, friend?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 3) {
                    taskMessage = TextFormatting.GREEN + "[Richie Rich] " + TextFormatting.WHITE + "I'm the richest man alive!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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

// GRUNCH