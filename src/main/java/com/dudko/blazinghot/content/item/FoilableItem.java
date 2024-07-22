package com.dudko.blazinghot.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FoilableItem extends Item {

    private final boolean foil;

    public FoilableItem(Properties properties, boolean foil) {
        super(properties);
        this.foil = foil;
    }

    public FoilableItem(Properties properties) {
        this(properties, false);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return foil || super.isFoil(stack);
    }
}
