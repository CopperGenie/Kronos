package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
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

public class NPCScholarEntity extends AnimalEntity {

    public NPCScholarEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0d)
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

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 5;
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCScholarEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCScholarEntity.class, DataSerializers.VARINT);

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TEXTURE, 0);
        this.dataManager.register(QUEST_NUMBER, 0);
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
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setTexture(compound.getInt("Texture"));
        this.setQuestNumber(compound.getInt("QuestNumber"));
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = 0;
        if (spawnDataIn instanceof NPCScholarEntity.NPCData) {
            i = ((NPCScholarEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCScholarEntity.NPCData(i); }
        this.setTexture(i);

        this.setQuestNumber(0);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }



    public void setTexture(int i) { this.dataManager.set(TEXTURE, i); }
    public int getTexture() { return this.dataManager.get(TEXTURE); }

    public void setQuestNumber(int i) { this.dataManager.set(QUEST_NUMBER, i); }
    public int getQuestNumber() { return this.dataManager.get(QUEST_NUMBER); }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(2);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    int speechLine = this.rand.nextInt(4);
                    switch(speechLine) {
                        case 0:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Hello, friend!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 1:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Fine day, isn't it?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 2:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Carpe diem, my good lad."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 3:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I am dreadfully tired! Leave me in peace, please."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                    }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    int speechLine = this.rand.nextInt(15);
                    switch(speechLine) {
                        case 0:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I've read every book in the library at least twice. Am I still not worthy to enter the College of the Magi?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 1:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I've heard Headmaster Wynnbrim has a collection of forbidden books. I'd like to get my hands on it!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 2:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I don't believe in Kronos. Where is the proof?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 3:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I'm late to class."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 4:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Got any spare parchment?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 5:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I need a new quill..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 6:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Misbehaving scholars have to spend the weekend rebinding old books."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 7:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "How do you spell loquacious?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 8:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Why did they have to separate the male and female colleges?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 9:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I think I chose the wrong career path."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 10:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "Would you wash my robes? I just don't have the time these days."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 11:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I can speak 32 languages, you know. But I'm still working on the language of love."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 12:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "What's infinity times negative infinity?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 13:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I can cast a mean fireball, so don't try anything funny!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                        case 14:
                            taskMessage = TextFormatting.YELLOW + "[Scholar] " + TextFormatting.WHITE + "I'm the current college speedreading champion, you know."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            break;
                    }
                }
            }
        }

        return ActionResultType.PASS;
    }

}

// No crime 8am to 5pm
