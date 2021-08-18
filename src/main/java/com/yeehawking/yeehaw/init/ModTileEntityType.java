package com.yeehawking.yeehaw.init;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.types.Type;
import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.entities.DionysusEntity;
import com.yeehawking.yeehaw.tile_entities.AnticreeperNodeTileEntity;
import com.yeehawking.yeehaw.tile_entities.GlowingAirBlockTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.function.Supplier;

public class ModTileEntityType {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Yeehaw.MOD_ID);

    public static final RegistryObject<TileEntityType<AnticreeperNodeTileEntity>> ANTICREEPER_NODE_TILE_ENTITY =
            TILE_ENTITY_TYPES.register("anticreeper_node", () -> TileEntityType.Builder
                    .create(AnticreeperNodeTileEntity::new, ModBlocks.ANTICREEPER_NODE.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingAirBlockTileEntity>> GLOWING_AIR_BLOCK_TILE_ENTITY =
            TILE_ENTITY_TYPES.register("glowing_air_block", () -> TileEntityType.Builder
                    .create(GlowingAirBlockTileEntity::new, ModBlocks.GLOWING_AIR_BLOCK.get()).build(null));
}
