package com.dudko.blazinghot.registry;

import static net.minecraft.world.item.Items.BUCKET;

import javax.annotation.Nullable;

import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.AllFluids;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.fluid.FluidHelper;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

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
import net.minecraft.resources.ResourceLocation;
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
			.registrate()
			.setCreativeTab(BlazingTabs.BLAZING_HOT.key());

	public static final FluidEntry<SimpleFlowableFluid.Flowing> MOLTEN_GOLD = REGISTRATE
			.standardFluid("molten_gold")
			.lang("Molten Gold")
			.tag(BlazingTags.forgeFluidTag("molten_gold"), FluidTags.LAVA) // fabric: lava tag controls physics
			.fluidProperties(p -> p.levelDecreasePerBlock(2).tickRate(25).flowSpeed(10).blastResistance(100f))
			.fluidAttributes(() -> new CreateAttributeHandler("block.create_blazing_hot.molten_gold", 1500, 1400))
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

	public static BlockState whenFluidsMeet(LevelAccessor world, BlockPos pos, BlockState blockState) {
		FluidState fluidState = blockState.getFluidState();

		if (fluidState.isSource() && FluidHelper.isWater(fluidState.getType())) return null;

		for (Direction direction : Iterate.directions) {
			FluidState metFluidState = fluidState.isSource() ?
									   fluidState :
									   world.getFluidState(pos.relative(direction));
			if (!metFluidState.is(FluidTags.LAVA)) continue;
			BlockState lavaInteraction = getWaterInteraction(metFluidState);
			if (lavaInteraction == null) continue;
			return lavaInteraction;
		}
		return null;
	}

	@Nullable
	public static BlockState getWaterInteraction(FluidState fluidState) {
		Fluid fluid = fluidState.getType();
		if (fluid.isSame(MOLTEN_GOLD.get())) return Blocks.COBBLESTONE.defaultBlockState();
		return null;
	}
}
