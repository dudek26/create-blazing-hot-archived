package com.dudko.blazinghot.registry;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.block.modern_lamp.ModernLampBlock;
import com.dudko.blazinghot.block.modern_lamp.ModernLampGenerator;
import com.dudko.blazinghot.block.modern_lamp.ModernLampPanelBlock;
import com.dudko.blazinghot.block.modern_lamp.ModernLampPanelGenerator;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlazingBlocks {

	private static final CreateRegistrate REGISTRATE = BlazingHot.REGISTRATE.setCreativeTab(BlazingCreativeTabs.BLAZING_HOT.key());

	public static final BlockEntry<Block> BLAZE_GOLD_BLOCK = REGISTRATE
			.block("blaze_gold_block", Block::new)
			.initialProperties(() -> Blocks.GOLD_BLOCK)
			.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.tag(BlockTags.NEEDS_IRON_TOOL)
			.tag(Tags.Blocks.STORAGE_BLOCKS)
			.tag(BlockTags.BEACON_BASE_BLOCKS)
			.tag(BlazingTags.BlockTags.BLAZE_GOLD_BLOCKS.tag)
			.item()
			.tag(Tags.Items.STORAGE_BLOCKS)
			.build()
			.register();

	public static final BlockEntry<ModernLampBlock> WHITE_MODERN_LAMP = REGISTRATE
			.block("white_modern_lamp", ModernLampBlock::new)
			.initialProperties(() -> Blocks.GLOWSTONE)
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate(new ModernLampGenerator(DyeColor.WHITE)::generate)
			.item()
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.modern_lamp"))
			.transform(ModelGen.customItemModel("modern_lamp", "white"))
			.register();
	public static final BlockEntry<ModernLampPanelBlock> WHITE_MODERN_LAMP_PANEL = REGISTRATE
			.block("white_modern_lamp_panel", ModernLampPanelBlock::new)
			.initialProperties(() -> Blocks.GLOWSTONE)
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate(new ModernLampPanelGenerator(DyeColor.WHITE)::generate)
			.item()
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.modern_lamp"))
			.transform(ModelGen.customItemModel("modern_lamp_panel", "white"))
			.register();

	public static void setRegister() {

	}

	public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItem(String... path) {
		return b -> {
			for (String p : path)
				b.tag(BlazingTags.commonBlockTag(p));
			ItemBuilder<BlockItem, BlockBuilder<T, P>> item = b.item();
			for (String p : path)
				item.tag(BlazingTags.commonItemTag(p));
			return item;
		};
	}
}
