package com.dudko.blazinghot.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public enum Foods {

	BLAZE_CARROT(new FoodProperties.Builder()
						 .alwaysEat()
						 .nutrition(6)
						 .saturationMod(1.2F)
						 .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100), 1)
						 .build()),
	STELLAR_GOLDEN_APPLE(new FoodProperties.Builder()
								 .alwaysEat()
								 .nutrition(4)
								 .saturationMod(1.2F)
								 .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400), 1)
								 .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1)
								 .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000), 1)
								 .build());

	public final FoodProperties foodProperties;

	Foods(FoodProperties foodProperties) {
		this.foodProperties = foodProperties;
	}

}
