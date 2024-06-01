package com.dudko.blazinghot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dudko.blazinghot.registry.BlazingItems;
import com.dudko.blazinghot.registry.BlazingTabs;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

public class BlazingHot implements ModInitializer {
	public static final String ID = "create_blazing_hot";
	public static final String NAME = "Create: Blazing Hot";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	private static final NonNullSupplier<CreateRegistrate> REGISTRATE = NonNullSupplier.lazy(() -> CreateRegistrate.create(
			ID));

	@Override
	public void onInitialize() {
		BlazingItems.setRegister();
		BlazingTabs.setRegister();
		REGISTRATE.get().register();

		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(() -> () -> "{} is accessing Porting Lib from the client!",
												 () -> () -> "{} is accessing Porting Lib from the server!"), NAME);


	}

	public static CreateRegistrate registrate() {
		return REGISTRATE.get();
	}
}
