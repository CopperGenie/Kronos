package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RoseumOre extends OreBlock {
    public RoseumOre() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(6.0f, 5.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3));
    }
}
