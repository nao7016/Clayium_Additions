package com.nao7016.ClayiumAdditions.common;

import static cpw.mods.fml.common.registry.GameRegistry.registerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

import com.nao7016.ClayiumAdditions.block.AutoWaterWheel;
import com.nao7016.ClayiumAdditions.block.tile.TileAutoWaterWheel;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.clayium.block.CBlocks;
import mods.clayium.block.itemblock.ItemBlockTiered;

public class CABlocks {

    public static CreativeTabs CATabs = com.nao7016.ClayiumAdditions.common.CATabs.ca_tabs;

    public static Block blockAutoSimpleWaterWheel;
    public static Block blockAutoBasicWaterWheel;
    public static Block blockAutoAdvancedWaterWheel;
    public static Block[] blocksWaterWheel = new Block[16];

    public static void registerBlocks() {
        if (Config.cfgAutoWaterWheelEnabled) {
            blockAutoSimpleWaterWheel = register(
                new AutoWaterWheel("Auto Simple Water Wheel", "clayiumadditions:autowaterwheel", 3)
                    .setBlockName("blockAutoSimpleWaterWheel")
                    .setCreativeTab(CATabs),
                ItemBlockTiered.class,
                "blockAutoSimpleWaterWheel");
            blockAutoBasicWaterWheel = register(
                new AutoWaterWheel("Auto Basic Water Wheel", "clayiumadditions:autowaterwheel", 4)
                    .setBlockName("blockAutoBasicWaterWheel")
                    .setCreativeTab(CATabs),
                ItemBlockTiered.class,
                "blockAutoBasicWaterWheel");
            blockAutoAdvancedWaterWheel = register(
                new AutoWaterWheel("Auto Advanced Water Wheel", "clayiumadditions:autowaterwheel", 5)
                    .setBlockName("blockAutoAdvancedWaterWheel")
                    .setCreativeTab(CATabs),
                ItemBlockTiered.class,
                "blockAutoAdvancedWaterWheel");
            registerTileEntity(TileAutoWaterWheel.class, "AutoWaterWheel");

            blocksWaterWheel[2] = CBlocks.blockDenseClayWaterWheel;
            blocksWaterWheel[3] = blockAutoSimpleWaterWheel;
            blocksWaterWheel[4] = blockAutoBasicWaterWheel;
            blocksWaterWheel[5] = blockAutoAdvancedWaterWheel;
        }
    }

    private static Block register(Block block, Class<? extends ItemBlock> extendclass, String name) {
        GameRegistry.registerBlock(block, extendclass, name);
        return block;
    }

}
