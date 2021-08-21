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
import java.util.Random;

public class NPCWynnbrimEntity extends AnimalEntity {

    public NPCWynnbrimEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCWynnbrimEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCWynnbrimEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCWynnbrimEntity.NPCData) {
            i = ((NPCWynnbrimEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCWynnbrimEntity.NPCData(i); }
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
                this.setQuestNumber(40);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Ah, the great Godborn! I have been expecting you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "I am Wynnbrim the Wise. I understand you have come seeking my knowledge."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "You want to enter the realm of Kronos, correct? That would be a truly amazing feat. Few have ever managed to penetrate its defenses."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "In fact, the last person known to enter the realm was Lord Nicolau, a Godborn from the 28th century. He did not return, sadly."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Nicolau wrote of his efforts in penetrating the interdimensional barrier. From what I have read, there are four major steps in disabling the protection magic."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "But, centuries later, Kronos may have more security than before. Unfortunately, I have no way of finding out, but we will cross that bridge when we come to it."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Instead of lecturing you on the penetration procedure, I have compiled the pertinent excerpts from Nicolau's writings. They are in the trunk by my desk."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "You may want to make extra copies of the book when you have the time."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }
                else if (questNumber == 4) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Also, when you want to return to my library in the future, you may use this teleport gem to come straight here. These gems are very difficult to enchant, so please do not lose it."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.TELEPORT_GEM_THE_LIBRARY.get());
                    this.setQuestNumber(5);
                }
                else if (questNumber == 5) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Now, are you ready to begin the procedure? The first step is to build the portal, and I trust you can take care of that yourself. However, the materials required are quite interesting."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "I don't believe I have any of these materials on hand, I'm afraid, but I do know how they may be obtained."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(6);
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The Void Gold is the most straightforward. You must take a block of Magic Gold and surround it with 8 Raw Data. The data will be absorbed into the gold, amplifying its interdimensional conductivity."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "I believe the most efficient way of collecting Raw Data is by deconstructing Data Chronicles found in the severed heads of Cosmic Titans. There should be plenty of heads scattered around the Skypiercers, if I'm not mistaken."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(7);
                }
                else if (questNumber == 7) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.VOID_GOLD_BLOCK_ITEM.get()) && (itemstack.getCount() >= 9) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Excellent! Let us continue."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(8);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Return when you have 9 Void Gold Blocks, and I will explain the next layer in the portal."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 8) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "A Titan Block may be formed from 9 Titan Ingots. But these ingots are not easily obtained."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The ingots must be carefully crafted by combining one Titanite Ingot, one Diamond, and one Etherium, resulting in two Titan Ingots."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(9);
                }
                else if (questNumber == 9) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.TITAN_BLOCK_ITEM.get()) && (itemstack.getCount() >= 4) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Great work, Godborn! Let us continue."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(10);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Return with 4 Titan Blocks, and I will explain where to find the crystals."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 10) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The last thing we need for the portal altar are the Theosian Crystals. High in the heavens of Theos, great purple crystals grow from the ground. The crystals are fabled to mark the birthplace of an angel."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "If you can make your way into the Theos Heavens, you will surely find what you seek. However, King Matheus has decreed that Theos must not be entered, as he fears something terrible lies within. He has collected all the keys leading to Theos and locked them securely away."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(11);
                }
                else if (questNumber == 11) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "What lies within Theos, even I do not know. The King also restricted all literature on the subject, fearing that the stories may entice curious souls to venture forth against his wishes."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "It seems you must talk to the king yourself if you wish to gain entry. I believe the wizard who guides you, Aelymore, holds a key to The Kingdom."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest5");
                    this.setQuestNumber(12);
                }
                else if (questNumber == 12) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.THEOSIAN_CRYSTAL_ITEM.get()) && (itemstack.getCount() >= 4) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Well done, well done! You must share with me what you've seen in that unknown land once this is all over. Shall we continue?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(13);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Speak to Aelymore about finding the four Theosian Crystals required for the portal altar. Return to me once you have them."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 13) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Now that the portal altar is complete, you must drink the bypass potion so you don't get atomized by the dimension's barrier."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "I will take care of the brewing. Just get me all of the ingredients listed in the manual and we'll be done in no time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(14);
                }
                else if (questNumber == 14) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.HONEYCOMB) && (itemstack.getCount() >= 30) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Great work, Godborn!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(30);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(15);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "First, gather 30 Honeycomb. Beehives dripping with honey are sure to have lots of it!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 15) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.ETHERIUM.get()) && (itemstack.getCount() >= 15) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Excellent work, Godborn!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.addTag("Quest10");
                        itemstack.shrink(15);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(16);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Bring me 15 Etherium. This material is valued highly by powerful beings. It may take some effort to acquire."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 16) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.BLACK_IRIS_ITEM.get()) && (itemstack.getCount() >= 20) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Ah, thank you very much!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(17);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Gather 20 Black Irises. These rare flowers are fabled to grow when remembering a lost lover. I believe your good fellow Sir Peragon may be of more help."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 17) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Now bring me 20 tanks of Biodiesel. This is another tough ingredient, as it is only manufactured in a few places which use motorized vehicles."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "There was once a famous traveller, Captain Kent, who visited such a place. However, one one of his later travels, he found a strange artifact which summoned a horrific monster. The monster possessed the captain and began to lay ruin to every world it could find."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(18);
                }
                else if (questNumber == 18) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The College of the Magi was eventually able to overcome the monster and banish it to an empty dimension where it could do no more harm. But, since I know of no other way to reach a world that manufactures Biodiesel, I believe we must find a way to remove Captain Kent's curse so that he may give you passage into such a world."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "However, I have not mastered the summoning arts, and I doubt the College can be convinced to return the monster to an inhabited world. Therefore, it seems we must find a summoner."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest11");
                    this.setQuestNumber(19);
                }
                else if (questNumber == 19) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.BIODIESEL.get()) && (itemstack.getCount() >= 20) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Good work, Godborn! I knew you could manage it."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(20);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Aelymore has many wizardly connections. Perhaps he may know someone who can assist us. I'm afraid I can't be of any more help until you return with 20 Biodiesel."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 20) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.ESSENCE_OF_MAN.get()) && (itemstack.getCount() >= 10) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Exquisite specimens indeed!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(10);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(21);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "We require 10 Essence of Man. Simply boil down a Soul into a vial, and you should have what we need."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 21) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.KRAKEN_TOOTH.get()) ) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Wonderful! You have outdone yourself, Godborn."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(22);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Have you seen the giant beasts sulking in the seas of The Kingdom? Bring me 1 Kraken Tooth."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 22) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.METEORITE_ITEM.get()) && (itemstack.getCount() >= 2)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Excellent! I will begin synthesizing these ingredients over the soul fire. Meanwhile, we can gather what we need for the second--wait, what is that noise?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(2);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(23);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Finally, we must retrieve the last ingredient in this phase of the potion. We require 2 Meteorite, an ore found beneath all worlds except the Overworld."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 23) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "WHAT-"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Dark Knight] " + TextFormatting.WHITE + "THE KING SENDS HIS REGARDS!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0f, 0.8f);
                    playerIn.addTag("Quest15");
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        for (int i = 0; i<=6; i++) {
                            ModEntityType.DARK_KNIGHT.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true);
                        }
                    }
                    this.setQuestNumber(24);
                }
                else if (questNumber == 24) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Dark knights! How did they know you were here? It appears the Dark King wants you dead, Godborn. We must make haste!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(25);
                }
                else if (questNumber == 25) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.PERFECT_OPAL.get()) && (itemstack.getCount() >= 1)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Opulent indeed, my friend."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(26);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The next ingredient we need for the potion is 1 Perfect Opal. I'm sure that one won't be too difficult!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 26) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.PHYLACTERY.get()) && (itemstack.getCount() >= 1)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "My my! I never thought I'd ever set eyes upon one of these beauties. I'm tempted to study it, but your mission is far more important."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(27);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "It seems we must acquire a Phylactery. This object, in which a lich stores its life energy, is extremely rare indeed. Many a lich has been known to take up residence within the Cursed Forest, so perhaps you might begin your search there. Be careful!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 27) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.RAW_DATA.get()) && (itemstack.getCount() >= 50)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Great work, Godborn! Now for the other 50."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(50);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(28);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "100 Raw Data! Can you handle it, Godborn? Give me 50 at a time, please."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 28) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.RAW_DATA.get()) && (itemstack.getCount() >= 50)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Very fine indeed! I've never seen so much raw knowledge! Imagine what we could learn from it all..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(50);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(29);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "We need 50 more Raw Data! I'm sure you can manage it."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 29) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Have you ever wondered why you can come back to life after falling in battle? It is because the souls of all the previous Godborn live within you! They give you the power to learn from your mistakes where they could not."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Indeed, you are truly one-of-a-kind. I hear Captain Kent is seeking your attention, by the way."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(30);
                    playerIn.addTag("Quest18");
                    playerIn.addTag("Quest14");
                }
                else if (questNumber == 30) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.SACRED_WATER.get()) && (itemstack.getCount() >= 12)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Well done, Godborn!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(12);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(31);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Now we must bless 12 Sacred Water. This can be done by surrounding Sacred Texts by Water Bottles and chanting the prayers within."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        if (playerIn.getTags().contains("Quest18")) {
                            taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Luckily, being a librarian, I have a copy of the Sacred Texts right here. Be careful with this!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            playerIn.removeTag("Quest18");
                            this.entityDropItem(ModItems.SACRED_TEXTS.get());
                        }
                    }
                }
                else if (questNumber == 31) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.TOAD_CAP.get()) && (itemstack.getCount() >= 20)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Excellent! Thank you for not squashing them, Godborn."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(32);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Next, we require 20 Toad Caps. Have you seen the mushroom forests in The Kingdom? Perhaps you should go for a stroll within."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 32) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.TWILIGHT_ROSE_ITEM.get()) && (itemstack.getCount() >= 16)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "What a beautifully fragrant smell, don't you think?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(16);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(33);
                        this.removeTag("Quest19");
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "We need 16 Twilight Roses! I hear the princess Elizabeth knows a thing or two about flowers. Perhaps she might have some at hand!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.addTag("Quest19");
                    }
                }
                else if (questNumber == 33) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.UNDERSTONE.get()) && (itemstack.getCount() >= 3)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Ah, yes. I can feel the dark aura emanating from these stones. You shouldn't hold these for too long, you know."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(3);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(34);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "The next ingredients required are 3 Understone from the malevolent Cursed Forest. Do not smelt them! We need the crystal structure to remain intact."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 34) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.ENDER_EYE) && (itemstack.getCount() >= 6)) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Oh my! They seem to stare right into your very soul, don't they?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(6);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(35);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Finally! The last ingredient! You must procure 6 Eyes of Ender, then we will be ready for the final step."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 35) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Right then. Now that the potion is ready, we must craft the Roseum bottle. Bring me 1 Hexagonal Roseum and 64 Nether Quartz. We're going to use the old sandblasting technique."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(36);
                }
                else if (questNumber == 36) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.HEXAGONAL_ROSEUM.get()) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Would you look at that beauty! Truly the embodiment of hexagonality! Now we just need 64 Nether Quartz to begin the sandblasting."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(37);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Bring me the 1 Hexagonal Roseum first, if you please."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 37) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.QUARTZ && itemstack.getCount() >= 64) {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "Right-o! Time to get down to business. You better put on some PPE. Safety is number one priority!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(64);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.setQuestNumber(38);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "We need 64 Nether Quartz to begin sandblasting this roseum!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 38) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "WOOOOOOOOOOOOOOOO!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    for (int i = 1; i <= 150; i++) {
                        this.world.addParticle(ParticleTypes.CRIT, this.getPosXRandom(1.5D), this.getPosYRandom(), this.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    playerIn.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 1f, 1f);
                    playerIn.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 1f, 0.7f);
                    this.setQuestNumber(39);
                }
                else if (questNumber == 39) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "YEAH BABYYY!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    for (int i = 1; i <= 150; i++) {
                        this.world.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getPosXRandom(1.5D), this.getPosYRandom(), this.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    playerIn.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 1f, 1f);
                    playerIn.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, 1f, 0.7f);
                    this.setQuestNumber(40);
                }
                else if (questNumber == 40) {
                    taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "And voila, we have out bottle! Let me pour the potion and-"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    for (int i = 1; i <= 15; i++) {
                        this.world.addParticle(ParticleTypes.CRIT, this.getPosXRandom(1.5D), this.getPosYRandom(), this.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    playerIn.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.8f, 1f);
                    this.setQuestNumber(41);
                }
                else if (questNumber == 41) {
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.NPC_TIMEKEEPER.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true);
                    }
                    playerIn.playSound(SoundInit.LICH_SUMMON.get(), 0.8f, 0.7f);
                    this.setQuestNumber(42);
                }
                else if (questNumber == 42) {
                    if (playerIn.getTags().contains("quest23")) {
                        playerIn.removeTag("quest23");
                        taskMessage = TextFormatting.YELLOW + "[Wynnbrim the Wise] " + TextFormatting.WHITE + "????"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(43);
                    }
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

// No crime 8am to 5pm
