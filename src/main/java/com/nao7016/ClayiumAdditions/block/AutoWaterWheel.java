package com.nao7016.ClayiumAdditions.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.nao7016.ClayiumAdditions.block.tile.TileAutoWaterWheel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.ClayNoRecipeMachines;
import mods.clayium.util.UtilBuilder;
import mods.clayium.util.UtilLocale;

public class AutoWaterWheel extends ClayNoRecipeMachines {

    @SideOnly(Side.CLIENT)
    public IIcon[] FrontOverlayIcons;

    public AutoWaterWheel(String guiTitle, String iconStr, int tier) {
        super(guiTitle, iconStr, tier, TileAutoWaterWheel.class, 2);
        this.guiId = 10;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.FrontOverlayIcons = new IIcon[] { this.FrontOverlayIcon, iconRegister.registerIcon(this.iconstr + "_1") };
    }

    @SideOnly(Side.CLIENT)
    public IIcon getOverlayIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileAutoWaterWheel te = (TileAutoWaterWheel) UtilBuilder.safeGetTileEntity(world, x, y, z);
        this.FrontOverlayIcon = this.FrontOverlayIcons[te.getProgressIcon()];
        IIcon icon = super.getOverlayIcon(world, x, y, z, side);
        this.FrontOverlayIcon = this.FrontOverlayIcons[0];
        return icon;
    }

    public List getTooltip(ItemStack itemStack) {
        List tooltip = new java.util.ArrayList<>();
        int tier = getTier(itemStack) - 1;
        tooltip.add(UtilLocale.localizeAndFormat("tooltip.AutoWaterWheel.info", tier));
        tooltip.addAll(UtilLocale.localizeTooltip("tooltip.AutoWaterWheel"));
        tooltip.addAll(super.getTooltip(itemStack));
        return tooltip;
    }
}
