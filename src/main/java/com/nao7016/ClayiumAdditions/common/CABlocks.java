package com.nao7016.ClayiumAdditions.common;

import static cpw.mods.fml.common.registry.GameRegistry.registerTileEntity;

import com.nao7016.ClayiumAdditions.block.RawClayOreBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.nao7016.ClayiumAdditions.block.AutoWaterWheel;
import com.nao7016.ClayiumAdditions.block.DeepslateClayOre;
import com.nao7016.ClayiumAdditions.block.tile.TileAutoWaterWheel;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.DeepslateOreRegistry;
import mods.clayium.block.CBlocks;
import mods.clayium.block.itemblock.ItemBlockDamaged;
import mods.clayium.block.itemblock.ItemBlockTiered;

public class CABlocks {

    public static CreativeTabs CATabs = com.nao7016.ClayiumAdditions.common.CATabs.ca_tabs;

    public static Block blockAutoSimpleWaterWheel;
    public static Block blockAutoBasicWaterWheel;
    public static Block blockAutoAdvancedWaterWheel;
    public static Block[] blocksWaterWheel = new Block[16];
    public static Block blockDeepslateClayOre;
    public static Block blockRawClayOre;

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

        if (Config.cfgEtFuturum) {
            if (Loader.isModLoaded("etfuturum")) {
                blockDeepslateClayOre = register(
                    (new DeepslateClayOre()).setBlockName("blockDeepslateClayOre")
                        .setCreativeTab(CATabs),
                    ItemBlockDamaged.class,
                    "blockDeepslateClayOre");
                OreDictionary.registerOre("oreClay", new ItemStack(blockDeepslateClayOre, 1, 0));
                OreDictionary.registerOre("oreDenseClay", new ItemStack(blockDeepslateClayOre, 1, 1));
                OreDictionary.registerOre("oreLargeDenseClay", new ItemStack(blockDeepslateClayOre, 1, 2));
                OreDictionary.registerOre("oreDeepslateClay", new ItemStack(blockDeepslateClayOre, 1, 0));
                OreDictionary.registerOre("oreDeepslateDenseClay", new ItemStack(blockDeepslateClayOre, 1, 1));
                OreDictionary.registerOre("oreDeepslateLargeDenseClay", new ItemStack(blockDeepslateClayOre, 1, 2));
                DeepslateOreRegistry.addOreByOreDict("oreClay", blockDeepslateClayOre, 0);
                DeepslateOreRegistry.addOreByOreDict("oreDenseClay", blockDeepslateClayOre, 1);
                DeepslateOreRegistry.addOreByOreDict("oreLargeDenseClay", blockDeepslateClayOre, 2);

                blockRawClayOre = register(new RawClayOreBlock().setBlockName("blockRawClayOre")
                        .setCreativeTab(CATabs),
                    ItemBlockTiered.class,
                    "blockRawClayOre");
            }
        }
    }

    private static Block register(Block block, Class<? extends ItemBlock> extendclass, String name) {
        GameRegistry.registerBlock(block, extendclass, name);
        return block;
    }

}
