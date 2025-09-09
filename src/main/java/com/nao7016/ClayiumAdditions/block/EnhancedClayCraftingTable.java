package com.nao7016.ClayiumAdditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.ClayContainerTiered;
import mods.clayium.block.tile.TileClayCraftingTable;

public class EnhancedClayCraftingTable extends ClayContainerTiered {

    public EnhancedClayCraftingTable(int tier) {
        super(Material.clay, TileClayCraftingTable.class, 0, tier);
        setStepSound(Block.soundTypeGravel);
        setHardness(1.0F);
        setResistance(4.0F);
        setHarvestLevel("shovel", 0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.UpOverlayIcon = iconRegister.registerIcon("clayium:claycraftingtable");
        super.registerBlockIcons(iconRegister);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void setInitialBlockBounds() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    }
}
