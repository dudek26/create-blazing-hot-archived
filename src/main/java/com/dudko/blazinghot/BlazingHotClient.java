package com.dudko.blazinghot;

import com.dudko.blazinghot.entity.renderer.BlazeArrowRenderer;
import com.dudko.blazinghot.registry.BlazingEntityTypes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BlazingHotClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(BlazingEntityTypes.BLAZE_ARROW.get(), BlazeArrowRenderer::new);

	}
}
