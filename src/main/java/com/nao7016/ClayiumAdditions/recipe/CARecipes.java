package com.nao7016.ClayiumAdditions.recipe;

import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.ItemClayiumAdditions;

import mods.clayium.block.CBlocks;
import mods.clayium.core.ClayiumCore;
import mods.clayium.util.crafting.CRecipes;

public class CARecipes {

    public static void register() {
        registerCESpritted();
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
}
