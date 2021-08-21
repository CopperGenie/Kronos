package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class NPCTimekeeperEntity extends AnimalEntity {

    public NPCTimekeeperEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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
    protected int getExperiencePoints(PlayerEntity player) {
        return 5;
    }

    @Override
    protected SoundEvent getAmbientSound() { return null; }
    @Override
    protected SoundEvent getDeathSound() { return SoundInit.GASMAN_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.GASMAN_HURT.get(); }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    private static final DataParameter<Integer> QUEST_NUMBER = EntityDataManager.createKey(NPCTimekeeperEntity.class, DataSerializers.VARINT);

    protected void registerData() {
        super.registerData();
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
            Random rand = new Random();
            ItemStack itemstack = playerIn.getHeldItemMainhand();
            String taskMessage;
            int questNumber = this.getQuestNumber();

            // For testing
            if (playerIn.getHeldItemMainhand().getItem() == ModItems.TESTING_ROD_1.get()) {
                this.setQuestNumber(4);
                taskMessage = TextFormatting.BLUE + "Quest number: " + this.getQuestNumber();
                if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

            }

            else {
                if (questNumber == 0) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Godborn. You are required for a task. I have frozen your associate in time. He will be released from timelock when you complete your mission."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(1);
                }
                else if (questNumber == 1) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "We must talk in private."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.teleportKeepLoaded(this.getPosX(), this.getPosY() + 6, this.getPosZ());
                    playerIn.teleportKeepLoaded(playerIn.getPosX(), playerIn.getPosY() + 6, playerIn.getPosZ());
                    this.setQuestNumber(2);
                }
                else if (questNumber == 2) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "My intelligence tells me you have the ability to find people."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "I need you to locate a man named Tommy Tempus, a time criminal from my dimension. He has just stolen a timebank worth 1,000,000 years from the United Time Vault. I need you to find this criminal, quickly."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(3);
                }
                else if (questNumber == 3) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "You will be put on a clock. If you find him and kill him before your clock empties, you will be rewarded. If not, you will time out."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "We have tracked Tempus to a dimension known as Nowhere. The timebank has a location tracker; we determined his position to be (50,000, 30,000)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    this.setQuestNumber(4);
                }
                else if (questNumber == 4) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Kill Tommy Tempus and return to me with the timebank. Nowhere: (50,000, 30,000)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Use this one on yourself. Your clock starts now."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("stopwatched");
                    playerIn.addTag("quest22");
                    this.entityDropItem(ModItems.TIMEBANK.get());
                    this.setQuestNumber(5);

                    if (playerIn.getEntityWorld() instanceof ServerWorld) {
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
                        ServerWorld worldNowhere = serverPlayer.server.getWorld(Yeehaw.NOWHERE);
                        double tommyPosY = 0;
                        for (int i = 250; i >= 0; i--) {
                            if ( (worldNowhere.getBlockState(new BlockPos(50000, i - 1, 30000)) != Blocks.AIR.getDefaultState()) &&
                                    (worldNowhere.getBlockState(new BlockPos(50000, i - 1, 30000)) != Blocks.CAVE_AIR.getDefaultState()) &&
                                    (worldNowhere.getBlockState(new BlockPos(50000, i - 1, 30000)) != Blocks.VOID_AIR.getDefaultState())) {

                                tommyPosY = i;
                                break;
                            }
                        }
                        ModEntityType.NPC_TOMMY_TEMPUS.get().spawn(worldNowhere, null, null, new BlockPos(50000, tommyPosY, 30000), SpawnReason.EVENT, false, true);
                    }
                }
                else if (questNumber == 5) {
                    if (playerIn.getTags().contains("stopwatched")) {
                        taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Kill Tommy Tempus and return to me with the timebank. Nowhere: (50,000, 30,000)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    } else {
                        if (playerIn.getTags().contains("killedTommy")) {
                            playerIn.removeTag("killedTommy");
                            taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Well done. Intelligence tells me that Tempus is deceased. I have stopped your clock."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Hand over the timebank."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            this.setQuestNumber(6);
                        } else {
                            taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "You live? Very well. I will give you another chance. However, this time you will not receive extra time."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Kill Tommy Tempus and return to me with the timebank. Nowhere: (50,000, 30,000)."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                            playerIn.addTag("stopwatched");
                        }
                    }
                }
                else if (questNumber == 6) {
                    playerIn.removeTag("quest22");
                    if (playerIn.getHeldItemMainhand().getItem() == ModItems.TIMEBANK.get()) {
                        itemstack.shrink(1);
                        playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.8f, 1f);
                        taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Blast! The device is empty. It appears he has transferred the years into a secure time server. At any rate, excellent work in retrieving the timebank."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                        this.setQuestNumber(7);
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Hand over the timebank."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    }

                }
                else if (questNumber == 7) {
                    if (playerIn.getTags().contains("timedOutQuest22")) {
                        playerIn.removeTag("timedOutQuest22");
                        taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Unfortunately, you did not kill Tempus in time. I have no reward for you now. However, you killed him nonetheless. I may have more work for you in the future. Keep in touch.";
                    } else {
                        taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "I was going to reward you 1,000 years from the timebank. However, since the device is drained, I asked intelligence to dropship some other valuables from the Time Vault. Enjoy your reward, and keep in touch. I may have work for you in the future.";
                        for (int i = 64; i>=1; i--) {
                            this.entityDropItem(Items.LAPIS_LAZULI);
                            this.entityDropItem(Items.GOLD_INGOT);
                            this.entityDropItem(ModItems.CHEESE.get());
                        }
                    }
                    if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }

                    this.setQuestNumber(8);
                }
                else if (questNumber == 8) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "I have release your associate from timelock. You may return to your business."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                    playerIn.addTag("quest23");
                    this.setQuestNumber(9);
                }
                else if (questNumber == 9) {
                    taskMessage = TextFormatting.YELLOW + "[Timekeeper] " + TextFormatting.WHITE + "Intelligence is working to locate the time server that Tempus transferred the years to. Until then, I have no mission for you."; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
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

// HIS GLASSES IS GLUED TO HIS FACE!