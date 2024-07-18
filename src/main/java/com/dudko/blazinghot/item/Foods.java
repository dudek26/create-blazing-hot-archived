package com.dudko.blazinghot.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public enum Foods {

	BLAZE_CARROT(new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(1.2F),
				 new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200)),
	STELLAR_GOLDEN_APPLE(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2F),
						 new MobEffectInstance(MobEffects.ABSORPTION, 2400),
						 new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
						 new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000));

	public final MobEffectInstance[] effects;
	public final FoodProperties.Builder propertiesBuilder;

	Foods(FoodProperties.Builder propertiesBuilder, MobEffectInstance... effects) {
		this.propertiesBuilder = propertiesBuilder;
		this.effects = effects;
	}

	public FoodProperties buildProperties() {
		FoodProperties.Builder builder = propertiesBuilder;
		for (MobEffectInstance effect : effects) {
			builder.effect(effect, 1);
		}
		return builder.build();
	}

}
