package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.Yeehaw;
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
import net.minecraft.entity.player.ServerPlayerEntity;
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
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class NPCKentEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCKentEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3d, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCKentEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCKentEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCKentEntity.NPCData) {
            i = ((NPCKentEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCKentEntity.NPCData(i); }
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
            Random rand = new Random();
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(0);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber(); if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "Rrrrgh... Thank you, sir. I've been...trapped with him for centuries... I am in your eternal debt."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "On one of my travels, I came across an ancient artifact. A powerful warlock had sealed part of his soul within so that he may live on after his own body had perished. When I touched the artifact, the warlock took over my mind and I've been under his control ever since. I'm very weak and the warlock's remnant magic is the only thing keeping me alive. Could I ask one more favor of you?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "There is an advanced technology called a Time Card that has the power to manipulate time in minuscule degrees. This technology should allow me to return to my younger self."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "Will you retrieve the Time Card for me? There is a world governed by chaos, known as PilotRedSun, where I have seen this temporal technology in use. I acquired a key to the world on my last journey there. Take it, and find the card!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                    this.entityDropItem(ModItems.WORLD_KEY_PILOT_RED_SUN.get());
                    playerIn.addTag("GotPilotRedSunKey");
                }
                else if (questNumber == 2) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.TIME_CARD.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "I can hardly believe my eyes! You have found it! Thank you, thank you!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(3);
                    } else {
                        taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "Will you help me? Travel to PilotRedSun's dimension and find a Time Card so that I may return to my younger self. Please, I don't have much time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.GOLD + "[Captain Kent] " + TextFormatting.WHITE + "Biodiesel? Yes, I do believe the vehicles there use it. If you have a need for such fuel, there should be no shortage."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 3) {
                    this.setTexture(1);
                    playerIn.playSound(SoundInit.GENIE_DEATH.get(), 1.2f, 0.7f);
                    taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHH!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }
                else if (questNumber == 4) {
                    this.setTexture(1);
                    taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Phew, that hurt...but here I am! Alive and young! I don't even recognize myself... You have done me the greatest kindness, my friend. Whatever you need, I will aid you until death."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Here, I have no use for this wretched thing. It is the artifact within which the warlock had trapped his soul. I felt the soul being destroyed as you defeated the warlock, so it should be safe to handle. It is surely valuable, but I can't stand to lay eyes upon it any longer..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.COSMIC_EYE.get());
                    this.setQuestNumber(5);
                }
                else if (questNumber == 5) {
                    if (playerIn.getTags().contains("Quest14")) {
                        playerIn.removeTag("Quest14");
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Ah, master Godborn! Your chum Peragon has been telling me of your great feats. It seems you have a great journey ahead of you yet!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "I thought you might have use for this old thing. I found it in my coat pocket; no doubt meant by the warlock to trouble some innocent traveller."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.entityDropItem(ModItems.SCROLL_OF_EXPULSION.get());
                        this.setQuestNumber(6);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Oh, what is a captain without his ship? She was quite marvellous...but centuries of reckless trespassing by the warlock has reduced her to mere splinters..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Actually...there is something I may need your help with. Do you know of Princess Elizabeth of Evenwood? She is quite fair, don't you agree?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "I would very much like to meet her someday, but I haven't quite worked out what I want to say. I hear she loves flowers too! I was wondering, would you take this fine tulip to her and acquaint her with me and my deeds? I feel it would make a good impression on her while I ponder on what to say when we meet."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(7);
                    this.entityDropItem(Items.RED_TULIP);
                    playerIn.addTag("Quest16");
                }
                else if (questNumber == 7) {
                    if (playerIn.getTags().contains("Quest17")) {
                        playerIn.removeTag("Quest17");
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Did the princess like my gift? I knew she would! Thank you, my friend. Perhaps all is not yet lost for this fateful captain."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(8);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Have you taken Princess Elizabeth my tulip?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 8) {
                    if (playerIn.getTags().contains("???")) {
                        playerIn.removeTag("???");
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(9);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "How are you, good friend?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        if (this.rand.nextInt(5) == 0) {
                            taskMessage = TextFormatting.YELLOW + "[Captain Kent] " + TextFormatting.WHITE + "Fancy an apple?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            this.entityDropItem(Items.APPLE);
                        }
                    }
                }
            }
        }

        return ActionResultType.PASS;
    }

    public boolean isImmuneToFire() { return true; }

    // No fall damage
    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        if (onGroundIn) { this.fallDistance = 0.0F; } else if (y < 0.0D) { this.fallDistance = 0.0F; }
    }
}

// !!!