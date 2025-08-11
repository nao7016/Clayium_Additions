package com.nao7016.ClayiumAdditions.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.CATabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.ItemDamaged;
import mods.clayium.util.UtilLocale;

public class itemClayBowl extends ItemDamaged {

    private final Map<Integer, IIcon> iconMap = new HashMap<>();

    public itemClayBowl() {
        setUnlocalizedName("clay_bowl");
        setContainerItem(CAItems.clayBowlEmpty);
        setHasSubtypes(true);
        setCreativeTab(CATabs.ca_tabs);
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

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return itemStack.getItemDamage();
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
        iconMap.put(0, iconRegister.registerIcon("clayiumadditions:clay_bowl_0"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (iconMap.containsKey(meta)) {
            return iconMap.containsKey(meta) ? iconMap.get(meta) : this.itemIcon;
        } else {
            return this.itemIcon;
        }
    }
}
