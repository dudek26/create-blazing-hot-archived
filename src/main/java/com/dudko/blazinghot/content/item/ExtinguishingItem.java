package com.dudko.blazinghot.content.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExtinguishingItem extends Item {

    private final boolean foil;

    public ExtinguishingItem(Properties properties) {
        this(properties, false);
    }

    public ExtinguishingItem(Properties properties, boolean foil) {
        super(properties);
        this.foil = foil;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.extinguishFire();
        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        if (!foil) return super.isFoil(stack);
        return true;
    }
}
