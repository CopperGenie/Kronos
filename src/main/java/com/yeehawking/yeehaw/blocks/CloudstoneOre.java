package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class CloudstoneOre extends OreBlock {
    public CloudstoneOre() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 5.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }
}
