package com.nao7016.ClayiumAdditions.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CATabs extends CreativeTabs {

    public static final CreativeTabs tabs_creative_additions = new CATabs();

    private CATabs() {
        super("clayiumAdditionsTabs");
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.clay);
    }
}
