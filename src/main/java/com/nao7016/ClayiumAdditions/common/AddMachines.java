package com.nao7016.ClayiumAdditions.common;

import static mods.clayium.block.CBlocks.*;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.clayium.block.ClayAssembler;
import mods.clayium.block.ClayAutoCrafter;
import mods.clayium.block.ClayChemicalReactor;
import mods.clayium.block.CobblestoneGenerator;
import mods.clayium.block.SaltExtractor;
import mods.clayium.block.itemblock.ItemBlockTiered;
import mods.clayium.block.tile.TileCACondenser;
import mods.clayium.block.tile.TileClayCentrifuge;
import mods.clayium.core.ClayiumCore;
import mods.clayium.util.UtilTier;

public class AddMachines extends CABlocks {

    // public static Block[] blocks
    public static Block[] blocksAddBendingMachine;
    public static Block[] blocksAddWireDrawingMachine;
    public static Block[] blocksAddPipeDrawingMachine;
    public static Block[] blocksAddCuttingMachine;
    public static Block[] blocksAddLathe;
    public static Block[] blocksAddCobblestoneGenerator;

    public static Block[] blocksAddCondenser;
    public static Block[] blocksAddGrinder;
    public static Block[] blocksAddDecomposer;
    public static Block[] blocksAddMillingMachine;
    public static Block[] blocksAddAssembler;
    public static Block[] blocksAddInscriber;
    public static Block[] blocksAddCentrifuge;
    public static Block[] blocksAddSmelter;
    public static Block[] blocksAddChemicalReactor;
    public static Block[] blocksAddSaltExtractor;
    public static Block[] blocksAddElectrolysisReactor;
    public static Block[] blocksAddAutoCrafter;
    public static Block[] blocksAddTransformer;
    public static Block[] blocksAddCACondenser;

    public static void registerAddMachines() {

        blocksAddBendingMachine = registerTieredMachines(
            "BendingMachine",
            "bendingmachine",
            "BendingMachine",
            new int[] { 8, 10, 11, 12, 13 });
        blocksAddWireDrawingMachine = registerTieredMachines(
            "WireDrawingMachine",
            "wiredrawingmachine",
            "WireDrawingMachine",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddPipeDrawingMachine = registerTieredMachines(
            "PipeDrawingMachine",
            "pipedrawingmachine",
            "PipeDrawingMachine",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddCuttingMachine = registerTieredMachines(
            "CuttingMachine",
            "cuttingmachine",
            "CuttingMachine",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddLathe = registerTieredMachines("Lathe", "lathe", "Lathe", new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddCobblestoneGenerator = new Block[16];

        for (int i = 8; i <= 13; ++i) {
            blocksAddCobblestoneGenerator[i] = new CobblestoneGenerator(i);
            blocksAddCobblestoneGenerator[i].setBlockName("block" + tierPrefix[i] + "CobblestoneGenerator")
                .setCreativeTab(ClayiumCore.creativeTabClayium);
            GameRegistry.registerBlock(
                blocksAddCobblestoneGenerator[i],
                ItemBlockTiered.class,
                "block" + tierPrefix[i] + "CobblestoneGenerator");
        }

        blocksAddCondenser = registerTieredMachines(
            "Condenser",
            "condenser",
            "Condenser",
            new int[] { 6, 7, 8, 9, 11, 12, 13 });
        UtilTier.TierManager.applyMachineTierManager(blocksAddCondenser, UtilTier.tierGeneric);
        blocksAddGrinder = registerTieredMachines("Grinder", "grinder", "Grinder", new int[] { 7, 8, 9, 11, 12, 13 });
        UtilTier.TierManager.applyMachineTierManager(blocksAddGrinder, UtilTier.tierGeneric);
        blocksAddDecomposer = registerTieredMachines(
            "Decomposer",
            "decomposer",
            "Decomposer",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddMillingMachine = registerTieredMachines(
            "MillingMachine",
            "millingmachine",
            "MillingMachine",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        blocksAddAssembler = registerTieredMachines(
            "Assembler",
            "assembler",
            "Assembler",
            new int[] { 5, 7, 8, 9, 11, 12, 13 },
            ClayAssembler.class,
            ItemBlockTiered.class);
        blocksAddInscriber = registerTieredMachines(
            "Inscriber",
            "inscriber",
            "Inscriber",
            new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13 },
            ClayAssembler.class,
            ItemBlockTiered.class);
        blocksAddCentrifuge = registerTieredMachines(
            "Centrifuge",
            "centrifuge",
            "Centrifuge",
            new int[] { 7, 8, 9, 10, 11, 12, 13 },
            TileClayCentrifuge.class,
            3);
        UtilTier.TierManager.applyMachineTierManager(blocksAddCentrifuge, UtilTier.tierGeneric);
        blocksAddSmelter = registerTieredMachines("Smelter", "smelter", "Smelter", new int[] { 10, 11, 12, 13 });
        UtilTier.TierManager.applyMachineTierManager(blocksAddSmelter, UtilTier.tierSmelter);

        blocksAddChemicalReactor = registerTieredMachines(
            "ChemicalReactor",
            "chemicalreactor",
            "ChemicalReactor",
            new int[] { 6, 7, 9, 10, 11, 12, 13 },
            ClayChemicalReactor.class,
            ItemBlockTiered.class);
        blocksAddSaltExtractor = registerTieredContainers(
            "SaltExtractor",
            new int[] { 8, 9, 10, 11, 12, 13 },
            SaltExtractor.class);
        blocksAddElectrolysisReactor = registerTieredMachines(
            "ElectrolysisReactor",
            "electrolysisreactor",
            "ElectrolysisReactor",
            new int[] { 10, 11, 12, 13 });
        blocksAddAutoCrafter = registerTieredContainers(
            "AutoCrafter",
            new int[] { 10, 11, 12, 13 },
            ClayAutoCrafter.class);
        blocksAddTransformer = registerTieredMachines(
            "MatterTransformer",
            "transformer",
            "Transformer",
            new int[] { 13 });
        blocksAddCACondenser = registerTieredMachines(
            "CACondenser",
            "cacondenser",
            "CACondenser",
            new int[] { 12, 13 },
            TileCACondenser.class);
        UtilTier.TierManager.applyMachineTierManager(blocksAddCACondenser, UtilTier.tierCACondenser);
    }

}
