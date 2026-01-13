package com.nao7016.ClayiumAdditions.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.ItemDamaged;
import mods.clayium.item.ItemGadget;

public class ItemAddGadget extends ItemGadget {

    private Map<Integer, String> entryMap = new HashMap<>();
    private Map<Integer, IIcon> iconMap = new HashMap<>();

    @Override
    public ItemDamaged addItemList(String itemName, int meta, String iconString, int tier) {
        entryMap.put(meta, iconString);
        return super.addItemList(itemName, meta, iconString, tier);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iicon) {
        this.itemIcon = iicon.registerIcon("clayiumadditions:gadget_blank");
        for (Map.Entry<Integer, String> entry : entryMap.entrySet()) {
            String iconString = "clayiumadditions:" + entry.getValue();
            int meta = entry.getKey();
            IIcon icon = iicon.registerIcon(iconString);
            iconMap.put(meta, icon);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        IIcon icon = iconMap.get(meta);
        return icon != null ? icon : this.itemIcon;
    }
}
