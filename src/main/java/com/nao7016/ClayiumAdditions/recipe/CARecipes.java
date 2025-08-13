package com.nao7016.ClayiumAdditions.recipe;

import static mods.clayium.util.crafting.CRecipes.*;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.init.Items;

import com.nao7016.ClayiumAdditions.common.CABlocks;
import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.Config;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.clayium.block.CBlocks;
import mods.clayium.core.ClayiumCore;
import mods.clayium.item.CItems;
import mods.clayium.item.CMaterial;
import mods.clayium.item.CMaterials;
import mods.clayium.util.crafting.CRecipes;

public class CARecipes {

    public static void register() {
        registerCESpritted();
        registerGrinder();
        registerTransformer();
        registerAssembler();
        registerCrafting();
        registerCAInjector();
    }

    private static void registerCrafting() {
        GameRegistry.addShapelessRecipe(
            CMaterials.get(CMaterials.CLAY, CMaterials.BALL, 8),
            CMaterials.get(CMaterials.CLAY, CMaterials.LARGE_BALL));
        GameRegistry.addShapelessRecipe(
            CMaterials.get(CMaterials.CLAY, CMaterials.BALL, 4),
            CMaterials.get(CMaterials.CLAY, CMaterials.BLOCK));
        GameRegistry.addRecipe(
            CMaterials.get(CMaterials.CLAY, CMaterials.BLOCK, 8),
            "CC",
            "CC",
            'C',
            CMaterials.get(CMaterials.CLAY, CMaterials.LARGE_BALL));

        if (Config.cfgModeSky) {
            GameRegistry.addShapelessRecipe(i(CAItems.clayBowlEmpty, 2), Items.bowl);
            GameRegistry
                .addShapelessRecipe(CMaterials.get(CMaterials.CLAY, CMaterials.LARGE_BALL, 1), CAItems.clayBowl);
        }
        if (Config.cfgEtFuturum && Loader.isModLoaded("etfuturum")) {
            GameRegistry
                .addRecipe(i(CABlocks.blockRawClayOre, 1, 1), "OOO", "OOO", "OOO", 'O', i(CAItems.rawClayOre, 1, 1));
            GameRegistry
                .addRecipe(i(CABlocks.blockRawClayOre, 1, 2), "OOO", "OOO", "OOO", 'O', i(CAItems.rawClayOre, 1, 2));
            GameRegistry.addShapelessRecipe(i(CAItems.rawClayOre, 9, 1), i(CABlocks.blockRawClayOre, 1, 1));
            GameRegistry.addShapelessRecipe(i(CAItems.rawClayOre, 9, 2), i(CABlocks.blockRawClayOre, 1, 2));
        }
    }

    private static void registerCESpritted() {
        if (Config.cfgSplittedEnergeticClayEnabled) {
            for (int tier = 4; tier <= 12; tier++) {
                CRecipes.recipeCuttingMachine.addRecipe(
                    i(CBlocks.blockCompressedClay, 1, tier),
                    4,
                    i(CAItems.clayEnergy, 9, tier),
                    10L,
                    ClayiumCore.divideByProgressionRateI(10));
            }
        }
    }

    private static void registerGrinder() {
        if (Config.cfgEtFuturum && Loader.isModLoaded("etfuturum")) {
            CRecipes.recipeGrinder.addRecipe(
                i(CABlocks.blockRawClayOre, 1, 1),
                CItems.itemCompressedClayShard.get("2", ClayiumCore.multiplyProgressionRateStackSize(27)),
                9L,
                (long) ClayiumCore.divideByProgressionRateI(6));
            CRecipes.recipeGrinder.addRecipe(
                i(CABlocks.blockRawClayOre, 1, 2),
                CItems.itemCompressedClayShard.get("3", ClayiumCore.multiplyProgressionRateStackSize(45)),
                9L,
                (long) ClayiumCore.divideByProgressionRateI(9));
        }
    }

    private static void registerTransformer() {
        if (Config.cfgFishesTransform) {
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
        }
        if (Config.cfgCalciumChlorideTransform) {
            CRecipes.recipeTransformer.addRecipe(
                CMaterials.get(CMaterials.CALCIUM_CHLORIDE, CMaterials.DUST),
                7,
                CMaterials.get(CMaterials.IMPURE_CALCIUM, CMaterials.DUST),
                200000,
                200);
        }
    }

    private static void registerAssembler() {
        if (Config.cfgAutoWaterWheelEnabled) {
            for (int i = 2; i <= 4; i++) {
                CRecipes.recipeAssembler.addRecipe(
                    ii(i(CABlocks.blocksWaterWheel[i]), CMaterials.get(getTier(i + 1), CMaterials.LARGE_PLATE)),
                    0,
                    i + 1,
                    ii(i(CABlocks.blocksWaterWheel[i + 1])),
                    40L * (long) Math.pow(5.0F, (i - 2)),
                    120L);
            }
        }
    }

    private static void registerCAInjector() {
        CRecipes.recipeCAInjector.addRecipe(
            ii(i(CBlocks.blockElementalMillingMachine), CMaterials.get(CMaterials.ANTIMATTER, CMaterials.GEM, 1)),
            0,
            3,
            ii(i(CBlocks.blocksMillingMachine[3])),
            e(3.0F, 3),
            4000L);
        Block[][] arrayblocklist = new Block[][] { CBlocks.blocksMultitrackBuffer };
        ArrayList<Block[]> blockslist = new ArrayList(Arrays.asList(arrayblocklist));
        if (Config.cfgAutoWaterWheelEnabled) {
            blockslist.add(CABlocks.blocksWaterWheel);
        }

        for (Block[] blocks : blockslist) {
            int j = -1;
            int n = 0;

            for (int i = 0; i < blocks.length; ++i) {
                n = (int) ((double) n + Math.pow(1.3F, i));
                if (n >= 64) {
                    n = 64;
                }

                if (blocks[i] != null) {
                    if (j != -1) {
                        CRecipes.recipeCAInjector.addRecipe(
                            ii(i(blocks[j]), CMaterials.get(CMaterials.ANTIMATTER, CMaterials.GEM, n)),
                            0,
                            i,
                            ii(i(blocks[i])),
                            e(3.0F, i + 1),
                            4000L);
                    }

                    j = i;
                    n = 0;
                }
            }
        }
    }

    public static CMaterial getTier(int tier) {
        if (tier == 1) return CMaterials.CLAY;
        if (tier == 2) return CMaterials.DENSE_CLAY;
        if (tier == 3) return CMaterials.IND_CLAY;
        if (tier == 4) return CMaterials.ADVIND_CLAY;
        if (tier == 5) return CMaterials.IMPURE_SILICON;
        if (ClayiumCore.cfgHardcoreAluminium) {
            if (tier == 6) return CMaterials.IMPURE_ALUMINIUM;
        } else {
            if (tier == 6) return CMaterials.ALUMINIUM;
        }
        if (tier == 7) return CMaterials.CLAY_STEEL;
        if (tier == 8) return CMaterials.CLAYIUM;
        if (tier == 9) return CMaterials.ULTIMATE_ALLOY;
        if (tier == 10) return CMaterials.ANTIMATTER;
        if (tier == 11) return CMaterials.PURE_ANTIMATTER;
        if (tier == 12) return CMaterials.OCTUPLE_CLAY;
        if (tier == 13) return CMaterials.OCTUPLE_PURE_ANTIMATTER;
        return null;
    }
}
