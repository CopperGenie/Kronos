package com.yeehawking.yeehaw.events;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.*;
import com.yeehawking.yeehaw.init.ModBlocks;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.init.ModItems;
import com.yeehawking.yeehaw.init.SoundInit;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.jmx.Server;

import javax.imageio.spi.ServiceRegistry;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Yeehaw.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void onJoinWorld(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            if (!event.getEntity().getEntityWorld().isRemote) {
                ServerWorld serverWorld = (ServerWorld) event.getEntity().getEntityWorld();
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getEntity();

                // Check whether this is the first time entering the world
                if (!serverPlayer.getTags().contains("firstTimeFalse")) {
                    float y = 0;
                    while ( (serverWorld.getBlockState(serverPlayer.getPosition().add(5, y, 0)) != Blocks.AIR.getDefaultState()) && (serverWorld.getBlockState(serverPlayer.getPosition().add(5, y+1, 0)) != Blocks.AIR.getDefaultState()) ) {
                        y += 1;
                    }

                    ModEntityType.NPC_AELYMORE.get().spawn(serverWorld, null, null, serverPlayer.getPosition().add(5, y+1, 0), SpawnReason.EVENT, false, true);

                    serverPlayer.addTag("firstTimeFalse");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHoldSoulVacuum(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == ModItems.SOUL_VACUUM.get()) {
            World world = player.getEntityWorld();
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.AIR.getDefaultState());
        }
        if (player.getHeldItemMainhand().getItem() == ModItems.OVERCHARGED_SOUL_VACUUM.get()) {
            World world = player.getEntityWorld();
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, -1, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(1, -1, 0), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(0, -1, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(0, -1, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, -1, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(1, -1, -1), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(1, -1, 1), Blocks.AIR.getDefaultState());
            world.setBlockState(player.getPosition().add(-1, -1, 1), Blocks.AIR.getDefaultState());
        }
    }

    @SubscribeEvent
    public static void whileWearingYWNB(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        Random rand = new Random();
        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.YANKEE_WITH_NO_BRIM.get()) {
            World world = player.getEntityWorld();
            int r = 2;
            int r1 = 15;
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 0, -1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 0, 0), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 0, 1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 0, -1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 0, 0), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 0, 1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-1, 0, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(0, 0, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(1, 0, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-1, 0, -2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(0, 0, -2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(1, 0, -2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 1, -1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 1, 0), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(2, 1, 1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 1, -1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 1, 0), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-2, 1, 1), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-1, 1, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(0, 1, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(1, 1, 2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(-1, 1, -2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(0, 1, -2), Blocks.AIR.getDefaultState());}
            if (rand.nextInt(r) == 0) {world.setBlockState(player.getPosition().add(1, 1, -2), Blocks.AIR.getDefaultState());}

            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 0, -1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 0, 0), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 0, 1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 0, -1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 0, 0), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 0, 1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-1, 0, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(0, 0, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(1, 0, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-1, 0, -2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(0, 0, -2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(1, 0, -2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 1, -1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 1, 0), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(2, 1, 1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 1, -1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 1, 0), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-2, 1, 1), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-1, 1, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(0, 1, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(1, 1, 2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(-1, 1, -2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(0, 1, -2), Blocks.STONE.getDefaultState());}
            if (rand.nextInt(r1) == 0) {world.setBlockState(player.getPosition().add(1, 1, -2), Blocks.STONE.getDefaultState());}
        }

    }

    @SubscribeEvent
    public static void whileHoldingPathwinder(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        World world = player.getEntityWorld();
        if ((player.getHeldItemOffhand().getItem() == ModItems.PATHWINDER.get()) && (world.getBlockState(player.getPosition().add(0, -1, 0)) == Blocks.AIR.getDefaultState())) {
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.GRASS_BLOCK.getDefaultState());
        }
        else if ((player.getHeldItemOffhand().getItem() == ModItems.PATHWINDER.get()) && (world.getBlockState(player.getPosition().add(0, -1, 0)) == Blocks.CAVE_AIR.getDefaultState())) {
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.STONE.getDefaultState());
        }
        else if ((player.getHeldItemOffhand().getItem() == ModItems.PATHWINDER.get()) && (world.getBlockState(player.getPosition().add(0, -1, 0)) == Blocks.WATER.getDefaultState())) {
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.ICE.getDefaultState());
        }
        else if ((player.getHeldItemOffhand().getItem() == ModItems.PATHWINDER.get()) && (world.getBlockState(player.getPosition().add(0, -1, 0)) == Blocks.LAVA.getDefaultState())) {
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.OBSIDIAN.getDefaultState());
        }
    }

    @SubscribeEvent
    public static void onHitWithBusterBlade(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.BUSTER_BLADE.get()) {
            if (event.getTarget().isAlive() && rand.nextInt(3) == 0) {
                if (event.getTarget() instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) event.getTarget();
                    if (target instanceof SilverfishEntity) {

                        PlayerEntity player = event.getPlayer();
                        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20*3, 0, false, false));

                        if (!event.getPlayer().getEntityWorld().isRemote) {
                            String msg = TextFormatting.RED + "Busted!";
                            player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                        }
                    }

                    else {
                        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20*3, 0, false, false));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithGazerblade(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.GAZERBLADE.get()) {
            if (event.getTarget().isLiving()) {
                if (event.getTarget().isAlive()) {
                    PlayerEntity player = event.getPlayer();
                    player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20 * 10, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithAngelTablet(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.ANGEL_TABLET.get()) {
            LivingEntity target = (LivingEntity) event.getTarget();
            if (event.getTarget().isLiving()) {
                if (event.getTarget().isAlive()) {
                    target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20*30, 0, false, false));
                    target.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 20*40, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithTwilightSword(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.TWILIGHT_SWORD.get()) {
            if (event.getTarget() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) event.getTarget();
                PlayerEntity player = event.getPlayer();
                World world = player.getEntityWorld();
                if (event.getTarget().isLiving() & (target.getHealth() <= 5)) {
                    if (event.getTarget().isAlive()) {
                        if ((world.getBlockState(target.getPosition().add(0, -1, 0)) == Blocks.GRASS_BLOCK.getDefaultState())
                                && (world.getBlockState(target.getPosition().add(0, 0, 0)) == Blocks.AIR.getDefaultState()))
                        {
                            world.setBlockState(target.getPosition().add(0, 0, 0), ModBlocks.BLACK_IRIS.get().getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithPlagueRod(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.PLAGUE_ROD.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (event.getTarget().isAlive()) {
                    if (target instanceof CreepingHorrorEntity){
                        target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 20, 2, false, false));
                    }
                    else {
                        target.addPotionEffect(new EffectInstance(Effects.POISON, 20 * 20, 2, false, false));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithDrowRapier(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.DROW_RAPIER.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (event.getTarget().isAlive() && rand.nextInt(5) == 0) {
                    target.addPotionEffect(new EffectInstance(Effects.POISON, 20*5, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithSoulSword(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.SOUL_SWORD.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (event.getTarget().isAlive()) {
                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, 20*20, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWhileWearingHellHeater(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemOffhand().getItem() == ModItems.HELL_HEATER.get()) {
            if (event.getTarget().isLiving()) {
                if (event.getTarget().isAlive() && rand.nextInt(10) == 0) {
                    PlayerEntity player = event.getPlayer();
                    player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 20*10, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void whileWearingHellHeater(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (event.getEntityLiving().getHeldItemOffhand().getItem() == ModItems.HELL_HEATER.get()) {
            player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 20*2, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void onHitWhileWearingFlayer(AttackEntityEvent event) {
        Random rand = new Random();
        if ((event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.FLAYER_ROBE_TOP.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.FLAYER_ROBE_BOTTOM.get())) {
            if (event.getTarget().isLiving()) {
                if (event.getTarget().isAlive() && rand.nextInt(10) == 0) {
                    PlayerEntity player = event.getPlayer();
                    player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20*4, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWhenWearingDrow(AttackEntityEvent event) {
        Random rand = new Random();
        if ((event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.DROW_CHESTPLATE.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.DROW_LEGGINGS.get()) &&
                (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.DROW_HELMET.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.DROW_BOOTS.get())) {
            if (event.getTarget().isLiving()) {
                if (event.getTarget().isAlive() && rand.nextInt(10) == 0) {
                    PlayerEntity player = event.getPlayer();
                    player.addPotionEffect(new EffectInstance(Effects.SPEED, 20*6, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void whileWieldingSoulPick(LivingEvent.LivingUpdateEvent event) {
        Random rand = new Random();
        LivingEntity player = event.getEntityLiving();
        if ((event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.SOUL_PICKAXE.get()) && rand.nextInt(20*10) == 0) {
            player.addPotionEffect(new EffectInstance(Effects.HASTE, 20*5, 1, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWieldingSunsetBlade(LivingEvent.LivingUpdateEvent event) {
        Random rand = new Random();
        LivingEntity player = event.getEntityLiving();
        if ((event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.SUNSET_BLADE.get()) && rand.nextInt(20*90) == 0) {
            player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 20*30, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWieldingDrowPickWithArmor(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if ((event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.DROW_BOOTS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.DROW_CHESTPLATE.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.DROW_LEGGINGS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.DROW_HELMET.get()) && (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.DROW_PICKAXE.get())) {
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20*15, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWearingCloudBoots(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.CLOUD_BOOTS.get()) {
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20*2, 1, false, false));
            player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 20*2, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWearingMeteorHelmet(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.METEOR_HELMET.get()) {
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20*2, 19, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWearingSoulArmor(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if ((event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.SOUL_BOOTS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.SOUL_CHESTPLATE.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.SOUL_LEGGINGS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.SOUL_HELMET.get())) {
            player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 20*2, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWearingTitanArmor(AttackEntityEvent event) {
        Random rand = new Random();
        LivingEntity player = event.getEntityLiving();
        if ((event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.TITAN_BOOTS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.TITAN_CHESTPLATE.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.TITAN_LEGGINGS.get()) && (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.TITAN_HELMET.get())) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (event.getTarget().isAlive() && rand.nextInt(25) == 0) {
                    player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void whileWearingPhylactery(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemOffhand().getItem() == ModItems.PHYLACTERY.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (event.getTarget().isAlive() && rand.nextInt(20) == 1) {
                    PlayerEntity player = event.getPlayer();
                    target.addPotionEffect(new EffectInstance(Effects.WITHER, 20*4, 0, false, false));
                    player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0, false, false));
                }
            }
        }
    }

    // TODO: Add ghost pirate for event
/*
    @SubscribeEvent
    public static void whileWieldingCutlass(AttackEntityEvent event) {
        if (event.getEntityLiving().getHeldItemOffhand().getItem() == ModItems.CUTLASS.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                LivingEntity player = event.getEntityLiving();
                if (target instanceof GhostPirateEntity) {
                    player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20*2, 3, false, false));
                }
            }
        }
    }
    */

    @SubscribeEvent
    public static void onJumpWithSkyGem(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemOffhand().getItem() == ModItems.SKY_GEM.get()) {
            player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20*10, 2, false, false));
            player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 20*15, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void whileWearingToadRing(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemOffhand().getItem() == ModItems.TOAD_RING.get()) {
            if (event.getTarget().isLiving()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                PlayerEntity player = event.getPlayer();
                if (event.getTarget().isAlive() && rand.nextInt(2) == 0) {
                    player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20*5, 0, false, false));
                }
                if (event.getTarget().isAlive() && rand.nextInt(5) == 0) {
                    player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 20*30, 0, false, false));
                }
                if (event.getTarget().isAlive() && rand.nextInt(5) == 0) {
                    player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20*10, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickReceiverWithKey(PlayerInteractEvent.RightClickBlock event) {
        LivingEntity player = event.getEntityLiving();
        if (!event.getWorld().isRemote()) {
            if (player.getHeldItemMainhand().getItem() == ModItems.DECRYPTED_HOMING_KEY.get()) {
                if (player.getEntityWorld() instanceof ServerWorld) {
                    if (event.getHand().equals(Hand.MAIN_HAND)) {
                        ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();

                        if (((serverWorld.getBlockState(event.getPos().add(0, -1, 0))) == Blocks.ANCIENT_DEBRIS.getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(1, -1, 1))) == Blocks.REDSTONE_BLOCK.getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(1, -1, -1))) == Blocks.REDSTONE_BLOCK.getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(-1, -1, -1))) == Blocks.REDSTONE_BLOCK.getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(-1, -1, 1))) == Blocks.REDSTONE_BLOCK.getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(0, -1, 1))) == ModBlocks.VOID_GOLD_BLOCK.get().getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(0, -1, -1))) == ModBlocks.VOID_GOLD_BLOCK.get().getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(1, -1, 0))) == ModBlocks.VOID_GOLD_BLOCK.get().getDefaultState()) &&
                                ((serverWorld.getBlockState(event.getPos().add(-1, -1, 0))) == ModBlocks.VOID_GOLD_BLOCK.get().getDefaultState())) {

                            serverWorld.playSound(event.getPlayer(), event.getPlayer().getPosition(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                                    SoundCategory.AMBIENT, 1.0f, 1.0f);

                            if (player.getHorizontalFacing() == Direction.NORTH) { //  -Z
                                ModEntityType.HEROBRINE.get().spawn(serverWorld, null, null, player.getPosition().add(0,0,-10), SpawnReason.EVENT, false, true);
                            }

                            if (player.getHorizontalFacing() == Direction.EAST) { //  +X
                                ModEntityType.HEROBRINE.get().spawn(serverWorld, null, null, player.getPosition().add(10,0,0), SpawnReason.EVENT, false, true);
                            }

                            if (player.getHorizontalFacing() == Direction.SOUTH) { //  +Z
                                ModEntityType.HEROBRINE.get().spawn(serverWorld, null, null, player.getPosition().add(0,0,10), SpawnReason.EVENT, false, true);
                            }

                            if (player.getHorizontalFacing() == Direction.WEST) { //  -X
                                ModEntityType.HEROBRINE.get().spawn(serverWorld, null, null, player.getPosition().add(-10,0,0), SpawnReason.EVENT, false, true);
                            }

                            ItemStack itemstack = player.getHeldItemMainhand();
                            itemstack.shrink(1);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickSwordInTheStone(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        if (event.getHand().equals(Hand.MAIN_HAND)) {
            if (!event.getWorld().isRemote()) {
                if (player.getHeldItemMainhand().getItem() == Items.LAPIS_LAZULI) {
                    World world = player.getEntityWorld();

                    if ((world.getBlockState(event.getPos())) == ModBlocks.SWORD_IN_THE_STONE.get().getDefaultState()) { // not working well
                        if (player.experienceTotal >= 200) {
                            player.giveExperiencePoints(-200);
                            ItemStack itemstack = player.getHeldItemMainhand();
                            itemstack.shrink(1);
                            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f);
                            player.entityDropItem(ModItems.LOST_MEMORY.get());
                        } else {
                            String msg = TextFormatting.RED + "You have no memories to lose!";
                            player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackHerobrine(AttackEntityEvent event) {
        if (event.getTarget() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getTarget();

            if (target instanceof HerobrineEntity) {
                LivingEntity player = event.getEntityLiving();
                if (player.getEntityWorld() instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
                    Random rand = new Random();

                    if (rand.nextInt(150) == 0) {
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, player.getPosition().add(0,0,0), SpawnReason.EVENT, false, true);
                        player.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 1f);
                    }
                    if (rand.nextInt(150) == 0) {
                        EntityType.CAVE_SPIDER.spawn(serverWorld, null, null, target.getPosition().add(2,1,0), SpawnReason.EVENT, false, true);
                        EntityType.HUSK.spawn(serverWorld, null, null, target.getPosition().add(0,1,2), SpawnReason.EVENT, false, true);
                        EntityType.STRAY.spawn(serverWorld, null, null, target.getPosition().add(-2,1,0), SpawnReason.EVENT, false, true);
                        EntityType.WITHER_SKELETON.spawn(serverWorld, null, null, target.getPosition().add(0,1,-2), SpawnReason.EVENT, false, true);
                        player.playSound(SoundEvents.ENTITY_GHAST_SCREAM, 0.6f, 1f);
                    }
                    if (rand.nextInt(150) == 0) {
                        int i = rand.nextInt(5) - rand.nextInt(5);
                        int j = rand.nextInt(5) - rand.nextInt(5);
                        EntityType.ILLUSIONER.spawn(serverWorld, null, null, target.getPosition().add(i,2,j), SpawnReason.EVENT, false, true);
                        player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
                    }
                    if (rand.nextInt(150) == 0) {
                        for (int i = 1; i <= 15; i++) {
                            EntityType.SILVERFISH.spawn(serverWorld, null, null, player.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
                        }
                    }
                    if (rand.nextInt(150) == 0) {
                        for (int i = 1; i <= 4; i++) {
                            EntityType.TNT.spawn(serverWorld, null, null, player.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
                        }
                    }
                    if (rand.nextInt(150) == 0) {
                        for (int i = 1; i <= 10; i++) {
                            EntityType.ZOMBIE.spawn(serverWorld, null, null, player.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
                        }
                    }
                    if (rand.nextInt(150) == 0) {
                        serverWorld.setBlockState(player.getPosition().add(0, -1, 0), Blocks.LAVA.getDefaultState());
                    }
                    if (rand.nextInt(150) == 0) {
                        serverWorld.setBlockState(player.getPosition().add(0, 0, 0), Blocks.FIRE.getDefaultState());
                    }
                    if (rand.nextInt(150) == 0) {
                        serverWorld.setBlockState(player.getPosition().add(0, 3, 0), Blocks.ANVIL.getDefaultState());
                        serverWorld.setBlockState(player.getPosition().add(0, 2, 0), Blocks.AIR.getDefaultState());
                        //TODO: Anvil doesn't fall sometimes
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackCreeperWithAnticreepusInInv(AttackEntityEvent event) {
        if (event.getTarget() instanceof CreeperEntity) {
            LivingEntity target = (LivingEntity) event.getTarget();
            PlayerEntity player = event.getPlayer();
            Random rand = new Random();

            for (ItemStack item : player.inventory.mainInventory) {
                if (item.getItem() == ModItems.ANTICREEPUS.get()) {
                    for (int i = 1; i <= 150; i++) {
                        target.world.addParticle(ParticleTypes.LARGE_SMOKE, target.getPosXRandom(1.5D), target.getPosYRandom(), target.getPosZRandom(1.5D), rand.nextDouble() - rand.nextDouble(), rand.nextDouble(), rand.nextDouble() - rand.nextDouble());
                    }
                    player.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.8F, 1.0F);
                    target.teleportKeepLoaded(0,0,0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackNimbusGiant(AttackEntityEvent event) {
        if (event.getTarget() instanceof NimbusGiantEntity) {
            Random rand = new Random();

            if (rand.nextInt(7) == 0) {
                if (event.getPlayer().getEntityWorld() instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) event.getPlayer().getEntityWorld();
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getTarget().getPosition().add(6, 0, 4), SpawnReason.EVENT, false, true);
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getTarget().getPosition().add(0, 0, -7), SpawnReason.EVENT, false, true);
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getTarget().getPosition().add(-6, 0, 4), SpawnReason.EVENT, false, true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickMongerWithFlint(PlayerInteractEvent.EntityInteractSpecific event) {
        LivingEntity player = event.getEntityLiving();
        if (event.getHand().equals(Hand.MAIN_HAND)) {
            if (event.getTarget() instanceof FlintMongerEntity) {
                LivingEntity target = (LivingEntity) event.getTarget();

                if (player.getHeldItemMainhand().getItem() == Items.FLINT) {
                    ItemStack itemstack = player.getHeldItemMainhand();
                    float itemCount = itemstack.getCount();
                    int itemMultiples = MathHelper.floor(itemCount / 16);

                    if (!event.getPlayer().getEntityWorld().isRemote) {
                        String rewardMessage = TextFormatting.YELLOW + "[Flint Monger] " + TextFormatting.GREEN + "Ah... You have treasure!";
                        player.sendMessage(new StringTextComponent(rewardMessage), player.getUniqueID());
                    }

                    for (int i = itemMultiples; i >= 1; i--) {
                        itemstack.shrink(16);
                        target.playSound(SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER, 1.0F, 1.0F);

                        Random rand = new Random();
                        int itemReturn = rand.nextInt(4);

                        switch (itemReturn) {
                            case 0:
                                target.entityDropItem(Items.DIAMOND);
                                break;
                            case 1:
                                target.entityDropItem(ModItems.MAGIC_GOLD.get());
                                break;
                            case 2:
                                target.entityDropItem(ModItems.UNDERSTEEL.get());
                                break;
                            case 3:
                                target.entityDropItem(ModItems.SOUL.get());
                                break;
                        }
                    }
                }

                else {
                    if (!event.getPlayer().getEntityWorld().isRemote) {
                        String rejectMessage = TextFormatting.YELLOW + "[Flint Monger] " + TextFormatting.RED + "Leave me alone! I have work to do.";
                        player.sendMessage(new StringTextComponent(rejectMessage), player.getUniqueID());
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickSoulTrader(PlayerInteractEvent.EntityInteractSpecific event) {
        LivingEntity player = event.getEntityLiving();
        String rewardMessage = TextFormatting.YELLOW + "[Soul Trader] " + TextFormatting.GREEN + "Thank you kindly.";

        if (event.getHand().equals(Hand.MAIN_HAND)) {
            if (event.getTarget() instanceof SoulTraderEntity) {
                LivingEntity target = (LivingEntity) event.getTarget();
                ItemStack itemstack = player.getHeldItemMainhand();
                float itemCount = itemstack.getCount();

                if ((player.getHeldItemMainhand().getItem() == Items.ROTTEN_FLESH) && (itemCount == 64)) {
                    itemstack.shrink(64);
                    target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    target.entityDropItem(ModItems.TATTERED_SOUL.get());

                    if (!event.getPlayer().getEntityWorld().isRemote) {
                        player.sendMessage(new StringTextComponent(rewardMessage), player.getUniqueID());
                    }
                }

                else if (player.getHeldItemMainhand().getItem() == Items.BONE) {
                    int itemMultiples = MathHelper.floor(itemCount / 32);

                    for (int i = itemMultiples; i >= 1; i--) {
                        itemstack.shrink(32);
                        target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

                        target.entityDropItem(ModItems.TATTERED_SOUL.get());

                        if (!event.getPlayer().getEntityWorld().isRemote) {
                            player.sendMessage(new StringTextComponent(rewardMessage), player.getUniqueID());
                        }
                    }
                }

                else if (player.getHeldItemMainhand().getItem() == Items.STRING) {
                    int itemMultiples = MathHelper.floor(itemCount / 32);

                    for (int i = itemMultiples; i >= 1; i--) {
                        itemstack.shrink(32);
                        target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

                        target.entityDropItem(ModItems.TATTERED_SOUL.get());

                        if (!event.getPlayer().getEntityWorld().isRemote) {
                            player.sendMessage(new StringTextComponent(rewardMessage), player.getUniqueID());
                        }
                    }
                }

                else if (player.getHeldItemMainhand().getItem() == Items.GUNPOWDER) {
                    World world = player.getEntityWorld();
                    int itemMultiples = MathHelper.floor(itemCount / 32);

                    if (itemMultiples >= 1) {
                        itemstack.shrink(32);

                        target.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                        target.setHealth(0);
                        world.createExplosion(target, target.getPosX(), target.getPosY(), target.getPosZ(), 2, Explosion.Mode.NONE);

                        target.entityDropItem(ModItems.SOUL.get());

                        if (!event.getPlayer().getEntityWorld().isRemote) {
                            player.sendMessage(new StringTextComponent(rewardMessage), player.getUniqueID());
                        }
                    }
                }

                else {
                    if (!event.getPlayer().getEntityWorld().isRemote) {
                        String rejectMessage = TextFormatting.YELLOW + "[Soul Trader] " + TextFormatting.RED + "You do not have what I seek, stranger.";
                        player.sendMessage(new StringTextComponent(rejectMessage), player.getUniqueID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackTenebrik(AttackEntityEvent event) {
        if (event.getTarget() instanceof NPCTenebrikEntity) {
            if (event.getTarget().getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getTarget().getEntityWorld();
                LivingEntity target = (LivingEntity) event.getTarget();
                Random rand = new Random();

                if (rand.nextInt(6) == 0) { event.getPlayer().setFire(4); }

                if (rand.nextInt(10) == 0) {
                    EntityType.ENDERMITE.spawn(serverWorld, null, null, target.getPosition().add(0, 1, 0), SpawnReason.EVENT, false, true);
                    EntityType.ENDERMITE.spawn(serverWorld, null, null, target.getPosition().add(0, 1, 0), SpawnReason.EVENT, false, true);
                    EntityType.ENDERMITE.spawn(serverWorld, null, null, target.getPosition().add(0, 1, 0), SpawnReason.EVENT, false, true);

                    String taskMessage = TextFormatting.RED + "[Tenebrik] " + TextFormatting.WHITE + "Muahahaha!";
                    if (!event.getTarget().getEntityWorld().isRemote) { event.getPlayer().sendMessage(new StringTextComponent(taskMessage), event.getPlayer().getUniqueID()); }
                }

                if ( (((NPCTenebrikEntity) event.getTarget()).getHealth() <= 100) && (!event.getTarget().getTags().contains("phantomSummon")) ) {

                    EntityType.PHANTOM.spawn(serverWorld, null, null, target.getPosition().add(2, 1, 0), SpawnReason.EVENT, false, true);
                    EntityType.PHANTOM.spawn(serverWorld, null, null, target.getPosition().add(-2, 1, 0), SpawnReason.EVENT, false, true);
                    EntityType.PHANTOM.spawn(serverWorld, null, null, target.getPosition().add(0, 1, 2), SpawnReason.EVENT, false, true);
                    EntityType.PHANTOM.spawn(serverWorld, null, null, target.getPosition().add(0, 1, -2), SpawnReason.EVENT, false, true);

                    String taskMessage = TextFormatting.RED + "[Tenebrik] " + TextFormatting.WHITE + "Fool! This is but a fraction of my power!";
                    if (!event.getTarget().getEntityWorld().isRemote) { event.getPlayer().sendMessage(new StringTextComponent(taskMessage), event.getPlayer().getUniqueID()); }

                    target.addTag("phantomSummon");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickWithTorch(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand().equals(Hand.MAIN_HAND)) {
            LivingEntity player = event.getEntityLiving();
            if (player.getHeldItemMainhand().getItem() == Items.TORCH) {
                if (event.getPlayer().getEntityWorld() instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) event.getPlayer().getEntityWorld();
                    if ( (serverWorld.getDimensionKey().equals(RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
                            new ResourceLocation("minecraft:the_nether")))) && (player.getTags().contains("SummonTenebrik")) ) {
                        if (!event.getEntity().getEntityWorld().isRemote) {
                            ModEntityType.NPC_TENEBRIK.get().spawn(serverWorld, null, null, player.getPosition().add(1, 1, 0), SpawnReason.EVENT, false, true);
                            player.removeTag("SummonTenebrik");
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackFriendWizard(AttackEntityEvent event) {
        if ( (event.getTarget() instanceof NPCAelymoreEntity) || (event.getTarget() instanceof NPCOswirEntity) || (event.getTarget() instanceof NPCPhantasmusEntity) || (event.getTarget() instanceof NPCWynnbrimEntity) || (event.getTarget() instanceof NPCScholarEntity) ) {
            if (event.getTarget().getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getTarget().getEntityWorld();
                LivingEntity target = (LivingEntity) event.getTarget();
                Random rand = new Random();

                if (rand.nextInt(10) == 0) {
                    EntityType.SILVERFISH.spawn(serverWorld, null, null, target.getPosition().add(0, 1, 0), SpawnReason.EVENT, false, true);

                    if (!event.getTarget().getEntityWorld().isRemote) { event.getPlayer().sendMessage(
                            new StringTextComponent("[").mergeStyle(TextFormatting.GOLD).appendSibling(new TranslationTextComponent(target.getType().getTranslationKey()))
                                    .appendString("] ").appendSibling(new StringTextComponent("Oi! Stop that!").mergeStyle(TextFormatting.WHITE)), event.getPlayer().getUniqueID()); }
                }

                if (rand.nextInt(10) == 0) {
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPlayer().getPosition(), SpawnReason.EVENT, false, true);

                    if (!event.getTarget().getEntityWorld().isRemote) { event.getPlayer().sendMessage(
                            new StringTextComponent("[").mergeStyle(TextFormatting.GOLD).appendSibling(new TranslationTextComponent(target.getType().getTranslationKey()))
                                    .appendString("] ").appendSibling(new StringTextComponent("Oi! Stop that!").mergeStyle(TextFormatting.WHITE)), event.getPlayer().getUniqueID()); }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackMatheus(AttackEntityEvent event) {
        if (event.getTarget() instanceof NPCMatheusEntity) {
            if (event.getTarget().getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getTarget().getEntityWorld();
                LivingEntity target = (LivingEntity) event.getTarget();

                ModEntityType.KNIGHT.get().spawn(serverWorld, null, null, target.getPosition().add(0, 1, 0), SpawnReason.EVENT, false, true);
                String taskMessage = TextFormatting.RED + "[Knight] " + TextFormatting.WHITE + "CEASE!"; if (!event.getPlayer().getEntityWorld().isRemote) { event.getPlayer().sendMessage(new StringTextComponent(taskMessage), event.getPlayer().getUniqueID()); }
            }
        }
    }

    @SubscribeEvent
    public static void onHoldTTGloveInOffhand(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemOffhand().getItem() == ModItems.TOTALLY_TUBULAR_GLOVE.get()) {
            if (player.world.getDimensionKey().equals(Yeehaw.PILOT_RED_SUN)) {
                ItemStack itemstack = player.getHeldItemOffhand();
                int itemCount = itemstack.getCount();
                player.addPotionEffect(new EffectInstance(Effects.SPEED, 2, (int)Math.floor((float)itemCount/2), false, false));
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickMGWithEmerald(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand().equals(Hand.MAIN_HAND)) {
            LivingEntity player = event.getEntityLiving();
            World world = player.getEntityWorld();
            if ( (player.getHeldItemMainhand().getItem() == Items.EMERALD) && (world.getBlockState(event.getPos()) == ModBlocks.MAGIC_GOLD_BLOCK.get().getDefaultState()) ) {
                if (player.getTags().contains("Quest7")) {
                    if ( (world.getBlockState(event.getPos().add(1,1,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(1,2,0)) == Blocks.BEDROCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(1,1,0), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(1,2,0), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest7");
                    }
                    else if ( (world.getBlockState(event.getPos().add(-1,1,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-1,2,0)) == Blocks.BEDROCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(-1,1,0), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(-1,2,0), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest7");
                    }
                    else if ( (world.getBlockState(event.getPos().add(0,1,1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,1)) == Blocks.BEDROCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(0,1,1), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(0,2,1), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest7");
                    }
                    else if ( (world.getBlockState(event.getPos().add(0,1,-1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-1)) == Blocks.BEDROCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(0,1,-1), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(0,2,-1), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest7");
                    }
                }
                if (player.getTags().contains("Quest8")) {
                    if ( (world.getBlockState(event.getPos().add(1,1,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(1,2,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,2)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,4)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-2)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-4)) == Blocks.EMERALD_BLOCK.getDefaultState())) {
                        world.setBlockState(event.getPos().add(1,1,0), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(1,2,0), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest8"); player.addTag("Quest9");
                        player.addTag("GotTheosKey"); player.addTag("GotTheosHeavensKey");
                    }
                    else if ( (world.getBlockState(event.getPos().add(-1,1,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-1,2,0)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,2)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,4)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-2)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-4)) == Blocks.EMERALD_BLOCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(-1,1,0), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(-1,2,0), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest8"); player.addTag("Quest9");
                        player.addTag("GotTheosKey"); player.addTag("GotTheosHeavensKey");
                    }
                    else if ( (world.getBlockState(event.getPos().add(0,1,1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(2,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(4,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-2,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-4,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(0,1,1), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(0,2,1), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest8"); player.addTag("Quest9");
                        player.addTag("GotTheosKey"); player.addTag("GotTheosHeavensKey");
                    }
                    else if ( (world.getBlockState(event.getPos().add(0,1,-1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(0,2,-1)) == Blocks.BEDROCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(2,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(4,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-2,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) &&
                            (world.getBlockState(event.getPos().add(-4,2,0)) == Blocks.EMERALD_BLOCK.getDefaultState()) ) {
                        world.setBlockState(event.getPos().add(0,1,-1), Blocks.AIR.getDefaultState());
                        world.setBlockState(event.getPos().add(0,2,-1), Blocks.AIR.getDefaultState());
                        player.removeTag("Quest8"); player.addTag("Quest9");
                        player.addTag("GotTheosKey"); player.addTag("GotTheosHeavensKey");
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackAngel(AttackEntityEvent event) {
        if (event.getTarget() instanceof AngelEntity) {
            event.getPlayer().playSound(SoundEvents.ENTITY_GHAST_HURT, 0.8f, 1.1f);
            Random rand = new Random();

            if (event.getTarget().getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getTarget().getEntityWorld();

                if (rand.nextInt(6) == 0) {

                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getPlayer();
                    ServerWorld targetWorld = serverPlayer.server.getWorld(Yeehaw.THEOS);
                    float targetY = 250;
                    while (targetWorld.getBlockState(BlockPos.ZERO.add(serverPlayer.getPosX(), targetY, serverPlayer.getPosZ())) == Blocks.AIR.getDefaultState()) {
                        targetY--;
                    }
                    Vector3d targetVec = new Vector3d(serverPlayer.getPosX(), targetY, serverPlayer.getPosZ());
                    // ensure destination chunk is loaded before we put the player in it
                    targetWorld.getChunk(new BlockPos(targetVec));
                    serverPlayer.teleport(targetWorld, targetVec.getX(), targetVec.getY(), targetVec.getZ(), serverPlayer.rotationYaw, serverPlayer.rotationPitch);
                    String taskMessage = TextFormatting.RED + "[Angel] " + TextFormatting.WHITE + "BEGONE!"; if (!event.getPlayer().getEntityWorld().isRemote) { event.getPlayer().sendMessage(new StringTextComponent(taskMessage), event.getPlayer().getUniqueID()); }
                } else {
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPlayer().getPosition(), SpawnReason.EVENT, false, true);
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPlayer().getPosition().add(2,0,1), SpawnReason.EVENT, false, true);
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPlayer().getPosition().add(0,0,3), SpawnReason.EVENT, false, true);
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPlayer().getPosition().add(-2,0,2), SpawnReason.EVENT, false, true);
                    String taskMessage = TextFormatting.RED + "[Angel] " + TextFormatting.WHITE + "STOP!"; if (!event.getPlayer().getEntityWorld().isRemote) { event.getPlayer().sendMessage(new StringTextComponent(taskMessage), event.getPlayer().getUniqueID()); }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBreakTheosianCrystal(BlockEvent.BreakEvent event) {
        if (event.getState() == ModBlocks.THEOSIAN_CRYSTAL.get().getDefaultState()) {
            Random rand = new Random();
            int picker = rand.nextInt(6);

            if (event.getPlayer().getEntityWorld() instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) event.getPlayer().getEntityWorld();
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getPlayer();
                serverWorld.playSound(event.getPlayer(), event.getPlayer().getPosition(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                        SoundCategory.AMBIENT, 1.0f, 1.0f);

                if (picker == 0) {
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    serverPlayer.teleport(serverWorld, serverPlayer.getPosX(), serverPlayer.getPosY() + 20, serverPlayer.getPosZ(), serverPlayer.rotationYaw, serverPlayer.rotationPitch);
                }
                else if (picker == 1){
                    EntityType.GHAST.spawn(serverWorld, null, null, event.getPos().add(0,8,0), SpawnReason.EVENT, false, true);
                }
                else if (picker == 2){
                    for (int i = 0; i <= 20; i++) {
                        EntityType.ENDERMITE.spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    }
                }
                else if (picker == 3){
                    ModEntityType.FELONIOUS_BOLUS.get().spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    ModEntityType.FELONIOUS_BOLUS.get().spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    ModEntityType.FELONIOUS_BOLUS.get().spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                }
                else if (picker == 4){
                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    event.getPlayer().addPotionEffect(new EffectInstance(Effects.WITHER, 20*10, 2, false, false));
                    event.getPlayer().addPotionEffect(new EffectInstance(Effects.LEVITATION, 20*5, 2, false, false));
                } else {
                    for (int i = 0; i <= 5; i++) {
                        ModEntityType.GNOME_ELDER.get().spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHerobrineDeath(EntityLeaveWorldEvent event) {
        if (event.getEntity() instanceof HerobrineEntity) {
            if (!event.getWorld().getClosestPlayer(event.getEntity(), 1000).getTags().contains("KilledHerobrine")) {
                if (event.getEntity().getEntityWorld() instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld) event.getEntity().getEntityWorld();
                    serverWorld.getClosestPlayer(event.getEntity(), 1000).addTag("KilledHerobrine");
                    serverWorld.getClosestPlayer(event.getEntity(), 1000).addTag("Quest13");
                    ModEntityType.NPC_KENT.get().spawn(serverWorld, null, null, event.getEntity().getPosition(), SpawnReason.EVENT, false, true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKilledWithHarvester(EntityLeaveWorldEvent event) {
        if (event.getEntity() instanceof MobEntity) {
            if (event.getWorld().isPlayerWithin(event.getEntity().getPosX(), event.getEntity().getPosY(),event.getEntity().getPosZ(),40)) {
                if (event.getWorld().getClosestPlayer(event.getEntity(), 40).getHeldItemMainhand().getItem() == ModItems.THE_HARVESTER.get()) {
                    Random rand = new Random();
                    if (rand.nextInt(20) == 0) {
                        event.getEntity().entityDropItem(ModItems.TATTERED_SOUL.get());

                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackWolf(AttackEntityEvent event) {
        if (event.getTarget() instanceof WolfEntity) {
            if (((WolfEntity) event.getTarget()).isTamed()) {
                Random rand = new Random();
                if (rand.nextInt(20) == 0) {
                    if (event.getPlayer().getEntityWorld() instanceof ServerWorld) {
                        ServerWorld serverWorld = (ServerWorld) event.getPlayer().getEntityWorld();
                        ModEntityType.KNIGHT.get().spawn(serverWorld, null, null, event.getEntity().getPosition(), SpawnReason.EVENT, false, true);
                        String msg = TextFormatting.RED + "[PPA Enforcer] " + TextFormatting.WHITE + "In the name of the law, halt! You are under arrest for pet assault!"; event.getPlayer().sendMessage(new StringTextComponent(msg), event.getPlayer().getUniqueID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHitWithYS(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.YOUNGLING_SLAYER.get()) {
            if ( (event.getTarget() instanceof ZombieEntity) || (event.getTarget() instanceof ZombifiedPiglinEntity) ||
                    (event.getTarget() instanceof PiglinEntity)) {
                LivingEntity target = (LivingEntity) event.getTarget();

                if (target.isChild()) {
                    target.setHealth(0);
                    for (int i = 1; i <= 150; i++) {
                        target.world.addParticle(ParticleTypes.CRIMSON_SPORE, target.getPosXRandom(1.0D), target.getPosYRandom(), target.getPosZRandom(1.0D), 2 * (rand.nextDouble() - rand.nextDouble()), 2 * rand.nextDouble(), 2 * (rand.nextDouble() - rand.nextDouble()));
                    }
                    for (int i = 1; i <= 100; i++) {
                        target.world.addParticle(ParticleTypes.SMOKE, target.getPosXRandom(1.0D), target.getPosYRandom(), target.getPosZRandom(1.0D), 2 * (rand.nextDouble() - rand.nextDouble()), 2 * rand.nextDouble(), 2 * (rand.nextDouble() - rand.nextDouble()));
                    }
                    event.getPlayer().getEntityWorld().playSound(event.getPlayer(), event.getPlayer().getPosition(), SoundInit.DESTROY_THE_CHILD.get(), SoundCategory.AMBIENT, 1.4f, 1.0f);
                } else {
                    if (event.getTarget().isAlive() && rand.nextInt(20) == 0 && target.getHealth() - 5 > 0) {
                        PlayerEntity player = event.getPlayer();
                        if (!event.getPlayer().getEntityWorld().isRemote) {
                            String msg = TextFormatting.AQUA + "Grody! You slice off its limb!"; player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                        }
                        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20*5, 0, false, false));
                        target.setHealth(target.getHealth() - 5);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onHitWithAgaricCleaver(AttackEntityEvent event) {
        Random rand = new Random();
        if (event.getEntityLiving().getHeldItemMainhand().getItem() == ModItems.AGARIC_CLEAVER.get() && event.getTarget().isAlive()) {
            LivingEntity target = (LivingEntity) event.getTarget();
            if (rand.nextInt(12) == 0) {
                target.addPotionEffect(new EffectInstance(Effects.WITHER, 20*5, 0, false, false));
                event.getPlayer().addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 20 * 30, 0, false, false));
                event.getPlayer().addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 5, 0, false, false));
            }
        }
    }

    @SubscribeEvent
    public static void onEnderDragonDeath(EntityLeaveWorldEvent event) {
        if (event.getEntity() instanceof EnderDragonEntity) {
            PlayerEntity player = event.getWorld().getClosestPlayer(event.getEntity(), 400);
            player.entityDropItem(ModItems.ETHERIUM.get());
            player.entityDropItem(ModItems.ETHERIUM.get());
            player.entityDropItem(ModItems.ETHERIUM.get());
            player.entityDropItem(ModItems.ENDER_DRAGON_SOUL.get());
        }
    }

    @SubscribeEvent
    public static void lightWhenHoldingTorch(LivingEvent.LivingUpdateEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.TORCH || player.getHeldItemOffhand().getItem() == Items.TORCH) {
            if (player.getEntityWorld().getBlockState(player.getPosition().add(0, 1, 0)) == Blocks.AIR.getDefaultState() ||
                    player.getEntityWorld().getBlockState(player.getPosition().add(0, 1, 0)) == Blocks.CAVE_AIR.getDefaultState() ||
                    player.getEntityWorld().getBlockState(player.getPosition().add(0, 1, 0)) == Blocks.VOID_AIR.getDefaultState()) {
                player.getEntityWorld().setBlockState(player.getPosition().add(0, 1, 0), ModBlocks.GLOWING_AIR_BLOCK.get().getDefaultState());
            }
        }
    }
}
