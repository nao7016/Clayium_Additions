package com.nao7016.ClayiumAdditions.recipe;

import static mods.clayium.util.crafting.CRecipes.*;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapelessRecipes;

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
        registerCrafting();
        registerHammer();
        registerCESplitted();
        registerCMachines();
        registerGrinder();
        registerTransformer();
        registerAssembler();
        registerBlastFurnace();
        registerReactor();
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
            GameRegistry.addRecipe(
                i(CAItems.clayHammer),
                "CCC",
                " S ",
                " S ",
                'C',
                CMaterials.get(CMaterials.CLAY, CMaterials.CYLINDER),
                'S',
                CMaterials.get(CMaterials.CLAY, CMaterials.STICK));
        }

        if (Config.cfgMiningHammer) {
            GameRegistry.addRecipe(
                i(CAItems.clayMiningHammer),
                "CBC",
                " S ",
                " S ",
                'B',
                CMaterials.get(CMaterials.IND_CLAY, CMaterials.BLOCK),
                'C',
                CMaterials.get(CMaterials.DENSE_CLAY, CMaterials.CYLINDER),
                'S',
                CMaterials.get(CMaterials.DENSE_CLAY, CMaterials.STICK));
        }

        if (Config.cfgEtFuturum && Loader.isModLoaded("etfuturum")) {
            GameRegistry
                .addRecipe(i(CABlocks.blockRawClayOre, 1, 1), "OOO", "OOO", "OOO", 'O', i(CAItems.rawClayOre, 1, 1));
            GameRegistry
                .addRecipe(i(CABlocks.blockRawClayOre, 1, 2), "OOO", "OOO", "OOO", 'O', i(CAItems.rawClayOre, 1, 2));
            GameRegistry.addShapelessRecipe(i(CAItems.rawClayOre, 9, 1), i(CABlocks.blockRawClayOre, 1, 1));
            GameRegistry.addShapelessRecipe(i(CAItems.rawClayOre, 9, 2), i(CABlocks.blockRawClayOre, 1, 2));
        }

        if (Config.cfgStorageBox) {
            GameRegistry.addRecipe(
                i(CAItems.storageBox),
                "CCC",
                "CSC",
                "CCC",
                'C',
                i(Blocks.chest),
                'S',
                i(CBlocks.blockStorageContainer, 1, 480));
            GameRegistry.addRecipe(
                i(CAItems.clayStorageBox),
                "CCC",
                "CBC",
                "CCC",
                'C',
                i(Blocks.chest),
                'B',
                i(Items.clay_ball));
            GameRegistry.addRecipe(
                i(CAItems.miningStorageBox),
                "CCC",
                "CMC",
                "CCC",
                'C',
                i(Blocks.chest),
                'M',
                i(Blocks.cobblestone));

            ShapelessRecipes recipe1 = new SBAutoCollectRecipe(i(CAItems.storageBox));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(recipe1);
            ShapelessRecipes recipe2 = new SBAutoCollectRecipe(i(CAItems.clayStorageBox));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(recipe2);
            ShapelessRecipes recipe3 = new SBAutoCollectRecipe(i(CAItems.storageBox));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(recipe3);
        }
    }

    private static void registerHammer() {
        if (Config.cfgModeSky) {
            CrushRecipes.addRecipe(i(Blocks.cobblestone), i(Blocks.gravel));
            CrushRecipes.addRecipe(i(Blocks.gravel), i(Blocks.sand));
            CrushRecipes.addRecipe(i(Blocks.wool), i(Items.string), 3);
        }
    }

    private static void registerCESplitted() {
        if (Config.cfgSplittedEnergeticClay) {
            for (int tier = 4; tier <= 12; tier++) {
                CRecipes.recipeCuttingMachine.addRecipe(
                    i(CBlocks.blockCompressedClay, 1, tier),
                    4,
                    i(CAItems.clayEnergy, 9, tier),
                    10L,
                    ClayiumCore.divideByProgressionRateI(10));
            }
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 4), 4, i((Block) CBlocks.blockCompressedClay, 1, 4), 100L, 16L);
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 5), 4, i((Block) CBlocks.blockCompressedClay, 1, 5), 1000L, 16L);
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 6), 4, i((Block) CBlocks.blockCompressedClay, 1, 6), 10000L, 13L);
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 7), 5, i((Block) CBlocks.blockCompressedClay, 1, 7), 100000L, 10L);
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 8), 5, i((Block) CBlocks.blockCompressedClay, 1, 8), 1000000L, 8L);
            CRecipes.recipeCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 9), 5, i((Block) CBlocks.blockCompressedClay, 1, 9), 10000000L, 6L);
            CRecipes.recipeCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 10),
                5,
                i((Block) CBlocks.blockCompressedClay, 1, 10),
                100000000L,
                4L);
            CRecipes.recipeCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 11),
                5,
                i((Block) CBlocks.blockCompressedClay, 1, 11),
                1000000000L,
                3L);
            CRecipes.recipeCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 12),
                5,
                i((Block) CBlocks.blockCompressedClay, 1, 12),
                1000000000L,
                25L);

            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 4), 3, i((Block) CBlocks.blockCompressedClay, 1, 4), 1L, 16L);
            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 5), 3, i((Block) CBlocks.blockCompressedClay, 1, 5), 10L, 32L);
            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 6), 3, i((Block) CBlocks.blockCompressedClay, 1, 6), 100L, 64L);
            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 7), 4, i((Block) CBlocks.blockCompressedClay, 1, 7), 1000L, 64L);
            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 8), 4, i((Block) CBlocks.blockCompressedClay, 1, 8), 10000L, 64L);
            CRecipes.recipeEnergeticClayCondenser
                .addRecipe(i(CAItems.clayEnergy, 9, 9), 4, i((Block) CBlocks.blockCompressedClay, 1, 9), 100000L, 64L);
            CRecipes.recipeEnergeticClayCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 10),
                4,
                i((Block) CBlocks.blockCompressedClay, 1, 10),
                1000000L,
                64L);
            CRecipes.recipeEnergeticClayCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 11),
                4,
                i((Block) CBlocks.blockCompressedClay, 1, 11),
                10000000L,
                64L);
            CRecipes.recipeEnergeticClayCondenser.addRecipe(
                i(CAItems.clayEnergy, 9, 12),
                4,
                i((Block) CBlocks.blockCompressedClay, 1, 12),
                10000000L,
                64L);

        }
    }

    private static void registerCMachines() {
        CMaterial[] clays = new CMaterial[] { CMaterials.CLAY, CMaterials.DENSE_CLAY, CMaterials.IND_CLAY,
            CMaterials.ADVIND_CLAY };
        CMaterial[] materials = new CMaterial[] { CMaterials.IMPURE_SILICON, CMaterials.SILICON, CMaterials.SILICONE,
            CMaterials.ALUMINIUM, CMaterials.CLAY_STEEL, CMaterials.CLAYIUM, CMaterials.ULTIMATE_ALLOY,
            CMaterials.AZ91D_ALLOY, CMaterials.ZK60A_ALLOY };

        if (Config.cfgQoLRecipe) {
            // Bending Clays
            for (CMaterial clay : clays) {
                CRecipes.recipeBendingMachine.addRecipe(
                    CMaterials.get(clay, CMaterials.BLOCK, 4),
                    getTier(clay) + 3,
                    CMaterials.get(clay, CMaterials.PLATE, 4),
                    4L * e(getTier(clay)),
                    (long) ((int) clay.hardness));
                CRecipes.recipeBendingMachine.addRecipe(
                    CMaterials.get(clay, CMaterials.PLATE, 16),
                    getTier(clay) + 3,
                    CMaterials.get(clay, CMaterials.LARGE_PLATE, 4),
                    4L * e(getTier(clay)),
                    (long) ((int) (2.0F * clay.hardness)));
            }
            // Bending Materials
            for (CMaterial material : materials) {
                CRecipes.recipeBendingMachine.addRecipe(
                    CMaterials.getOD(material, CMaterials.INGOT, 4),
                    9,
                    CMaterials.get(material, CMaterials.PLATE, 4),
                    4L * e(7),
                    (long) ((int) (material.hardness)));
                CRecipes.recipeBendingMachine.addRecipe(
                    CMaterials.getOD(material, CMaterials.PLATE, 16),
                    9,
                    CMaterials.get(material, CMaterials.LARGE_PLATE, 4),
                    4L * e(7),
                    (long) ((int) (2.0F * material.hardness)));
            }
        }
    }

    private static void registerGrinder() {
        if (Config.cfgEtFuturum && Loader.isModLoaded("etfuturum")) {
            CRecipes.recipeGrinder.addRecipe(
                i(CABlocks.blockRawClayOre, 1, 1),
                CItems.itemCompressedClayShard.get("2", ClayiumCore.multiplyProgressionRateStackSize(27)),
                9L,
                ClayiumCore.divideByProgressionRateI(6));
            CRecipes.recipeGrinder.addRecipe(
                i(CABlocks.blockRawClayOre, 1, 2),
                CItems.itemCompressedClayShard.get("3", ClayiumCore.multiplyProgressionRateStackSize(45)),
                9L,
                ClayiumCore.divideByProgressionRateI(9));
        }
    }

    private static void registerTransformer() {
        if (Config.cfgTransform) {
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
                200L);
        }
    }

    private static void registerAssembler() {
        if (Config.cfgAutoWaterWheel) {
            for (int i = 2; i <= 4; i++) {
                CRecipes.recipeAssembler.addRecipe(
                    ii(i(CABlocks.blocksWaterWheel[i]), CMaterials.get(getMaterial(i + 1), CMaterials.LARGE_PLATE)),
                    0,
                    i + 1,
                    ii(i(CABlocks.blocksWaterWheel[i + 1])),
                    40L * (long) Math.pow(5.0F, (i - 2)),
                    120L);
            }
        }

        // Buffer One
        for (int i = 4; i <= 13; i++) {
            CRecipes.recipeAssembler.addRecipe(
                ii(i(CBlocks.blocksBuffer[i]), CMaterials.get(getMaterial(i), CMaterials.PLATE)),
                0,
                4,
                ii(i(CABlocks.blocksLimitBuffer[i], 2)),
                100L * (long) Math.pow(10.0F, (i - 4)),
                40L);
        }

        // Circuit and Synchro
        if (Config.cfgQoLRecipe) {
            CRecipes.recipeAssembler.addRecipe(
                ii(CItems.itemMisc.get("PrecisionCircuit"), CMaterials.get(CMaterials.EXC_CLAY, CMaterials.DUST, 8)),
                0,
                10,
                ii(CItems.itemMisc.get("IntegratedCircuit")),
                6000000L,
                20L);
            CRecipes.recipeAssembler.addRecipe(
                ii(CMaterials.get(CMaterials.BERYLLIUM, CMaterials.DUST, 8), CItems.itemMisc.get("ClayCore")),
                0,
                10,
                ii(CItems.itemMisc.get("SynchronousParts")),
                e(9),
                1200L);
        }
    }

    private static void registerBlastFurnace() {
        if (Config.cfgQoLRecipe) {
            CMaterial[] materials = new CMaterial[] { CMaterials.CALCIUM, CMaterials.POTASSIUM };
            for (CMaterial material : materials) {
                CRecipes.recipeBlastFurnace.addRecipe(
                    oo(
                        CMaterials.get(CMaterials.IND_CLAY, CMaterials.DUST, 8),
                        CMaterials.getOD(material, CMaterials.DUST)),
                    0,
                    6,
                    ii(CMaterials.get(material, CMaterials.INGOT)),
                    e((double) 20.0F, 6),
                    10L);
            }

            materials = new CMaterial[] { CMaterials.BERYLLIUM, CMaterials.HAFNIUM, CMaterials.CLAY_STEEL,
                CMaterials.STEEL };
            for (CMaterial material : materials) {
                CRecipes.recipeBlastFurnace.addRecipe(
                    oo(
                        CMaterials.get(CMaterials.IND_CLAY, CMaterials.DUST, 8),
                        CMaterials.getOD(material, CMaterials.DUST)),
                    0,
                    7,
                    ii(CMaterials.get(material, CMaterials.INGOT)),
                    e((double) 40.0F, 7),
                    10L);
            }

            materials = new CMaterial[] { CMaterials.MANGANESE, CMaterials.STRONTIUM, CMaterials.BARIUM,
                CMaterials.CLAYIUM };
            for (CMaterial material : materials) {
                CRecipes.recipeBlastFurnace.addRecipe(
                    oo(
                        CMaterials.get(CMaterials.IND_CLAY, CMaterials.DUST, 8),
                        CMaterials.getOD(material, CMaterials.DUST)),
                    0,
                    8,
                    ii(CMaterials.get(material, CMaterials.INGOT)),
                    e((double) 40.0F, 8),
                    20L);
            }

            materials = new CMaterial[] { CMaterials.TITANIUM, CMaterials.ULTIMATE_ALLOY };
            for (CMaterial material : materials) {
                CRecipes.recipeBlastFurnace.addRecipe(
                    oo(
                        CMaterials.get(CMaterials.ADVIND_CLAY, CMaterials.DUST, 8),
                        CMaterials.getOD(material, CMaterials.DUST)),
                    0,
                    9,
                    ii(CMaterials.get(material, CMaterials.INGOT)),
                    e((double) 80.0F, 9),
                    40L);
            }

            materials = new CMaterial[] { CMaterials.CHROME };
            for (CMaterial material : materials) {
                CRecipes.recipeBlastFurnace.addRecipe(
                    oo(
                        CMaterials.get(CMaterials.ADVIND_CLAY, CMaterials.DUST, 16),
                        CMaterials.getOD(material, CMaterials.DUST)),
                    0,
                    10,
                    ii(CMaterials.get(material, CMaterials.INGOT)),
                    e((double) 80.0F, 10),
                    40L);
            }
        }
    }

    private static void registerReactor() {
        if (Config.cfgQoLRecipe) {
            CRecipes.recipeReactor.addRecipe(
                ii(i(CBlocks.blockClayTreeSapling), CMaterials.get(CMaterials.ORG_CLAY, CMaterials.DUST, 1)),
                0,
                8,
                ii(i(CBlocks.blockClayTreeLog, 8)),
                e(8),
                24000L);
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

        Block[][] arrayBlocklist = new Block[][] { CBlocks.blocksMultitrackBuffer };
        ArrayList<Block[]> blocksList = new ArrayList(Arrays.asList(arrayBlocklist));
        if (Config.cfgAutoWaterWheel) {
            blocksList.add(CABlocks.blocksWaterWheel);
            blocksList.add(CABlocks.blocksLimitBuffer);
        }

        for (Block[] blocks : blocksList) {
            recipeCAInjector(blocks);
        }
    }

    public static CMaterial getMaterial(int tier) {
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

    public static int getTier(CMaterial material) {
        if (material == CMaterials.CLAY) return 1;
        if (material == CMaterials.DENSE_CLAY) return 2;
        if (material == CMaterials.IND_CLAY) return 3;
        if (material == CMaterials.ADVIND_CLAY) return 4;
        if (material == CMaterials.IMPURE_SILICON) return 5;
        if (ClayiumCore.cfgHardcoreAluminium) {
            if (material == CMaterials.IMPURE_ALUMINIUM) return 6;
        } else {
            if (material == CMaterials.ALUMINIUM) return 6;
        }
        if (material == CMaterials.CLAY_STEEL) return 7;
        if (material == CMaterials.CLAYIUM) return 8;
        if (material == CMaterials.ULTIMATE_ALLOY) return 9;
        if (material == CMaterials.ANTIMATTER) return 10;
        if (material == CMaterials.PURE_ANTIMATTER) return 11;
        if (material == CMaterials.OCTUPLE_CLAY) return 12;
        if (material == CMaterials.OCTUPLE_PURE_ANTIMATTER) return 13;
        return -1;
    }

    private static void recipeCAInjector(Block[] blocks) {
        if (blocks == null) return;

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
                        e(3.0F, i),
                        4000L);
                }

                j = i;
                n = 0;
            }
        }
    }
}
