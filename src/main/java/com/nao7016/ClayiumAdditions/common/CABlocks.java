package com.nao7016.ClayiumAdditions.common;

import static cpw.mods.fml.common.registry.GameRegistry.registerTileEntity;
import static mods.clayium.block.CBlocks.tierPrefix;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.nao7016.ClayiumAdditions.block.AutoWaterWheel;
import com.nao7016.ClayiumAdditions.block.ClayBufferOne;
import com.nao7016.ClayiumAdditions.block.DeepslateClayOre;
import com.nao7016.ClayiumAdditions.block.RawClayOreBlock;
import com.nao7016.ClayiumAdditions.block.tile.TileAutoWaterWheel;
import com.nao7016.ClayiumAdditions.block.tile.TileClayBufferOne;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.DeepslateOreRegistry;
import mods.clayium.block.CBlocks;
import mods.clayium.block.itemblock.ItemBlockDamaged;
import mods.clayium.block.itemblock.ItemBlockTiered;

public class CABlocks {

    public static CreativeTabs CATabs = com.nao7016.ClayiumAdditions.common.CATabs.ca_tabs;

    public static Block[] blocksAutoWaterWheel = new Block[tierPrefix.length];
    public static Block[] blocksWaterWheel = new Block[tierPrefix.length];
    public static Block[] blocksBufferOne = new Block[tierPrefix.length];
    public static Block blockDeepslateClayOre;
    public static Block blockRawClayOre;

    public static void registerBlocks() {
        if (Config.cfgAutoWaterWheel) {
            for (int tier = 3; tier <= 5; tier++) {
                blocksAutoWaterWheel[tier] = register(
                    new AutoWaterWheel(
                        "Auto " + tierPrefix[tier] + " Water Wheel",
                        "clayiumadditions:autowaterwheel",
                        tier).setBlockName("blockAuto" + tierPrefix[tier] + "WaterWheel")
                            .setCreativeTab(CATabs),
                    ItemBlockTiered.class,
                    "blockAuto" + tierPrefix[tier] + "WaterWheel");
            }
            registerTileEntity(TileAutoWaterWheel.class, "AutoWaterWheel");

            blocksWaterWheel[2] = CBlocks.blockDenseClayWaterWheel;
            System.arraycopy(blocksAutoWaterWheel, 3, blocksWaterWheel, 3, 3);
        }

        for (int tier = 4; tier <= 13; tier++) {
            blocksBufferOne[tier] = register(
                new ClayBufferOne(tier).setBlockName("block" + tierPrefix[tier] + "ClayBufferOne")
                    .setCreativeTab(CATabs),
                ItemBlockTiered.class,
                "block" + tierPrefix[tier] + "ClayBufferOne");
        }
        registerTileEntity(TileClayBufferOne.class, "ClayBufferOne");

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

                blockRawClayOre = register(
                    new RawClayOreBlock().setBlockName("blockRawClayOre")
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
