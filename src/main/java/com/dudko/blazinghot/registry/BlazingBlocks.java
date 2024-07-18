package com.dudko.blazinghot.registry;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.block.ModernLampBlock;
import com.dudko.blazinghot.block.ModernLampPanelBlock;
import com.dudko.blazinghot.block.ModernRedstoneLampBlock;
import com.dudko.blazinghot.block.ModernRedstoneLampPanelBlock;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

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

	public static final BlockEntry<ModernLampBlock> MODERN_LAMP = REGISTRATE
			.block("modern_lamp", ModernLampBlock::new)
			.initialProperties(() -> Blocks.GLOWSTONE)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate((c, p) -> BlockStateGen.simpleBlock(c, p, s -> {
				boolean powered = s.getValue(BooleanProperty.create("lit"));
				String name = c.getName() + (powered ? "_powered" : "");
				return p.models().cubeAll(name, p.modLoc("block/" + name));
			}))
			.simpleItem()
			.register();
	public static final BlockEntry<ModernLampPanelBlock> MODERN_LAMP_PANEL = REGISTRATE
			.block("modern_lamp_panel", ModernLampPanelBlock::new)
			.initialProperties(() -> Blocks.GLOWSTONE)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate((c, p) -> BlockStateGen.simpleBlock(c, p, s -> {
				boolean powered = s.getValue(BooleanProperty.create("lit"));
				String name = c.getName() + (powered ? "_powered" : "");
				return p.models().cubeAll(name, p.modLoc("block/" + name));
			}))
			.simpleItem()
			.register();

	public static final BlockEntry<ModernRedstoneLampBlock> MODERN_REDSTONE_LAMP = REGISTRATE
			.block("modern_redstone_lamp", ModernRedstoneLampBlock::new)
			.initialProperties(() -> Blocks.REDSTONE_LAMP)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate((c, p) -> BlockStateGen.simpleBlock(c, p, s -> {
				boolean powered = s.getValue(BooleanProperty.create("lit"));
				String name = c.getName() + (powered ? "_powered" : "");
				return p.models().cubeAll(name, p.modLoc("block/" + name));
			}))
			.simpleItem()
			.register();
	public static final BlockEntry<ModernRedstoneLampPanelBlock> MODERN_REDSTONE_LAMP_PANEL = REGISTRATE
			.block("modern_redstone_lamp_panel", ModernRedstoneLampPanelBlock::new)
			.initialProperties(() -> Blocks.REDSTONE_LAMP)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate((c, p) -> BlockStateGen.simpleBlock(c, p, s -> {
				boolean powered = s.getValue(BooleanProperty.create("lit"));
				String name = c.getName() + (powered ? "_powered" : "");
				return p.models().cubeAll(name, p.modLoc("block/" + name));
			}))
			.simpleItem()
			.register();

	public static final BlockEntry<SlabBlock> GRASS_SLAB = REGISTRATE
			.block("grass_slab", SlabBlock::new)
			.initialProperties(() -> Blocks.GRASS_BLOCK)
			.blockstate((c, p) -> p.slabBlock(c.get(),
											  new ResourceLocation("block/grass_block"),
											  new ResourceLocation("block/grass")))
			.simpleItem()
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
