package com.nao7016.ClayiumAdditions.block;

import com.nao7016.ClayiumAdditions.block.tile.TileCompressedSolarClayFabricator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.ClayNoRecipeMachines;
import mods.clayium.block.SolarClayFabricator;
import mods.clayium.block.tile.TileSolarClayFabricator;
import net.minecraft.client.renderer.texture.IIconRegister;

public class CompressedSolarClayFabricator extends ClayNoRecipeMachines {
    public CompressedSolarClayFabricator(String guititle, int tier) {
        super(guititle, "", tier, TileCompressedSolarClayFabricator.class, 2);
        this.guiId = 1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister) {
        super.registerBlockIcons(iIconRegister);
        this.UpOverlayIcon = iIconRegister.registerIcon("clayiumadditions:compressed1solar");
    }

    @SideOnly(Side.CLIENT)
    public void registerIOIcons(IIconRegister iIconRegister) {
        this.registerInsertIcons(iIconRegister, new String[]{"import"});
        this.registerExtractIcons(iIconRegister, new String[]{"export"});
    }
}
