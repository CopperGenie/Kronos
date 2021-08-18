package com.yeehawking.yeehaw.blocks;

import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ForbiddenFruitCropsBlock extends CropsBlock {
    public ForbiddenFruitCropsBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ModItems.FORBIDDEN_FRUIT.get();
    }

    private static final VoxelShape[] SHAPES = new VoxelShape[]
            {
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 2D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 3D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 4D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 5D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 6D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 7D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 8D, 16D),
                    Block.makeCuboidShape(0D, 0D, 0D, 16D, 9D, 16D)
            };

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(this.getAgeProperty())];
    }
}
