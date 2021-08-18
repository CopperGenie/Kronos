package com.yeehawking.yeehaw.init;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.blocks.AnticreeperNode;
import com.yeehawking.yeehaw.blocks.GlowingAirBlock;
import com.yeehawking.yeehaw.items.BlockItemBase;
import com.yeehawking.yeehaw.items.*;
import com.yeehawking.yeehaw.util.enums.ModItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Yeehaw.MOD_ID);

    // Items
    public static final RegistryObject<Item> ARCANE_EMULSIFIER = ITEMS.register("arcane_emulsifier", ItemBase::new);
    public static final RegistryObject<Item> BIODIESEL = ITEMS.register("biodiesel", ItemBase::new);
    public static final RegistryObject<Item> BLANK_SCROLL = ITEMS.register("blank_scroll", ItemBase::new);
    public static final RegistryObject<Item> BRIMSTONE = ITEMS.register("brimstone", ItemBase::new);
    public static final RegistryObject<Item> CLOUDSTONE = ITEMS.register("cloudstone", ItemBase::new);
    public static final RegistryObject<Item> COSMIC_EYE = ITEMS.register("cosmic_eye", ItemBase::new);
    public static final RegistryObject<Item> CRYSTAL_SHARD = ITEMS.register("crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> CUBIC_ROSEUM = ITEMS.register("cubic_roseum", ItemBase::new);
    public static final RegistryObject<Item> DATA_CHRONICLE = ITEMS.register("data_chronicle", ItemBase::new);
    public static final RegistryObject<Item> DECRYPTED_HOMING_KEY = ITEMS.register("decrypted_homing_key", DecryptedHomingKey::new);
    public static final RegistryObject<Item> ENCHANTED_DUST = ITEMS.register("enchanted_dust", ItemBase::new);
    public static final RegistryObject<Item> ESSENCE_OF_MAN = ITEMS.register("essence_of_man", ItemBase::new);
    public static final RegistryObject<Item> ETHERIUM = ITEMS.register("etherium", ItemBase::new);
    public static final RegistryObject<Item> EYE_OF_THE_BEHOLDER = ITEMS.register("eye_of_the_beholder", ItemBase::new);
    public static final RegistryObject<Item> FORBIDDEN_FRUIT = ITEMS.register("forbidden_fruit",
            () -> new ForbiddenFruit(ModBlocks.FORBIDDEN_FRUIT_CROPS_BLOCK.get()));
    public static final RegistryObject<Item> FUNGAL_ESSENCE = ITEMS.register("fungal_essence", ItemBase::new);
    public static final RegistryObject<Item> FUNGAL_TITANITE = ITEMS.register("fungal_titanite", ItemBase::new);
    public static final RegistryObject<Item> GIANTS_BRANCH = ITEMS.register("giants_branch", ItemBase::new);
    public static final RegistryObject<Item> HEROBRINE_CHRONICLE = ITEMS.register("herobrine_chronicle", ItemBase::new);
    public static final RegistryObject<Item> HEXAGONAL_ROSEUM = ITEMS.register("hexagonal_roseum", ItemBase::new);
    public static final RegistryObject<Item> LOST_MEMORY = ITEMS.register("lost_memory", LostMemory::new);
    public static final RegistryObject<Item> MAGIC_GOLD = ITEMS.register("magic_gold", ItemBase::new);
    public static final RegistryObject<Item> OPAL = ITEMS.register("opal", ItemBase::new);
    public static final RegistryObject<Item> PERFECT_OPAL = ITEMS.register("perfect_opal", ItemBase::new);
    public static final RegistryObject<Item> PRIMORDIAL_SPIRIT = ITEMS.register("primordial_spirit", PrimordialSpirit::new);
    public static final RegistryObject<Item> RAW_DATA = ITEMS.register("raw_data", ItemBase::new);
    public static final RegistryObject<Item> REFINED_METEORITE = ITEMS.register("refined_meteorite", ItemBase::new);
    public static final RegistryObject<Item> SACRED_TEXTS = ITEMS.register("sacred_texts", ItemBase::new);
    public static final RegistryObject<Item> SACRED_WATER = ITEMS.register("sacred_water", ItemBase::new);
    public static final RegistryObject<Item> SOUL_STEEL = ITEMS.register("soul_steel", ItemBase::new);
    public static final RegistryObject<Item> SPACE_JUNK = ITEMS.register("space_junk", ItemBase::new);
    public static final RegistryObject<Item> SUNSET_BLADE_BLADE = ITEMS.register("sunset_blade_blade", ItemBase::new);
    public static final RegistryObject<Item> SUNSET_BLADE_HILT = ITEMS.register("sunset_blade_hilt", ItemBase::new);
    public static final RegistryObject<Item> TIME_CARD = ITEMS.register("time_card", ItemBase::new);
    public static final RegistryObject<Item> TITAN_INGOT = ITEMS.register("titan_ingot", ItemBase::new);
    public static final RegistryObject<Item> TITANITE = ITEMS.register("titanite", ItemBase::new);
    public static final RegistryObject<Item> TOAD_CAP = ITEMS.register("toad_cap", ItemBase::new);
    public static final RegistryObject<Item> TOAD_SPORES = ITEMS.register("toad_spores", ItemBase::new);
    public static final RegistryObject<Item> UNDERSTEEL = ITEMS.register("understeel", ItemBase::new);

    public static final RegistryObject<Item> TATTERED_SOUL = ITEMS.register("tattered_soul", ItemBase::new);
    public static final RegistryObject<Item> SOUL = ITEMS.register("soul", ItemBase::new);
    public static final RegistryObject<Item> GIANT_SOUL = ITEMS.register("giant_soul", ItemBase::new);

    public static final RegistryObject<Item> CREEPING_HORROR_SOUL = ITEMS.register("creeping_horror_soul", ItemBase::new);
    public static final RegistryObject<Item> DIONYSUS_SOUL = ITEMS.register("dionysus_soul", ItemBase::new);
    public static final RegistryObject<Item> ENDER_DRAGON_SOUL = ITEMS.register("ender_dragon_soul", ItemBase::new);
    public static final RegistryObject<Item> NIMBUS_GIANT_SOUL = ITEMS.register("nimbus_giant_soul", ItemBase::new);
    public static final RegistryObject<Item> ROSE_QUEEN_SOUL = ITEMS.register("rose_queen_soul", ItemBase::new);
    public static final RegistryObject<Item> SHADOW_DEMON_SOUL = ITEMS.register("shadow_demon_soul", ItemBase::new);
    public static final RegistryObject<Item> TOAD_FATHER_SOUL = ITEMS.register("toad_father_soul", ItemBase::new);

    // Food
    public static final RegistryObject<Item> APPLE_ICE_CREAM = ITEMS.register("apple_ice_cream", AppleIceCream::new);
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", Cheese::new);
    public static final RegistryObject<Item> MELON_ICE_CREAM = ITEMS.register("melon_ice_cream", MelonIceCream::new);
    public static final RegistryObject<Item> QUAALUDE = ITEMS.register("quaalude", Quaalude::new);
    public static final RegistryObject<Item> SANDVICH = ITEMS.register("sandvich", Sandvich::new);
    public static final RegistryObject<Item> SEGGRED_TEGGSTS = ITEMS.register("seggred_teggsts", SeggredTeggsts::new);
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", Tomato::new);

    public static final RegistryObject<Item> ALE = ITEMS.register("ale", Ale::new);
    public static final RegistryObject<Item> ANTIDOTE = ITEMS.register("antidote", Antidote::new);
    public static final RegistryObject<Item> CIDER = ITEMS.register("cider", Cider::new);
    public static final RegistryObject<Item> CUREALL = ITEMS.register("cureall", Cureall::new);
    public static final RegistryObject<Item> ELIXIR_OF_LIFE = ITEMS.register("elixir_of_life", ElixirOfLife::new);
    public static final RegistryObject<Item> MEAD = ITEMS.register("mead", Mead::new);
    public static final RegistryObject<Item> PANACEA = ITEMS.register("panacea", Panacea::new);
    public static final RegistryObject<Item> WINE = ITEMS.register("wine", Wine::new);


    // Scrolls
    public static final RegistryObject<Item> SCROLL_OF_ASCENSION = ITEMS.register("scroll_of_ascension", ScrollOfAscension::new);
    public static final RegistryObject<Item> SCROLL_OF_BIG_BOMB = ITEMS.register("scroll_of_big_bomb", ScrollOfBigBomb::new);
    public static final RegistryObject<Item> SCROLL_OF_BRIDGING = ITEMS.register("scroll_of_bridging", ScrollOfBridging::new);
    public static final RegistryObject<Item> SCROLL_OF_DAYLIGHT = ITEMS.register("scroll_of_daylight", ScrollOfDaylight::new);
    public static final RegistryObject<Item> SCROLL_OF_DECIMATION = ITEMS.register("scroll_of_decimation", ScrollOfDecimation::new);
    public static final RegistryObject<Item> SCROLL_OF_DESCENSION = ITEMS.register("scroll_of_descension", ScrollOfDescension::new);
    public static final RegistryObject<Item> SCROLL_OF_DISSOCIATION = ITEMS.register("scroll_of_dissociation", ScrollOfDissociation::new);
    public static final RegistryObject<Item> SCROLL_OF_EARTH_BARRIER = ITEMS.register("scroll_of_earth_barrier", ScrollOfEarthBarrier::new);
    public static final RegistryObject<Item> SCROLL_OF_ESCAPE = ITEMS.register("scroll_of_escape", ScrollOfEscape::new);
    public static final RegistryObject<Item> SCROLL_OF_EXPLORATION = ITEMS.register("scroll_of_exploration", ScrollOfExploration::new);
    public static final RegistryObject<Item> SCROLL_OF_EXPULSION = ITEMS.register("scroll_of_expulsion", ScrollOfExpulsion::new);
    public static final RegistryObject<Item> SCROLL_OF_FIRE = ITEMS.register("scroll_of_fire", ScrollOfFire::new);
    public static final RegistryObject<Item> SCROLL_OF_FLAME_WALL = ITEMS.register("scroll_of_flame_wall", ScrollOfFlameWall::new);
    public static final RegistryObject<Item> SCROLL_OF_FREEZING = ITEMS.register("scroll_of_freezing", ScrollOfFreezing::new);
    public static final RegistryObject<Item> SCROLL_OF_GODS_GRIP = ITEMS.register("scroll_of_gods_grip", ScrollOfGodsGrip::new);
    public static final RegistryObject<Item> SCROLL_OF_GUIDING_LIGHT = ITEMS.register("scroll_of_guiding_light", ScrollOfGuidingLight::new);
    public static final RegistryObject<Item> SCROLL_OF_HEALING = ITEMS.register("scroll_of_healing", ScrollOfHealing::new);
    public static final RegistryObject<Item> SCROLL_OF_HOLY_BLESSING = ITEMS.register("scroll_of_holy_blessing", ScrollOfHolyBlessing::new);
    public static final RegistryObject<Item> SCROLL_OF_IMMOBILIZATION = ITEMS.register("scroll_of_immobilization", ScrollOfImmobilization::new);
    public static final RegistryObject<Item> SCROLL_OF_IMMOLATION = ITEMS.register("scroll_of_immolation", ScrollOfImmolation::new);
    public static final RegistryObject<Item> SCROLL_OF_KACHOW = ITEMS.register("scroll_of_kachow", ScrollOfKachow::new);
    public static final RegistryObject<Item> SCROLL_OF_LAVA_LAKE = ITEMS.register("scroll_of_lava_lake", ScrollOfLavaLake::new);
    public static final RegistryObject<Item> SCROLL_OF_MIGHT = ITEMS.register("scroll_of_might", ScrollOfMight::new);
    public static final RegistryObject<Item> SCROLL_OF_MINING = ITEMS.register("scroll_of_mining", ScrollOfMining::new);
    public static final RegistryObject<Item> SCROLL_OF_ORIGIN = ITEMS.register("scroll_of_origin", ScrollOfOrigin::new);
    public static final RegistryObject<Item> SCROLL_OF_SHELTER = ITEMS.register("scroll_of_shelter", ScrollOfShelter::new);
    public static final RegistryObject<Item> SCROLL_OF_SMITE_LESSER_UNDEAD = ITEMS.register("scroll_of_smite_lesser_undead", ScrollOfSmiteLesserUndead::new);
    public static final RegistryObject<Item> SCROLL_OF_STAIRS_DOWN = ITEMS.register("scroll_of_stairs_down", ScrollOfStairsDown::new);
    public static final RegistryObject<Item> SCROLL_OF_STAIRS_UP = ITEMS.register("scroll_of_stairs_up", ScrollOfStairsUp::new);
    public static final RegistryObject<Item> SCROLL_OF_STONESKIN = ITEMS.register("scroll_of_stoneskin", ScrollOfStoneskin::new);
    public static final RegistryObject<Item> SCROLL_OF_SUICIDE = ITEMS.register("scroll_of_suicide", ScrollOfSuicide::new);
    public static final RegistryObject<Item> SCROLL_OF_SUMMON_ALLIES = ITEMS.register("scroll_of_summon_allies", ScrollOfSummonAllies::new);
    public static final RegistryObject<Item> SCROLL_OF_SUMMON_WOLVES = ITEMS.register("scroll_of_summon_wolves", ScrollOfSummonWolves::new);
    public static final RegistryObject<Item> SCROLL_OF_SUMMONING = ITEMS.register("scroll_of_summoning", ScrollOfSummoning::new);
    public static final RegistryObject<Item> SCROLL_OF_THE_ABYSS = ITEMS.register("scroll_of_the_abyss", ScrollOfTheAbyss::new);
    public static final RegistryObject<Item> SCROLL_OF_THE_PLAINS = ITEMS.register("scroll_of_the_plains", ScrollOfThePlains::new);

    public static final RegistryObject<Item> JULIAS_SCROLL = ITEMS.register("julias_scroll", JuliasScroll::new);
    public static final RegistryObject<Item> MARYS_SCROLL = ITEMS.register("marys_scroll", MarysScroll::new);
    public static final RegistryObject<Item> MINNAS_SCROLL = ITEMS.register("minnas_scroll", MinnasScroll::new);
    public static final RegistryObject<Item> SAMANTHAS_SCROLL = ITEMS.register("samanthas_scroll", SamanthasScroll::new);

    // Keys
    public static final RegistryObject<Item> WORLD_KEY_CELIRIA = ITEMS.register("world_key_celiria", WorldKeyCeliria::new);
    public static final RegistryObject<Item> WORLD_KEY_CURSED_FOREST = ITEMS.register("world_key_cursed_forest", WorldKeyCursedForest::new);
    public static final RegistryObject<Item> WORLD_KEY_ERODED_DESERT = ITEMS.register("world_key_eroded_desert", WorldKeyErodedDesert::new);
    public static final RegistryObject<Item> WORLD_KEY_NOWHERE = ITEMS.register("world_key_nowhere", WorldKeyNowhere::new);
    public static final RegistryObject<Item> WORLD_KEY_PILOT_RED_SUN = ITEMS.register("world_key_pilot_red_sun", WorldKeyPilotRedSun::new);
    public static final RegistryObject<Item> WORLD_KEY_REALM_OF_PHANTASMUS = ITEMS.register("world_key_realm_of_phantasmus", WorldKeyRealmOfPhantasmus::new);
    public static final RegistryObject<Item> WORLD_KEY_SKYPIERCERS = ITEMS.register("world_key_skypiercers", WorldKeySkypiercers::new);
    public static final RegistryObject<Item> WORLD_KEY_THE_DUNGEON = ITEMS.register("world_key_the_dungeon", WorldKeyTheDungeon::new);
    public static final RegistryObject<Item> WORLD_KEY_THE_KINGDOM = ITEMS.register("world_key_the_kingdom", WorldKeyTheKingdom::new);
    public static final RegistryObject<Item> WORLD_KEY_THEOS = ITEMS.register("world_key_theos", WorldKeyTheos::new);
    public static final RegistryObject<Item> WORLD_KEY_THEOS_HEAVENS = ITEMS.register("world_key_theos_heavens", WorldKeyTheosHeavens::new);
    public static final RegistryObject<Item> WORLD_KEY_THEOS_UNDERWORLD = ITEMS.register("world_key_theos_underworld", WorldKeyTheosUnderworld::new);

    // Gems
    public static final RegistryObject<Item> TELEPORT_GEM_THE_CASTLE = ITEMS.register("teleport_gem_the_castle", TeleportGemTheCastle::new);
    public static final RegistryObject<Item> TELEPORT_GEM_THE_LIBRARY = ITEMS.register("teleport_gem_the_library", TeleportGemTheLibrary::new);

    // Tools
    public static final RegistryObject<PickaxeItem> DROW_PICKAXE = ITEMS.register("drow_pickaxe", DrowPickaxe::new);
    public static final RegistryObject<PickaxeItem> SOUL_PICKAXE = ITEMS.register("soul_pickaxe", SoulPickaxe::new);

    public static final RegistryObject<Item> ANTICREEPUS = ITEMS.register("anticreepus", Anticreepus::new);
    public static final RegistryObject<Item> MECHANICAL_ASSEMBLER = ITEMS.register("mechanical_assembler", MechanicalAssembler::new);
    public static final RegistryObject<Item> OVERCHARGED_SOUL_VACUUM = ITEMS.register("overcharged_soul_vacuum", OverchargedSoulVacuum::new);
    public static final RegistryObject<Item> PATHWINDER = ITEMS.register("pathwinder", Pathwinder::new);
    public static final RegistryObject<Item> SKY_GEM = ITEMS.register("sky_gem", SkyGem::new);
    public static final RegistryObject<Item> SOUL_VACUUM = ITEMS.register("soul_vacuum", SoulVacuum::new);
    public static final RegistryObject<Item> TOTALLY_TUBULAR_GLOVE = ITEMS.register("totally_tubular_glove", TotallyTubularGlove::new);

    // Combat
    public static final RegistryObject<AxeItem> AGARIC_CLEAVER = ITEMS.register("agaric_cleaver", AgaricCleaver::new);
    public static final RegistryObject<SwordItem> ANGEL_TABLET = ITEMS.register("angel_tablet", AngelTablet::new);
    public static final RegistryObject<SwordItem> BUSTER_BLADE = ITEMS.register("buster_blade", BusterBlade::new);
    public static final RegistryObject<SwordItem> CUTLASS = ITEMS.register("cutlass", Cutlass::new);
    public static final RegistryObject<SwordItem> DROW_RAPIER = ITEMS.register("drow_rapier", DrowRapier::new);
    public static final RegistryObject<SwordItem> GAZERBLADE = ITEMS.register("gazerblade", Gazerblade::new);
    public static final RegistryObject<SwordItem> KRAKEN_TOOTH = ITEMS.register("kraken_tooth", () ->
            new SwordItem(ModItemTier.KRAKEN, 5, -2.4f, new Item.Properties().group(Yeehaw.COMBAT_TAB)));
    public static final RegistryObject<Item> PHYLACTERY = ITEMS.register("phylactery", Phylactery::new);
    public static final RegistryObject<SwordItem> PLAGUE_ROD = ITEMS.register("plague_rod", PlagueRod::new);
    public static final RegistryObject<SwordItem> SOUL_SWORD = ITEMS.register("soul_sword", SoulSword::new);
    public static final RegistryObject<SwordItem> SUNSET_BLADE = ITEMS.register("sunset_blade", SunsetBlade::new);
    public static final RegistryObject<SwordItem> THE_HARVESTER = ITEMS.register("the_harvester", TheHarvester::new);
    public static final RegistryObject<SwordItem> TWILIGHT_SWORD = ITEMS.register("twilight_sword", TwilightSword::new);
    public static final RegistryObject<SwordItem> YOUNGLING_SLAYER = ITEMS.register("youngling_slayer", YounglingSlayer::new);

    public static final RegistryObject<BowItem> SKYPIERCER_BOW = ITEMS.register("skypiercer_bow", SkypiercerBow::new);

    public static final RegistryObject<ShieldItem> AEGIS = ITEMS.register("aegis", Aegis::new);
    public static final RegistryObject<ShieldItem> HELL_HEATER = ITEMS.register("hell_heater", HellHeater::new);
    public static final RegistryObject<Item> TOAD_RING = ITEMS.register("toad_ring", ToadRing::new);

    public static final RegistryObject<ArmorItem> CLOUD_BOOTS = ITEMS.register("cloud_boots", CloudBoots::new);
    public static final RegistryObject<ArmorItem> DROW_HELMET = ITEMS.register("drow_helmet", DrowHelmet::new);
    public static final RegistryObject<ArmorItem> DROW_CHESTPLATE = ITEMS.register("drow_chestplate", DrowChestplate::new);
    public static final RegistryObject<ArmorItem> DROW_LEGGINGS = ITEMS.register("drow_leggings", DrowLeggings::new);
    public static final RegistryObject<ArmorItem> DROW_BOOTS = ITEMS.register("drow_boots", DrowBoots::new);
    public static final RegistryObject<ArmorItem> FLAYER_ROBE_TOP = ITEMS.register("flayer_robe_top", FlayerRobeTop::new);
    public static final RegistryObject<ArmorItem> FLAYER_ROBE_BOTTOM = ITEMS.register("flayer_robe_bottom", FlayerRobeBottom::new);
    public static final RegistryObject<ArmorItem> METEOR_HELMET = ITEMS.register("meteor_helmet", MeteorHelmet::new);
    public static final RegistryObject<ArmorItem> SOUL_HELMET = ITEMS.register("soul_helmet", SoulHelmet::new);
    public static final RegistryObject<ArmorItem> SOUL_CHESTPLATE = ITEMS.register("soul_chestplate", SoulChestplate::new);
    public static final RegistryObject<ArmorItem> SOUL_LEGGINGS = ITEMS.register("soul_leggings", SoulLeggings::new);
    public static final RegistryObject<ArmorItem> SOUL_BOOTS = ITEMS.register("soul_boots", SoulBoots::new);
    public static final RegistryObject<ArmorItem> TITAN_HELMET = ITEMS.register("titan_helmet", TitanHelmet::new);
    public static final RegistryObject<ArmorItem> TITAN_CHESTPLATE = ITEMS.register("titan_chestplate", TitanChestplate::new);
    public static final RegistryObject<ArmorItem> TITAN_LEGGINGS = ITEMS.register("titan_leggings", TitanLeggings::new);
    public static final RegistryObject<ArmorItem> TITAN_BOOTS = ITEMS.register("titan_boots", TitanBoots::new);
    public static final RegistryObject<ArmorItem> YANKEE_WITH_NO_BRIM = ITEMS.register("yankee_with_no_brim", YankeeWithNoBrim::new);

    // Block Items
    public static final RegistryObject<Item> CLOUDSTONE_ORE_ITEM = ITEMS.register("cloudstone_ore",
            () -> new BlockItemBase(ModBlocks.CLOUDSTONE_ORE.get()));
    public static final RegistryObject<Item> METEORITE_ITEM = ITEMS.register("meteorite",
            () -> new BlockItemBase(ModBlocks.METEORITE.get()));
    public static final RegistryObject<Item> OPAL_ORE_ITEM = ITEMS.register("opal_ore",
            () -> new BlockItemBase(ModBlocks.OPAL_ORE.get()));
    public static final RegistryObject<Item> ROSEUM_ORE_ITEM = ITEMS.register("roseum_ore",
            () -> new BlockItemBase(ModBlocks.ROSEUM_ORE.get()));
    public static final RegistryObject<Item> THEOSIAN_CRYSTAL_ITEM = ITEMS.register("theosian_crystal",
            () -> new BlockItemBase(ModBlocks.THEOSIAN_CRYSTAL.get()));
    public static final RegistryObject<Item> TITANITE_ORE_ITEM = ITEMS.register("titanite_ore",
            () -> new BlockItemBase(ModBlocks.TITANITE_ORE.get()));
    public static final RegistryObject<Item> UNDERSTONE = ITEMS.register("understone",
            () -> new BlockItemBase(ModBlocks.UNDERSTONE.get()));

    public static final RegistryObject<Item> CUBE_OF_ROSEUM_ITEM = ITEMS.register("cube_of_roseum",
            () -> new BlockItemBase(ModBlocks.CUBE_OF_ROSEUM.get()));
    public static final RegistryObject<Item> ENCHANTED_BLOCK_ITEM = ITEMS.register("enchanted_block",
            () -> new BlockItemBase(ModBlocks.ENCHANTED_BLOCK.get()));
    public static final RegistryObject<Item> FUNGAL_TITANITE_BLOCK_ITEM = ITEMS.register("fungal_titanite_block",
            () -> new BlockItemBase(ModBlocks.FUNGAL_TITANITE_BLOCK.get()));
    public static final RegistryObject<Item> MAGIC_GOLD_BLOCK_ITEM = ITEMS.register("magic_gold_block",
            () -> new BlockItemBase(ModBlocks.MAGIC_GOLD_BLOCK.get()));
    public static final RegistryObject<Item> REFINED_METEORITE_BLOCK_ITEM = ITEMS.register("refined_meteorite_block",
            () -> new BlockItemBase(ModBlocks.REFINED_METEORITE_BLOCK.get()));
    public static final RegistryObject<Item> SOUL_STEEL_BLOCK_ITEM = ITEMS.register("soul_steel_block",
            () -> new BlockItemBase(ModBlocks.SOUL_STEEL_BLOCK.get()));
    public static final RegistryObject<Item> TITAN_BLOCK_ITEM = ITEMS.register("titan_block",
            () -> new BlockItemBase(ModBlocks.TITAN_BLOCK.get()));
    public static final RegistryObject<Item> TITANITE_BLOCK_ITEM = ITEMS.register("titanite_block",
            () -> new BlockItemBase(ModBlocks.TITANITE_BLOCK.get()));
    public static final RegistryObject<Item> UNDERSTEEL_BLOCK_ITEM = ITEMS.register("understeel_block",
            () -> new BlockItemBase(ModBlocks.UNDERSTEEL_BLOCK.get()));

    public static final RegistryObject<Item> SWORD_IN_THE_STONE_ITEM = ITEMS.register("sword_in_the_stone",
            () -> new BlockItemBase(ModBlocks.SWORD_IN_THE_STONE.get()));

    public static final RegistryObject<Item> BLACK_IRIS_ITEM = ITEMS.register("black_iris",
            () -> new BlockItemBase(ModBlocks.BLACK_IRIS.get()));
    public static final RegistryObject<Item> TWILIGHT_ROSE_ITEM = ITEMS.register("twilight_rose",
            () -> new BlockItemBase(ModBlocks.TWILIGHT_ROSE.get()));

    public static final RegistryObject<Item> ANTICREEPER_NODE_ITEM = ITEMS.register("anticreeper_node",
            () -> new BlockItemBase(ModBlocks.ANTICREEPER_NODE.get()));
    public static final RegistryObject<Item> GLOWING_AIR_BLOCK_ITEM = ITEMS.register("glowing_air_block",
            () -> new BlockItemBase(ModBlocks.GLOWING_AIR_BLOCK.get()));
    public static final RegistryObject<Item> INTERDIMENSIONAL_RECEIVER_ITEM = ITEMS.register("interdimensional_receiver",
            () -> new InterdimensionalReceiver(ModBlocks.INTERDIMENSIONAL_RECEIVER.get()));
    public static final RegistryObject<Item> VOID_GOLD_BLOCK_ITEM = ITEMS.register("void_gold_block",
            () -> new BlockItemBase(ModBlocks.VOID_GOLD_BLOCK.get()));

    // Spawn Eggs
    public static final RegistryObject<ModSpawnEggItem> BEHOLDER_SPAWN_EGG = ITEMS.register("beholder_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.BEHOLDER, 0x571280, 0xFFF1C4, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> CACTOID_SPAWN_EGG = ITEMS.register("cactoid_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.CACTOID, 0x42BC00, 0xECFF94, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> DARK_KNIGHT_SPAWN_EGG = ITEMS.register("dark_knight_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.DARK_KNIGHT, 0x171717, 0x3b0000, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> FELONIOUS_BOLUS_SPAWN_EGG = ITEMS.register("felonious_bolus_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.FELONIOUS_BOLUS, 0x634900, 0x3d2f18, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> GIANT_SPAWN_EGG = ITEMS.register("giant_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.GIANT, 0x859489, 0x353b37, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> GNOME_ELDER_SPAWN_EGG = ITEMS.register("gnome_elder_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.GNOME_ELDER, 0x65501B, 0x347319, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> KNIGHT_SPAWN_EGG = ITEMS.register("knight_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.KNIGHT, 0x2c2e30, 0xfffcdb, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> KNIGHT_COMMANDER_SPAWN_EGG = ITEMS.register("knight_commander_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.KNIGHT_COMMANDER, 0x001d75, 0xffd500, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> KRAKEN_SPAWN_EGG = ITEMS.register("kraken_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.KRAKEN, 0xCFCFCF, 0x655267, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> THE_POLICE_SPAWN_EGG = ITEMS.register("the_police_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.THE_POLICE, 0xffffff, 0x0700db, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> THE_RESTLESS_SPAWN_EGG = ITEMS.register("the_restless_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.THE_RESTLESS, 0x313131, 0x7D4444, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> TOAD_MOTHER_SPAWN_EGG = ITEMS.register("toad_mother_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.TOAD_MOTHER, 0x960000, 0xEAEAEA, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> TROLL_SPAWN_EGG = ITEMS.register("troll_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.TROLL, 0x26c937, 0x4f380a, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));

    public static final RegistryObject<ModSpawnEggItem> ALLIED_KNIGHT_SPAWN_EGG = ITEMS.register("allied_knight_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.ALLIED_KNIGHT, 0x00358a, 0x008a51, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> ANGEL_SPAWN_EGG = ITEMS.register("angel_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.ANGEL, 0xfafafa, 0xffd119, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> GNOME_SPAWN_EGG = ITEMS.register("gnome_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.GNOME, 0x1400c7, 0xe60000, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> TOADKIN_SPAWN_EGG = ITEMS.register("toadkin_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.TOADKIN, 0xDE0000, 0xEAEAEA, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> X0026G_SPAWN_EGG = ITEMS.register("x0026g_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.X0026G, 0x594116, 0xffffff, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));

    public static final RegistryObject<ModSpawnEggItem> FLINT_MONGER_SPAWN_EGG = ITEMS.register("flint_monger_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.FLINT_MONGER, 0x595427, 0x825419, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> NPC_SLAYER_SPAWN_EGG = ITEMS.register("npc_slayer_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.NPC_SLAYER, 0x423124, 0x1b2b22, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<ModSpawnEggItem> SOUL_TRADER_SPAWN_EGG = ITEMS.register("soul_trader_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityType.SOUL_TRADER, 0x1c1c1c, 0x440061, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));

    public static final RegistryObject<Item> ABOMINABLE_SLUDGE = ITEMS.register("abominable_sludge", AbominableSludge::new);
    public static final RegistryObject<ChaliceOfKnowledge> CHALICE_OF_KNOWLEDGE = ITEMS.register("chalice_of_knowledge",
            () -> new ChaliceOfKnowledge(ModEntityType.MIND_FLAYER, 0, 0, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<Item> EVIL_TOADSTOOL = ITEMS.register("evil_toadstool", EvilToadstool::new);
    public static final RegistryObject<Item> SHADOW_HORN = ITEMS.register("shadow_horn", ShadowHorn::new);
    public static final RegistryObject<Item> STORM_IN_A_BOTTLE = ITEMS.register("storm_in_a_bottle", StormInABottle::new);
    public static final RegistryObject<Item> THYRSUS = ITEMS.register("thyrsus", Thyrsus::new);
    public static final RegistryObject<Item> UNCHARGED_PHYLACTERY = ITEMS.register("uncharged_phylactery", UnchargedPhylactery::new);

    public static final RegistryObject<WiltingRose> WILTING_ROSE = ITEMS.register("wilting_rose",
            () -> new WiltingRose(ModEntityType.ROSE_QUEEN, 0xb0813c, 0xeeeee4, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));

    public static final RegistryObject<MagicLamp> MAGIC_LAMP = ITEMS.register("magic_lamp",
            () -> new MagicLamp(ModEntityType.GENIE, 0x9856CA, 0xFFFFFF, new Item
                    .Properties().group(Yeehaw.SUMMON_TAB)));
    public static final RegistryObject<Item> NPC_SPAWNER = ITEMS.register("npc_spawner", NPCSpawner::new);
    public static final RegistryObject<Item> FAIRY_STONE = ITEMS.register("fairy_stone", FairyStone::new);


    // Temporary & Testing
    public static final RegistryObject<Item> TESTING_ROD_1 = ITEMS.register("testing_rod_1", TestingRod1::new);

}
