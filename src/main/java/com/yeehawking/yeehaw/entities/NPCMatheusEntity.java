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

import javax.annotation.Nullable;
import java.util.Random;

public class NPCMatheusEntity extends AnimalEntity {

    public NPCMatheusEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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

    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(NPCMatheusEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCMatheusEntity.class, DataSerializers.VARINT);

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
        if (spawnDataIn instanceof NPCMatheusEntity.NPCData) {
            i = ((NPCMatheusEntity.NPCData)spawnDataIn).typeData;
        } else { spawnDataIn = new NPCMatheusEntity.NPCData(i); }
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
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Who addresses me?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "The Godborn? Hmph, very well. What do you want?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Haha! The keys to Theos? You must me joking. I don't care if Aelymore sent you himself--Theos is off limits to all."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "It is extremely dangerous and I won't have the last Godborn getting killed in there. "; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Infinite lives, you say? Don't make me laugh! Now unless you have something less insulting to say, get out of my sight."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "The keys will remain secure in my vault until the danger has passed."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("Quest6");
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    if (playerIn.getTags().contains("Quest20")) {
                        playerIn.removeTag("Quest20");
                        taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Someone broke into my vault?! RAAAH!! Useless knights, the lot of you!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(4);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Leave me."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }
                }
                else if (questNumber == 4) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "You, Godborn! Do you know anything about this? When I find out who broke in, I'm going to kill them myself!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "What do you want, anyway?"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(5);
                }
                else if (questNumber == 5) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "What?! My Elizabeth was attacked by trolls? Is she safe? How do you know this? Have you been visiting her?!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Godborn, you're nothing but trouble! Stay away from my daughter!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(6);
                }
                else if (questNumber == 6) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "As a matter of fact, I'll make sure that you do. GUARDS, SEIZE HIM!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                        for (int i = 1; i<=4; i++) {
                            ModEntityType.KNIGHT.get().spawn(serverWorld, null, null, this.getPosition(), SpawnReason.EVENT, false, true);
                        }
                    }
                    this.setQuestNumber(7);
                }
                else if (questNumber == 7) {
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "I should have your head! But as it turns out, I have a shortage of guards thanks to the incessant orc hordes. Just know that you are no longer welcome in my kingdom."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "You are now a criminal of Evenwood, and a bounty will be placed on your head for your crimes! Begone!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(8);
                    playerIn.addTag("Quest21");
                }
                else if (questNumber == 8) {
                    if (playerIn.getTags().contains("???")) {
                        taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "..."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                        this.setQuestNumber(9);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[King Matheus] " + TextFormatting.WHITE + "Begone, criminal!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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
