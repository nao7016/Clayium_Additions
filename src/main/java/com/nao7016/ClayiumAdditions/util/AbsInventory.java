package com.nao7016.ClayiumAdditions.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AbsInventory extends TileEntity implements IInventory {

    protected ItemStack[] inventory;
    protected String invName;
    protected int stackSizeLimit;

    public AbsInventory(int invSize) {
        this(invSize, 64);
    }

    public AbsInventory(int invSize, int maxStackSize) {
        inventory = new ItemStack[invSize];
        stackSizeLimit = maxStackSize;
    }

    /**
     * Inventory management
     */

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot < inventory.length ? inventory[slot] : null;
    }

    public boolean isStackInSlot(int slot) {
        return slot < inventory.length && inventory[slot] != null;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    public boolean canDropInventorySlot(int slot) {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return stackSizeLimit;
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        ItemStack target = inventory[slot];
        if (target != null) {
            if (target.stackSize <= quantity) {
                ItemStack stack = target;
                target = null;
                return stack;
            }

            ItemStack split = target.splitStack(quantity);
            if (target.stackSize == 0) {
                target = null;
            }
            return split;
        } else {
            return null;
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        } else {
            return player.getDistance((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
        }
    }

    public abstract Container getGuiContainer(InventoryPlayer inventoryPlayer, World world, int x, int y, int z);

    /* for NBT */

    @Override
    public void readFromNBT(NBTTagCompound tags) {
        super.readFromNBT(tags);
        readInventoryFromNBT(tags);
    }

    public void readInventoryFromNBT(NBTTagCompound tags) {
        super.readFromNBT(tags);
        NBTTagList nbtList = tags.getTagList("Items", 10);
        this.inventory = new ItemStack[getSizeInventory()];

        if (tags.hasKey("CustomName", 8)) {
            this.invName = tags.getString("CustomName");
        }

        for (int i = 0; i < nbtList.tagCount(); ++i) {
            NBTTagCompound nbt = nbtList.getCompoundTagAt(i);

            int j = nbt.getShort("Slot");

            if (j < 0) j += 256;

            if (j >= 0 && j < this.inventory.length) {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbt);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tags) {
        super.writeToNBT(tags);
        whiteInventoryToNBT(tags);
    }

    public void whiteInventoryToNBT(NBTTagCompound tags) {
        super.writeToNBT(tags);
        NBTTagList nbtList = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setShort("Slot", (short) i);
                this.inventory[i].writeToNBT(nbt);
                nbtList.appendTag(nbt);
            }
        }

        tags.setTag("Items", nbtList);
        if (this.isInvNameLocalized()) {
            tags.setString("CustomName", this.invName);
        }
    }

    /* Default implementation */
    public ItemStack getStackInSlotOnClosing() {
        return null;
    }

    public void openChest() {}

    public void closeChest() {}

    protected abstract String getDefaultName();

    public void setInvName(String name) {
        this.invName = name;
    }

    public String getInvName() {
        return this.isInvNameLocalized() ? this.invName : getDefaultName();
    }

    public boolean hasCustomInventoryName() {
        return isInvNameLocalized();
    }

    public boolean isInvNameLocalized() {
        return this.invName != null && this.invName.length() > 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        ItemStack target = inventory[slot];
        if (slot < getSizeInventory()) {
            return target == null || itemStack.stackSize + target.stackSize <= getInventoryStackLimit();
        }
        return false;
    }

    public void placeBlock(EntityLivingBase entity, ItemStack stack) {}

    public void removeBlock() {}
}
