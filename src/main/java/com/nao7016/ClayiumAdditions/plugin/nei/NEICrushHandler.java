package com.nao7016.ClayiumAdditions.plugin.nei;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.item.itemClayHammer;
import com.nao7016.ClayiumAdditions.recipe.CrushRecipes;
import com.nao7016.ClayiumAdditions.util.CrushList;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEICrushHandler extends TemplateRecipeHandler {

    public class CachedCrushRecipe extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack output;

        public CachedCrushRecipe(CrushList recipe) {
            this.input = new PositionedStack(recipe.input, 48, 24);
            this.output = new PositionedStack(recipe.output, 108, 24);
        }

        @Override
        public PositionedStack getIngredient() {
            return input;
        }

        @Override
        public PositionedStack getResult() {
            return output;
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
        return "hammer";
    }

    @Override
    public String getRecipeName() {
        return I18n.format("recipe." + this.getOverlayIdentifier());
    }

    @Override
    public String getGuiTexture() {
        return "minecraft:textures/gui/container/furnace.png"; // temp
    }

    @Override
    public void loadTransferRects() {}
}
