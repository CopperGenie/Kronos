package com.yeehawking.yeehaw.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class SwordInTheStone extends Block {

    public SwordInTheStone() {
        super(AbstractBlock.Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 5.0f)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));


    }

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6.5, 14, 6.5, 9.5, 16, 8.5),
            Block.makeCuboidShape(0, 0, 1, 16, 3, 14),
            Block.makeCuboidShape(2, 3, 3, 14, 4, 12),
            Block.makeCuboidShape(6, 4, 7, 10, 9, 8),
            Block.makeCuboidShape(4, 9, 6, 12, 10, 9),
            Block.makeCuboidShape(7, 10, 7, 9, 14, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(7.5, 14, 6.5, 9.5, 16, 9.5),
            Block.makeCuboidShape(2, 0, 0, 15, 3, 16),
            Block.makeCuboidShape(4, 3, 2, 13, 4, 14),
            Block.makeCuboidShape(8, 4, 6, 9, 9, 10),
            Block.makeCuboidShape(7, 9, 4, 10, 10, 12),
            Block.makeCuboidShape(8, 10, 7, 9, 14, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6.5, 14, 7.5, 9.5, 16, 9.5),
            Block.makeCuboidShape(0, 0, 2, 16, 3, 15),
            Block.makeCuboidShape(2, 3, 4, 14, 4, 13),
            Block.makeCuboidShape(6, 4, 8, 10, 9, 9),
            Block.makeCuboidShape(4, 9, 7, 12, 10, 10),
            Block.makeCuboidShape(7, 10, 8, 9, 14, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(6.5, 14, 6.5, 8.5, 16, 9.5),
            Block.makeCuboidShape(1, 0, 0, 14, 3, 16),
            Block.makeCuboidShape(3, 3, 2, 12, 4, 14),
            Block.makeCuboidShape(7, 4, 6, 8, 9, 10),
            Block.makeCuboidShape(6, 9, 4, 9, 10, 12),
            Block.makeCuboidShape(7, 10, 7, 8, 14, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;

        }
    }

    // Make block face you when placed
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) { builder.add(FACING); }
}
