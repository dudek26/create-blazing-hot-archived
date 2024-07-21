package com.dudko.blazinghot.registry;

import static com.dudko.blazinghot.BlazingHot.REGISTRATE;
import static com.dudko.blazinghot.registry.BlazingItems.setupCreativeTab;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.content.block.modern_lamp.ModernLampBlock;
import com.dudko.blazinghot.content.block.modern_lamp.ModernLampGenerator;
import com.dudko.blazinghot.content.block.modern_lamp.ModernLampPanelBlock;
import com.dudko.blazinghot.content.block.modern_lamp.ModernLampPanelGenerator;
import com.dudko.blazinghot.util.DyeUtil;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class BlazingBlocks {

	public static final BlockEntry<Block> BLAZE_GOLD_BLOCK = REGISTRATE
			.block("blaze_gold_block", Block::new)
			.initialProperties(() -> net.minecraft.world.level.block.Blocks.GOLD_BLOCK)
			.properties(p -> p.lightLevel(s -> 8))
			.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.tag(BlockTags.NEEDS_IRON_TOOL)
			.tag(Tags.Blocks.STORAGE_BLOCKS)
			.tag(BlockTags.BEACON_BASE_BLOCKS)
			.tag(BlazingTags.Blocks.BLAZE_GOLD_BLOCKS.tag)
			.item()
			.tag(Tags.Items.STORAGE_BLOCKS)
			.build()
			.register();

	public static final BlockEntry<ModernLampPanelBlock> WHITE_MODERN_LAMP_PANEL = REGISTRATE
			.block("white_modern_lamp_panel", ModernLampPanelBlock::new)
			.initialProperties(() -> net.minecraft.world.level.block.Blocks.GLOWSTONE)
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.properties(p -> p.lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
			.blockstate(new ModernLampPanelGenerator(DyeColor.WHITE)::generate)
			.item()
			.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.modern_lamp"))
			.transform(ModelGen.customItemModel("modern_lamp_panel", "white"))
			.register();

	public static final DyedBlockList<ModernLampBlock> MODERN_LAMP_BLOCKS = new DyedBlockList<>(color -> {
		String colorName = color.getSerializedName();
		return REGISTRATE
				.block(colorName + "_modern_lamp", p -> new ModernLampBlock(p, color))
				.initialProperties(() -> net.minecraft.world.level.block.Blocks.GLOWSTONE)
				.properties(p -> p.mapColor(color).lightLevel(s -> s.getValue(ModernLampBlock.LIT) ? 15 : 0))
				.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag, BlazingTags.Blocks.MODERN_LAMPS.tag)
				.blockstate(new ModernLampGenerator(color)::generate)
				.recipe((c, p) -> {
					ShapedRecipeBuilder
							.shaped(RecipeCategory.REDSTONE, c.get())
							.pattern("lll")
							.pattern("ldl")
							.pattern("lll")
							.define('l', BlazingTags.Items.MODERN_LAMPS.tag)
							.define('d', DyeUtil.getDyeTag(color))
							.unlockedBy("has_lamp", RegistrateRecipeProvider.has(BlazingTags.Items.MODERN_LAMPS.tag))
							.save(p, BlazingHot.asResource("crafting/modern_lamp/" + c.getName() + "_from_other_lamps"));
					ShapelessRecipeBuilder
							.shapeless(RecipeCategory.REDSTONE, c.get())
							.requires(BlazingTags.Items.MODERN_LAMPS.tag)
							.requires(DyeUtil.getDyeTag(color))
							.unlockedBy("has_lamp", RegistrateRecipeProvider.has(BlazingTags.Items.MODERN_LAMPS.tag))
							.save(p, BlazingHot.asResource("crafting/modern_lamp/" + c.getName() + "_from_other_lamp"));
				})
				.item()
				.tag(BlazingTags.Items.MODERN_LAMPS.tag)
				.onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.blazinghot.modern_lamp"))
				.model((c, b) -> b.blockItem(c).texture("#all", b.modLoc("block/modern_lamp/" + colorName)))
				.build()
				.register();
	});

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
