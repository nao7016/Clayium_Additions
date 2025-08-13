package com.nao7016.ClayiumAdditions.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.CATabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.ItemTiered;
import mods.clayium.util.UtilLocale;

public class itemClayBowl extends ItemTiered {

    private final Map<Integer, IIcon> iconMap = new HashMap<>();

    public itemClayBowl() {
        setUnlocalizedName("clay_bowl");
        setContainerItem(CAItems.clayBowlEmpty);
        setCreativeTab(CATabs.ca_tabs);
        setBaseTier(0);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);

        String baseKey = getUnlocalizedName(stack);
        if (baseKey.contains(".")) {
            baseKey = baseKey.substring(0, baseKey.lastIndexOf('.'));
        }
        list.addAll(UtilLocale.localizeTooltip(baseKey + ".tooltip"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("clayiumadditions:clay_bowl");
    }

    // @SideOnly(Side.CLIENT)
    // @Override
    // public IIcon getIconFromDamage(int meta) {
    // if (iconMap.containsKey(meta)) {
    // return iconMap.containsKey(meta) ? iconMap.get(meta) : this.itemIcon;
    // } else {
    // return this.itemIcon;
    // }
    // }
}
