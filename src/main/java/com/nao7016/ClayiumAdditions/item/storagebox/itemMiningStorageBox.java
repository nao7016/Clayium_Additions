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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemMiningStorageBox extends itemStorageBox {

    private IIcon defaultIcon;

    public itemMiningStorageBox() {
        super();
        setUnlocalizedName("mining_storage_box");
    }

    private static final Set<Item> Whitelist = new HashSet<>(
        Arrays.asList(
            Item.getItemFromBlock(Blocks.dirt),
            Item.getItemFromBlock(Blocks.cobblestone),
            Item.getItemFromBlock(Blocks.stone),
            Item.getItemFromBlock(Blocks.gravel),
            Items.flint,
            Item.getItemFromBlock(Blocks.sand),
            Item.getItemFromBlock(Blocks.sandstone),
            Item.getItemFromBlock(Blocks.torch),
            Item.getItemFromBlock(Blocks.netherrack),
            Item.getItemFromBlock(Blocks.soul_sand)));

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        defaultIcon = iconRegister.registerIcon("clayiumadditions:storage_box_mining");
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
