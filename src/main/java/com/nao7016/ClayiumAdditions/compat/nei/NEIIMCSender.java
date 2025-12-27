package com.nao7016.ClayiumAdditions.compat.nei;

import java.util.Arrays;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

import com.nao7016.ClayiumAdditions.common.AddMachines;
import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.Config;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.clayium.block.CBlocks;

public class NEIIMCSender {

    public static void IMCHandlerSender() {
        // template
        // sendHandlerClayium("", "clayium:blockBasic");

        // tier 0
        sendHandlerClayium("ClayWorkTable", "clayium:blockClayWorkTable");

        // tier 1
        sendHandlerClayium("BendingMachine", "clayium:blockClayWorkTable");
        sendHandlerClayium("WireDrawingMachine", "clayium:blockBasicWireDrawingMachine");
        sendHandlerClayium("PipeDrawingMachine", "clayium:blockBasicPipeDrawingMachine");
        sendHandlerClayium("CuttingMachine", "clayium:blockBasicCuttingMachine");
        sendHandlerClayium("Lathe", "clayium:blockBasicLathe");
        sendHandlerClayium("MillingMachine", "clayium:blockBasicMillingMachine");
        sendHandlerClayium("CobblestoneGenerator", "clayium:blockBasicCobblestoneGenerator");

        // tier 2
        sendHandlerClayium("Condenser", "clayium:blockBasicCondenser");
        sendHandlerClayium("Grinder", "clayium:blockBasicGrinder");
        sendHandlerClayium("Decomposer", "clayium:blockBasicDecomposer");

        // tier 3
        sendHandlerClayium("Assembler", "clayium:blockBasicAssembler");
        sendHandlerClayium("Centrifuge", "clayium:blockBasicCentrifuge");
        sendHandlerClayium("Inscriber", "clayium:blockBasicInscriber");
        sendHandlerClayium("ECCondenser", "clayium:blockEnergeticClayCondenserMK2");

        // tier 4
        sendHandlerClayium("Smelter", "clayium:blockBasicSmelter");
        sendHandlerClayium("ChemicalReactor", "clayium:blockBasicChemicalReactor");
        sendHandlerClayium("SaltExtractor", "clayium:blockBasicSaltExtractor");

        // tier 5
        sendHandlerClayium("QuartzCrucible", "clayium:blockQuartzCrucible");
        sendHandlerClayium("SolarClayFabricator", "clayium:blockSolarClayFabricatorMK1");

        // tier 6
        sendHandlerClayium("BlastFurnace", "clayium:blockClayBlastFurnace");
        sendHandlerClayium("AlloySmelter", "clayium:blockAlloySmelter");
        sendHandlerClayium("ChemicalMetalSeparator", "clayium:blockChemicalMetalSeparator");
        sendHandlerClayium("ElectrolysisReactor", "clayium:blockPrecisionElectrolysisReactor");

        // tier 7
        sendHandlerClayium("Reactor", "clayium:blockClayReactor");
        sendHandlerClayium("ClayTree", "clayium:blockClayTreeSapling");
        sendHandlerClayium("MatterTransformer", "clayium:blockClaySteelMatterTransformer");

        // tier 9
        sendHandlerClayium("CACondenser", "clayium:itemGems:800");
        sendHandlerClayium("CAInjector", "clayium:blockAntimatterCAInjector");

        // tier 10
        sendHandlerClayium("CAReactor", "clayium:itemGems:801");
        sendHandlerClayium("ECDecomposer", "clayium:blockEnergeticClayDecomposer");

        if (Config.cfgModeSky) {
            sendHandlerCA("ClayHammer", "clayiumadditions:clay_hammer");
        }
    }

    public static void IMCCatalystSender() {
        // tier 0
        sendCatalyst("ClayWorkTable", CBlocks.blockClayWorkTable);
        sendCatalyst("crafting", CBlocks.blockClayCraftingTable);

        // tier 1
        sendCatalyst("BendingMachine", CBlocks.blocksBendingMachine);
        sendCatalyst("WireDrawingMachine", CBlocks.blocksWireDrawingMachine);
        sendCatalyst("PipeDrawingMachine", CBlocks.blocksPipeDrawingMachine);
        sendCatalyst("CuttingMachine", CBlocks.blocksCuttingMachine);
        sendCatalyst("Lathe", CBlocks.blocksLathe);
        sendCatalyst("MillingMachine", CBlocks.blockElementalMillingMachine);
        sendCatalyst("MillingMachine", CBlocks.blocksMillingMachine);
        sendCatalyst("CobblestoneGenerator", CBlocks.blocksCobblestoneGenerator);

        // tier 2
        sendCatalyst("Condenser", CBlocks.blocksCondenser);
        sendCatalyst("Grinder", CBlocks.blocksGrinder);
        sendCatalyst("Decomposer", CBlocks.blocksDecomposer);

        // tier 3
        sendCatalyst("Assembler", CBlocks.blocksAssembler);
        sendCatalyst("Centrifuge", CBlocks.blocksCentrifuge);
        sendCatalyst("Inscriber", CBlocks.blocksInscriber);
        sendCatalyst("ECCondenser", CBlocks.blockEnergeticClayCondenser);
        sendCatalyst("ECCondenser", CBlocks.blockEnergeticClayCondenserMK2);

        // tier 4
        sendCatalyst("Smelter", CBlocks.blocksSmelter);
        sendCatalyst("ChemicalReactor", CBlocks.blocksChemicalReactor);
        sendCatalyst("SaltExtractor", CBlocks.blocksSaltExtractor);

        // tier 5
        sendCatalyst("QuartzCrucible", CBlocks.blockQuartzCrucible);
        sendCatalyst("SolarClayFabricator", CBlocks.blockSolarClayFabricatorMK1);
        sendCatalyst("SolarClayFabricator", CBlocks.blockSolarClayFabricatorMK2);
        sendCatalyst("SolarClayFabricator", CBlocks.blockLithiumSolarClayFabricator);

        // tier 6
        sendCatalyst("BlastFurnace", CBlocks.blockClayBlastFurnace);
        sendCatalyst("AlloySmelter", CBlocks.blocksAlloySmelter);
        sendCatalyst("ChemicalMetalSeparator", CBlocks.blockChemicalMetalSeparator);
        sendCatalyst("ElectrolysisReactor", CBlocks.blocksElectrolysisReactor);

        // tier 7
        sendCatalyst("Reactor", CBlocks.blockClayReactor);
        sendCatalyst("ClayTree", CBlocks.blocksClayEnergyLaser);
        sendCatalyst("ClayTree", CBlocks.blockClayTreeLog);

        // tier 9
        sendCatalyst("MatterTransformer", CBlocks.blocksTransformer);
        sendCatalyst("CACondenser", CBlocks.blocksCACondenser);

        // tier 10
        sendCatalyst("CAInjector", CBlocks.blocksCAInjector);
        sendCatalyst("CAReactor", CBlocks.blocksCAReactorCore);

        // tier 13
        sendCatalyst("ECDecomposer", CBlocks.blockEnergeticClayDecomposer);

        if (Config.cfgAddMachines) {
            // tier 1
            sendCatalyst("BendingMachine", AddMachines.blocksAddBendingMachine);
            sendCatalyst("WireDrawingMachine", AddMachines.blocksAddWireDrawingMachine);
            sendCatalyst("PipeDrawingMachine", AddMachines.blocksAddPipeDrawingMachine);
            sendCatalyst("CuttingMachine", AddMachines.blocksAddCuttingMachine);
            sendCatalyst("Lathe", AddMachines.blocksAddLathe);
            sendCatalyst("MillingMachine", AddMachines.blocksAddMillingMachine);
            sendCatalyst("CobblestoneGenerator", AddMachines.blocksAddCobblestoneGenerator);

            // tier 2
            sendCatalyst("Condenser", AddMachines.blocksAddCondenser);
            sendCatalyst("Grinder", AddMachines.blocksAddGrinder);
            sendCatalyst("Decomposer", AddMachines.blocksAddDecomposer);

            // tier 3
            sendCatalyst("Assembler", AddMachines.blocksAddAssembler);
            sendCatalyst("Centrifuge", AddMachines.blocksAddCentrifuge);
            sendCatalyst("Inscriber", AddMachines.blocksAddInscriber);

            // tier 4
            sendCatalyst("Smelter", AddMachines.blocksAddSmelter);
            sendCatalyst("ChemicalReactor", AddMachines.blocksAddChemicalReactor);
            sendCatalyst("SaltExtractor", AddMachines.blocksAddSaltExtractor);

            // tier 6
            sendCatalyst("ElectrolysisReactor", AddMachines.blocksAddElectrolysisReactor);

            // tier 9
            sendCatalyst("MatterTransformer", AddMachines.blocksAddTransformer);
            sendCatalyst("CACondenser", AddMachines.blocksAddCACondenser);
        }

        if (Config.cfgModeSky) {
            sendCatalyst("ClayHammer", CAItems.clayHammer);
        }
    }

    private static void sendHandlerClayium(String handlerId, String name) {
        sendHandlerClayium(handlerId, name, 5, true);
    }

    private static void sendHandlerClayium(String handlerId, String name, int maxRecipesPerPage, boolean showButtons) {
        sendHandler(handlerId, "Clayium", "clayium", true, name, 65, 166, maxRecipesPerPage, showButtons);
    }

    private static void sendHandlerCA(String handlerId, String name) {
        sendHandlerCA(handlerId, name, 5, true);
    }

    private static void sendHandlerCA(String handlerId, String name, int maxRecipesPerPage, boolean showButtons) {
        sendHandler(
            handlerId,
            "ClayiumAdditions",
            "clayiumadditions",
            true,
            name,
            65,
            166,
            maxRecipesPerPage,
            showButtons);
    }

    private static void sendHandler(String handlerId, String modName, String modId, boolean modRequired, String block,
        int handlerHeight, int handlerWidth, int maxRecipesPerPage, boolean showButtons) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("handler", handlerId);
        nbt.setString("modName", modName);
        nbt.setString("modId", modId);
        nbt.setBoolean("modRequired", modRequired);
        nbt.setString("itemName", block);
        nbt.setInteger("handlerHeight", handlerHeight);
        nbt.setInteger("handlerWidth", handlerWidth);
        nbt.setInteger("maxRecipesPerPage", maxRecipesPerPage);
        nbt.setBoolean("showFavoritesButton", showButtons);
        nbt.setBoolean("showOverlayButton", showButtons);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", nbt);
    }

    private static void sendCatalyst(String id, Object[] objects) {
        String[] stacks = getNames(objects);
        sendCatalyst(id, stacks);
    }

    private static void sendCatalyst(String id, String[] names) {
        names = nonNullObjects(names);
        for (String name : names) {
            sendCatalyst(id, name);
        }
    }

    private static void sendCatalyst(String id, Object object) {
        String name = getName(object);
        if (name == null) return;
        sendCatalyst(id, name);
    }

    private static void sendCatalyst(String id, String name) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("handlerID", id);
        nbt.setString("itemName", name);
        nbt.setInteger("priority", 0);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", nbt);
    }

    private static String getName(Object object) {
        if (object instanceof Block) return GameRegistry.findUniqueIdentifierFor((Block) object)
            .toString();
        if (object instanceof Item) return GameRegistry.findUniqueIdentifierFor((Item) object)
            .toString();
        return null;
    }

    private static String[] getNames(Object[] objects) {
        objects = nonNullObjects(objects);
        String[] names = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            names[i] = getName(objects[i]);
        }
        return names;
    }

    private static <T> T[] nonNullObjects(T[] objects) {
        return Arrays.stream(objects)
            .filter(Objects::nonNull)
            .toArray(size -> Arrays.copyOf(objects, size));
    }
}
