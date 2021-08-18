package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScrollOfShelter extends Item {
    public ScrollOfShelter() {
        super(new Properties().group(Yeehaw.SCROLL_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        World world = playerIn.getEntityWorld();
        world.setBlockState(playerIn.getPosition().add(1, -1, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, -1, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, -1, -1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, -1, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, -1, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, -1, -1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, -1, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, -1, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, -1, -1), Blocks.OAK_PLANKS.getDefaultState());

        world.setBlockState(playerIn.getPosition().add(1, 3, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 3, -1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 3, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 3, -1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 3, 1), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 3, -1), Blocks.OAK_PLANKS.getDefaultState());

        world.setBlockState(playerIn.getPosition().add(0, -1, 2), Blocks.OAK_PLANKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 3, 2), Blocks.STONE_BRICKS.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 1, -1), Blocks.WALL_TORCH.getDefaultState()
                .with(StairsBlock.FACING, Direction.SOUTH));
        world.setBlockState(playerIn.getPosition().add(1, 1, 3), Blocks.WALL_TORCH.getDefaultState()
                .with(StairsBlock.FACING, Direction.SOUTH));
        world.setBlockState(playerIn.getPosition().add(-1, 1, 3), Blocks.WALL_TORCH.getDefaultState()
                .with(StairsBlock.FACING, Direction.SOUTH));
        world.setBlockState(playerIn.getPosition().add(0, 0, 2), Blocks.SPRUCE_DOOR.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(0, 1, 2), Blocks.SPRUCE_DOOR.getDefaultState()
                .with(DoorBlock.HALF, DoubleBlockHalf.UPPER));
        world.setBlockState(playerIn.getPosition().add(-1, 0, 0), Blocks.BLUE_BED.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(-1, 0, -1), Blocks.BLUE_BED.getDefaultState()
                .with(BedBlock.PART, BedPart.HEAD));
        world.setBlockState(playerIn.getPosition().add(1, 0, 1), Blocks.CRAFTING_TABLE.getDefaultState());
        world.setBlockState(playerIn.getPosition().add(1, 0, 0), Blocks.FURNACE.getDefaultState()
                .with(StairsBlock.FACING, Direction.WEST));

        world.setBlockState(playerIn.getPosition().add(0, 2, 2), Blocks.STONE_BRICKS.getDefaultState());

        for (int i = -1; i <= 3; i++) {
            world.setBlockState(playerIn.getPosition().add(2, i, -2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, -1), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 0), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 1), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(2, i, 2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, -2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(1, i, 2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(0, i, -2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, -2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-1, i, 2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, -2), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, -1), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 0), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 1), Blocks.STONE_BRICKS.getDefaultState());
            world.setBlockState(playerIn.getPosition().add(-2, i, 2), Blocks.STONE_BRICKS.getDefaultState());
        }
        playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 0.8f, 1f);

        ItemStack itemstack = playerIn.getHeldItemMainhand();
        itemstack.shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
