package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class TheosianCrystal extends Block {
    public TheosianCrystal() {
        super(Properties.create(Material.GLASS)
                .hardnessAndResistance(6.0f, 4.0f)
                .sound(SoundType.GLASS)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(7, 28, 7, 9, 32, 9),
            Block.makeCuboidShape(0, 0.05, 0, 16, 4, 16),
            Block.makeCuboidShape(1, 4, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 14, 12, 14),
            Block.makeCuboidShape(3, 12, 3, 13, 16, 13),
            Block.makeCuboidShape(4, 16, 4, 12, 20, 12),
            Block.makeCuboidShape(5, 20, 5, 11, 24, 11),
            Block.makeCuboidShape(6, 24, 6, 10, 28, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
