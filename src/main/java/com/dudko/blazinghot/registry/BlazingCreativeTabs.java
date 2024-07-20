package com.dudko.blazinghot.registry;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.Create;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class BlazingCreativeTabs {

	public static final AllCreativeModeTabs.TabInfo BLAZING_HOT = register("blazinghot",
																		   () -> FabricItemGroup
																				   .builder()
																				   .title(Component.translatable(
																						   "itemGroup.blazinghot"))
																				   .displayItems(new BlazingHotItemsGenerator())
																				   .icon(() -> BlazingItems.BLAZE_GOLD_INGOT
																						   .get()
																						   .getDefaultInstance())
																				   .build());

	public static void setRegister() {
	}

	private static AllCreativeModeTabs.TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
		ResourceLocation id = Create.asResource(name);
		ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
		CreativeModeTab tab = supplier.get();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
		return new AllCreativeModeTabs.TabInfo(key, tab);
	}

	public static class BlazingHotItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

		@SuppressWarnings("OptionalGetWithoutIsPresent")
		@Override
		public void accept(CreativeModeTab.@NotNull ItemDisplayParameters parameters, CreativeModeTab.@NotNull Output output) {
			output.accept(BlazingBlocks.WHITE_MODERN_LAMP.asStack());
			output.accept(BlazingBlocks.WHITE_MODERN_REDSTONE_LAMP.asStack());
			output.accept(BlazingBlocks.WHITE_MODERN_LAMP_PANEL.asStack());
			output.accept(BlazingBlocks.WHITE_MODERN_REDSTONE_LAMP_PANEL.asStack());
			output.accept(BlazingItems.NETHER_COMPOUND.asStack());
			output.accept(BlazingItems.NETHER_ESSENCE.asStack());
			output.accept(BlazingBlocks.BLAZE_GOLD_BLOCK.asStack());
			output.accept(BlazingItems.BLAZE_GOLD_INGOT.asStack());
			output.accept(BlazingItems.BLAZE_GOLD_NUGGET.asStack());
			output.accept(BlazingItems.BLAZE_GOLD_SHEET.asStack());
			output.accept(BlazingItems.BLAZE_GOLD_ROD.asStack());
			output.accept(BlazingItems.STONE_DUST);
			output.accept(BlazingItems.SOUL_DUST);
			output.accept(BlazingItems.NETHERRACK_DUST);
			output.accept(BlazingFluids.MOLTEN_GOLD.getBucket().get());
			output.accept(BlazingFluids.MOLTEN_BLAZE_GOLD.getBucket().get());
			output.accept(BlazingItems.BLAZE_CARROT.asStack());
			output.accept(BlazingItems.STELLAR_GOLDEN_APPLE.asStack());
			output.accept(BlazingItems.BLAZE_ARROW.asStack());
		}
	}
}
