package com.dudko.blazinghot.registry;


import static com.simibubi.create.AllTags.AllItemTags.PLATES;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.item.BlazeArrowItem;
import com.dudko.blazinghot.item.ExtinguishingItem;
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

	private static final CreateRegistrate REGISTRATE = BlazingHot.REGISTRATE.setCreativeTab(BlazingCreativeTabs.BLAZING_HOT.key());

	private static ItemEntry<Item> ingredient(String name) {
		return REGISTRATE.item(name, Item::new).register();
	}

	private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
		return REGISTRATE.item(name, SequencedAssemblyItem::new).register();
	}

	private static ItemEntry<Item> food(String name, Foods food, Rarity rarity) {
		return REGISTRATE.item(name, Item::new).properties(p -> p.rarity(rarity).food(food.foodProperties)).register();
	}

	private static ItemEntry<Item> food(String name, Foods food) {
		return food(name, food, Rarity.COMMON);
	}

	@SafeVarargs
	private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
		return REGISTRATE.item(name, Item::new).tag(tags).register();
	}

	public static final ItemEntry<Item> BLAZE_GOLD_INGOT = taggedIngredient("blaze_gold_ingot",
																			BlazingTags.commonItemTag(
																					"blaze_gold_ingots")), BLAZE_GOLD_NUGGET = taggedIngredient(
			"blaze_gold_nugget",
			BlazingTags.commonItemTag("blaze_gold_nuggets")), BLAZE_GOLD_SHEET = taggedIngredient("blaze_gold_sheet",
																								  BlazingTags.commonItemTag(
																										  "blaze_gold_plates"),
																								  PLATES.tag), BLAZE_GOLD_ROD = taggedIngredient(
			"blaze_gold_rod",
			BlazingTags.commonItemTag("blaze_gold_rods"));

	public static final ItemEntry<Item> NETHERRACK_DUST = taggedIngredient("netherrack_dust",
																		   BlazingTags.commonItemTag("netherrack_dusts")), STONE_DUST = taggedIngredient(
			"stone_dust",
			BlazingTags.commonItemTag("stone_dusts")), SOUL_DUST = taggedIngredient("soul_dust",
																					BlazingTags.commonItemTag(
																							"soul_sand_dusts"));

	public static final ItemEntry<Item> NETHER_COMPOUND = ingredient("nether_compound"), NETHER_ESSENCE = ingredient(
			"nether_essence");

	public static final ItemEntry<ExtinguishingItem> BLAZE_CARROT = REGISTRATE
			.item("blaze_carrot", ExtinguishingItem::new)
			.properties(p -> p.food(Foods.BLAZE_CARROT.foodProperties))
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.blaze_carrot"))
			.register();

	public static final ItemEntry<Item> STELLAR_GOLDEN_APPLE = food("stellar_golden_apple",
																	Foods.STELLAR_GOLDEN_APPLE,
																	Rarity.RARE);

	public static final ItemEntry<SequencedAssemblyItem> GILDED_STELLAR_GOLDEN_APPLE = REGISTRATE
			.item("gilded_stellar_golden_apple", SequencedAssemblyItem::new)
			.properties(p -> p.rarity(Rarity.RARE))
			.register();

	public static final ItemEntry<BlazeArrowItem> BLAZE_ARROW = REGISTRATE
			.item("blaze_arrow", BlazeArrowItem::new)
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.blaze_arrow"))
			.register();

	public static void setRegister() {
		BlazingHot.LOGGER.info("Registering Mod Items for " + BlazingHot.ID);
	}
}
