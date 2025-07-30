package com.nao7016.ClayiumAdditions.recipe;

import static mods.clayium.util.crafting.CRecipes.*;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.CABlocks;
import com.nao7016.ClayiumAdditions.common.CAItems;

import mods.clayium.block.CBlocks;
import mods.clayium.core.ClayiumCore;
import mods.clayium.item.CMaterials;
import mods.clayium.util.crafting.CRecipes;

public class CARecipes {

    public static void register() {
        registerCESpritted();
        registerTransformer();
        registerAssembler();
    }

    private static void registerCESpritted() {
        for (int tier = 4; tier <= 12; tier++) {
            CRecipes.recipeCuttingMachine.addRecipe(
                new ItemStack(CBlocks.blockCompressedClay, 1, tier),
                4,
                new ItemStack(CAItems.clayEnergy, 9, tier),
                10L,
                (long) ClayiumCore.divideByProgressionRateI(10));
        }
    }

    private static void registerTransformer() {
        CRecipes.registerStackChainRecipes(
            CRecipes.recipeTransformer,
            ii(
                i(Items.chicken, 2, 0),
                i(Items.fish, 1, 0),
                i(Items.fish, 1, 1),
                i(Items.fish, 1, 2),
                i(Items.fish, 1, 3)),
            new int[] { 2, 1, 1, 1, 1 },
            new int[] { 0, 9, 9, 9, 9 },
            200);
        CRecipes.recipeTransformer.addRecipe(
            CMaterials.get(CMaterials.CALCIUM_CHLORIDE, CMaterials.DUST),
            7,
            CMaterials.get(CMaterials.IMPURE_CALCIUM, CMaterials.DUST),
            200000,
            200);
    }

    private static void registerAssembler() {
        CRecipes.recipeAssembler.addRecipe(
            ii(i(CBlocks.blockDenseClayWaterWheel), CMaterials.get(CMaterials.IND_CLAY, CMaterials.LARGE_PLATE)),
            0,
            3,
            ii(i(CABlocks.blockAutoSimpleWaterWheel)),
            40L,
            120L);
        CRecipes.recipeAssembler.addRecipe(
            ii(i(CABlocks.blockAutoSimpleWaterWheel), CMaterials.get(CMaterials.ADVIND_CLAY, CMaterials.LARGE_PLATE)),
            0,
            4,
            ii(i(CABlocks.blockAutoBasicWaterWheel)),
            200L,
            120L);
        CRecipes.recipeAssembler.addRecipe(
            ii(i(CABlocks.blockAutoBasicWaterWheel), CMaterials.get(CMaterials.IMPURE_SILICON, CMaterials.LARGE_PLATE)),
            0,
            4,
            ii(i(CABlocks.blockAutoAdvancedWaterWheel)),
            1000L,
            120L);
    }
}
