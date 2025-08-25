package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.setItemStack;

public class InventoryStorage implements IInventory {
    private ItemStack stackResult[];
    private ItemStack sItemStack;
    private InventoryPlayer player;
    public InventoryStorage(ItemStack itemStack, InventoryPlayer player, World world) {
        stackResult = new ItemStack[1];
        sItemStack = itemStack;
        this.player = player;
    }

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int i) {
        return stackResult[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (stackResult[i] != null) {
            ItemStack itemStack = stackResult[i];
            stackResult[i] =null;
            return itemStack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemStack) {
        stackResult[i] = itemStack;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    private void save() {
        ItemStack itemStack = stackResult[0];
        setItemStack(sItemStack, itemStack);
        player.mainInventory[player.currentItem] = sItemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (stackResult[var1] != null)
        {
            ItemStack itemstack = stackResult[var1];
            stackResult[var1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public String getInventoryName() {
        return "container.StorageBox";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public void markDirty() {
        save();
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
        save();
    }
}
