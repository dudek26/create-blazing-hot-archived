package com.dudko.blazinghot;

import java.util.List;

import com.dudko.blazinghot.entity.renderer.BlazeArrowRenderer;
import com.dudko.blazinghot.item.Foods;
import com.dudko.blazinghot.registry.BlazingEntityTypes;
import com.dudko.blazinghot.registry.BlazingItems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BlazingHotClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(BlazingEntityTypes.BLAZE_ARROW.get(), BlazeArrowRenderer::new);

		ItemTooltipCallback.EVENT.register(((stack, context, lines) -> {

			addEffectsTooltip(stack, lines, BlazingItems.BLAZE_CARROT.get(), Foods.BLAZE_CARROT);
			addEffectsTooltip(stack, lines, BlazingItems.STELLAR_GOLDEN_APPLE.get(), Foods.STELLAR_GOLDEN_APPLE);

		}));
	}

	private void addEffectsTooltip(ItemStack stack, List<Component> lines, Item item, Foods food) {
		if (stack.is(item)) {
			for (MobEffectInstance effect : food.effects) {
				Component amplifier = effect.getAmplifier() == 0 ?
									  Component.empty() :
									  Component.translatable("potion.potency." + effect.getAmplifier()).append(" ");
				lines.add(Component
								  .translatable(effect.getDescriptionId())
								  .append(" ")
								  .append(amplifier)
								  .append("(")
								  .append(MobEffectUtil.formatDuration(effect, 1))
								  .append(")")
								  .withStyle(effect.getEffect().getCategory().getTooltipFormatting()));
			}
		}
	}
}
