package com.dudko.blazinghot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dudko.blazinghot.registry.BlazingBlocks;
import com.dudko.blazinghot.registry.BlazingFluids;
import com.dudko.blazinghot.registry.BlazingItems;
import com.dudko.blazinghot.registry.BlazingTabs;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

public class BlazingHot implements ModInitializer {
	public static final String ID = "blazinghot";
	public static final String NAME = "Create: Blazing Hot";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	static {
		REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item,
																				  TooltipHelper.Palette.STANDARD_CREATE).andThen(
				TooltipModifier.mapNull(KineticStats.create(item))));
	}

	@Override
	public void onInitialize() {
		BlazingItems.setRegister();
		BlazingTabs.setRegister();
		BlazingBlocks.setRegister();
		BlazingFluids.setRegister();
		BlazingFluids.registerFluidInteractions();
		REGISTRATE.register();

		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(() -> () -> "{} is accessing Porting Lib from the client!",
												 () -> () -> "{} is accessing Porting Lib from the server!"), NAME);


	}
}
