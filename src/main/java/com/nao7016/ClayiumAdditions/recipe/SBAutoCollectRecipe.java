package com.nao7016.ClayiumAdditions.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;

public class SBAutoCollectRecipe extends ShapelessRecipes {

    public SBAutoCollectRecipe(ItemStack storageBox) {
        super(storageBox, getRecipeList(storageBox));
    }

    @Override
    public boolean matches(InventoryCrafting crafting, World world) {
        for (int i = 0; i < crafting.getSizeInventory(); i++) {
            ItemStack stack = crafting.getStackInSlot(i);
            if (stack == null) continue;

            // 元のコードでは itemStBox 固定ですが、派生も OK にする
            if (!(stack.getItem() instanceof itemStorageBox)) {
                return false; // StorageBox 系以外は無効
            }
        }
        // 全て StorageBox 系だった場合のみマッチ
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                ItemStack itemStack = inventory.getStackInRowAndColumn(col, row);
                if (itemStack != null && itemStack.getItem() instanceof itemStorageBox) {
                    ItemStack result = itemStack.copy();
                    itemStorageBox.changeAutoCollect(result);
                    return result;
                }
            }
        }
        return null;
    }

    private static List<ItemStack> getRecipeList(ItemStack stackStorage) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(stackStorage.copy());
        return list;
    }
}
