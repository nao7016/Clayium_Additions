package com.nao7016.ClayiumAdditions.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabsClayiumAdditions extends CreativeTabs {

    public static final CreativeTabs tabs_creative_additions = new CreativeTabsClayiumAdditions();

    private CreativeTabsClayiumAdditions() {
        super("clayiumAdditionsTabs");
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.clay);
    }
}
