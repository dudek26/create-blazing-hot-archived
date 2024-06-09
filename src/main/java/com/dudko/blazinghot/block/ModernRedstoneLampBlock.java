package com.dudko.blazinghot.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class ModernRedstoneLampBlock extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public ModernRedstoneLampBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(LIT));
	}

	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
		BlockState stateForPlacement = super.getStateForPlacement(pContext);
		return stateForPlacement.setValue(LIT, pContext.getLevel()
														   .hasNeighborSignal(pContext.getClickedPos()));
	}

	@Override
	public void neighborChanged(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Block pBlock, @NotNull BlockPos pFromPos,
								boolean pIsMoving) {
		if (pLevel.isClientSide)
			return;

		boolean isPowered = pState.getValue(LIT);
		if (isPowered == pLevel.hasNeighborSignal(pPos))
			return;
		if (isPowered) {
			pLevel.setBlock(pPos, pState.setValue(LIT, false), 2);
			return;
		}

		pLevel.setBlock(pPos, pState.setValue(LIT, true), 2);
		scheduleActivation(pLevel, pPos);
	}

	private void scheduleActivation(Level pLevel, BlockPos pPos) {
		if (!pLevel.getBlockTicks()
				   .hasScheduledTick(pPos, this))
			pLevel.scheduleTick(pPos, this, 1);
	}
}
