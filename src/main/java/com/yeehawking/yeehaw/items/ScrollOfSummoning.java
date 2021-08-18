package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScrollOfSummoning extends Item {
    public ScrollOfSummoning() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
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

            chosenMob.spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,2), SpawnReason.EVENT, false, true);

        }
        playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
