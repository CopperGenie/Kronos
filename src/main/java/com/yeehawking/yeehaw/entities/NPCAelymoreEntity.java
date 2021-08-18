package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
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
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
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

public class NPCAelymoreEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCAelymoreEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCAelymoreEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCAelymoreEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCAelymoreEntity.NPCData) {
            i = ((NPCAelymoreEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCAelymoreEntity.NPCData(i); }
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
                this.setQuestNumber(41);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Do you believe in legends, sir?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Allow me to tell you a story, if you wish to hear it. But I advise that we seek shelter before night falls upon us. Hold out your torch and I will follow close behind."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "There once was a god with unlimited creative power, who constructed this world, and many others, from cosmic dust. He imagined the earth, the trees. The ocean, the breeze. It was a beautiful world and it made the god very glad, but he still longed for more. He wanted a son--a legacy.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }

                else if (questNumber == 2) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "The god knew that his service to the universe was complete and a new creative mind must be born. So the god used all of his power, destroying himself, to birth a new god with a new set of creative eyes to continue his legacy.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }

                else if (questNumber == 3) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "This son called himself Kronos. He had the power of his father, but Kronos was different. He felt his father had destroyed himself because there was nothing left to create. He felt that the reason his father had created him was to do what his father could only do to himself: destroy.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }

                else if (questNumber == 4) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Kronos began destroying entire worlds--killing their people, immolating their lands, corrupting their core. He summoned horrifying monsters to terrorize the worlds he had yet to destroy.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "But there was one thing Kronos did not know. His father had created certain powerful humans, the Godborn, strong enough to defeat a god. If a time came when the god no longer wished to create, but to destroy, the Godborn would fulfil their duty and take away the god's power.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(5);
                }

                else if (questNumber == 5) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Many men and women through history, all Godborn, have made effort to defeat Kronos. Some even made it far enough to face him, so close to fulfilling their duty. But all were unprepared for Kronos and his unimaginable wrath. All were slain. There is only one Godborn left in the entire world.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(6);
                }

                else if (questNumber == 6) {
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Yes, it is you. You are the world's last hope in ending the destructive path of this evil god. If you fail, the universe will fall into ruin and be lost to the void from which it came. But if you succeed, peace will reign once more upon this land. The entire universe will offer you its eternal gratitude.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Man] " + TextFormatting.WHITE + "Will you accept your responsibility?";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(7);
                }

                else if (questNumber == 7) {
                    for (int i = 1; i <= 150; i++) {
                        this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXRandom(1.5D), this.getPosYRandom(), this.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    playerIn.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.8f, 1f);
                    playerIn.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_TWINKLE, 0.8f, 1f);
                    this.setTexture(1);

                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Excellent! Just as I had hoped. I am Aelymore, advisor to King Matheus of Evenwood. You two will meet in time.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I've come a great distance to find you, Godborn, and to guide you. With my aid, I hope to ensure your success in defeating the evil Kronos.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(8);
                }

                else if (questNumber == 8) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "First thing's first. You must learn to walk between worlds. Kronos inhabits an unknown realm, and he cannot be reached without a special key. In fact, each dimension requires a World Key in order to access it. The key will allow you to walk into one world, and when you are ready, it will return you to this one.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "You must never lose a world's key, lest you be trapped within. Then you will need to find other means of escape. Here, take one of my own keys. Enter the world and return, but be safe and do not lose the key!";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.entityDropItem(ModItems.WORLD_KEY_ERODED_DESERT.get());

                    this.setQuestNumber(9);
                }

                else if (questNumber == 9) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Easy enough, right? Now give it back.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(10);
                }

                else if (questNumber == 10) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.WORLD_KEY_ERODED_DESERT.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Thank you. Right then, let us continue.";
                    }
                    else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Fine, you keep it. It may be of use to us later, so hold on to it!";
                    }

                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(11);

                }

                else if (questNumber == 11) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Now that you've become acquainted with worldwalking, it is time for you to begin preparing for your journey. Return to me when you have acquired combat equipment. In the meantime, I will be planning our next course of action. ";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(12);
                }

                else if (questNumber == 12) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) || ( (playerIn.getHeldItemMainhand().getItem() == Items.IRON_SWORD) && ( (playerIn.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == Items.IRON_HELMET) || (playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.IRON_CHESTPLATE) || (playerIn.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == Items.IRON_LEGGINGS) || (playerIn.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == Items.IRON_BOOTS) || (playerIn.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == Items.DIAMOND_HELMET) || (playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.DIAMOND_CHESTPLATE) || (playerIn.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == Items.DIAMOND_LEGGINGS) || (playerIn.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == Items.DIAMOND_BOOTS) ) ) ) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Alas, not what I had hoped for. But it will do for now.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I have been thinking of our next move. As I mentioned, Kronos resides in an unreachable realm and I do not yet know of a way in. But there is a great historian, Wynnbrim, who can be found in his library far to the east in the Skypiercers. Of all the great wisemen in our time, he is truly the most knowledgeable. He may know a way into that wretched place.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(13);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I've seen slimes more fearsome than you! You're not worth the effort it would take Kronos to strike you down. Go get some better gear.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    }
                }

                else if (questNumber == 13) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "But the library is far, far away from here. We could walk, of course, and perish of old age before we arrived. Or we could use the power of the keys.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "There is a dimension called Celiria, within which you can travel only a small distance and arrive many lengths more away from where you started in the overworld. Long ago, many wizards such as myself would use this dimension to avoid travelling extreme distances, allowing them to get where they needed to go and still be home in time for cake and tea.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(14);
                }

                else if (questNumber == 14) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Alas, many of these keys were lost to time and only a few now remain. Centuries ago, the wizards were given them by the great keycrafter, Archmagus Clavius. It saddens me dearly to say that Clavius died with many other great heroes in the war against Kronos's denizens.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "The knowledge of keycraft has been lost, perhaps forever.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(15);
                }

                else if (questNumber == 15) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I recently possessed a key to Celiria, but I lent it to a fellow wizard, Tenebrik, so that he may fulfill a task of his own. You must find Tenebrik if we are to continue our journey. He spoke of venturing into the Nether in search of the fabled Netherite. But I do not possess a key to that realm, so you must find another means of entry.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(16);
                }

                else if (questNumber == 16) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Here, take this. It will aid you in that fiery abyss.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    ItemStack activeItem = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_FIRE_RESISTANCE);
                    this.entityDropItem(activeItem);

                    playerIn.addTag("SummonTenebrik");
                    this.setQuestNumber(17);
                }

                else if (questNumber == 17) {
                    if (playerIn.getTags().contains("PeragonTrapped")) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "What? Tenebrik is attempting to summon a demon?! This is grave news. Tenebrik has always kept his work hidden from others, which I found suspicious. Now it seems you have discovered his true intentions.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "And to trap another human in crystal for use as sacrifice...that is beyond cruel. He has truly lost all morality. We must act now. First, we must rescue Sir Peragon.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        playerIn.removeTag("PeragonTrapped");
                        this.setQuestNumber(18);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Find Tenebrik and return to me with the key to Celiria. Try waving your torch in the Nether to call Tenebrik to you. He may spot you through the darkness.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                }

                else if (questNumber == 18) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Tenebrik is a powerful mage. Our best chance is to charm him with Enchanted Dust, and convince him to hand over Peragon's crystal.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "As the Godborn, you must learn to create and use complex tools. With 5 Lapis Lazuli and 4 Glowstone Dust, you may craft Enchanted Dust. Use this dust to charm Tenebrik and retrieve the crystal.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(19);
                }

                else if (questNumber == 19) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.CLOUDSTONE.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);

                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Excellent work! Now, allow me to free our friend here.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        if (playerIn.getEntityWorld() instanceof ServerWorld) {
                            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                            ModEntityType.NPC_PERAGON.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0), SpawnReason.EVENT, false, true);
                        }
                        this.setQuestNumber(20);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "With 5 Lapis Lazuli and 4 Glowstone Dust, you may craft Enchanted Dust. Use this dust to charm Tenebrik and retrieve the crystal.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                }

                else if (questNumber == 20) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Sir Peragon is free, but Tenebrik must still be stopped. I would suggest you play along with his plan for now. When he realizes he is missing the crystal, he will become enraged and more vulnerable.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I understand he tasked you to bring him Understeel. This is a rare metal found in the depths of the Cursed Forest.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(21);
                }

                else if (questNumber == 21) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I believe our new ally possesses a key to that realm. Perhaps if you reinstate him with his weapon, he may lend you the key.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Return to me when you have defeated Tenebrik. Be careful, Godborn, and good luck.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(22);
                }

                else if (questNumber == 22) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.WORLD_KEY_CELIRIA.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "You may have promise after all, Godborn. It is sad to see a fellow wizard perish, but better to fall by blade than to fall into darkness."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(23);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Defeat Tenebrik and return with the key to Celiria, so that we may continue our journey."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                }

                else if (questNumber == 23) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Now that we have the key to Celiria, we can make our way to Wynnbrim's library. Let us hope he has the answers we seek. We must find a way into--"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Mysterious Voice] " + TextFormatting.WHITE + "Well, well, well! If it isn't the legendary " + TextFormatting.LIGHT_PURPLE + "GODBORN! "; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.removeTag("GotCeliriaKey");
                    this.setQuestNumber(24);
                }

                else if (questNumber == 24) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Good God! Who said that? Show yourself!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Oh, Aelymore. Don't you recognize an old friend when you hear him? It is I! The amazing, the mysterious, Phantasmus!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.NPC_PHANTASMUS.get().spawn(serverWorld, null, null, this.getPosition().add(0, 0, 0), SpawnReason.EVENT, false, true);
                    }
                    this.setQuestNumber(25);
                }

                else if (questNumber == 25) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Oh great, just what I need right now. Begone, trickster! The Godborn has important work to do."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Come now Aelymore, is this how you greet all your friends? I was just curious to see if this last Dogborn was really all he was cracked up to be."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(26);
                }

                else if ( (questNumber == 26) && (playerIn.getTags().contains("Quest1")) ) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "This is your final warning, Phantasmus!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Olobole bo obolebo."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.removeTag("Quest1");
                    this.setQuestNumber(27);
                }

                else if (questNumber == 27) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Begone!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.DARK_PURPLE + "[Phantasmus] " + TextFormatting.WHITE + "Bobo lebolobelo... obobelo."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.addTag("Quest2");
                    this.setQuestNumber(28);
                }

                else if ( (questNumber == 28) && (!playerIn.getTags().contains("Quest2"))) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Thank the heavens! The devil is gone!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "That 'wizard' has a knack for trouble. Decades ago, he was banished from the Order of the Magi for summoning 20,000 chickens in the alchemy tower. Stay away from that man. He is nothing but trouble!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("GotCeliriaKey");

                    this.setQuestNumber(29);
                }

                else if (questNumber == 29) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Now, where were we? Right! We were on our way to the great library."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "No time to lose! Let us...go... Hold on a minute... WHAT?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(30);
                }

                else if (questNumber == 30) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "The key is gone! Phantasmus must have stolen it!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(31);
                }
                else if (questNumber == 31) {
                    taskMessage = TextFormatting.GOLD + "[Aelymore] " + TextFormatting.WHITE + "RAAAAAAHHHH!!!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, this.getPosition().add(2, 0, 0), SpawnReason.EVENT, false, true);
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, this.getPosition().add(-2, 0, 0), SpawnReason.EVENT, false, true);
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, this.getPosition().add(0, 0, 2), SpawnReason.EVENT, false, true);
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, this.getPosition().add(0, 0, -2), SpawnReason.EVENT, false, true);
                    }
                    this.setQuestNumber(32);
                }
                else if (questNumber == 32) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + ". . ."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(33);
                }
                else if (questNumber == 33) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "What does he think he's doing? Saving you? We're all doomed if Kronos remains unchallenged. You truly are our only hope, Godborn."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest4");
                    this.setQuestNumber(34);
                }
                else if (questNumber == 34) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.WORLD_KEY_CELIRIA.get()) {
                        itemstack.shrink(1);
                        this.entityDropItem(ModItems.WORLD_KEY_CELIRIA.get());
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "You have the key to Celiria! But how did you retrieve it?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(35);

                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Leave me to my thoughts, please. I must figure out how to track that scoundrel down."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 35) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "So he's come to his senses, has he? Good. Now we may carry on wih out journey."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Now that you have the key again, I will show you how to find Wynnbrim's library."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(36);
                }
                else if (questNumber == 36) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "One step in Celiria is equivalent to one hundred paces in the overworld."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "With this tool, you may turn a ten-day journey into a short walk."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(37);
                }
                else if (questNumber == 37) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "To find the library, head East to coordinates (40,000, 0) in the overworld. Then invoke your Skypiercers world key, and you should be able to spot the library."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "In Celiria, these coordinates would be (400, 0)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(38);
                }
                else if (questNumber == 38) {
                    if (playerIn.getTags().contains("Quest5")) {
                        playerIn.removeTag("Quest5");
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "The Godborn returns! Wynnbrim has told you the full procedure to follow for entering Kronos's realm? Astounding!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(39);

                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Go to Wynnbrim's library at (40,000, 0) in the Skypiercers and seek his knowledge. We need to find a way into Kronos's realm."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Follow the sage's instruction, then return to me with your findings."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 39) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I see that you require Theosian Crystals...this is tricky. Against my advice, the king has banned all entry into Theos. Why? I am uncertain. He is hiding something from me."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "The king is a stubborn man, but he also understands the importance of the Godborn and their duty. We shall speak to him and see if he holds himself accountable to that same duty. Let us hope he sees sense and grants us access to the keys."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(40);
                }
                else if (questNumber == 40) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Ah, I see you have been gifted a teleport gem. These are very difficult to craft, even for the most skilled wizards. Luckily, I know someone who specializes in gem enchantment."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I present to you: Jafir, the most masterful enchanter in the south!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.NPC_JAFIR.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true); }
                    this.setQuestNumber(41);
                }
                else if (questNumber == 41) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Right, back to business. I will entrust you with my spare key to The Kingdom. King Matheus resides in his castle at the heart of Evenwood. Travel to the coordinates (200, 100). You shant miss its great walls."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Tell the king I sent you to ask permission to enter Theos in order to continue our journey. This key to his kingdom will serve as proof of my trust in you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.WORLD_KEY_THE_KINGDOM.get());
                    playerIn.addTag("GotTheKingdomKey");
                    this.setQuestNumber(42);
                }
                else if (questNumber == 42) {
                    if (playerIn.getTags().contains("Quest6")) {
                        playerIn.removeTag("Quest6");
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "The king sent you away, hasn't he? Oh well, it was worth a try. As I said, he is quite a stubborn man."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Since it seems that the keys are critical to our success...I suppose we must get them ourselves. We need to break into the vault."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(43);

                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Ask King Matheus for the keys to Theos. His castle lies in The Kingdom at the coordinates (200, 100)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 43) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "This was my last resort but it's a path we must take. The vault will surely be guarded, so I suggest you prepare yourself."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(44);
                }
                else if (questNumber == 44) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Ready? Alright then, here is the plan. You must do this on your own, so as to attract less attention. First, I will give you this gem so you can teleport to the king's castle. Once in The Kingdom, you must travel to the coordinates (400, 200). There you will find the vault."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.TELEPORT_GEM_THE_CASTLE.get());
                    playerIn.addTag("GotTheCastleGem");
                    this.setQuestNumber(45);
                }
                else if (questNumber == 45) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Once you locate the vault, you must be extremely stealthy. The king guards his valuables well, so there will be no shortage of loyal knights."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Take this potion. It will help you avoid them. Once at the vault door, you will see a block of Magic Gold. Touch it with an emerald, and the door will open."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    ItemStack activeItem = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_INVISIBILITY);
                    this.entityDropItem(activeItem);
                    this.setQuestNumber(46);
                }
                else if (questNumber == 46) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "There should be no guards once you enter the vault. However, there is a second door leading to a more secure section of the vault within. I assume the keys will be held within this inner repository."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "To open the second door, you must place four emerald blocks in the empty slots in the wall near the door. There should be another magic gold block on the floor. Right click it with the emerald, and the second door should open."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest7");
                    playerIn.addTag("Quest8");
                    this.setQuestNumber(47);
                }
                else if (questNumber == 47) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Once in, take the keys and return to me. Godspeed!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(48);
                }
                else if (questNumber == 48) {
                    if (playerIn.getTags().contains("Quest9")) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "You have succeeded! Excellent work as always, Godborn. I hope you left the rest of the king's valuables where they were...we don't want him to suspect anything."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("Quest9");
                        this.setQuestNumber(49);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Allow me to give you a rundown of the mission. Locate the vault at (400, 200). Use an emerald on the Magic Gold block to open the first door. Once inside, place the four Emerald Blocks in their wall slots, then use an emerald on the second Magic Gold block to open the inner repository. Take the keys to Theos and return to me."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 49) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I suggest you make haste to gather the Theosian Crystals for Wynnbrim. From what I have studied, these crystals grow rarely in the heavens of Theos. I am unfamiliar with those lands, so I am unable to provide any more help. Be prepared for anything!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Here, these may be of use in the heavens."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.entityDropItem(ModItems.SCROLL_OF_BRIDGING.get()); this.entityDropItem(ModItems.SCROLL_OF_BRIDGING.get()); this.entityDropItem(ModItems.SCROLL_OF_BRIDGING.get());
                    this.setQuestNumber(50);
                }
                else if (questNumber == 50) {
                    if (playerIn.getTags().contains("Quest11")) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Godborn, hello! How is the potion coming along? A master summoner you say? Well, I suppose I know someone who can help. I believe it is time you visit Phantasmus once again. Let's hope he hasn't had a change of heart since your last encounter with him."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("Quest11");
                        playerIn.addTag("Quest12");
                        this.setQuestNumber(51);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Return when you need my help again."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 51) {
                    if (playerIn.getTags().contains("Quest20")) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Godborn, I have been experimenting with potioncraft as of late. I believe I have created a potion that you will find useful. If you could get me the ingredients, I can brew it for you!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("Quest20");
                        this.setQuestNumber(52);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Good luck on your quest, Godborn."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 52) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I knew you would be interested! It is a fairly simple recipe. I require 1 Glistering Melon Slice, 1 Poisonous Potato, and 20 Gunpowder. I have plenty of bottles, so just fetch these ingredients and I'll have it ready in no time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(53);
                }
                else if (questNumber == 53) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.GLISTERING_MELON_SLICE) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Much appreciated, Godborn. Next, I need 1 Poisonous Potato."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(54);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "First, give me 1 Glistering Melon Slice."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 54) {
                    if (playerIn.getHeldItemMainhand().getItem() == Items.POISONOUS_POTATO) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Thank you kindly. Quite smelly, isn't it? Lastly, I require 20 Gunpowder."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(55);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Now I need 1 Poisonous Potato."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 55) {
                    if ((playerIn.getHeldItemMainhand().getItem() == Items.GUNPOWDER) && (itemstack.getCount() >= 20)) {
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Excellent! Give me a moment while the potion brews."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(56);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Lastly, I require 20 Gunpowder."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 56) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Almost finished..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(57);
                }
                else if (questNumber == 57) {
                    taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "Ah, here we are! I call it the XXXX."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(58);
                    playerIn.addTag("Quest21");

                    for (int i = 1; i <= 3; i++) {
                        ItemStack activeItem = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LONG_FIRE_RESISTANCE);
                        // TODO: 7/16/2021 Custom effect
                        this.entityDropItem(activeItem);
                    }
                }
                else if (questNumber == 58) {
                    if (playerIn.getTags().contains("???")) {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        playerIn.removeTag("???");
                        this.setQuestNumber(59);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Aelymore] " + TextFormatting.WHITE + "I hope you put my potions to good use!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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
