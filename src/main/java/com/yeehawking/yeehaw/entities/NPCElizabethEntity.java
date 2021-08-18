package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
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

public class NPCElizabethEntity extends AnimalEntity {

    public NPCElizabethEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.POPPY, Items.DANDELION, Items.ROSE_BUSH, Items.BLUE_ORCHID, Items.LILY_OF_THE_VALLEY, Items.RED_TULIP);

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
    protected SoundEvent getDeathSound() { return SoundInit.TREASURE_FAIRY_SUMMON.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.FEMALE_HURT.get(); }



    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCElizabethEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCElizabethEntity.class, DataSerializers.VARINT);

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TEXTURE, 0);
        this.dataManager.register(QUEST_NUMBER, 0);
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
        if (spawnDataIn instanceof NPCElizabethEntity.NPCData) {
            i = ((NPCElizabethEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCElizabethEntity.NPCData(i); }
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
                this.setQuestNumber(10);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber(); if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Who are you?! How did you get in here? GUARDS!! There's a weirdo in my room!!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Oh, a Godborn? Father has told me of you! Destined to destroy the great evil Kronos..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Sounds like a grand adventure! Have you slain many monsters?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Oh how I wish Father would let me leave the castle. I've been practicing swordfighting with the guards and I'm getting pretty good! I could take on any monster that comes at me!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "*Yawn* I'm ready for a nap. Good luck on your adventures! Come back and see me sometime."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }
                else if (questNumber == 4) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.ROSE_BUSH) {
                        itemstack.shrink(1);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Wow! These are so pretty! Where did you get them?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(5);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "My room needs more roses, what do you think?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 5) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "What do you think of the fairies? Father says they're evil creatures that can't be trusted, but I don't think he's ever even seen one! How can he know? Maybe they're cute and friendly little guys! I'd love to meet one someday..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(6);
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Do you like music? I can play the piano, you know. Lord Aelymore taught me when I was really young. Aelymore said he learned to play from his great grandfather! Isn't that wonderful? I wish I had grandparents...but it's just me and my father."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(7);
                }
                else if (questNumber == 7) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Hey, guess what? I had a dream last night that I was peeking through a weird rock and there was a beautiful world on the other side! And guess what else?? There were fairies there too! Oh, I wish I could go back to sleep!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(8);
                }
                else if (questNumber == 8) {
                    if (playerIn.getTags().contains("Quest16") && playerIn.getHeldItemMainhand().getItem() == Items.RED_TULIP) {
                        playerIn.removeTag("Quest16");
                        playerIn.addTag("Quest17");
                        itemstack.shrink(1);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Aww, for me? It's beautiful! Thank you! It smells so good!!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(9);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "My flowers are dying, eek!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                }
                else if (questNumber == 9) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I have something for you too! I found it in our back garden. It's shiny...but I don't know what to do with it, ahaha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Did you say Captain Crunch? That's my favorite cereal, how did you know?? Now I'm hungry!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(Items.NETHERITE_SCRAP);
                    this.setQuestNumber(10);
                }
                else if (questNumber == 10) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Oooh, do you know what I'm hungry for? CAKE! Could you get some from the kitchen for me, please? I'll find somewhere to put this tulip!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(11);
                }
                else if (questNumber == 11) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.CAKE) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Wow, thanks! Our baker makes the best cakes!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(12);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Could you get me some cake from the kitchen? You can have some too!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 12) {
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "Cake!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "I smell sweets!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "Cake? CAKE!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "Grab it!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "Get the cake!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "EEK! No, get your hands off!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        for (int i = 1; i<=5; i++) {
                            ModEntityType.TROLL.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true);
                        }
                    }
                    this.setQuestNumber(13);
                }
                else if (questNumber == 13) {
                    taskMessage = TextFormatting.RED + "[Troll] " + TextFormatting.WHITE + "It's mine!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Where are they coming from?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.TROLL.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true);
                    }
                    this.setQuestNumber(14);
                }
                else if (questNumber == 14) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Thanks for getting rid of those pests! I guess they really like cake too, haha!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(15);
                }
                else if (questNumber == 15) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Could you tell my father what happened? I don't wants any more trolls getting in here!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(16);
                    playerIn.addTag("Quest20");
                }
                else if (questNumber == 16) {
                    if (playerIn.getTags().contains("Quest21")) {
                        playerIn.removeTag("Quest21");
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "No! Father has banished you from the kingdom?! Grr...he ruins everything!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(17);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Let my father know what happened!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 17) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "We'll just have to be extra sneaky then, won't we? Muahaha! By the way, I found something when the trolls came to steal my cake..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(18);
                }
                else if (questNumber == 18) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "It's the stone ring from my dream!! I wonder what the trolls were doing with it... Do you think I should look in the hole?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(19);
                }
                else if (questNumber == 19) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I'm gonna do it!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.WHITE + "The princess peers through the hole..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(20);
                }
                else if (questNumber == 20) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I see the fairy forest! And glowy water! And cute little houses! But where are the fairies?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(21);
                }
                else if (questNumber == 21) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Maybe they're asleep..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.AQUA + "[Voice] " + TextFormatting.WHITE + "Who dares disturb my slumber?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(22);
                }
                else if (questNumber == 22) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Oh! I didn't mean to wake you, sorry! I'm Princess Elizabeth of Evenwood!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(23);
                }
                else if (questNumber == 23) {
                    taskMessage = TextFormatting.AQUA + "[Voice] " + TextFormatting.WHITE + "My goodness! Elizabeth, daughter of Agatha and Matheus?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(24);
                }
                else if (questNumber == 24) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "You know me? Are you a telepathic fairy?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(25);
                }
                else if (questNumber == 25) {
                    taskMessage = TextFormatting.AQUA + "[Voice] " + TextFormatting.WHITE + "Haha! No, my child. The Fairy Elder has foretold of your coming. The elder spoke of a great princess that would come to the fairies in their greatest time of need and save us from destruction. It seems you have finally come! But what is more concerning...are the fairies in danger?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(26);
                }
                else if (questNumber == 26) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "What?! No way, I can't be who you think I am! I've never even been outside the castle, let alone travelled to another world!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(27);
                }
                else if (questNumber == 27) {
                    taskMessage = TextFormatting.AQUA + "[Voice] " + TextFormatting.WHITE + "My child, I am certain that it is you the elder spoke of. And the elder's prophecies always come true! I am just worried what malevolence is imminent upon our race..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.AQUA + "[Voice] " + TextFormatting.WHITE + "Allow me to finally introduce myself. I am Periculus, one of many scribes to the Fairy Elder. I am honored to make your acquaintance, princess, and I'm sure the elder will be delighted to hear of our meeting!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(28);
                }
                else if (questNumber == 28) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Wow! I can't believe it! Can I meet the Fairy Elder?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(29);
                }
                else if (questNumber == 29) {
                    taskMessage = TextFormatting.AQUA + "[Periculus] " + TextFormatting.WHITE + "Ah, you two will meet in time. For now, let me speak with her and wait for my return. I don't know how you came across this stone, but it was fated so. Keep it safe! I will return in time."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.AQUA + "[Periculus] " + TextFormatting.WHITE + "Farewell, princess."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(30);
                }
                else if (questNumber == 30) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Bye bye! I will keep it safe, I promise!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(31);
                }
                else if (questNumber == 31) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Can you believe it? I got to see a fairy! This is amazing! Do you really think I'm going to save the whole fairy world from evil? That sounds like a big deal..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I guess I'd better hold onto this stone until Periculus come back."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(32);
                }
                else if (questNumber == 32) {
                    if (playerIn.getTags().contains("Quest19")) {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Oh hi! Twilight Roses? Those are one of my favorites! I wish I had some, but they take special magic to grow..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Lord Aelymore grows them for me in our garden, but he's been away for a while... I can remember the spell, but I don't have any of the casting ingredients!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(33);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "When do you think Periculus will come back?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 33) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "If you could get the ingredients, I could grow them for us! If you need 16 Twilight Roses, we would need 30 Bones, 20 Blaze Powder, 40 Lapis Lazuli, and 20 Rose Bushes, so I can have enough seeds to keep some for myself!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Can you get all that?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(34);
                }
                else if (questNumber == 34) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.BONE) && (itemstack.getCount() >= 30) ) {
                        itemstack.shrink(30);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "You're the best!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(35);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Yay! Bring me 30 Bones first!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 35) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.BLAZE_POWDER) && (itemstack.getCount() >= 20) ) {
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Wow! This stuff feels warm!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(36);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Now we need 20 Blaze Powder! That sounds hard to find..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 36) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.LAPIS_LAZULI) && (itemstack.getCount() >= 40) ) {
                        itemstack.shrink(40);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "You did it! I think this will be enough."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(37);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Can you get me 40 Lapis Lazuli? Aelymore said it's great for enchanting things!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 37) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.ROSE_BUSH) && (itemstack.getCount() >= 20) ) {
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "Thank you! That was the last thing we needed!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(38);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "The last thing I need are 20 Rose Bushes so I can use their seeds!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 38) {
                    taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I'll plant the seeds and cast the spell, but it will take a little while for them to grow. Come back a little later!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest20");
                    this.setQuestNumber(39);
                }
                else if (questNumber == 39) {
                    if (playerIn.getTags().contains("Quest21")) {
                        playerIn.removeTag("Quest21");
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "They're done growing! Here you go!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        for (int i=1; i<=16; i++) {
                            this.entityDropItem(ModItems.TWILIGHT_ROSE_ITEM.get());
                        }
                        this.setQuestNumber(40);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "They are still growing! Come back a little later."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 40) {
                    if (playerIn.getTags().contains("???")) {
                        playerIn.removeTag("???");
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(41);
                    } else {
                        taskMessage = TextFormatting.LIGHT_PURPLE + "[Princess Elizabeth] " + TextFormatting.WHITE + "I still haven't heard from Periculus..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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
