package com.yeehawking.yeehaw.world;

import com.google.common.collect.ImmutableList;
import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.world.structures.*;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class STStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     *
     * HOWEVER, do note that Deferred Registries only work for anything that is a Forge Registry. This means that
     * configured structures and configured features need to be registered directly to WorldGenRegistries as there
     * is no Deferred Registry system for them.
     */
    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister
            .create(ForgeRegistries.STRUCTURE_FEATURES, Yeehaw.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> DUNGEON1 = DEFERRED_REGISTRY_STRUCTURE
            .register("dungeon1", () -> (new Dungeon1(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> LICH_TOWER = DEFERRED_REGISTRY_STRUCTURE
            .register("lich_tower", () -> (new LichTower(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> GIANT_HEAD = DEFERRED_REGISTRY_STRUCTURE
            .register("giant_head", () -> (new GiantHead(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BASALT_HOUSE = DEFERRED_REGISTRY_STRUCTURE
            .register("basalt_house", () -> (new BasaltHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BLACK_MAUSOLEUM = DEFERRED_REGISTRY_STRUCTURE
            .register("black_mausoleum", () -> (new BlackMausoleum(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SOUL_TEMPLE = DEFERRED_REGISTRY_STRUCTURE
            .register("soul_temple", () -> (new SoulTemple(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> NICE_FORT = DEFERRED_REGISTRY_STRUCTURE
            .register("nice_fort", () -> (new NiceFort(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TWILIGHT_GARDEN = DEFERRED_REGISTRY_STRUCTURE
            .register("twilight_garden", () -> (new TwilightGarden(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SLAYER_HOUSE = DEFERRED_REGISTRY_STRUCTURE
            .register("slayer_house", () -> (new SlayerHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> KNIGHT_FORT = DEFERRED_REGISTRY_STRUCTURE
            .register("knight_fort", () -> (new KnightFort(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> GAS_STATION = DEFERRED_REGISTRY_STRUCTURE
            .register("gas_station", () -> (new GasStation(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THE_LIBRARY = DEFERRED_REGISTRY_STRUCTURE
            .register("the_library", () -> (new TheLibrary(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THE_CASTLE = DEFERRED_REGISTRY_STRUCTURE
            .register("the_castle", () -> (new TheCastle(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THE_VAULT = DEFERRED_REGISTRY_STRUCTURE
            .register("the_vault", () -> (new TheVault(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HEAVENS_1 = DEFERRED_REGISTRY_STRUCTURE
            .register("heavens_1", () -> (new Heavens1(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HEAVENS_2 = DEFERRED_REGISTRY_STRUCTURE
            .register("heavens_2", () -> (new Heavens2(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HEAVENS_3 = DEFERRED_REGISTRY_STRUCTURE
            .register("heavens_3", () -> (new Heavens3(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HEAVENS_4 = DEFERRED_REGISTRY_STRUCTURE
            .register("heavens_4", () -> (new Heavens4(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> STONE_SWORD = DEFERRED_REGISTRY_STRUCTURE
            .register("stone_sword", () -> (new StoneSword(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THEOSIAN_CHURCH = DEFERRED_REGISTRY_STRUCTURE
            .register("theosian_church", () -> (new TheosianChurch(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THEOSIAN_TEMPLE = DEFERRED_REGISTRY_STRUCTURE
            .register("theosian_temple", () -> (new TheosianTemple(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> THEOSIAN_FOUNTAIN = DEFERRED_REGISTRY_STRUCTURE
            .register("theosian_fountain", () -> (new TheosianFountain(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CRYSTAL = DEFERRED_REGISTRY_STRUCTURE
            .register("crystal", () -> (new Crystal(NoFeatureConfig.CODEC)));

    /**
     * This is where we set the rarity of your structures and determine if land conforms to it.
     * See the comments in below for more details.
     */
    public static void setupStructures() {
        setupMapSpacingAndLand(
                DUNGEON1.get(), /* The instance of the structure */
                new StructureSeparationSettings(
                        10 /* average distance apart in chunks between spawn attempts */,
                        5 /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
                        1001000000 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);
        setupMapSpacingAndLand(LICH_TOWER.get(), new StructureSeparationSettings(10 , 5 , 1002000000), true);
        setupMapSpacingAndLand(GIANT_HEAD.get(), new StructureSeparationSettings(10, 5, 1003000000), true);
        setupMapSpacingAndLand(BASALT_HOUSE.get(), new StructureSeparationSettings(10, 5, 1004000000), true);
        setupMapSpacingAndLand(BLACK_MAUSOLEUM.get(), new StructureSeparationSettings(10, 5, 1005000000), true);
        setupMapSpacingAndLand(SOUL_TEMPLE.get(), new StructureSeparationSettings(10, 5, 1006000000), true);
        setupMapSpacingAndLand(SLAYER_HOUSE.get(), new StructureSeparationSettings(10, 5, 1007000000), true);
        setupMapSpacingAndLand(TWILIGHT_GARDEN.get(), new StructureSeparationSettings(10, 5, 1008000000), true);
        setupMapSpacingAndLand(NICE_FORT.get(), new StructureSeparationSettings(10, 5, 1009000000), true);
        setupMapSpacingAndLand(KNIGHT_FORT.get(), new StructureSeparationSettings(10, 5, 1010000000), true);
        setupMapSpacingAndLand(GAS_STATION.get(), new StructureSeparationSettings(1, 0, 1011000000), true);
        setupMapSpacingAndLand(THE_LIBRARY.get(), new StructureSeparationSettings(1, 0, 1012000000 ), true);
        setupMapSpacingAndLand(THE_CASTLE.get(), new StructureSeparationSettings(1, 0, 1013000000 ), true);
        setupMapSpacingAndLand(THE_VAULT.get(), new StructureSeparationSettings(1, 0, 1014000000 ), true);
        setupMapSpacingAndLand(HEAVENS_1.get(), new StructureSeparationSettings(10, 5, 1015000000), true);
        setupMapSpacingAndLand(HEAVENS_2.get(), new StructureSeparationSettings(10, 5, 1016000000), true);
        setupMapSpacingAndLand(HEAVENS_3.get(), new StructureSeparationSettings(10, 5, 1017000000), true);
        setupMapSpacingAndLand(HEAVENS_4.get(), new StructureSeparationSettings(10, 5, 1018000000), true);
        setupMapSpacingAndLand(STONE_SWORD.get(), new StructureSeparationSettings(10, 5, 1019000000), true);
        setupMapSpacingAndLand(THEOSIAN_FOUNTAIN.get(), new StructureSeparationSettings(10, 5, 1020000000), true);
        setupMapSpacingAndLand(THEOSIAN_CHURCH.get(), new StructureSeparationSettings(10, 5, 1021000000), true);
        setupMapSpacingAndLand(THEOSIAN_TEMPLE.get(), new StructureSeparationSettings(10, 5, 1022000000), true);
        setupMapSpacingAndLand(CRYSTAL.get(), new StructureSeparationSettings(10, 5, 1023000000), true);

    }


    /**
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument.
     * This method is called by setupStructures above.
     */
    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        /*
         * We need to add our structures into the map in Structure class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);


        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         * Note: The air space this method will create will be filled with water if the structure is below sealevel.
         * This means this is best for structure above sealevel so keep that in mind.
         *
         * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        if(transformSurroundingLand){
            Structure.field_236384_t_ =
                    ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.field_236384_t_)
                            .add(structure)
                            .build();
        }
    }
}