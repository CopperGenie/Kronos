package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Random;

public class NPCOswirEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCOswirEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCOswirEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCOswirEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCOswirEntity.NPCData) {
            i = ((NPCOswirEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCOswirEntity.NPCData(i); }
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
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(15);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber(); if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Hello! There has been speak of a great rising hero in our midst. Is it you? The last Godborn?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "A pleasure to finally meet you. I am Oswir! The Keyfinder, as some call me. At your service, noble one."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }

                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "I have spent decades honing my craft, specializing in location and retrieval spells. World Keys are a valuable resource. Even more so now that the knowledge of keycraft has been lost to us."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "If ever you find yourself without a key, my friend, call upon me and I shall find it! Of course, my spells do require some material ingredients to cast properly. Magic is like a trade, you see?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(2);
                }

                else if (questNumber == 2) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.GOLD_INGOT) && (itemstack.getCount() >= 30) ) {
                        itemstack.shrink(30);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_ERODED_DESERT.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == Items.LAPIS_LAZULI) && (itemstack.getCount() >= 60) ) {
                        itemstack.shrink(60);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_CURSED_FOREST.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == Items.DIAMOND) && (itemstack.getCount() >= 5) && (playerIn.getTags().contains("GotCeliriaKey")) ) {
                        itemstack.shrink(5);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_CELIRIA.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == Items.OBSIDIAN) && (itemstack.getCount() >= 20) && (playerIn.getTags().contains("GotNowhereKey")) ) {
                        itemstack.shrink(20);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_NOWHERE.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.OPAL.get()) && (itemstack.getCount() >= 30) && (playerIn.getTags().contains("GotSkypiercersKey")) ) {
                        itemstack.shrink(30);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_SKYPIERCERS.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == Items.QUARTZ) && (itemstack.getCount() >= 60) && (playerIn.getTags().contains("GotRealmOfPhantasmusKey")) ) {
                        itemstack.shrink(60);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_REALM_OF_PHANTASMUS.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.CUBIC_ROSEUM.get()) && (itemstack.getCount() >= 3) && (playerIn.getTags().contains("GotTheKingdomKey")) ) {
                        itemstack.shrink(3);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_THE_KINGDOM.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.THEOSIAN_CRYSTAL_ITEM.get()) && (itemstack.getCount() >= 3) && (playerIn.getTags().contains("GotTheosKey")) ) {
                        itemstack.shrink(3);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_THEOS.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == Items.WHITE_WOOL) && (itemstack.getCount() >= 40) && (playerIn.getTags().contains("GotTheosHeavensKey")) ) {
                        itemstack.shrink(40);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_THEOS_HEAVENS.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.ETHERIUM.get()) && (playerIn.getTags().contains("GotPilotRedSunKey")) ) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        this.entityDropItem(ModItems.WORLD_KEY_PILOT_RED_SUN.get());
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here you are, noble one. Be careful not to lose it this time!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                    else {
                        taskMessage = TextFormatting.YELLOW + "[Oswir the Keyfinder] " + TextFormatting.WHITE + "Here are the keys I can currently retrieve for you:"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.GOLD + "Eroded Desert -- 30 Gold Ingots"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.GOLD + "Cursed Forest -- 60 Lapis Lazuli"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        if (playerIn.getTags().contains("GotCeliriaKey")) {
                            taskMessage = TextFormatting.GOLD + "Celiria -- 5 Diamonds"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotNowhereKey")) {
                            taskMessage = TextFormatting.GOLD + "Nowhere -- 20 Obsidian"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotSkypiercersKey")) {
                            taskMessage = TextFormatting.GOLD + "Skypiercers -- 30 Opals"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotRealmOfPhantasmusKey")) {
                            taskMessage = TextFormatting.GOLD + "Realm of Phantasmus -- 60 Quartz"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotTheKingdomKey")) {
                            taskMessage = TextFormatting.GOLD + "The Kingdom -- 3 Cubic Roseum"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotTheosKey")) {
                            taskMessage = TextFormatting.GOLD + "Theos -- 3 Theosian Crystals"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotTheosHeavensKey")) {
                            taskMessage = TextFormatting.GOLD + "Theos Heavens -- 40 White Wool"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
                        if (playerIn.getTags().contains("GotPilotRedSunKey")) {
                            taskMessage = TextFormatting.GOLD + "PilotRedSun -- 1 Etherium"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        }
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
