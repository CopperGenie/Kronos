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

public class NPCJafirEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.TORCH);

    public NPCJafirEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCJafirEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCJafirEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCJafirEntity.NPCData) {
            i = ((NPCJafirEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCJafirEntity.NPCData(i); }
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
                    taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "Hello, Godborn. Aelymore has told me much about you. Destined for greatness, I am sure."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "As you know, I am a master of gem enchantment. If you bring me a crystal fragment from Theos, I can enchant it to teleport you to any destination to which you have previously ventured."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                if (questNumber == 1) {
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.CRYSTAL_SHARD.get()) {
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "Ah, a fine crystal shard. Where would you like to go with this gem?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        itemstack.shrink(1);
                        this.setQuestNumber(2);
                    }
                    else if (playerIn.getHeldItemMainhand().getItem() == ModItems.THEOSIAN_CRYSTAL_ITEM.get()) {
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "My friend, that crystal is much too large to be turned into a teleport gem! Try breaking it into smaller pieces."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "Bring me a Crystal Shard so that I may enchant it for you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 2) {
                    if ( (playerIn.getHeldItemMainhand().getItem() == Items.LAPIS_LAZULI) && (itemstack.getCount() >= 40) ) {
                        itemstack.shrink(40);
                        playerIn.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1f, 0.5f);
                        this.entityDropItem(ModItems.TELEPORT_GEM_THE_LIBRARY.get());
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "As you wish. Here is your new gem!";
                        if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(1);
                    }
                    else if ( (playerIn.getHeldItemMainhand().getItem() == ModItems.CUBIC_ROSEUM.get()) && (itemstack.getCount() >= 3) && (playerIn.getTags().contains("GotTheCastleGem"))) {
                        itemstack.shrink(3);
                        playerIn.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1f, 0.5f);
                        this.entityDropItem(ModItems.TELEPORT_GEM_THE_CASTLE.get());
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "As you wish. Here is your new gem!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(1);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Jafir the Enchanter] " + TextFormatting.WHITE + "Here are the locations I can currently tie the gem to:"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        taskMessage = TextFormatting.GOLD + "The Library -- 40 Lapis Lazuli"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        if (playerIn.getTags().contains("GotTheCastleGem")) {
                            taskMessage = TextFormatting.GOLD + "The Castle -- 3 Cubic Roseum"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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
