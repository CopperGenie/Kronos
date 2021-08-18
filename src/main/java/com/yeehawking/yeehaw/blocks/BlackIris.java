package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlackIris extends Block {

    public BlackIris() {
        super(Properties.create(Material.PLANTS)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement());


    }
}
