package com.dudko.blazinghot.registry;

import static net.minecraft.world.item.Items.BUCKET;

import javax.annotation.Nullable;

import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.fluid.FluidHelper;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

import io.github.fabricators_of_create.porting_lib.event.common.FluidPlaceBlockCallback;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.EmptyItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.FullItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@SuppressWarnings("UnstableApiUsage")
public class BlazingFluids {

	private static final CreateRegistrate REGISTRATE = BlazingHot
			.REGISTRATE
			.setCreativeTab(BlazingTabs.BLAZING_HOT.key());


	public static final FluidEntry<SimpleFlowableFluid.Flowing> MOLTEN_GOLD = REGISTRATE
			.standardFluid("molten_gold")
			.lang("Molten Gold")
			.tag(BlazingTags.forgeFluidTag("molten_gold"), FluidTags.LAVA) // fabric: lava tag controls physics
			.fluidProperties(p -> p.levelDecreasePerBlock(2).tickRate(10).flowSpeed(3).blastResistance(100f))
			.fluidAttributes(() -> new CreateAttributeHandler("block.blazinghot.molten_gold", 1500, 1400))
			.block()
			.properties(p -> p.lightLevel(s -> 15))
			.build()
			.onRegisterAfter(Registries.ITEM, moltenGold -> {
				Fluid source = moltenGold.getSource();
				// transfer values
				FluidStorage
						.combinedItemApiProvider(source.getBucket())
						.register(context -> new FullItemFluidStorage(context,
																	  bucket -> ItemVariant.of(BUCKET),
																	  FluidVariant.of(source),
																	  FluidConstants.BUCKET));
				FluidStorage
						.combinedItemApiProvider(BUCKET)
						.register(context -> new EmptyItemFluidStorage(context,
																	   bucket -> ItemVariant.of(source.getBucket()),
																	   source,
																	   FluidConstants.BUCKET));
			})
			.register();
	public static final FluidEntry<SimpleFlowableFluid.Flowing> MOLTEN_BLAZE_GOLD = REGISTRATE
			.standardFluid("molten_blaze_gold")
			.lang("Molten Blaze Gold")
			.tag(BlazingTags.forgeFluidTag("molten_blaze_gold"), FluidTags.LAVA) // fabric: lava tag controls physics
			.fluidProperties(p -> p.levelDecreasePerBlock(2).tickRate(30).flowSpeed(3).blastResistance(100f))
			.fluidAttributes(() -> new CreateAttributeHandler("block.blazinghot.molten_blaze_gold", 1500, 1400))
			.block()
			.properties(p -> p.lightLevel(s -> 15))
			.build()
			.onRegisterAfter(Registries.ITEM, moltenBlazeGold -> {
				Fluid source = moltenBlazeGold.getSource();
				// transfer values
				FluidStorage
						.combinedItemApiProvider(source.getBucket())
						.register(context -> new FullItemFluidStorage(context,
																	  bucket -> ItemVariant.of(BUCKET),
																	  FluidVariant.of(source),
																	  FluidConstants.BUCKET));
				FluidStorage
						.combinedItemApiProvider(BUCKET)
						.register(context -> new EmptyItemFluidStorage(context,
																	   bucket -> ItemVariant.of(source.getBucket()),
																	   source,
																	   FluidConstants.BUCKET));
			})
			.register();

	private record CreateAttributeHandler(Component name, int viscosity,
										  boolean lighterThanAir) implements FluidVariantAttributeHandler {
		private CreateAttributeHandler(String key, int viscosity, int density) {
			this(Component.translatable(key), viscosity, density <= 0);
		}

		public CreateAttributeHandler(String key) {
			this(key, FluidConstants.LAVA_VISCOSITY, 1000);
		}

		@Override
		public Component getName(FluidVariant fluidVariant) {
			return name.copy();
		}

		@Override
		public int getViscosity(FluidVariant variant, @Nullable Level world) {
			return viscosity;
		}

		@Override
		public boolean isLighterThanAir(FluidVariant variant) {
			return lighterThanAir;
		}
	}

	public static void setRegister() {
	}

	public static void registerFluidInteractions() {
		// fabric: no fluid interaction API, use legacy method
		FluidPlaceBlockCallback.EVENT.register(BlazingFluids::whenFluidsMeet);
	}

	public static BlockState whenFluidsMeet(LevelAccessor world, BlockPos pos, BlockState blockState) {
		FluidState fluidState = blockState.getFluidState();

		if (fluidState.isSource() && FluidHelper.isWater(fluidState.getType())) return null;

		for (Direction direction : Iterate.directions) {
			FluidState metFluidState = fluidState.isSource() ?
									   fluidState :
									   world.getFluidState(pos.relative(direction));
			if (!metFluidState.is(FluidTags.LAVA)) continue;
			BlockState waterInteraction = getWaterInteraction(metFluidState);
			if (waterInteraction == null) continue;
			return waterInteraction;
		}
		return null;
	}

	@Nullable
	public static BlockState getWaterInteraction(FluidState fluidState) {
		Fluid fluid = fluidState.getType();
		if (fluid.isSame(MOLTEN_GOLD.get())) return Blocks.COBBLESTONE.defaultBlockState();
		if (fluid.isSame(MOLTEN_BLAZE_GOLD.get())) return Blocks.NETHERRACK.defaultBlockState();
		return null;
	}
}
