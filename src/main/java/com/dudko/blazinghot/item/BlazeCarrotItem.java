package com.dudko.blazinghot.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

public class BlazeCarrotItem extends Item {

	public BlazeCarrotItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		livingEntity.extinguishFire();
		return super.finishUsingItem(stack, level, livingEntity);
	}
}
