package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrollOfSmiteLesserUndead extends Item {
    public ScrollOfSmiteLesserUndead() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Random rand = new Random();
        LivingEntity player = playerIn;
        if (player.getHeldItemMainhand().getItem() == ModItems.SCROLL_OF_SMITE_LESSER_UNDEAD.get()) {
            World world = player.getEntityWorld();

            List<ZombieEntity> zombieList = world.getEntitiesWithinAABB(ZombieEntity.class, player.getBoundingBox().grow(10.0D, 10.0D, 10.0D));
            List<SkeletonEntity> skeletonList = world.getEntitiesWithinAABB(SkeletonEntity.class, player.getBoundingBox().grow(10.0D, 10.0D, 10.0D));
            List<ZombieVillagerEntity> zombieVillagerList = world.getEntitiesWithinAABB(ZombieVillagerEntity.class, player.getBoundingBox().grow(10.0D, 10.0D, 10.0D));
            List<ZombifiedPiglinEntity> zombifiedPiglinList = world.getEntitiesWithinAABB(ZombifiedPiglinEntity.class, player.getBoundingBox().grow(10.0D, 10.0D, 10.0D));
            List<WitherSkeletonEntity> witherSkeletonList = world.getEntitiesWithinAABB(WitherSkeletonEntity.class, player.getBoundingBox().grow(10.0D, 10.0D, 10.0D));
            List<MobEntity> mobList = new ArrayList<>(); mobList.addAll(zombieList); mobList.addAll(skeletonList); mobList.addAll(zombifiedPiglinList); mobList.addAll(zombieVillagerList); mobList.addAll(witherSkeletonList);

            int limit = 0;

            for (MobEntity entity : mobList) {
                entity.setHealth(0);

                for (int i = 1; i <= 50; i++) {
                    entity.world.addParticle(ParticleTypes.SMOKE, entity.getPosXRandom(0.5D), entity.getPosYRandom() - 0.7, entity.getPosZRandom(0.5D), (rand.nextDouble() - rand.nextDouble()) * 0.2, 0.1D, (rand.nextDouble() - rand.nextDouble()) * 0.2);
                }


                limit++;

                if (limit == 5) {break;}

            }
            playerIn.playSound(SoundEvents.ITEM_TOTEM_USE, 0.8f, 1f);

            ItemStack itemstack = player.getHeldItemMainhand();
            itemstack.shrink(1);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }


}
