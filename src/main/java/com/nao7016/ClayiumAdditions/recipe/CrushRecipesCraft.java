package com.nao7016.ClayiumAdditions.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.nao7016.ClayiumAdditions.item.hammer.itemClayHammer;

public class CrushRecipesCraft implements IRecipe {

    private final ItemStack input;
    private final ItemStack output;

    /**
     * --- Archived: 2025-08-19 ---
     * TODO: クラフトでも粉砕できるようにする
     * TODO: ニコイチ修理で増殖させない
     */
    @Deprecated
    public CrushRecipesCraft(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        boolean hammer = false;
        boolean matchedInput = false;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem() instanceof itemClayHammer) {
                    hammer = true;
                } else if (stack.isItemEqual(input)) {
                    matchedInput = true;
                }
            }
        }

        return hammer && matchedInput;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return output.copy();
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }
}
