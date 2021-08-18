package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.rcon.IServer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NPCSlayerEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCSlayerEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0d);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3d));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3d, TEMPTATION_ITEMS, false));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> TASK_ITEM_RAND = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<String> FIRST_NAME = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> LAST_NAME = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> TITLE = EntityDataManager.createKey(NPCSlayerEntity.class, DataSerializers.STRING);

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TEXTURE, 0);
        this.dataManager.register(QUEST_NUMBER, 0);
        this.dataManager.register(TASK_ITEM_RAND, 0);
        this.dataManager.register(FIRST_NAME, "Unknown");
        this.dataManager.register(LAST_NAME, "Unknown");
        this.dataManager.register(TITLE, "Unknown");
    }

    public static class NPCData extends AgeableEntity.AgeableData {
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
        compound.putInt("TaskItemRand", this.getTaskItemRand());
        compound.putString("FirstName", this.getFirstName());
        compound.putString("LastName", this.getLastName());
        compound.putString("Title", this.getTitle());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setTexture(compound.getInt("Texture"));
        this.setQuestNumber(compound.getInt("QuestNumber"));
        this.setTaskItemRand(compound.getInt("TaskItemRand"));
        this.setFirstName(compound.getString("FirstName"));
        this.setLastName(compound.getString("LastName"));
        this.setTitle(compound.getString("Title"));
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

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = this.rand.nextInt(4); // Number of textures to pick from
        if (spawnDataIn instanceof NPCSlayerEntity.NPCData) {
            i = ((NPCSlayerEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCSlayerEntity.NPCData(i); }
        this.setTexture(i);

        int j = this.rand.nextInt(9);
        this.setQuestNumber(j);

        int k = this.rand.nextInt(3);
        this.setTaskItemRand(k);

        List<String> firstNames = Arrays.asList("Sir ", "Bishop ", "Count ", "Baron ", "Doctor ", "Lord ", "Master ");
        List<String> lastNames = Arrays.asList("John", "Abraham", "Henry", "Bartholomew", "Severus", "Thomas", "William", "Richard", "Charles", "Robert", "Alexander", "Benjamin", "Jack", "George", "James", "Martin", "Edmund", "Christopher", "Ambrose", "Stephen", "Phillip", "Peter", "Morgan", "Paul", "David", "Brian", "Vincent", "Allen", "Baker", "Bennett", "Brown", "Carter", "Clark", "Cook", "Davies", "Edwards", "Evans", "Green", "Griffiths", "Hall", "Harris", "Hill", "Hughes", "Jackson", "Johnson", "Jones", "King", "Lee", "Lewis", "Moore", "Morgan", "Morris", "Parker", "Roberts", "Robinson", "Shaw", "Smith", "Thompson", "Turner", "Walker", "Ward", "Watson", "White", "Williams", "Wilson", "Wood", "Wright");
        List<String> titles = Arrays.asList(" the Red", " the Black", " the Brown", " the Blue", " the Champion", " the Annihilator", " One-eye", " the Valiant", " the Jester", " the Sleepy", " the Grumpy", " the Mad", " I", " II", " III", " IV", " V", " VI", " VII", " VIII", " the Outlaw", " the Wise", ", Defender of the Faith");
        String firstName = firstNames.get(rand.nextInt(firstNames.size()));
        String lastName = lastNames.get(rand.nextInt(lastNames.size()));
        String title = titles.get(rand.nextInt(titles.size()));
        if (rand.nextInt(5) != 0) { firstName = ""; }
        if (rand.nextInt(5) != 0) { title = ""; }
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setTitle(title);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }



    public void setTexture(int i) { this.dataManager.set(TEXTURE, i); }
    public int getTexture() { return this.dataManager.get(TEXTURE); }

    public void setQuestNumber(int i) { this.dataManager.set(QUEST_NUMBER, i); }
    public int getQuestNumber() { return this.dataManager.get(QUEST_NUMBER); }

    public void setTaskItemRand(int i) { this.dataManager.set(TASK_ITEM_RAND, i); }
    public int getTaskItemRand() { return this.dataManager.get(TASK_ITEM_RAND); }

    public void setFirstName(String i) { this.dataManager.set(FIRST_NAME, i); }
    public String getFirstName() { return this.dataManager.get(FIRST_NAME); }

    public void setLastName(String i) { this.dataManager.set(LAST_NAME, i); }
    public String getLastName() { return this.dataManager.get(LAST_NAME); }

    public void setTitle(String i) { this.dataManager.set(TITLE, i); }
    public String getTitle() { return this.dataManager.get(TITLE); }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            String taskMessage = "Unknown";
            String rewardMessage = "Unknown";
            int questNumber = this.getQuestNumber();
            Random rand = new Random();
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            float itemCount = itemstack.getCount();
            String firstName = this.getFirstName();
            String lastName = this.getLastName();
            String title = this.getTitle();
            int taskItemRand = this.getTaskItemRand();
            Item taskItem = null;
            Item rewardItem = null;
            int taskItemCount = 0;
            int rewardItemCount = 0;

            if (questNumber == 0) {
                taskItem = Items.BONE;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 25 + 5 * taskItemRand;
                rewardItemCount = taskItemCount / 10;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Remains long dead have risen once again! Smite these clattering aberrations--the foul Skeletons--and return to me with " + taskItemCount + " Bones!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. There might be hope yet for this world... Here, something for your endeavors.";
            }

            if (questNumber == 1) {
                taskItem = Items.ROTTEN_FLESH;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 40 + 5 * taskItemRand;
                rewardItemCount = taskItemCount / 15;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "The stench of rotting flesh is in the air. Find the lurking Zombies that roam these lands and return to me with " + taskItemCount + " Rotten Flesh!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Many thanks from my nostrils, friend. Here, something for your endeavors.";
            }

            if (questNumber == 2) {
                taskItem = Items.SPIDER_EYE;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 15 + 5 * taskItemRand;
                rewardItemCount = taskItemCount / 7;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Giant spiders are brooding in the night. Cut down their numbers and return to me with " + taskItemCount + " Spider Eyes!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. The creeping arachnids multiply at great speeds, but you have controlled their population for now. Here, something for your endeavors.";
            }

            if (questNumber == 3) {
                taskItem = ModItems.TOAD_SPORES.get();
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 15 + 5 * taskItemRand;
                rewardItemCount = taskItemCount / 7;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Last night I beheld a walking fungus rambling through the forest. Undoubtedly evil... Slay these creatures and return to me with " + taskItemCount + " Toad Spores!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. It is unnatural for a mushroom to get that big! Here, something for your endeavors.";
            }

            if (questNumber == 4) {
                taskItem = Items.PHANTOM_MEMBRANE;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 8 + 3 * taskItemRand;
                rewardItemCount = taskItemCount / 4;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Phantoms...great aerial hunters of the night. Many wanderers have fallen prey to their talons. Slay these predators and return to me with  " + taskItemCount + " Phantom Membranes!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. These lands are now safer for travellers roaming past dark. Here, something for your endeavors.";
            }

            if (questNumber == 5) {
                taskItem = Items.ENDER_PEARL;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 10 + 3 * taskItemRand;
                rewardItemCount = taskItemCount / 5;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Slender, black figures from another world. Endermen are out of their place in this realm. Smite them back to their home and return to me with " + taskItemCount + " Ender Pearls!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. Such unfamiliar creatures do not belong in our world. Here, something for your endeavors.";
            }

            if (questNumber == 6) {
                taskItem = ModItems.ENCHANTED_DUST.get();
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 15 + 5 * taskItemRand;
                rewardItemCount = taskItemCount / 7;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + "Gnomes are not to be underestimated. They possess the magic of the fae and have unknown powers. Return to me with " + taskItemCount + " Enchanted Dust so I may study them.";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Thank you, friend. Who can say what secrets are hidden in this powerful substance. Here, something for your endeavors.";
            }

            if (questNumber == 7) {
                taskItem = Items.WITHER_SKELETON_SKULL;
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 1;
                rewardItemCount = 3;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + " I have heard rumors of a powerful, smoldering skeleton deep in the netherworld. Kill the Wither Skeleton and return to me with its skull!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "Ah... It's still warm. The skeleton must have been quite a challenge. Here, something for your endeavors.";
            }

            if (questNumber == 8) {
                taskItem = ModItems.GIANTS_BRANCH.get();
                List<Item> rewardItems = Arrays.asList(Items.GOLD_INGOT, Items.EMERALD, Items.DIAMOND, Items.GHAST_TEAR, ModItems.UNDERSTEEL.get(), ModItems.CUBIC_ROSEUM.get(), ModItems.HEXAGONAL_ROSEUM.get());
                rewardItem = rewardItems.get(rand.nextInt(rewardItems.size()));
                taskItemCount = 1;
                rewardItemCount = 2;
                taskMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.BLUE + " Thundering footsteps beyond the mountains! Giants roam eternal through the Skypiercers, pulling trees from the ground with their ancient limbs. Return to me with a Giant's Branch, if you can burden it!";
                rewardMessage = TextFormatting.YELLOW + "[" + firstName + lastName + title + "] " + TextFormatting.GREEN + "You have returned! A truly inspiring feat. Here, something for your endeavors.";
            }

            // If the player has quest items in hand
            if ((playerIn.getHeldItemMainhand().getItem() == taskItem) && (itemCount >= taskItemCount)) {
                itemstack.shrink(taskItemCount);
                playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                this.setQuestNumber(rand.nextInt(9));
                this.setTaskItemRand(rand.nextInt(3));

                for (int i = rewardItemCount; i >= 1; i--) {
                    this.entityDropItem(rewardItem);
                }

                if (!this.getEntityWorld().isRemote) {
                    playerIn.sendMessage(new StringTextComponent(rewardMessage), playerIn.getUniqueID());
                }
            }

            // If not, displays quest prompt
            else {
                if (!this.getEntityWorld().isRemote) {
                    playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID());
                }
            }
        }

        return ActionResultType.PASS;
    }

}
