package com.nao7016.ClayiumAdditions.item.storagebox;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.nao7016.ClayiumAdditions.common.CABlocks;
import com.nao7016.ClayiumAdditions.common.CAItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.CBlocks;
import mods.clayium.item.CItems;

public class itemClayStorageBox extends itemStorageBox {

    private IIcon defaultIcon;

    public itemClayStorageBox() {
        super();
        setUnlocalizedName("clay_storage_box");
    }

    private static final Set<Item> Whitelist = new HashSet<>(
        Arrays.asList(
            Items.clay_ball,
            CItems.itemLargeClayBall,
            Item.getItemFromBlock(Blocks.clay),
            Item.getItemFromBlock(CBlocks.blockClayOre),
            Item.getItemFromBlock(CABlocks.blockDeepslateClayOre),
            Item.getItemFromBlock(CBlocks.blockCompressedClay),
            CItems.itemCompressedClayShard,
            CAItems.clayEnergy));

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        defaultIcon = iconRegister.registerIcon("clayiumadditions:clay_storage_box");
        this.itemIcon = defaultIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return defaultIcon;
    }

    @Override
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

    @Override
    public boolean canStore(ItemStack stack) {
        return super.canStore(stack) && Whitelist.contains(stack.getItem());
    }
}
