package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PrimordialSpirit extends Item {
    public PrimordialSpirit() {
        super(new Properties().group(Yeehaw.TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();
            Random rand = new Random();

            List<EntityType> mobList = Arrays.asList(
                    ModEntityType.ALLIED_KNIGHT.get(),
                    ModEntityType.BEHOLDER.get(),
                    ModEntityType.CACTOID.get(),
                    ModEntityType.FLINT_MONGER.get(),
                    ModEntityType.GENIE.get(),
                    ModEntityType.GIANT.get(),
                    ModEntityType.GNOME.get(),
                    ModEntityType.GNOME_ELDER.get(),
                    ModEntityType.KRAKEN.get(),
                    ModEntityType.SOUL_TRADER.get(),
                    ModEntityType.THE_RESTLESS.get(),
                    ModEntityType.TOADKIN.get(),
                    ModEntityType.TOAD_MOTHER.get(),
                    ModEntityType.TROLL.get(),
                    EntityType.ZOMBIE, EntityType.PANDA, EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.ENDERMAN, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.ILLUSIONER, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.OCELOT, EntityType.PARROT, EntityType.PHANTOM, EntityType.PIGLIN, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SILVERFISH, EntityType.SLIME, EntityType.SNOW_GOLEM, EntityType.SPIDER, EntityType.STRAY, EntityType.STRIDER, EntityType.VEX, EntityType.VILLAGER, EntityType.VINDICATOR, EntityType.WANDERING_TRADER, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.WOLF, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIE_HORSE);
            EntityType chosenMob = mobList.get(rand.nextInt(mobList.size()));

            for (int i = 1; i <= 5; i++) {
                chosenMob.spawn(serverWorld, null, null, playerIn.getPosition().add(rand.nextInt(3) - rand.nextInt(3),2,rand.nextInt(3) - rand.nextInt(3)), SpawnReason.EVENT, false, true);
            }

            playerIn.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.8f, 1f);

            ItemStack itemstack = playerIn.getHeldItemMainhand();
            itemstack.shrink(1);
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
