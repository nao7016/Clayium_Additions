package com.nao7016.ClayiumAdditions.recipe.storagebox;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class AutoCollectRecipes extends ShapelessRecipes {
    public AutoCollectRecipes(ItemStack storageBox) {
        super(storageBox,getRecipeList(storageBox));
    }

    @Override
    public boolean matches(InventoryCrafting crafting, World world) {
        return super.matches(crafting, world);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting crafting) {
        ItemStack result = null;

        for (int x = 0; x < 3 ; x++) {
            for (int y = 0; y < 3 ; y++) {
                ItemStack itemstack = crafting.getStackInRowAndColumn(x, y);

                if (itemstack == null) continue;
                if (itemstack.getItem() != CAItems.storageBox) continue;

                result = itemstack.copy();
                SBNBTUtil.changeAutoCollect(result);
                break;
            }
        }

        return result;
    }

    private static List<ItemStack> getRecipeList(ItemStack stackStorage) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(stackStorage.copy());
        return list;
    }
}
