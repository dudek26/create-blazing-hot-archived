package com.dudko.blazinghot.registry;


import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;

public class BlazingItems {

	private static final CreateRegistrate REGISTRATE = BlazingHot
			.registrate()
			.setCreativeTab(BlazingTabs.BLAZING_HOT.key());

	private static ItemEntry<Item> ingredient(String name) {
		return REGISTRATE.item(name, Item::new).register();
	}

	public static final ItemEntry<Item> BLAZE_GOLD_INGOT = ingredient("blaze_gold_ingot");

	public static void setRegister() {
		BlazingHot.LOGGER.info("Registering Mod Items for " + BlazingHot.ID);
	}
}
