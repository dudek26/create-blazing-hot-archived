package com.dudko.blazinghot.entity.renderer;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.entity.BlazeArrowEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BlazeArrowRenderer extends ArrowRenderer<BlazeArrowEntity> {

	@Override
	public ResourceLocation getTextureLocation(BlazeArrowEntity entity) {
		return new ResourceLocation(BlazingHot.ID, "textures/entity/projectiles/blaze_arrow.png");
	}

	public BlazeArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
	}
}
