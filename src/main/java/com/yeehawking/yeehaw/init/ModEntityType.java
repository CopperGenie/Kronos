package com.yeehawking.yeehaw.init;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.render.GnomeElderRenderer;
import com.yeehawking.yeehaw.entities.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityType {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Yeehaw.MOD_ID);

    // Entity types
    public static final RegistryObject<EntityType<ToadkinEntity>> TOADKIN = ENTITY_TYPES.register("toadkin",
            () -> EntityType.Builder.create(ToadkinEntity::new, EntityClassification.CREATURE)
                    .size(0.55f, 0.7f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "toadkin").toString()));
    public static final RegistryObject<EntityType<ToadMotherEntity>> TOAD_MOTHER = ENTITY_TYPES.register("toad_mother",
            () -> EntityType.Builder.create(ToadMotherEntity::new, EntityClassification.MONSTER)
                    .size(0.7f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "toad_mother").toString()));
    public static final RegistryObject<EntityType<BeholderEntity>> BEHOLDER = ENTITY_TYPES.register("beholder",
            () -> EntityType.Builder.create(BeholderEntity::new, EntityClassification.MONSTER)
                    .size(1.5f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "beholder").toString()));
    public static final RegistryObject<EntityType<MindFlayerEntity>> MIND_FLAYER = ENTITY_TYPES.register("mind_flayer",
            () -> EntityType.Builder.create(MindFlayerEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "mind_flayer").toString()));
    public static final RegistryObject<EntityType<LichEntity>> LICH = ENTITY_TYPES.register("lich",
            () -> EntityType.Builder.create(LichEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "lich").toString()));
    public static final RegistryObject<EntityType<KrakenEntity>> KRAKEN = ENTITY_TYPES.register("kraken",
            () -> EntityType.Builder.create(KrakenEntity::new, EntityClassification.MONSTER)
                    .size(2f, 2f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "kraken").toString()));
    public static final RegistryObject<EntityType<GenieEntity>> GENIE = ENTITY_TYPES.register("genie",
            () -> EntityType.Builder.create(GenieEntity::new, EntityClassification.MONSTER)
                    .size(1.6f, 2.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "genie").toString()));
    public static final RegistryObject<EntityType<CactoidEntity>> CACTOID = ENTITY_TYPES.register("cactoid",
            () -> EntityType.Builder.create(CactoidEntity::new, EntityClassification.MONSTER)
                    .size(0.7f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "cactoid").toString()));
    public static final RegistryObject<EntityType<TheRestlessEntity>> THE_RESTLESS = ENTITY_TYPES.register("the_restless",
            () -> EntityType.Builder.create(TheRestlessEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "the_restless").toString()));
    public static final RegistryObject<EntityType<GnomeEntity>> GNOME = ENTITY_TYPES.register("gnome",
            () -> EntityType.Builder.create(GnomeEntity::new, EntityClassification.CREATURE)
                    .size(0.3f, 1.0f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "gnome").toString()));
    public static final RegistryObject<EntityType<GnomeElderEntity>> GNOME_ELDER = ENTITY_TYPES.register("gnome_elder",
            () -> EntityType.Builder.create(GnomeElderEntity::new, EntityClassification.MONSTER)
                    .size(0.3f, 1.0f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "gnome_elder").toString()));
    public static final RegistryObject<EntityType<ShadowDemonEntity>> SHADOW_DEMON = ENTITY_TYPES.register("shadow_demon",
            () -> EntityType.Builder.create(ShadowDemonEntity::new, EntityClassification.MONSTER)
                    .size(1.6f, 4.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "shadow_demon").toString()));
    public static final RegistryObject<EntityType<ToadFatherEntity>> TOAD_FATHER = ENTITY_TYPES.register("toad_father",
            () -> EntityType.Builder.create(ToadFatherEntity::new, EntityClassification.MONSTER)
                    .size(1.4f, 6f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "toad_father").toString()));
    public static final RegistryObject<EntityType<NimbusGiantEntity>> NIMBUS_GIANT = ENTITY_TYPES.register("nimbus_giant",
            () -> EntityType.Builder.create(NimbusGiantEntity::new, EntityClassification.MONSTER)
                    .size(2.2f, 4f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "nimbus_giant").toString()));
    public static final RegistryObject<EntityType<GiantEntity>> GIANT = ENTITY_TYPES.register("giant",
            () -> EntityType.Builder.create(GiantEntity::new, EntityClassification.CREATURE)
                    .size(2f, 8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "giant").toString()));
    public static final RegistryObject<EntityType<TrollEntity>> TROLL = ENTITY_TYPES.register("troll",
            () -> EntityType.Builder.create(TrollEntity::new, EntityClassification.MONSTER)
                    .size(0.8f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "troll").toString()));
    public static final RegistryObject<EntityType<CreepingHorrorEntity>> CREEPING_HORROR = ENTITY_TYPES.register("creeping_horror",
            () -> EntityType.Builder.create(CreepingHorrorEntity::new, EntityClassification.MONSTER)
                    .size(4.2f, 3.7f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "creeping_horror").toString()));
    public static final RegistryObject<EntityType<TreasureFairyEntity>> TREASURE_FAIRY = ENTITY_TYPES.register("treasure_fairy",
            () -> EntityType.Builder.create(TreasureFairyEntity::new, EntityClassification.MONSTER)
                    .size(0.5f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "treasure_fairy").toString()));
    public static final RegistryObject<EntityType<AlliedKnightEntity>> ALLIED_KNIGHT = ENTITY_TYPES.register("allied_knight",
            () -> EntityType.Builder.create(AlliedKnightEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "allied_knight").toString()));
    public static final RegistryObject<EntityType<DionysusEntity>> DIONYSUS = ENTITY_TYPES.register("dionysus",
            () -> EntityType.Builder.create(DionysusEntity::new, EntityClassification.MONSTER)
                    .size(2.0f, 3.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "dionysus").toString()));
    public static final RegistryObject<EntityType<RoseQueenEntity>> ROSE_QUEEN = ENTITY_TYPES.register("rose_queen",
            () -> EntityType.Builder.create(RoseQueenEntity::new, EntityClassification.MONSTER)
                    .size(1.0f, 3.2f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "rose_queen").toString()));
    public static final RegistryObject<EntityType<HerobrineEntity>> HEROBRINE = ENTITY_TYPES.register("herobrine",
            () -> EntityType.Builder.create(HerobrineEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "herobrine").toString()));
    public static final RegistryObject<EntityType<FlintMongerEntity>> FLINT_MONGER = ENTITY_TYPES.register("flint_monger",
            () -> EntityType.Builder.create(FlintMongerEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "flint_monger").toString()));
    public static final RegistryObject<EntityType<SoulTraderEntity>> SOUL_TRADER = ENTITY_TYPES.register("soul_trader",
            () -> EntityType.Builder.create(SoulTraderEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "soul_trader").toString()));
    public static final RegistryObject<EntityType<BigShaqEntity>> BIG_SHAQ = ENTITY_TYPES.register("big_shaq",
            () -> EntityType.Builder.create(BigShaqEntity::new, EntityClassification.MISC)
                    .size(4f, 4f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "big_shaq").toString()));
    public static final RegistryObject<EntityType<NPCSlayerEntity>> NPC_SLAYER = ENTITY_TYPES.register("npc_slayer",
            () -> EntityType.Builder.create(NPCSlayerEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_slayer").toString()));
    public static final RegistryObject<EntityType<NPCAelymoreEntity>> NPC_AELYMORE = ENTITY_TYPES.register("npc_aelymore",
            () -> EntityType.Builder.create(NPCAelymoreEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_aelymore").toString()));
    public static final RegistryObject<EntityType<NPCTenebrikEntity>> NPC_TENEBRIK = ENTITY_TYPES.register("npc_tenebrik",
            () -> EntityType.Builder.create(NPCTenebrikEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_tenebrik").toString()));
    public static final RegistryObject<EntityType<NPCOswirEntity>> NPC_OSWIR = ENTITY_TYPES.register("npc_oswir",
            () -> EntityType.Builder.create(NPCOswirEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_oswir").toString()));
    public static final RegistryObject<EntityType<NPCPeragonEntity>> NPC_PERAGON = ENTITY_TYPES.register("npc_peragon",
            () -> EntityType.Builder.create(NPCPeragonEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_peragon").toString()));
    public static final RegistryObject<EntityType<NPCPhantasmusEntity>> NPC_PHANTASMUS = ENTITY_TYPES.register("npc_phantasmus",
            () -> EntityType.Builder.create(NPCPhantasmusEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_phantasmus").toString()));
    public static final RegistryObject<EntityType<KnightEntity>> KNIGHT = ENTITY_TYPES.register("knight",
            () -> EntityType.Builder.create(KnightEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "knight").toString()));
    public static final RegistryObject<EntityType<KnightCommanderEntity>> KNIGHT_COMMANDER = ENTITY_TYPES.register("knight_commander",
            () -> EntityType.Builder.create(KnightCommanderEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "knight_commander").toString()));
    public static final RegistryObject<EntityType<DarkKnightEntity>> DARK_KNIGHT = ENTITY_TYPES.register("dark_knight",
            () -> EntityType.Builder.create(DarkKnightEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "dark_knight").toString()));
    public static final RegistryObject<EntityType<FeloniousBolusEntity>> FELONIOUS_BOLUS = ENTITY_TYPES.register("felonious_bolus",
            () -> EntityType.Builder.create(FeloniousBolusEntity::new, EntityClassification.MONSTER)
                    .size(1.45f, 1.45f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "felonious_bolus").toString()));
    public static final RegistryObject<EntityType<x0026gEntity>> X0026G = ENTITY_TYPES.register("x0026g",
            () -> EntityType.Builder.create(x0026gEntity::new, EntityClassification.MONSTER)
                    .size(1.8f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "x0026g").toString()));
    public static final RegistryObject<EntityType<ThePoliceEntity>> THE_POLICE = ENTITY_TYPES.register("the_police",
            () -> EntityType.Builder.create(ThePoliceEntity::new, EntityClassification.MONSTER)
                    .size(1.8f, 1.5f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "the_police").toString()));
    public static final RegistryObject<EntityType<NPCGasmanEntity>> NPC_GASMAN = ENTITY_TYPES.register("npc_gasman",
            () -> EntityType.Builder.create(NPCGasmanEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_gasman").toString()));
    public static final RegistryObject<EntityType<NPCKrorkEntity>> NPC_KRORK = ENTITY_TYPES.register("npc_krork",
            () -> EntityType.Builder.create(NPCKrorkEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_krork").toString()));
    public static final RegistryObject<EntityType<NPCScholarEntity>> NPC_SCHOLAR = ENTITY_TYPES.register("npc_scholar",
            () -> EntityType.Builder.create(NPCScholarEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_scholar").toString()));
    public static final RegistryObject<EntityType<NPCWynnbrimEntity>> NPC_WYNNBRIM = ENTITY_TYPES.register("npc_wynnbrim",
            () -> EntityType.Builder.create(NPCWynnbrimEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_wynnbrim").toString()));
    public static final RegistryObject<EntityType<NPCJafirEntity>> NPC_JAFIR = ENTITY_TYPES.register("npc_jafir",
            () -> EntityType.Builder.create(NPCJafirEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_jafir").toString()));
    public static final RegistryObject<EntityType<NPCMatheusEntity>> NPC_MATHEUS = ENTITY_TYPES.register("npc_matheus",
            () -> EntityType.Builder.create(NPCMatheusEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_matheus").toString()));
    public static final RegistryObject<EntityType<NPCElizabethEntity>> NPC_ELIZABETH = ENTITY_TYPES.register("npc_elizabeth",
            () -> EntityType.Builder.create(NPCElizabethEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_elizabeth").toString()));
    public static final RegistryObject<EntityType<AngelEntity>> ANGEL = ENTITY_TYPES.register("angel",
            () -> EntityType.Builder.create(AngelEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 2.3f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "angel").toString()));
    public static final RegistryObject<EntityType<NPCKentEntity>> NPC_KENT = ENTITY_TYPES.register("npc_kent",
            () -> EntityType.Builder.create(NPCKentEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_kent").toString()));
    public static final RegistryObject<EntityType<NPCTimekeeperEntity>> NPC_TIMEKEEPER = ENTITY_TYPES.register("npc_timekeeper",
            () -> EntityType.Builder.create(NPCTimekeeperEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_timekeeper").toString()));
    public static final RegistryObject<EntityType<NPCTommyTempusEntity>> NPC_TOMMY_TEMPUS = ENTITY_TYPES.register("npc_tommy_tempus",
            () -> EntityType.Builder.create(NPCTommyTempusEntity::new, EntityClassification.CREATURE)
                    .size(0.6f, 1.8f)
                    .build(new ResourceLocation(Yeehaw.MOD_ID, "npc_tommy_tempus").toString()));
}
