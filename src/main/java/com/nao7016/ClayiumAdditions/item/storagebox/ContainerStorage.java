package com.nao7016.ClayiumAdditions.item.storagebox;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerStorage extends Container {
    public IInventory storage;
    private ItemStack item;

    public ContainerStorage(ItemStack itemStack, InventoryPlayer player, World world) {
        storage = new InventoryStorage(itemStack, player, world);
        item = itemStack;
        addSlotToContainer(new SlotStorage(storage, 0, 12, 35));

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int hotBar = 0; hotBar <9; hotBar++) {
            addSlotToContainer(new Slot(player, hotBar, 8 + hotBar * 18, 142));
        }
    }

    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer player) {
        if (slotId == -999) {
            ItemStack dItem = player.inventory.getItemStack();

            if ((clickedButton == 0 || clickedButton ==1) && (dItem == null || item.getItem() == dItem.getItem())) return null;
        } else if (slotId == -1) {
            return null;
        } else {
            if (mode == 1) {
                ItemStack dItem = player.inventory.getItemStack();

                if (dItem != null && item.getItem() == dItem.getItem()) return null;
            } else {
                Slot slot = (Slot) this.inventorySlots.get(slotId);
                ItemStack dItem = slot.getStack();
                if (dItem != null && item.getItem() == dItem.getItem()) return null;
            }
        }

        ItemStack itemStack = super.slotClick(slotId, clickedButton, mode, player);
        storage.markDirty();
        return itemStack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        storage.markDirty();
        super.onContainerClosed(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        FMLLog.info("ContainerStorage.transferStackInSlot=%d", slotNum);
        ItemStack itemStack = null;
        Slot slot = inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (slotNum == 0) {
                if (!mergeItemStack(itemStack1, 1, 37, true)) return null;
            } else if (slotNum >= 1 && slotNum < 28) {
                final Slot targetSlot = inventorySlots.get(0);
                if (!targetSlot.isItemValid(itemStack1)) {
                } else if(!mergeItemStack(itemStack1, 0, 1, false)) return null;
            } else if (slotNum >= 28 && slotNum < 37) {
                if (!mergeItemStack(itemStack1, 1, 28, false)) return null;
            } else if (!mergeItemStack(itemStack1, 1, 37, false)) return null;

            if (itemStack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack1.stackSize != itemStack.stackSize) {
                slot.onPickupFromSlot(player, itemStack1);
            } else {
                storage.markDirty();
                return null;
            }
        }

        storage.markDirty();
        return itemStack;
    }

    @Override
    protected boolean mergeItemStack(ItemStack itemStack, int start, int end, boolean reverse) {
        boolean b = super.mergeItemStack(itemStack, start, end, reverse);
        storage.markDirty();
        return b;
    }
}
