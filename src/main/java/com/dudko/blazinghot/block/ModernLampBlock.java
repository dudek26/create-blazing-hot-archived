package com.dudko.blazinghot.block;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create.AllTags;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("deprecation")
public class ModernLampBlock extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty LOCKED = BooleanProperty.create("locked");

	public ModernLampBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(LIT, false).setValue(LOCKED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(LIT, LOCKED));
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

}
