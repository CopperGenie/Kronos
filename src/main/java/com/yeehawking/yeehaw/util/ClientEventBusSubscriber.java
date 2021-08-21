package com.yeehawking.yeehaw.util;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.render.*;
import com.yeehawking.yeehaw.entities.KnightEntity;
import com.yeehawking.yeehaw.init.ModBlocks;
import com.yeehawking.yeehaw.init.ModEntityType;
import com.yeehawking.yeehaw.items.ModSpawnEggItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Yeehaw.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.TOADKIN.get(), ToadkinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.TOAD_MOTHER.get(), ToadMotherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.MIND_FLAYER.get(), MindFlayerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.LICH.get(), LichRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.KRAKEN.get(), KrakenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.GENIE.get(), GenieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.CACTOID.get(), CactoidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.THE_RESTLESS.get(), TheRestlessRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.BEHOLDER.get(), BeholderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.GNOME.get(), GnomeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.SHADOW_DEMON.get(), ShadowDemonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.GNOME_ELDER.get(), GnomeElderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.TOAD_FATHER.get(), ToadFatherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NIMBUS_GIANT.get(), NimbusGiantRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.GIANT.get(), GiantRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.TROLL.get(), TrollRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.CREEPING_HORROR.get(), CreepingHorrorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.TREASURE_FAIRY.get(), TreasureFairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.ALLIED_KNIGHT.get(), AlliedKnightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.DIONYSUS.get(), DionysusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.ROSE_QUEEN.get(), RoseQueenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.HEROBRINE.get(), HerobrineRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.FLINT_MONGER.get(), FlintMongerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.SOUL_TRADER.get(), SoulTraderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.BIG_SHAQ.get(), BigShaqRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_SLAYER.get(), NPCSlayerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_AELYMORE.get(), NPCAelymoreRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_TENEBRIK.get(), NPCTenebrikRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_OSWIR.get(), NPCOswirRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_PERAGON.get(), NPCPeragonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_PHANTASMUS.get(), NPCPhantasmusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.KNIGHT.get(), KnightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.KNIGHT_COMMANDER.get(), KnightCommanderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.DARK_KNIGHT.get(), DarkKnightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.FELONIOUS_BOLUS.get(), FeloniousBolusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.THE_POLICE.get(), ThePoliceRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.X0026G.get(), x0026gRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_KRORK.get(), NPCKrorkRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_GASMAN.get(), NPCGasmanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_WYNNBRIM.get(), NPCWynnbrimRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_SCHOLAR.get(), NPCScholarRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_JAFIR.get(), NPCJafirRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_MATHEUS.get(), NPCMatheusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_ELIZABETH.get(), NPCElizabethRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.ANGEL.get(), AngelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_KENT.get(), NPCKentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_TIMEKEEPER.get(), NPCTimekeeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.NPC_TOMMY_TEMPUS.get(), NPCTommyTempusRenderer::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.TWILIGHT_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLACK_IRIS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.FORBIDDEN_FRUIT_CROPS_BLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.THEOSIAN_CRYSTAL.get(), RenderType.getTranslucent());


    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
