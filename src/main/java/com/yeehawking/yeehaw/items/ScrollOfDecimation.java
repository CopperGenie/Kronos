package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ScrollOfDecimation extends Item {
    public ScrollOfDecimation() {
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
                List<MobEntity> mobEntityList = worldIn.getEntitiesWithinAABB(MobEntity.class, mobAABB.grow(0.1D, 0.1D, 0.1D));
                if (!mobEntityList.isEmpty()) {
                    for (MobEntity entity : mobEntityList) {
                        // Add effects
                        if (entity.isEntityUndead()) {
                            entity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 2, false, false));
                        } else {
                            entity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 2, false, false));
                        }
                        if (playerIn.getEntityWorld() instanceof ServerWorld) {
                            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
                            EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, entity.getPosition().add(0,0,0), SpawnReason.EVENT, false, true);
                        }
                    }
                    playerIn.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 1f);

                    ItemStack itemstack = playerIn.getHeldItemMainhand();
                    itemstack.shrink(1);
                }
            }
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}