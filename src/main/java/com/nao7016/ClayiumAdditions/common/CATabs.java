package com.nao7016.ClayiumAdditions.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import mods.clayium.item.CMaterials;

public class CATabs extends CreativeTabs {

    public static final CreativeTabs ca_tabs = new CATabs();

    private CATabs() {
        super("clayiumAdditionsTabs");
    }

    @Override
    public ItemStack getIconItemStack() {
        return CMaterials.get(CMaterials.CLAY, CMaterials.WATER_WHEEL);
    }

    @Override
    public Item getTabIconItem() {
        // return Item.getItemFromBlock(Blocks.clay);
        return CMaterials.itemClayParts;
    }
}
