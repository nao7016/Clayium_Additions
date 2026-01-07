package com.nao7016.ClayiumAdditions.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.nao7016.ClayiumAdditions.common.CATabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.ItemDamaged;

public class ItemRawClayOre extends ItemDamaged {

    private IIcon[] icons = new IIcon[3];
    private static final int[] VALID_METAS = { 1, 2 };

    public ItemRawClayOre() {
        setUnlocalizedName("raw_clay_ore");
        setCreativeTab(CATabs.ca_tabs);
        setMaxStackSize(64);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return super.getUnlocalizedName() + "." + meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int meta : VALID_METAS) {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons[0] = iconRegister.registerIcon("clayiumadditions:raw_clay_ore");
        icons[1] = iconRegister.registerIcon("clayiumadditions:raw_ind_clay_ore");
        icons[2] = iconRegister.registerIcon("clayiumadditions:raw_advind_clay_ore");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage < 0 || damage >= icons.length) return icons[0];
        return icons[damage];
    }
}
