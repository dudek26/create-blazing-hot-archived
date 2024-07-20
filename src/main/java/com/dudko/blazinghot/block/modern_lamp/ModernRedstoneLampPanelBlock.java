package com.dudko.blazinghot.block.modern_lamp;

import org.jetbrains.annotations.NotNull;

import com.dudko.blazinghot.block.Shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class ModernRedstoneLampPanelBlock extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public ModernRedstoneLampPanelBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(LIT, FACING));
	}

	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
		BlockState stateForPlacement = super.getStateForPlacement(pContext);
		return stateForPlacement
				.setValue(LIT, pContext.getLevel().hasNeighborSignal(pContext.getClickedPos()))
				.setValue(FACING, pContext.getClickedFace());
	}

	@Override
	public void neighborChanged(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Block pBlock, @NotNull BlockPos pFromPos, boolean pIsMoving) {
		if (pLevel.isClientSide) return;

		boolean isPowered = pState.getValue(LIT);
		if (isPowered == pLevel.hasNeighborSignal(pPos)) return;
		if (isPowered) {
			pLevel.setBlock(pPos, pState.setValue(LIT, false), 2);
			return;
		}

		pLevel.setBlock(pPos, pState.setValue(LIT, true), 2);
		scheduleActivation(pLevel, pPos);
	}

	private void scheduleActivation(Level pLevel, BlockPos pPos) {
		if (!pLevel.getBlockTicks().hasScheduledTick(pPos, this)) pLevel.scheduleTick(pPos, this, 1);
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
		return Shapes.shape(0, 0, 0, 16, 1, 16).add(1, 0, 1, 15, 2, 15).forDirectional().get(pState.getValue(FACING));
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull PathComputationType pType) {
		return false;
	}
}
