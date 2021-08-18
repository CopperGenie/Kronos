package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class EnchantedBlock extends Block {
    public EnchantedBlock() {
        super(Block.Properties.create(Material.GLASS)
                .hardnessAndResistance(0.4f, 3.0f)
                .sound(SoundType.METAL)
                .setLightLevel(value -> 5));
    }
}
