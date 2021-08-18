package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
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
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;
import java.util.Random;

public class NPCTenebrikEntity extends AnimalEntity {

    public NPCTenebrikEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100000.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.25f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public void livingTick() {
        if (this.getTags().contains("livid")) {
            this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
            this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        }

        super.livingTick();
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 300;
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

    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCTenebrikEntity.class, DataSerializers.VARINT);

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
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(8);
            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Hmm, what? Who are you? How did you get here?";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Ah, a nether portal. What primitive technology. Well, if you're done wasting my time, I have work to do. Scurry off.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Why are you still here?! Fine! Since you're so persistent, go find me 5 Nether Warts.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Why do I need them? Well that's rightfully none of your business now, is it? Fetch me the warts and maybe I'll tell you.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }

                else if (questNumber == 2) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.NETHER_WART) && (itemstack.getCount() >= 5) ) {
                        itemstack.shrink(5);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "What is this?! These warts are contaminated! Did you put them in your backpack with the rest of your junk? AGH! I can't stand adventurers!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Yes, they are still acceptable to be used in the ritual. Now I must obtain the final ingredient, then the human sacrifice will commence!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(3);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Get out of my sight! And get me those 5 Nether Warts!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        if (playerIn.getHealth() > 1.0F) { playerIn.attackEntityFrom(DamageSource.MAGIC, 1.0F); }
                    }
                }

                else if (questNumber == 3) {
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "No, it isn't you. Don't worry.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }

                else if (questNumber == 4) {
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "What ritual, you ask? Well why do you think I'm here, if not to summon a great demon from the depths of this wretched hell? Once I complete the summoning, I will have under my power one of the greatest creatures in the realm. No one will come between me and my objectives.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(5);
                }

                else if (questNumber == 5) {
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "I have trapped the fabled Sir Peragon within a crystal of pure cloudstone. Once summoned, the demon will consume his soul and will thenceforth be under my control! Helpless Peragon is, and helpful he will soon become.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "What's in it for you? HA! AHAHAHAHAHAHA!! What's in it for you, little rat, is your life! Come back to me with the final ingredient, 1 Understeel, and I will spare you from the demon's wrath.";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.addTag("PeragonTrapped");
                    this.setQuestNumber(6);
                }

                else if (questNumber == 6) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.ENCHANTED_DUST.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Huh, what? You want the Peragon Crystal? Fine, take it.";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.entityDropItem(ModItems.CLOUDSTONE.get()); // TODO: 6/21/2021 Add NBT if possible

                        this.setQuestNumber(7);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Why do you tarry? Fetch the Understeel!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        playerIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
                    }
                }

                else if (questNumber == 7) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.UNDERSTEEL.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Ah, the Understeel. After years of preparation... I will finally be recognized for my efforts! The Order of the Magi will bow before me!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Wait--where is the crystal? You! You have stolen from me, wretched rat!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(8);
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Tenebrik] " + TextFormatting.WHITE + "Why do you tarry? Fetch the Understeel!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        playerIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
                    }
                }

                else if (questNumber == 8) {
                    taskMessage = TextFormatting.RED + "[Tenebrik] " + TextFormatting.WHITE + "It's time you learned your place in this world! Say your prayers. It's time for you to die!";
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    playerIn.addTag("GotCeliriaKey");
                    this.addTag("livid");
                    this.setHealth(200);

                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        ModEntityType.BEHOLDER.get().spawn(serverWorld, null, null, this.getPosition().add(2, 1, 0), SpawnReason.EVENT, false, true);
                        ModEntityType.BEHOLDER.get().spawn(serverWorld, null, null, this.getPosition().add(-2, 1, 0), SpawnReason.EVENT, false, true);
                    }

                    this.setQuestNumber(9);
                }
            }
        }

        return ActionResultType.PASS;
    }

    public boolean isImmuneToFire() {
        return true;
    }

}
