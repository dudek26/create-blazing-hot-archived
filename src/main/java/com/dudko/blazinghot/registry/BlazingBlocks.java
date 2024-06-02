package com.dudko.blazinghot.registry;

import com.dudko.blazinghot.BlazingHot;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlazingBlocks {

	private static final CreateRegistrate REGISTRATE = BlazingHot.REGISTRATE.setCreativeTab(BlazingTabs.BLAZING_HOT.key());

	public static final BlockEntry<Block> BLAZE_GOLD_BLOCK = REGISTRATE
			.block("blaze_gold_block", Block::new)
			.initialProperties(() -> Blocks.GOLD_BLOCK)
			.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.tag(BlockTags.NEEDS_IRON_TOOL)
			.tag(Tags.Blocks.STORAGE_BLOCKS)
			.tag(BlockTags.BEACON_BASE_BLOCKS)
			.item()
			.tag(Tags.Items.STORAGE_BLOCKS)
			.build()
			.register();


	public static void setRegister() {

	}

	public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItem(String... path) {
		return b -> {
			for (String p : path)
				b.tag(AllTags.forgeBlockTag(p));
			ItemBuilder<BlockItem, BlockBuilder<T, P>> item = b.item();
			for (String p : path)
				item.tag(AllTags.forgeItemTag(p));
			return item;
		};
	}
}
