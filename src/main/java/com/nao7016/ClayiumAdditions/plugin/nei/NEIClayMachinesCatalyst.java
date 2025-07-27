package com.nao7016.ClayiumAdditions.plugin.nei;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import codechicken.nei.recipe.CatalystInfo;
import codechicken.nei.recipe.RecipeCatalysts;
import mods.clayium.block.CBlocks;

public class NEIClayMachinesCatalyst {

    public static void registerMachineCatalysts() {
        // tier 0
        registerCatalysts("ClayWorkTable", CBlocks.blockClayWorkTable);
        registerCatalysts("crafting", CBlocks.blockClayCraftingTable);

        // tier 1
        registerCatalysts("BendingMachine", CBlocks.blocksBendingMachine);
        registerCatalysts("WireDrawingMachine", CBlocks.blocksWireDrawingMachine);
        registerCatalysts("PipeDrawingMachine", CBlocks.blocksPipeDrawingMachine);
        registerCatalysts("CuttingMachine", CBlocks.blocksCuttingMachine);
        registerCatalysts("Lathe", CBlocks.blocksLathe);
        registerCatalysts("MillingMachine", CBlocks.blocksMillingMachine);
        registerCatalysts("CobblestoneGenerator", CBlocks.blocksCobblestoneGenerator);

        // tier 2
        registerCatalysts("Condenser", CBlocks.blocksCondenser);
        registerCatalysts("Grinder", CBlocks.blocksGrinder);
        registerCatalysts("Decomposer", CBlocks.blocksDecomposer);

        // tier 3
        registerCatalysts("Assembler", CBlocks.blocksAssembler);
        registerCatalysts("Centrifuge", CBlocks.blocksCentrifuge);
        registerCatalysts("Inscriber", CBlocks.blocksInscriber);

        // tier 4
        registerCatalysts("Smelter", CBlocks.blocksSmelter);
        registerCatalysts("ChemicalReactor", CBlocks.blocksChemicalReactor);
        registerCatalysts("SaltExtractor", CBlocks.blocksSaltExtractor);

        // tier 5
        registerCatalysts("QuartzCrucible", CBlocks.blockQuartzCrucible);
        registerCatalysts("SolarClayFabricator", CBlocks.blockSolarClayFabricatorMK1);
        registerCatalysts("SolarClayFabricator", CBlocks.blockSolarClayFabricatorMK2);
        registerCatalysts("SolarClayFabricator", CBlocks.blockLithiumSolarClayFabricator);

        // tier 6
        registerCatalysts("BlastFurnace", CBlocks.blockClayBlastFurnace);
        registerCatalysts("AlloySmelter", CBlocks.blocksAlloySmelter);
        registerCatalysts("ChemicalMetalSeparator", CBlocks.blockChemicalMetalSeparator);
        registerCatalysts("ElectrolysisReactor", CBlocks.blocksElectrolysisReactor);

        // tier 7
        registerCatalysts("Reactor", CBlocks.blockClayReactor);
        registerCatalysts("ClayTree", CBlocks.blocksClayEnergyLaser);
        registerCatalysts("ClayTree", CBlocks.blockClayTreeLog);

        // tier 9
        registerCatalysts("MatterTransformer", CBlocks.blocksTransformer);
        registerCatalysts("CACondenser", CBlocks.blocksCACondenser);

        // tier 10
        registerCatalysts("CAInjector", CBlocks.blocksCAInjector);
        registerCatalysts("CAReactor", CBlocks.blocksCAReactorCore);

        // registerCatalysts("", CBlocks.blocks);

    }

    public static void registerCatalysts(String handlerId, Block[] blocks) {
        for (Block block : blocks) {
            if (block != null) {
                Item item = Item.getItemFromBlock(block);
                if (item != null) {
                    RecipeCatalysts.addRecipeCatalyst(handlerId, new CatalystInfo(new ItemStack(item), 0));
                }
            }
        }
    }

    public static void registerCatalysts(String handlerId, Block block) {
        if (block != null) {
            Item item = Item.getItemFromBlock(block);
            if (item != null) {
                RecipeCatalysts.addRecipeCatalyst(handlerId, new CatalystInfo(new ItemStack(item), 0));
            }
        }

    }
}
