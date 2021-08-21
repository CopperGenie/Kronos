package com.yeehawking.yeehaw;

import com.yeehawking.yeehaw.entities.*;
import com.yeehawking.yeehaw.init.*;
import com.yeehawking.yeehaw.world.STStructures;
import com.yeehawking.yeehaw.world.structures.Dungeon1;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod("yeehaw")
@Mod.EventBusSubscriber(modid = Yeehaw.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Yeehaw
{


    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "yeehaw";

    public static RegistryKey<World> CURSED_FOREST;
    public static RegistryKey<World> ERODED_DESERT;
    public static RegistryKey<World> SKYPIERCERS;
    public static RegistryKey<World> NOWHERE;
    public static RegistryKey<World> CELIRIA;
    public static RegistryKey<World> THE_KINGDOM;
    public static RegistryKey<World> REALM_OF_PHANTASMUS;
    public static RegistryKey<World> THE_DUNGEON;
    public static RegistryKey<World> PILOT_RED_SUN;
    public static RegistryKey<World> THEOS;
    public static RegistryKey<World> THEOS_HEAVENS;
    public static RegistryKey<World> THEOS_UNDERWORLD;

    public Yeehaw() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        SoundInit.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileEntityType.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        STStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);

        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityType.TOADKIN.get(), ToadkinEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.TOAD_MOTHER.get(), ToadMotherEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.MIND_FLAYER.get(), MindFlayerEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.LICH.get(), LichEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.KRAKEN.get(), KrakenEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.GENIE.get(), GenieEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.CACTOID.get(), CactoidEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.THE_RESTLESS.get(), TheRestlessEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.BEHOLDER.get(), BeholderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.GNOME.get(), BeholderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.SHADOW_DEMON.get(), ShadowDemonEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.GNOME_ELDER.get(), GnomeElderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.TOAD_FATHER.get(), ToadFatherEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NIMBUS_GIANT.get(), NimbusGiantEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.GIANT.get(), GiantEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.TROLL.get(), TrollEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.CREEPING_HORROR.get(), CreepingHorrorEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.TREASURE_FAIRY.get(), TreasureFairyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.ALLIED_KNIGHT.get(), AlliedKnightEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.DIONYSUS.get(), DionysusEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.ROSE_QUEEN.get(), RoseQueenEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.HEROBRINE.get(), HerobrineEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.FLINT_MONGER.get(), FlintMongerEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.SOUL_TRADER.get(), SoulTraderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_SLAYER.get(), NPCSlayerEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_AELYMORE.get(), NPCAelymoreEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_TENEBRIK.get(), NPCTenebrikEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_OSWIR.get(), NPCOswirEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_PERAGON.get(), NPCPeragonEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_PHANTASMUS.get(), NPCPhantasmusEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.KNIGHT.get(), KnightEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.KNIGHT_COMMANDER.get(), KnightCommanderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.DARK_KNIGHT.get(), DarkKnightEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.FELONIOUS_BOLUS.get(), FeloniousBolusEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.THE_POLICE.get(), ThePoliceEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.X0026G.get(), x0026gEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_GASMAN.get(), NPCGasmanEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_KRORK.get(), NPCKrorkEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_WYNNBRIM.get(), NPCWynnbrimEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_SCHOLAR.get(), NPCScholarEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_JAFIR.get(), NPCJafirEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_MATHEUS.get(), NPCMatheusEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_ELIZABETH.get(), NPCElizabethEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.ANGEL.get(), AngelEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_KENT.get(), NPCKentEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_TIMEKEEPER.get(), NPCTimekeeperEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityType.NPC_TOMMY_TEMPUS.get(), NPCTommyTempusEntity.setCustomAttributes().create());

            CURSED_FOREST = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "cursed_forest"));
            ERODED_DESERT = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "eroded_desert"));
            SKYPIERCERS = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "skypiercers"));
            NOWHERE = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "nowhere"));
            CELIRIA = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "celiria"));
            THE_KINGDOM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "the_kingdom"));
            REALM_OF_PHANTASMUS = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "realm_of_phantasmus"));
            THE_DUNGEON = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "the_dungeon"));
            PILOT_RED_SUN = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "pilot_red_sun"));
            THEOS = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "theos"));
            THEOS_HEAVENS = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "theos_heavens"));
            THEOS_UNDERWORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("yeehaw", "theos_underworld"));

            Registry.STRUCTURE_FEATURE.getValueForKey(RegistryKey.getOrCreateKey(Registry.STRUCTURE_FEATURE_KEY, new ResourceLocation("yeehaw", "dungeon1")));

        });

        event.enqueueWork(STStructures::setupStructures);
    }

    // Creative inventory tabs
    public static final ItemGroup TAB = new ItemGroup("yeehawTab") {
        @Override
        public ItemStack createIcon() { return new ItemStack(ModItems.EYE_OF_THE_BEHOLDER.get()); }};

    public static final ItemGroup BLOCK_TAB = new ItemGroup("blockTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TITAN_BLOCK_ITEM.get());
        }};

    public static final ItemGroup COMBAT_TAB = new ItemGroup("combatTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TITAN_CHESTPLATE.get());
        }};

    public static final ItemGroup FOOD_TAB = new ItemGroup("foodTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.SEGGRED_TEGGSTS.get());
        }};

    public static final ItemGroup TOOL_TAB = new ItemGroup("toolTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.PATHWINDER.get());
        }};

    public static final ItemGroup SUMMON_TAB = new ItemGroup("summonTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.KNIGHT_COMMANDER_SPAWN_EGG.get());
        }};

    public static final ItemGroup SCROLL_TAB = new ItemGroup("scrollTab") {
        @Override
        public ItemStack createIcon() { return new ItemStack(ModItems.SCROLL_OF_DISSOCIATION.get()); }};

    public static final ItemGroup KEY_TAB = new ItemGroup("keyTab") {
        @Override
        public ItemStack createIcon() { return new ItemStack(ModItems.WORLD_KEY_CELIRIA.get()); }};

    public static final ItemGroup GEM_TAB = new ItemGroup("gemTab") {
        @Override
        public ItemStack createIcon() { return new ItemStack(ModItems.TELEPORT_GEM_THE_LIBRARY.get()); }};


    /**
     * Helper method to quickly register features, blocks, items, structures, biomes--anything that can be registered.
     */
    public static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T entry, String registryKey) {
        entry.setRegistryName(new ResourceLocation(Yeehaw.MOD_ID, registryKey));
        registry.register(entry);
        return entry;
    }
}
