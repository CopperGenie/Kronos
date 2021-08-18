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

public class NPCPeragonEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCPeragonEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCPeragonEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCPeragonEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCPeragonEntity.NPCData) {
            i = ((NPCPeragonEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCPeragonEntity.NPCData(i); }
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
                this.setQuestNumber(14);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "My goodness! Where am I? I must have been frozen in that crystal for days!";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I could neither speak nor feel my body. I was left to tend to my own thoughts and listen to Tenebrik's mad mutterings.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I am in your debt, my liege. If ever you find yourself in need of a knight, my blade is yours.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Actually, come to think of it...mine seems to have disappeared. I can't recall if I was wearing it when Tenebrik got the better of me. Alas, it was a fine blade.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }

                else if (questNumber == 2) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "But our friend Aelymore tells me you are the last Godborn! Surely you have great skill in crafting. I am not too bad a smith myself.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Could you do me this one favor? Will you provide me with an Iron Sword, 30 Lapis Lazuli, and 3 Emeralds? With these, I can fashion a fine blade to rival that which was lost.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }

                else if (questNumber == 3) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.IRON_SWORD) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);

                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "What beauty! Never have I held such a balanced blade. There is no doubt that you are Godborn.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "30 Lapis Lazuli next. I'm sure you can manage!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(4);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Acquire the sword first, my liege.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }

                else if (questNumber == 4) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.LAPIS_LAZULI) && (itemstack.getCount() >= 30) ) {
                        itemstack.shrink(30);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);

                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "What magnificent color! A blue deeper than the deepest ocean...";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "And lastly, I require 3 Emeralds. The finest specimens, of course!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(5);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "30 Lapis Lazuli next. I'm sure you can manage!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }

                else if (questNumber == 5) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.EMERALD) && (itemstack.getCount() >= 3) ) {
                        itemstack.shrink(3);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);

                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Such works of art, these gems. Nature has outdone itself, and you!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Now I have all I need to craft my new blade, thank you kindly. When I return to The Kingdom, my fellow knights will be in awe!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(6);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "For my hilt, I require 3 Emeralds. The finest specimens, of course!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }

                else if (questNumber == 6) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I hear you are in search of Understeel. This key will bring you to the Cursed Forest. There, you will find much evil.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Far beneath the earth is where you will find what you seek: Understone. Smelt this in a furnace to obtain the Understeel. Safe travels, my liege.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.entityDropItem(ModItems.WORLD_KEY_CURSED_FOREST.get());
                    this.setQuestNumber(7);
                }

                else if (questNumber == 7) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Word has spread of your deeds, my liege. A friend of mine, the wizard Oswir, has traveled here to meet the last Godborn.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Oswir is known as the Keyfinder. Speak to him, and you will find he has much to offer.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.NPC_OSWIR.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0), SpawnReason.EVENT, false, true);
                    }
                    this.setQuestNumber(8);
                }

                else if (questNumber == 8) {
                    if (playerIn.getTags().contains("Quest4")) {
                        playerIn.removeTag("Quest4");
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "My liege, I hear that your key to Celiria has been stolen. This is grave news, but I have faith in Aelymore to find a solution!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "In the meantime, would you mind helping me with something?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(9);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Hail, my liege."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }

                else if (questNumber == 9) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I have heard tales of magical swords, enchanted by the fairies, that can be controlled with just your mind..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "If I had one such blade, I would be the greatest knight in The Kingdom! I could duel five rivals at once, haha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(10);
                }

                else if (questNumber == 10) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "But alas, the fairies reside in a mysterious land called Sidhe. Few have ever traveled there and returned."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "As far as I am aware, no keys exist to the fairy world. But there are tales of wizards summoning a fairy to this land with the hope of being taken back to Sidhe with it."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(11);
                }

                else if (questNumber == 11) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I am no summoner, but I do know of magical items called Fairy Stones. These stones are given to those whom a fairy trusts, so that it may be summoned in a time of need. With one of these stones, one can summon a fairy without the need of magical training."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "There is one race in particular that the fairies trust the most: trolls. These green creatures reside deep in the Skypiercers, a vast land of mountains and lakes. If there is one place you are likely to find a Fairy Stone, it is there."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(12);
                }

                else if (questNumber == 12) {
                    this.entityDropItem(ModItems.WORLD_KEY_SKYPIERCERS.get());
                    playerIn.addTag("GotSkypiercersKey");
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "In fact, I just so happen to have a key to the Skypiercers. I won it in a duel against a powerful duke! I would go myself, but this armor was not made for climbing mountains. Haha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Bring me a Fairy Stone, if you would, so that I may summon a fairy to enchant my blade! You may keep the key afterward if you would like. "; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(13);
                }

                else if (questNumber == 13) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.FAIRY_STONE.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Marvelous! You have brought the stone! If you look through the hole, they say you can see into the fairy realm. And if you speak into it, you can summon your fairy!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Well, no reason to tarry. Let's give it a try, shall we?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(14);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Have you found the Fairy Stone, my liege?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }

                else if (questNumber == 14) {
                    taskMessage = TextFormatting.WHITE + "Sir Peragon peers through the hole..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(15);

                }

                else if (questNumber == 15) {
                    taskMessage = TextFormatting.GOLD + "[Distant Voice] " + TextFormatting.WHITE + "Oi! What are you looking at?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.attackEntityFrom(DamageSource.MAGIC, 1.0F);
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Ouch!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(16);
                }

                else if (questNumber == 16) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "What did I do? I want to ask a favor of you, great fairy. I nee-"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Distant Voice] " + TextFormatting.WHITE + "You need me to enchant something for you? Or perhaps give you the ability to fly? Or everlasting life? Well you can forget it! I hate humans, the lot of you!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(17);
                }

                else if (questNumber == 17) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "But I am Sir Peragon the Valiant! I am true of heart. You have no reason to hate me, great fairy. Just let me exp-"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Distant Voice] " + TextFormatting.WHITE + "I said forget it, pentagon the smelliant! The last human invited into our world caused unimaginable destruction! Never again will we trust the humans!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(18);
                }

                else if (questNumber == 18) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Oh my! Well, I promise not all humans are like that. Who was it that caused such destruction?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.GOLD + "[Distant Voice] " + TextFormatting.WHITE + "Bah! I don't know his name. Tenebrack or something. We took swift care of him, at any rate. I'm not taking any chances with you. In fact, I'm going to come through that hole and kick your butt before you get any more ideas!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(19);
                }

                else if (questNumber == 19) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "No no, you don't have to-"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Angry Fairy] " + TextFormatting.WHITE + "Fear my wrath! Death to the humans!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.TREASURE_FAIRY.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0), SpawnReason.EVENT, false, true);
                    }
                    this.setQuestNumber(20);
                }
                else if (questNumber == 20) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "My liege, are you okay? What a disaster! That blasted Tenebrik, terrorizing us even in death."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I guess I'll have to stick with a regular old sword for now. But it was worth a try, right?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(21);
                }
                else if (questNumber == 21) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Thank you for the help, my liege. On that note, have you had any luck finding Phantasmus?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Who dares speak my name?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.NPC_PHANTASMUS.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0), SpawnReason.EVENT, false, true);
                    }
                    playerIn.addTag("PhantasmusSetQuest5");
                    this.setQuestNumber(22);
                }
                else if (questNumber == 22) {
                    if (playerIn.getTags().contains("Quest10")) {
                        playerIn.removeTag("Quest10");
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "How goes the quest, my liege? The Black Iris? By the gods...I haven't beheld one such sorrowful flower since..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(23);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "How fare thee, my liege?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 23) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Forgive me, my liege, for it is a sad tale to tell. Many years ago, I had a beloved paramour to which I was to be wed. One fateful day, her carriage was overtaken by bandits and she was imprisoned. When I came to save her, she had already been killed by the unholy savages. Her form lay cold and lifeless on the ground. And as I wept over her, I beheld a black flower spring from the ground and bloom before me."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(24);
                }
                else if (questNumber == 24) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Yes, this is the Black Iris you seek. But it seems that you require an unnatural amount of them, so you may need to use unorthodox methods to obtain them."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "My paramour was buried in a beautiful garden in the green plains of The Kingdom, as were may other lost lovers. I suggest you search these gardens first. However, there may be a more efficient way to gather more flowers."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(25);
                }
                else if (questNumber == 25) {
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "I am knowledgeable in the art of enchantment as well as swordsmithing, and I believe you may be able to channel the magic of the iris into a blade to reproduce its occurrence."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Two Diamonds for the blade, two Twilight Roses at the hilt to seal the enchantment, and a Black Iris in the pommel to bring forth the dreary magic."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(26);
                }
                else if (questNumber == 26) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.TWILIGHT_SWORD.get()) {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "A fine blade, my liege! I could not have done better myself. I expect you can handle the rest yourself. Good luck on your quest!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(27);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Have you sought the green gardens of The Kingdom? Once you obtain a Black Iris, craft the Twilight Blade from two Diamonds, two Twilight Roses, and the iris."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 27) {
                    if (playerIn.getTags().contains("Quest15")) {
                        playerIn.removeTag("Quest15");
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Hail, how goes the quest? I have an urgent matter that might interest you, my liege, if you have the time to spare."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(28);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Sir Peragon] " + TextFormatting.WHITE + "Good luck, my liege!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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

// Ethan smells