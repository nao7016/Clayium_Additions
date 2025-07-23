package com.nao7016.ClayiumAdditions.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.ItemClayiumAdditions;

import mods.clayium.block.CBlocks;
import mods.clayium.core.ClayiumCore;
import mods.clayium.util.crafting.CRecipes;

import static mods.clayium.util.crafting.CRecipes.*;

public class CARecipes {

    public static void register() {
        registerCESpritted();
        registerTransformer();
    }

    private static void registerCESpritted() {
        for (int tier = 4; tier <= 12; tier++) {
            CRecipes.recipeCuttingMachine.addRecipe(
                new ItemStack(CBlocks.blockCompressedClay, 1, tier),
                4,
                new ItemStack(ItemClayiumAdditions.clayEnergy, 9, tier),
                10L,
                (long) ClayiumCore.divideByProgressionRateI(10));
        }
    }

    private static void registerTransformer() {
        CRecipes.registerStackChainRecipes(
            CRecipes.recipeTransformer, ii(
                i(Items.chicken, 2, 0),
                i(Items.fish, 1, 0),
                i(Items.fish, 1, 1),
                i(Items.fish, 1, 2),
                i(Items.fish, 1, 3)
            ),
            new int[] {2, 1, 1, 1, 1},
            new int[] {0, 9, 9, 9, 9},
            200
        );
    }
}
