//package com.yeehawking.yeehaw.world.biomes;
//
//import com.yeehawking.yeehaw.STFeatures;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.DefaultBiomeFeatures;
//import net.minecraft.world.gen.GenerationStage;
//import net.minecraft.world.gen.carver.WorldCarver;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.IFeatureConfig;
//import net.minecraft.world.gen.feature.ProbabilityConfig;
//import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
//import net.minecraft.world.gen.placement.FrequencyConfig;
//import net.minecraft.world.gen.placement.Placement;
//
//public class EnchantedForest extends Biome {
//
//    public EnchantedForest(Builder biomeBuilder) {
//        super(biomeBuilder);
//
//        addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.03f)));
//        addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.01f)));
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_
//                .withConfiguration(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP
//                        .configure(new AtSurfaceWithExtraConfig(5, 0.1f, 1))));
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER
//                .withConfiguration(DefaultBiomeFeatures.PEONY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32
//                        .configure(new FrequencyConfig(1))));
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER
//                .withConfiguration(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32
//                        .configure(new FrequencyConfig(2))));
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH
//                .withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE
//                        .configure(new FrequencyConfig(6))));
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH
//                .withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE
//                        .configure(new FrequencyConfig(1))));
//        this.func_235063_a_(DefaultBiomeFeatures.BURIED_TREASURE);
//
//        this.func_235063_a_(STFeatures.GNOME_HOUSE.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
//        this.func_235063_a_(STFeatures.GNOME_PRISON.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
//
//        DefaultBiomeFeatures.addOres(this);
//    }
//
//    @Override
//    public int getGrassColor(double posX, double posZ) {
//        return 0x00EA82;
//    }
//
//    @Override
//    public int getFoliageColor() {
//        return 0x00CA7C;
//    }
//}