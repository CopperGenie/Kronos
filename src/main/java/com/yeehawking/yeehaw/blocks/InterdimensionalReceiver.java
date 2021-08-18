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

public class InterdimensionalReceiver extends Block {
    public InterdimensionalReceiver() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 4.0f)
                .sound(SoundType.METAL)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(5, 2, 12, 11, 3, 13),
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(4, 2, 4, 12, 3, 12),
            Block.makeCuboidShape(4, 23, 4, 12, 24, 12),
            Block.makeCuboidShape(5, 22, 5, 11, 23, 11),
            Block.makeCuboidShape(5, 26, 5, 11, 27, 11),
            Block.makeCuboidShape(6, 28, 6, 10, 29, 10),
            Block.makeCuboidShape(4, 25.68198, -2.47487, 12, 26.68198, 5.52513),
            Block.makeCuboidShape(4, 25.68198, 10.47487, 12, 26.68198, 18.47487),
            Block.makeCuboidShape(10.47487, 25.68198, 4, 18.47487, 26.68198, 12),
            Block.makeCuboidShape(1.02513, 22.18198, 4, 2.02513, 30.18198, 12),
            Block.makeCuboidShape(7, 3, 7, 9, 32, 9),
            Block.makeCuboidShape(12, 2, 5, 13, 3, 11),
            Block.makeCuboidShape(3, 2, 5, 4, 3, 11),
            Block.makeCuboidShape(5, 2, 3, 11, 3, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
