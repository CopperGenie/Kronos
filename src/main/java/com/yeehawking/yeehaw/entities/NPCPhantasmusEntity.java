package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.Block;
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
import net.minecraft.particles.ParticleTypes;
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

public class NPCPhantasmusEntity extends AnimalEntity {

    public NPCPhantasmusEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCPhantasmusEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCPhantasmusEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCPhantasmusEntity.NPCData) {
            i = ((NPCPhantasmusEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCPhantasmusEntity.NPCData(i); }
        this.setTexture(i);

        this.setQuestNumber(0);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }



    public void setTexture(int i) { this.dataManager.set(TEXTURE, i); }
    public int getTexture() { return this.dataManager.get(TEXTURE); }

    public void setQuestNumber(int i) { this.dataManager.set(QUEST_NUMBER, i); }
    public int getQuestNumber() { return this.dataManager.get(QUEST_NUMBER); }

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
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            Random rand = new Random();
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(4);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber(); if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    if (playerIn.getTags().contains("PhantasmusSetQuest5")) {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "How could I have guessed? The great Godborn, slayer of...fairies... What are you up to these days? Not trying to save the world I hope, for your sake. Ahaha!";if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("PhantasmusSetQuest5");
                        this.setQuestNumber(5);
                    }
                    else if (playerIn.getTags().contains("PhantasmusSetQuest8")) {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Come with me and you'll be...in a world of pure imagination..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "We'll begin with a spin...travelling in the world of my creation..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "What we'll see will defy explanation... Well, what do you think? Pretty impressive, right?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("PhantasmusSetQuest8");
                        this.setQuestNumber(8);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Hmph. You don't look like much."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(1);
                    }
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Do you know how Kronos ended the lives of all the previous Godborn?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "He ATE them! Whole! With each Godborn, Kronos only grew stronger. This fool is sending you to your death. Entering Kronos's domain is impossible. But we mustn't lose hope right? Ahahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "I have my own dimension, you know. Would you like to see it?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Enough of this charade!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.addTag("Quest1");
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    if (playerIn.getTags().contains("Quest2")) {
                        this.setTexture(1);
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Look at me! I'm a stuck-up old man who likes sending adventurers unknowingly to their deaths!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(4);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Obele bo obebobole."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 4) {
                    this.setTexture(0);
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Nice meeting you, Hogborn. It seems it's time for me to go. Farewell, for now... Ahahahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20 * 5, 2));
                    for (int i = 1; i <= 150; i++) {
                        this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXRandom(1.5D), this.getPosYRandom(), this.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    this.teleportKeepLoaded(0,0,0);
                    playerIn.removeTag("Quest2");
                    this.setQuestNumber(-1);
                }
                else if (questNumber == 5) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Well, seeing as the grumpy old man isn't around, how would you like to see my wonderful realm of impossibilities?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Warning: you may die of amazement! Or something more sinister...you have been warned! Leave all your precious items here before we go."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(6);
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Are you ready to enter the impossible?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(7);
                }
                else if (questNumber == 7) {
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        serverPlayer.teleport(serverPlayer.server.getWorld(Yeehaw.REALM_OF_PHANTASMUS), 4, 57, -8,
                                serverPlayer.rotationYaw, serverPlayer.rotationPitch);
                        serverPlayer.func_242111_a(Yeehaw.REALM_OF_PHANTASMUS, serverPlayer.getPosition(), 0F,
                                true, false);
                        ModEntityType.NPC_PHANTASMUS.get().spawn(serverPlayer.server.getWorld(Yeehaw.REALM_OF_PHANTASMUS), null, null, serverPlayer.getPosition().add(1, 0, 0),
                                SpawnReason.EVENT, false, true);
                    }
                    playerIn.addTag("PhantasmusSetQuest8");

                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Righto! Then behold!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setHealth(0);
                }
                else if (questNumber == 8) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "You're not impressed? Not even a little? I spent eleven whole years inventing this place! The biology, the physics, the torch flowers! The torch flowers!!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(9);
                }
                else if (questNumber == 9) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Okay, okay. I know why you're really here. You thought you could find your precious key, didn't you?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "I don't live here you know. There's nothing here but floaty islands for miles and miles! Muahaha! The key is safe in my tower with my other ten thousand keys to Celiria."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(10);
                }
                else if (questNumber == 10) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Why do you want to save the world so badly anyway?! You won't even be alive by the time Kronos gets around to destroying your overworld. There are billions of dimensions, and the dark god gets his satisfaction from slowly draining the life from each of them, one by one."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "But then I suppose you are the LAST of the Godborn...killing Kronos is your purpose. Smiting malevolent cosmic beings is what you were created to do. But do you really think you can succeed in the footsteps of the countless others who have failed before you?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(11);
                }
                else if (questNumber == 11) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Alright, fine! You want the key to Celiria? Prove to me you can handle the key to Celiria!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Enter the nether realm and battle the denizens within! Return to me with 1 Blaze Rod, 1 Ghast Tear, and 1 Wither Skeleton Skull!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(12);
                }
                else if (questNumber == 12) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Ha! You realized you're trapped here! Here, take my spare key. And don't go lending it to any grumpy old wizards!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "I will be here, awaiting your return."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.WORLD_KEY_REALM_OF_PHANTASMUS.get());
                    playerIn.addTag("GotRealmOfPhantasmusKey");
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
                        BlockPos originPos = new BlockPos(0,70,0);
                        serverPlayer.func_242111_a(RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(
                                "minecraft:overworld")), originPos, 0F, true, false);
                    }
                    this.setQuestNumber(13);
                }
                else if (questNumber == 13) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.BLAZE_ROD) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Ah, eternal heat! This will do nicely in my fireplace. But you have not yet proven yourself! Bring me 1 Ghast Tear!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(14);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "You can't even manage one Blaze Rod?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 14) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.GHAST_TEAR) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Are you sure this isn't just water? Hmmm... I'll have to taste it to be sure."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Hoo wee! What a zing! Alright, I'm convinced. But I still need a Wither Skeleton Skull from you! Don't let me down!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(15);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Is the great Godborn afraid of one little ghast? Bring me its tear, or I'll turn you into one and harvest it myself!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 15) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.WITHER_SKELETON_SKULL) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Would you look at that! Eyes like bottomless pits, smells like old coal...looks like old coal...it checks out!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Right then. You have almost proven that you can maybe, potentially succeed in making it to Kronos. But killing him? I am not so sure."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(16);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Are you afraid of skeletons, Godborn? I need that Wither Skeleton Skull! Well, not really. But you need the key, don't you? Ahahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 16) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Have you heard of the mind flayer? It is a species from the Cursed Forest with telekinetic powers and evil magic, fueled by the brain juice of the innocent!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "If you can defeat one, I may put some of my faith in you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(17);
                }
                else if (questNumber == 17) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Going to the Cursed Forest? Don't be silly! Why waste time trying to find something when you could just bring it to you with your excellent summoning skills?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "I was top of my class in summoning, don't you know? On my last day at the mage college, I made it rain chickens in the alchemy tower! They banished me, of course. But the look on Professor Snapple's face was well worth it! Hahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(18);
                }
                else if (questNumber == 18) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Are you ready, Godborn? Make sure you're ready! I don't want to have to send your brainless corpse to Aelymore, gross!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(19);
                }
                else if (questNumber == 19) {
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.MIND_FLAYER.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0),
                                SpawnReason.EVENT, false, true);
                    }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Behold, the mind flayer!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(20);
                }
                else if (questNumber == 20) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Quite a beast, isn't it? In the olden days, telekinesis spells needed mind flayer brains to work, don't you know?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(21);
                }
                else if (questNumber == 21) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Well it seems you are still alive. I am rather impressed, seeing as you have no magical training. Here, a gift."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    for (int i=1; i <=5; i++) {
                        this.entityDropItem(ModItems.SCROLL_OF_SUMMONING.get());
                    }
                    this.setQuestNumber(22);
                }
                else if (questNumber == 22) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "And of course, the key. I trust that you will use it wisely. I will be watching you, Godborn... Don't disappoint me! Ahahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.WORLD_KEY_CELIRIA.get());
                    this.setQuestNumber(23);
                }
                else if (questNumber == 23) {
                    if (playerIn.getTags().contains("Quest12")) {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Ah, the Godborn returns! A favor to ask, I presume. Why else would anyone visit this old knavish joker? Haha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("Quest12");
                        this.setQuestNumber(24);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Faring well, old chap? I believe there's still an evil god waiting to be smitten down. Don't keep him waiting! Hahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 24) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "A master of summoning? Why, you insult me my lad! I am THE master of summoning! I once opened a giant portal in the sky to the egg dimension, and it rained yolks for a week! Muahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Oh, the great Captain Kent you say? I believe he has come to be known now as Herobrine, the Cosmic Terror. And he sure lived up to that name... I accept the challenge, of course!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(25);
                }
                else if (questNumber == 25) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Herobrine was banished to a dimension the wizards call Nowhere, a black and barren land devoid of all life... except the terror himself of course! The problem is, the College put extra security around the dimension to be sure he would stay put."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "This means I can't cast any old whoopity doo and expect to pull him out, you see? We must make an energized summoning altar! Kind of like that one you're making for Kronos, but on a smaller scale. Yes, I've been watching you! Just in case you don't get yourself into too much trouble, haha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(26);
                }
                else if (questNumber == 26) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "My method is of course much simpler than that million step process Nicolau has you going through. All we need is an Interdimensional Receiver, Nowhere's dimensional code, and lots of VOID GOLD! My favorite metal, don't you know?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(27);
                }
                else if (questNumber == 27) {
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Since I don't think the wizards ever planned to bring that beast back from his prison, it is doubtful they even have access to the dimension anymore. However, you have undoubtedly seen those giant heads around Skypiercers, right? Those were the heads of great cosmic titans, and their brains work a little differently from ours. They have databanks which constantly collect information from the world around them and store it securely within. And these records should be intact, even in death! If the code exists, out best chance is to find it chronicled in one of these databanks."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(28);
                }
                else if (questNumber == 28) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.DECRYPTED_HOMING_KEY.get()) {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Excellent, my lad! Now you must craft the Interdimensional Receiver so we can find Herobrine and teleport him here."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(29);

                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "When you find the chronicle containing the code, just infuse it into a key of Raw Data and we can place it into the Interdimensional Receiver later on. I'm sure you can figure it out, my good lad! Bring me the key when you have finished."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 29) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.INTERDIMENSIONAL_RECEIVER_ITEM.get()) {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Everything looks to be in order! Now, I assume you have some Void Gold on hand, so we can get to business!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(30);

                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "For the Interdimensional Receiver, you will need 3 Iron Blocks, 2 End Rods, 2 Etherium, a Gold Block, and a Giant Soul. Easy enough, right? Once you build it, show it to me so I can check your craftsmanship!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 30) {
                    if (playerIn.getTags().contains("Quest13")) {
                        playerIn.removeTag("Quest13");
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Well I say! The great Godborn, slayer of the Cosmic Horror! Good show, old chap. The historians at the College will surely put that one in the books!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(31);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "For the base of the teleporter, you must place one piece of Ancient Debris surrounded by 4 Void Gold Blocks, with 4 Redstone Blocks at the corners. Then place the Receiver on the center, and insert the key when you're ready. And you had better be ready! If you can't handle the Cosmic Terror the first time, you'll have to summon him again with another key."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 31) {
                    if (playerIn.getTags().contains("???")) {
                        playerIn.removeTag("???");
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(32);
                    } else {
                        taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Tally-ho my good chap! Kronos awaits!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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

// MERASMUS !!! Go back to Canada!