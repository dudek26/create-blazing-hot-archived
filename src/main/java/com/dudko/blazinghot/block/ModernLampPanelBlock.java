package com.dudko.blazinghot.block;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create.AllTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class ModernLampPanelBlock extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public ModernLampPanelBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
									 .setValue(LIT, false)
									 .setValue(LOCKED, false)
									 .setValue(FACING, Direction.UP));
	}

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
		boolean locked = pState.getValue(LOCKED);

		if (pPlayer.getItemInHand(pHand).isEmpty() && !locked) {
			float pitch = pState.getValue(LIT) ? 0.5F : 0.8F;
			pLevel.setBlockAndUpdate(pPos, pState.cycle(LIT));
			pLevel.playLocalSound(pPos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1.0F, pitch, false);
			return InteractionResult.SUCCESS;
		}

		if (pPlayer.getItemInHand(pHand).is(AllTags.AllItemTags.WRENCH.tag) && !pPlayer.isCrouching()) {
			String action = locked ? "unlock" : "lock";
			pPlayer.displayClientMessage(Component.translatable("message.blazinghot.modern_lamp." + action), true);
			pLevel.setBlockAndUpdate(pPos, pState.cycle(LOCKED));
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(LIT, LOCKED, FACING));
	}

	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
		BlockState stateForPlacement = super.getStateForPlacement(pContext);
		return stateForPlacement.setValue(FACING, pContext.getClickedFace());
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
