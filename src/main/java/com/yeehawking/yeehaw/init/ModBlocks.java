package com.yeehawking.yeehaw.init;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Yeehaw.MOD_ID);

    // Ore
    public static final RegistryObject<Block> CLOUDSTONE_ORE = BLOCKS.register("cloudstone_ore", CloudstoneOre::new);
    public static final RegistryObject<Block> METEORITE = BLOCKS.register("meteorite", Meteorite::new);
    public static final RegistryObject<Block> OPAL_ORE = BLOCKS.register("opal_ore", OpalOre::new);
    public static final RegistryObject<Block> ROSEUM_ORE = BLOCKS.register("roseum_ore", RoseumOre::new);
    public static final RegistryObject<Block> THEOSIAN_CRYSTAL = BLOCKS.register("theosian_crystal", TheosianCrystal::new);
    public static final RegistryObject<Block> TITANITE_ORE = BLOCKS.register("titanite_ore", TitaniteOre::new);
    public static final RegistryObject<Block> UNDERSTONE = BLOCKS.register("understone", Understone::new);

    // Storage
    public static final RegistryObject<Block> CUBE_OF_ROSEUM = BLOCKS.register("cube_of_roseum", CubeOfRoseum::new);
    public static final RegistryObject<Block> ENCHANTED_BLOCK = BLOCKS.register("enchanted_block", EnchantedBlock::new);
    public static final RegistryObject<Block> FUNGAL_TITANITE_BLOCK = BLOCKS.register("fungal_titanite_block", FungalTitaniteBlock::new);
    public static final RegistryObject<Block> MAGIC_GOLD_BLOCK = BLOCKS.register("magic_gold_block", MagicGoldBlock::new);
    public static final RegistryObject<Block> REFINED_METEORITE_BLOCK = BLOCKS.register("refined_meteorite_block", RefinedMeteoriteBlock::new);
    public static final RegistryObject<Block> SOUL_STEEL_BLOCK = BLOCKS.register("soul_steel_block", SoulSteelBlock::new);
    public static final RegistryObject<Block> TITAN_BLOCK = BLOCKS.register("titan_block", TitanBlock::new);
    public static final RegistryObject<Block> TITANITE_BLOCK = BLOCKS.register("titanite_block", TitaniteBlock::new);
    public static final RegistryObject<Block> UNDERSTEEL_BLOCK = BLOCKS.register("understeel_block", UndersteelBlock::new);

    // Decoration
    public static final RegistryObject<Block> SWORD_IN_THE_STONE = BLOCKS.register("sword_in_the_stone", SwordInTheStone::new);

    // Organic
    public static final RegistryObject<Block> BLACK_IRIS = BLOCKS.register("black_iris", BlackIris::new);
    public static final RegistryObject<Block> TWILIGHT_ROSE = BLOCKS.register("twilight_rose", TwilightRose::new);

    // Crop blocks
    public static final RegistryObject<Block> FORBIDDEN_FRUIT_CROPS_BLOCK = BLOCKS.register("forbidden_fruit_crops_block",
            () -> new ForbiddenFruitCropsBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));

    // Misc
    public static final RegistryObject<Block> ANTICREEPER_NODE = BLOCKS.register("anticreeper_node", AnticreeperNode::new);
    public static final RegistryObject<Block> GLOWING_AIR_BLOCK = BLOCKS.register("glowing_air_block", GlowingAirBlock::new);
    public static final RegistryObject<Block> INTERDIMENSIONAL_RECEIVER = BLOCKS.register("interdimensional_receiver", InterdimensionalReceiver::new);
    public static final RegistryObject<Block> VOID_GOLD_BLOCK = BLOCKS.register("void_gold_block", VoidGoldBlock::new);

}
