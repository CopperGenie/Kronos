package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class VoidGoldBlock extends Block {
    public VoidGoldBlock() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 4.0f)
                .sound(SoundType.METAL)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }
}
