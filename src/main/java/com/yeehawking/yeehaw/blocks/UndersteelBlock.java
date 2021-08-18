package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class UndersteelBlock extends Block {
    public UndersteelBlock() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(2.5f, 5.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }
}
