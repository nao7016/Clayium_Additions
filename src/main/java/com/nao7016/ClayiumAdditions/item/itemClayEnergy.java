package com.nao7016.ClayiumAdditions.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.CreativeTabsClayiumAdditions;

public class itemClayEnergy extends Item {

    public static final int tier_amount = 12;

    public itemClayEnergy() {
        setUnlocalizedName("clay_energy");
        setHasSubtypes(true);
        // setTextureName("clayiumadditions:clay_energy");
        setCreativeTab(CreativeTabsClayiumAdditions.tabs_creative_additions);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return super.getUnlocalizedName() + "." + meta;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 4; i <= tier_amount; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
