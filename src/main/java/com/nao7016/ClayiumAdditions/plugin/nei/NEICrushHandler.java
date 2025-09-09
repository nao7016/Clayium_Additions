package com.nao7016.ClayiumAdditions.plugin.nei;

import java.util.Arrays;

import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.item.hammer.itemClayHammer;
import com.nao7016.ClayiumAdditions.recipe.CrushRecipes;
import com.nao7016.ClayiumAdditions.util.CrushList;

public class NEICrushHandler extends NEITemplate {

    public class CachedCrushRecipe extends NEITemplate.BaseCachedRecipe {

        public CachedCrushRecipe(CrushList recipe) {
            super(
                Arrays.asList(recipe.input),
                Arrays.asList(recipe.output),
                new int[][] { { 52, 21 } },
                new int[][] { { 98, 21 } },
                "clayiumadditions:textures/guis/hammer_progressbar.png");
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getOverlayIdentifier())) {
            for (CrushList recipe : CrushRecipes.getAll()) {
                arecipes.add(new CachedCrushRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (CrushList recipe : CrushRecipes.getAll()) {
            if (recipe.output.isItemEqual(result)) {
                arecipes.add(new CachedCrushRecipe(recipe));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (CrushList recipe : CrushRecipes.getAll()) {
            if (recipe.matches(ingredient) || ingredient.getItem() instanceof itemClayHammer) {
                arecipes.add(new CachedCrushRecipe(recipe));
            }
        }
    }

    @Override
    public String getOverlayIdentifier() {
        return "ClayHammer";
    }

    @Override
    public String getGuiTexture() {
        return "clayiumadditions:textures/guis/nei.png";
    }

    @Override
    public int[] getProgressBarInfo() {
        return new int[] { 71, 21, 24, 17 }; // x, y, width, height
    }

    @Override
    public int getProgressBarDirection() {
        return 0;
    }
}
