package com.nao7016.ClayiumAdditions.item;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import mods.clayium.item.ItemDamaged;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.CreativeTabsClayiumAdditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.IClayEnergy;
import mods.clayium.util.UtilLocale;
import net.minecraft.util.IIcon;

public class itemClayEnergy extends ItemDamaged implements IClayEnergy {

    @SideOnly(Side.CLIENT)
    private final Map<Integer, IIcon> iconMap = new HashMap<>();

    public itemClayEnergy() {
        setUnlocalizedName("clay_energy");
        setHasSubtypes(true);
        setCreativeTab(CreativeTabsClayiumAdditions.tabs_creative_additions);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return super.getUnlocalizedName() + "." + meta;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 4; i <= 12; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return itemStack.getItemDamage();
    }

    @Override
    public long getClayEnergy(ItemStack stack) {
        int meta = stack.getItemDamage();
        return meta >= 4 ? (long) Math.pow(10, meta) : 0L;
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

        long ce = getClayEnergy(stack);
        list.add(UtilLocale.ClayEnergyNumeral(ce) + "CE");

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("clayiumaddition:clay_energy");
        for (int tier =4; tier <= 12; tier++) {
            iconMap.put(tier, iconRegister.registerIcon("clayiumaddition:clay_energy_" + tier));
        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        return iconMap.containsKey(meta)
            ? iconMap.get(meta)
            :this.itemIcon;
    }
}
