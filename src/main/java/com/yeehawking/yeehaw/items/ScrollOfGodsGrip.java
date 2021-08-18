package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ScrollOfGodsGrip extends Item {
    public ScrollOfGodsGrip() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        RayTraceResult rayTraceResult = Minecraft.getInstance().objectMouseOver;
        if (rayTraceResult != null) {
            if (rayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
                Vector3d mobVec = rayTraceResult.getHitVec();
                BlockPos mobPos = new BlockPos(mobVec.getX(), mobVec.getY(), mobVec.getZ());
                AxisAlignedBB mobAABB = new AxisAlignedBB(mobPos);
                // Change BB size depending on whether scroll is AoE or single-target
                List<MobEntity> mobEntityList = worldIn.getEntitiesWithinAABB(MobEntity.class, mobAABB.grow(0.01D, 0.01D, 0.01D));
                if (!mobEntityList.isEmpty()) {
                    for (MobEntity entity : mobEntityList) {
                        // Add effects
                        entity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20*5, 2, false, false));
                        entity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 0, false, false));
                        entity.addPotionEffect(new EffectInstance(Effects.WITHER, 20*5, 1, false, false));
                    }
                    playerIn.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 0.8f, 1f);

                    ItemStack itemstack = playerIn.getHeldItemMainhand();
                    itemstack.shrink(1);
                }
            }
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}