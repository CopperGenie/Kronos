package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RefinedMeteoriteBlock extends Block {
    public RefinedMeteoriteBlock() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(3.0f, 6.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .setLightLevel(value -> 12));
    }
}
