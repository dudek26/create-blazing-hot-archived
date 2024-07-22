package com.dudko.blazinghot.content.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExtinguishingItem extends FoilableItem {

    public ExtinguishingItem(Properties properties) {
        super(properties);
    }

    public ExtinguishingItem(Properties properties, boolean foil) {
        super(properties, foil);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.extinguishFire();
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
