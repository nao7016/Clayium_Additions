package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStorage extends Slot {

    private final ContainerStorage container;

    public SlotStorage(ContainerStorage container, IInventory inventory, int ID, int x, int y) {
        super(inventory, ID, x, y);
        this.container = container;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        itemStorageBox storageBox = container.getBoxItem();
        if (storageBox == null) return false;

        return storageBox.canStore(itemStack);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }
}
