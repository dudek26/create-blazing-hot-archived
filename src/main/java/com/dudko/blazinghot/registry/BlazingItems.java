package com.dudko.blazinghot.registry;


import static com.simibubi.create.AllTags.AllItemTags.CREATE_INGOTS;
import static com.simibubi.create.AllTags.AllItemTags.PLATES;

import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

@SuppressWarnings("SameParameterValue")
public class BlazingItems {

	private static final CreateRegistrate REGISTRATE = BlazingHot.REGISTRATE.setCreativeTab(BlazingTabs.BLAZING_HOT.key());

	private static ItemEntry<Item> ingredient(String name) {
		return REGISTRATE.item(name, Item::new).register();
	}

	private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
		return REGISTRATE.item(name, SequencedAssemblyItem::new).register();
	}

	@SafeVarargs
	private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
		return REGISTRATE.item(name, Item::new).tag(tags).register();
	}

	public static final ItemEntry<Item> BLAZE_GOLD_INGOT = taggedIngredient("blaze_gold_ingot",
																			CREATE_INGOTS.tag,
																			BlazingTags.forgeItemTag("blaze_gold_ingots")), BLAZE_GOLD_NUGGET = taggedIngredient(
			"blaze_gold_nugget",
			BlazingTags.forgeItemTag("blaze_gold_nuggets")), BLAZE_GOLD_SHEET = taggedIngredient("blaze_gold_sheet",
																								 BlazingTags.forgeItemTag(
																										 "blaze_gold_plates"),
																								 PLATES.tag), BLAZE_GOLD_ROD = taggedIngredient(
			"blaze_gold_rod",
			BlazingTags.forgeItemTag("blaze_gold_rods"));

	public static final ItemEntry<Item> NETHER_DUST = ingredient("nether_dust");

	public static final ItemEntry<Item> NETHERRACK_DUST = taggedIngredient("netherrack_dust",
																		   BlazingTags.forgeItemTag("netherrack_dusts")), STONE_DUST = taggedIngredient(
			"stone_dust",
			BlazingTags.forgeItemTag("stone_dusts")), SOUL_DUST = taggedIngredient("soul_dust",
																				   BlazingTags.forgeItemTag(
																						   "soul_sand_dusts"));

	public static final ItemEntry<Item> NETHER_COMPOUND = ingredient("nether_compound");

	public static final ItemEntry<SequencedAssemblyItem> ENRICHED_GOLDEN_APPLE = REGISTRATE
			.item("enriched_golden_apple", SequencedAssemblyItem::new)
			.properties(p -> p.rarity(Rarity.RARE))
			.register();


	public static void setRegister() {
		BlazingHot.LOGGER.info("Registering Mod Items for " + BlazingHot.ID);
	}
}
