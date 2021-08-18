package com.yeehawking.yeehaw.entities;

import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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

public class AlliedKnightEntity extends AnimalEntity {

    public AlliedKnightEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD, ModItems.BUSTER_BLADE.get(), ModItems.SOUL_SWORD.get(), ModItems.TWILIGHT_SWORD.get(), ModItems.BUSTER_BLADE.get());

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 25d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.3f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.22f)
                .createMutableAttribute(Attributes.ARMOR, 2);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 45.0f));
        //this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DarkKnightEntity.class, true));

    }

    public void livingTick() {
        if (this.getTags().contains("followGodborn")) {
            this.goalSelector.addGoal(4, new TemptGoal(this, 1.45d, TEMPTATION_ITEMS, false));
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
    protected SoundEvent getDeathSound() { return SoundInit.HUMAN_DEATH.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundInit.HUMAN_HURT.get(); }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) { return null; }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity playerIn, Vector3d vec, Hand handIn) {
        if (handIn.equals(Hand.MAIN_HAND)) {
            Random rand = new Random();
            if (rand.nextInt(100) == 0) {
                String taskMessage = TextFormatting.BLUE + "[Knight] " +TextFormatting.WHITE + "Your mother was a hamster and you father smelled of elderberries!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                this.entityDropItem(Items.SWEET_BERRIES);
            } else {
                String taskMessage = TextFormatting.BLUE + "[Knight] " + TextFormatting.WHITE + "Hail, Godborn!"; if (!this.getEntityWorld().isRemote) { playerIn.sendMessage(new StringTextComponent(taskMessage), playerIn.getUniqueID()); }
                if (!this.getTags().contains("followGodborn")) {
                    this.addTag("followGodborn");
                }
            }
        }
            return ActionResultType.PASS;
    }
}