package com.nao7016.ClayiumAdditions.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.util.CrushList;

public class CrushRecipes {

    private static final List<CrushList> recipes = new ArrayList<>();
    private static final Set<Block> targets = new HashSet<>();

    public static void addRecipe(ItemStack input, ItemStack output) {
        if (output.stackSize != 1) {
            addRecipe(input, output, output.stackSize);
        } else {
            addRecipe(input, output, 1);
        }
    }

    public static void addRecipe(ItemStack input, ItemStack output, int size) {
        recipes.add(new CrushList(input, output, size));
        // GameRegistry.addRecipe(new CrushRecipesCraft(input, output));

        if (input != null && input.getItem() instanceof ItemBlock) {
            Block block = Block.getBlockFromItem(input.getItem());
            if (block != null) {
                targets.add(block);
            }
        }
    }

    public static ItemStack getResult(ItemStack input) {
        for (CrushList recipe : recipes) {
            if (recipe.matches(input)) {
                return recipe.output.copy();
            }
        }
        return null;
    }

    public static List<CrushList> getAll() {
        return recipes;
    }

    public static boolean isHammerTarget(Block block) {
        return targets.contains(block);
    }
}
