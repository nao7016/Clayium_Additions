package com.nao7016.ClayiumAdditions.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.BlockDamaged;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


import java.util.List;

public class RawClayOreBlock extends BlockDamaged {

    private static final int[] VALID_METAS = { 1, 2 };

    public RawClayOreBlock() {
        super(Material.rock, 3);
        this.setBlockTextureName("clayiumadditions:raw_clay_ore_block");
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setHarvestLevel("pickaxe", 0);
        for (int meta : VALID_METAS) {
            this.addBlockList(meta);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta : VALID_METAS) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
