package com.nao7016.ClayiumAdditions.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public abstract class NEITemplate extends TemplateRecipeHandler {

    public class BaseCachedRecipe extends CachedRecipe {

        private final List<PositionedStack> inputs = new ArrayList<>();
        private final List<PositionedStack> outputs = new ArrayList<>();
        private final String progressBarTexture;

        public BaseCachedRecipe(List<ItemStack> input, List<ItemStack> output, int[][] inputPos, int[][] outputPos,
            String progressBarTexture) {
            for (int i = 0; i < input.size(); i++) {
                inputs.add(new PositionedStack(input.get(i), inputPos[i][0], inputPos[i][1]));
            }
            for (int i = 0; i < output.size(); i++) {
                outputs.add(new PositionedStack(output.get(i), outputPos[i][0], outputPos[i][1]));
            }
            this.progressBarTexture = progressBarTexture;
        }

        public BaseCachedRecipe(List<ItemStack> input, List<ItemStack> output, int[][] inputPos, int[][] outputPos) {
            this(input, output, inputPos, outputPos, "clayium:textures/gui/progressbarfurnace.png");
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return inputs;
        }

        @Override
        public List<PositionedStack> getOtherStacks() {
            return new ArrayList<>();
        }

        @Override
        public PositionedStack getResult() {
            return outputs.isEmpty() ? null : outputs.get(0);
        }

        public String getProgressBarTexture() {
            return progressBarTexture;
        }
    }

    @Override
    public void drawExtras(int recipeIndex) {
        BaseCachedRecipe recipe = (BaseCachedRecipe) arecipes.get(recipeIndex);
        GuiDraw.changeTexture(recipe.getProgressBarTexture());

        int ticks = (int) (System.currentTimeMillis() / 50 % 40);
        float completion = ticks / 40.0F;
        int[] bar = getProgressBarInfo();

        drawProgressBar(bar[0], bar[1], 0, 0, bar[2], bar[3], completion, getProgressBarDirection());
    }

    @Override
    public void loadTransferRects() {
        super.loadTransferRects();
        int[] bar = getProgressBarInfo();
        transferRects.add(
            new TemplateRecipeHandler.RecipeTransferRect(
                new Rectangle(bar[0], bar[1], bar[2], bar[3]),
                getOverlayIdentifier()));
    }

    /**
     * オーバーレイ識別子、handlerIdになる (例:"hammer")
     */
    @Override
    public abstract String getOverlayIdentifier();

    /**
     * レシピ背景テクスチャのパス、"modid:textures/~"
     */
    @Override
    public abstract String getGuiTexture();

    /**
     * 進捗バーの位置とサイズを返す
     * [x, y, wide, height]
     */
    public abstract int[] getProgressBarInfo();

    /**
     * 進捗バーの描画方向
     * 0=左→右, 1=上→下, 2=右→左 , 3=下→上
     */
    public int getProgressBarDirection() {
        return 0;
    }

    @Override
    public String getRecipeName() {
        return I18n.format("recipe." + this.getOverlayIdentifier());
    }

    public void drawProgressBar(int x, int y, int tx, int ty, int w, int h, float completion, int direction) {
        GuiDraw.drawTexturedModalRect(x, y, tx, ty, w, h);
        super.drawProgressBar(x, y, tx, ty + h, w, h, completion, direction);
    }
}
