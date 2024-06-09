package com.dudko.blazinghot.entity;

import com.dudko.blazinghot.registry.BlazingItems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

public class BlazeArrow extends Arrow {
	public BlazeArrow(EntityType<? extends Arrow> entityType, Level level) {
		super(entityType, level);
	}

	public BlazeArrow(Level level, double x, double y, double z) {
		super(level, x, y, z);
	}

	public BlazeArrow(Level level, LivingEntity shooter) {
		super(level, shooter);
	}

	@Override
	protected @NotNull ItemStack getPickupItem() {
		return new ItemStack(BlazingItems.BLAZE_ARROW.get());
	}
}
