package com.nao7016.ClayiumAdditions.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.CATabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.util.UtilLocale;

public class ItemClayBowlEmpty extends Item {

    public ItemClayBowlEmpty() {
        setUnlocalizedName("clay_bowl_empty");
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("clayiumadditions:clay_bowl_empty");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        super.addInformation(stack, player, list, flag);

        List alist = UtilLocale.localizeTooltip(this.getUnlocalizedName(stack) + ".tooltip");
        if (alist != null) {
            list.addAll(alist);
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (world.isRemote) return false; // server check

        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (block == Blocks.farmland && meta == 7) {
            // consume bowl
            stack.stackSize--;

            if (stack.stackSize <= 0) {
                player.setCurrentItemOrArmor(0, null);
            }

            // get Clay Bowl
            ItemStack clayBowl = new ItemStack(CAItems.clayBowl, 1, 0);
            if (!player.inventory.addItemStackToInventory(clayBowl)) {
                player.dropPlayerItemWithRandomChoice(clayBowl, false);
            }

            float volume = 0.2F + world.rand.nextFloat() * 0.2F;
            float pitch = 0.7F + world.rand.nextFloat() * 0.6F;
            world.playSoundEffect(player.posX, player.posY, player.posZ, "random.pop", volume, pitch);

            player.inventory.markDirty();
            player.inventoryContainer.detectAndSendChanges();

            return true;

        }

        return false;
    }
}
