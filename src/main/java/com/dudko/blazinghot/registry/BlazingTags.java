package com.dudko.blazinghot.registry;

import static com.dudko.blazinghot.registry.BlazingTags.NameSpace.COMMON;
import static com.dudko.blazinghot.registry.BlazingTags.NameSpace.MOD;

import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("unused")
public class BlazingTags {

	public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
		return TagKey.create(registry.key(), id);
	}

	public static <T> TagKey<T> commonTag(Registry<T> registry, String path) {
		return optionalTag(registry, new ResourceLocation("c", path));
	}

	public static TagKey<Block> commonBlockTag(String path) {
		return commonTag(BuiltInRegistries.BLOCK, path);
	}

	public static TagKey<Item> commonItemTag(String path) {
		return commonTag(BuiltInRegistries.ITEM, path);
	}

	public static TagKey<Fluid> commonFluidTag(String path) {
		return commonTag(BuiltInRegistries.FLUID, path);
	}

	public enum NameSpace {
		MOD(BlazingHot.ID, false, true),
		COMMON("c");

		public final String id;
		public final boolean optionalDefault;
		public final boolean alwaysDatagenDefault;

		NameSpace(String id) {
			this(id, true, false);
		}

		NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
			this.id = id;
			this.optionalDefault = optionalDefault;
			this.alwaysDatagenDefault = alwaysDatagenDefault;
		}

	}

	public enum BlockTags {
		BLAZE_GOLD_BLOCKS(COMMON);

		public final TagKey<Block> tag;
		public final boolean alwaysDatagen;


		BlockTags() {
			this(MOD);
		}

		BlockTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		BlockTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		BlockTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		BlockTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
			if (optional) {
				tag = optionalTag(BuiltInRegistries.BLOCK, id);
			}
			else {
				tag = TagKey.create(Registries.BLOCK, id);
			}
			this.alwaysDatagen = alwaysDatagen;
		}

		@SuppressWarnings("deprecation")
		public boolean matches(Block block) {
			return block.builtInRegistryHolder().is(tag);
		}

		public boolean matches(ItemStack stack) {
			return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
		}

		public boolean matches(BlockState state) {
			return state.is(tag);
		}

		public static void register() {
		}
	}

	public enum FluidTags {
		MOLTEN_BLAZE_GOLD(COMMON),
		MOLTEN_GOLD(COMMON);

		public final TagKey<Block> tag;
		public final boolean alwaysDatagen;


		FluidTags() {
			this(MOD);
		}

		FluidTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		FluidTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		FluidTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		FluidTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
			if (optional) {
				tag = optionalTag(BuiltInRegistries.BLOCK, id);
			}
			else {
				tag = TagKey.create(Registries.BLOCK, id);
			}
			this.alwaysDatagen = alwaysDatagen;
		}

		@SuppressWarnings("deprecation")
		public boolean matches(Block block) {
			return block.builtInRegistryHolder().is(tag);
		}

		public boolean matches(ItemStack stack) {
			return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
		}

		public boolean matches(BlockState state) {
			return state.is(tag);
		}

		public static void register() {
		}
	}

	public static void register() {
		BlockTags.register();
		FluidTags.register();
		ItemTags.register();
	}

	public enum ItemTags {
		BLAZE_GOLD_BLOCKS(COMMON),
		BLAZE_GOLD_INGOTS(COMMON),
		BLAZE_GOLD_RODS(COMMON),
		BLAZE_GOLD_NUGGETS(COMMON),
		BLAZE_GOLD_PLATES(COMMON),
		MOLTEN_GOLD_BUCKETS(COMMON),
		MOLTEN_BLAZE_GOLD_BUCKETS(COMMON),
		NETHERRACK_DUSTS(COMMON),
		SOUL_SAND_DUSTS(COMMON),
		FOODS(COMMON),

		NETHER_FLORA;

		public final TagKey<Item> tag;
		public final boolean alwaysDatagen;

		ItemTags() {
			this(NameSpace.MOD);
		}

		ItemTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ItemTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ItemTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		ItemTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
			if (optional) {
				tag = optionalTag(BuiltInRegistries.ITEM, id);
			}
			else {
				tag = TagKey.create(Registries.ITEM, id);
			}
			this.alwaysDatagen = alwaysDatagen;
		}

		@SuppressWarnings("deprecation")
		public boolean matches(Item item) {
			return item.builtInRegistryHolder().is(tag);
		}

		public boolean matches(ItemStack stack) {
			return stack.is(tag);
		}

		public static void register() {
		}
	}

	public static void provideLangEntries(BiConsumer<String, String> consumer) {
		for (BlockTags blockTag : BlockTags.values()) {
			ResourceLocation loc = blockTag.tag.location();
			consumer.accept("tag.block." + loc.getNamespace() + "." + loc.getPath().replace('/', '.'),
							titleCaseConversion(blockTag.name()).replace('_', ' '));
		}

		for (ItemTags itemTag : ItemTags.values()) {
			ResourceLocation loc = itemTag.tag.location();
			consumer.accept("tag.item." + loc.getNamespace() + "." + loc.getPath().replace('/', '.'),
							titleCaseConversion(itemTag.name().replace('_', ' ')));
		}

		for (FluidTags itemTag : FluidTags.values()) {
			ResourceLocation loc = itemTag.tag.location();
			consumer.accept("tag.fluid." + loc.getNamespace() + "." + loc.getPath().replace('/', '.'),
							titleCaseConversion(itemTag.name().replace('_', ' ')));
		}
	}

	private static String titleCaseConversion(String inputString) {
		if (StringUtils.isBlank(inputString)) {
			return "";
		}

		if (StringUtils.length(inputString) == 1) {
			return inputString.toUpperCase(Locale.ROOT);
		}

		StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

		Stream.of(inputString.split(" ")).forEach(stringPart -> {
			if (stringPart.length() > 1) resultPlaceHolder
					.append(stringPart.substring(0, 1).toUpperCase(Locale.ROOT))
					.append(stringPart.substring(1).toLowerCase(Locale.ROOT));
			else resultPlaceHolder.append(stringPart.toUpperCase(Locale.ROOT));

			resultPlaceHolder.append(" ");
		});
		return StringUtils.trim(resultPlaceHolder.toString());
	}

}
