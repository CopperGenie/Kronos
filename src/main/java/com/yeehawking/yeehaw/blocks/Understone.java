package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class Understone extends OreBlock {
    public Understone() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(15.0f, 6.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3));
    }
}
