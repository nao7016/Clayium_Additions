package com.nao7016.ClayiumAdditions.common;

import static cpw.mods.fml.common.registry.GameRegistry.registerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

import com.nao7016.ClayiumAdditions.block.AutoWaterWheel;
import com.nao7016.ClayiumAdditions.block.tile.TileAutoWaterWheel;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.clayium.block.itemblock.ItemBlockTiered;

public class BlockClayiumAdditions {

    public static CreativeTabs CATabs = CreativeTabsClayiumAdditions.tabs_creative_additions;

    public static Block blockAutoSimpleWaterWheel;
    public static Block blockAutoBasicWaterWheel;
    public static Block blockAutoAdvancedWaterWheel;

    public static void registerBlocks() {
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
    }

    private static Block register(Block block, Class<? extends ItemBlock> extendclass, String name) {
        GameRegistry.registerBlock(block, extendclass, name);
        return block;
    }

}
