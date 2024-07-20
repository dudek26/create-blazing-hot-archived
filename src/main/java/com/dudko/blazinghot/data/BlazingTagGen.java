package com.dudko.blazinghot.data;

import com.dudko.blazinghot.registry.BlazingTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class BlazingTagGen {

	public static void generateBlockTags(RegistrateTagsProvider<Block> prov) {
		for (BlazingTags.BlockTags tag : BlazingTags.BlockTags.values()) {
			if (tag.alwaysDatagen) {
				tagAppender(prov, tag);
			}
		}
	}

	public static void generateItemTags(RegistrateTagsProvider<Item> prov) {
		prov
				.addTag(BlazingTags.ItemTags.NETHER_FLORA.tag)
				.add(Items.WARPED_FUNGUS,
					 Items.CRIMSON_FUNGUS,
					 Items.WARPED_ROOTS,
					 Items.CRIMSON_ROOTS,
					 Items.WEEPING_VINES,
					 Items.TWISTING_VINES);

		for (BlazingTags.ItemTags tag : BlazingTags.ItemTags.values()) {
			if (tag.alwaysDatagen) tagAppender(prov, tag);
		}
	}

	public static TagsProvider.TagAppender<Item> tagAppender(RegistrateTagsProvider<Item> prov, BlazingTags.ItemTags tag) {
		return tagAppender(prov, tag.tag);
	}

	public static TagsProvider.TagAppender<Block> tagAppender(RegistrateTagsProvider<Block> prov, BlazingTags.BlockTags tag) {
		return tagAppender(prov, tag.tag);
	}

	public static <T> TagsProvider.TagAppender<T> tagAppender(RegistrateTagsProvider<T> prov, TagKey<T> tag) {
		return prov.addTag(tag);
	}
}
