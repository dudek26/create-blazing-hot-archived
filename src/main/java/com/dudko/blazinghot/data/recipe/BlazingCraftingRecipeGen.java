package com.dudko.blazinghot.data.recipe;

import com.dudko.blazinghot.BlazingHot;
import com.dudko.blazinghot.registry.BlazingBlocks;
import com.dudko.blazinghot.registry.BlazingItems;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import static com.dudko.blazinghot.registry.BlazingBlocks.BLAZE_GOLD_BLOCK;
import static com.dudko.blazinghot.registry.BlazingItems.*;
import static com.dudko.blazinghot.registry.BlazingTags.Items.BLAZE_GOLD_NUGGETS;
import static com.dudko.blazinghot.registry.BlazingTags.Items.BLAZE_GOLD_RODS;

public class BlazingCraftingRecipeGen {

    private final RegistrateRecipeProvider provider;

    public BlazingCraftingRecipeGen(RegistrateRecipeProvider provider) {
        this.provider = provider;
    }

    private void compressing(RecipeCategory category, ItemLike ingredient, ItemLike result) {
        compressing(category, ingredient, result, RegistrateRecipeProvider.has(ingredient));
    }

    private void compressing(RecipeCategory category, ItemLike ingredient, ItemLike result, CriterionTriggerInstance unlockedBy) {
        ShapedRecipeBuilder
                .shaped(category, result)
                .define('X', ingredient)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_" + unlockedBy.getCriterion().getPath(), unlockedBy)
                .save(provider,
                      BlazingHot.asResource("crafting_shaped/" + result.asItem() + "_from_" + ingredient.asItem()));
    }

    private void decompressing(RecipeCategory category, ItemLike ingredient, ItemLike result, int count) {
        decompressing(category, ingredient, result, count, RegistrateRecipeProvider.has(ingredient));
    }

    private void decompressing(RecipeCategory category, ItemLike ingredient, ItemLike result, int count, CriterionTriggerInstance unlockedBy) {
        ShapelessRecipeBuilder
                .shapeless(category, result, count)
                .requires(ingredient)
                .unlockedBy("has_" + unlockedBy.getCriterion().getPath(), unlockedBy)
                .save(provider,
                      BlazingHot.asResource("crafting_shapeless/" + result.asItem() + "_from_" + ingredient.asItem()));
    }

    private void covering(RecipeCategory category, ItemLike ingredient, TagKey<Item> cover, ItemLike result) {
        covering(category, ingredient, cover, result, RegistrateRecipeProvider.has(ingredient));
    }

    private void covering(RecipeCategory category, ItemLike ingredient, TagKey<Item> cover, ItemLike result, CriterionTriggerInstance unlockedBy) {
        ShapedRecipeBuilder
                .shaped(category, result)
                .define('X', ingredient)
                .define('Y', cover)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_" + unlockedBy.getCriterion().getPath(), unlockedBy)
                .save(provider, BlazingHot.asResource("crafting_shaped/" + result.asItem()));
    }

    public void generate() {
        compressing(RecipeCategory.MISC, BLAZE_GOLD_INGOT, BLAZE_GOLD_BLOCK);
        compressing(RecipeCategory.MISC, BLAZE_GOLD_NUGGET, BLAZE_GOLD_INGOT, trigger(BLAZE_GOLD_INGOT));

        decompressing(RecipeCategory.MISC, BLAZE_GOLD_BLOCK, BLAZE_GOLD_INGOT, 9,
                      trigger(BLAZE_GOLD_INGOT));
        decompressing(RecipeCategory.MISC, BLAZE_GOLD_INGOT, BLAZE_GOLD_NUGGET, 9);

        covering(RecipeCategory.FOOD, Items.CARROT, BLAZE_GOLD_NUGGETS.tag, BLAZE_CARROT,
                 trigger(BLAZE_GOLD_INGOT));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, BlazingItems.BLAZE_ARROW, 2)
                .pattern("X")
                .pattern("Y")
                .pattern("Z")
                .define('X', ItemTags.COALS)
                .define('Y', BLAZE_GOLD_ROD)
                .define('Z', Items.FEATHER)
                .unlockedBy("has_" + BLAZE_GOLD_ROD.get(), trigger(BLAZE_GOLD_ROD))
                .save(provider, BlazingHot.asResource("crafting_shaped/blaze_arrow"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.REDSTONE, BlazingBlocks.MODERN_LAMP_BLOCKS.get(DyeColor.WHITE))
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .define('X', BLAZE_GOLD_RODS.tag)
                .define('Y', Items.GLOWSTONE)
                .unlockedBy("has_" + BLAZE_GOLD_ROD.get(), trigger(BLAZE_GOLD_ROD))
                .save(provider, BlazingHot.asResource("crafting_shaped/white_modern_lamp_block"));
    }

    private CriterionTriggerInstance trigger(ItemLike item) {
        return RegistrateRecipeProvider.has(item);
    }

    private CriterionTriggerInstance trigger(TagKey<Item> tag) {
        return RegistrateRecipeProvider.has(tag);
    }

}
