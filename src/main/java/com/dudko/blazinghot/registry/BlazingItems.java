package com.dudko.blazinghot.registry;


import static com.simibubi.create.AllTags.AllItemTags.CREATE_INGOTS;
import static com.simibubi.create.AllTags.AllItemTags.PLATES;

import java.util.List;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.item.BlazeArrowItem;
import com.dudko.blazinghot.item.Foods;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.core.registries.Registries;
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

	public static final ItemEntry<Item> NETHERRACK_DUST = taggedIngredient("netherrack_dust",
																		   BlazingTags.forgeItemTag("netherrack_dusts")), STONE_DUST = taggedIngredient(
			"stone_dust",
			BlazingTags.forgeItemTag("stone_dusts")), SOUL_DUST = taggedIngredient("soul_dust",
																				   BlazingTags.forgeItemTag(
																						   "soul_sand_dusts"));

	public static final ItemEntry<Item> NETHER_COMPOUND = ingredient("nether_compound"), NETHER_ESSENCE = ingredient(
			"nether_essence");

	public static final ItemEntry<Item> BLAZE_CARROT = REGISTRATE
			.item("blaze_carrot", Item::new)
			.properties(p -> p.food(Foods.BLAZE_CARROT.buildProperties()))
			.register();

	public static final ItemEntry<Item> STELLAR_GOLDEN_APPLE = REGISTRATE
			.item("stellar_golden_apple", Item::new)
			.properties(p -> p.rarity(Rarity.RARE).food(Foods.STELLAR_GOLDEN_APPLE.buildProperties()))
			.register();

	public static final ItemEntry<SequencedAssemblyItem> GILDED_STELLAR_GOLDEN_APPLE = REGISTRATE
			.item("gilded_stellar_golden_apple", SequencedAssemblyItem::new)
			.properties(p -> p.rarity(Rarity.EPIC))
			.register();

	public static final ItemEntry<BlazeArrowItem> BLAZE_ARROW = REGISTRATE
			.item("blaze_arrow", BlazeArrowItem::new)
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.blaze_arrow"))
			.register();

	public static final List<Item> FOOD_ITEMS = List.of(BLAZE_CARROT.get(), STELLAR_GOLDEN_APPLE.get());

	public static void setRegister() {
		BlazingHot.LOGGER.info("Registering Mod Items for " + BlazingHot.ID);
	}
}
