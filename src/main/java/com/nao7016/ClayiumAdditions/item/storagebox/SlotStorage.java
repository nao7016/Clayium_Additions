package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStorage extends Slot {
    public SlotStorage(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        // StorageBox不可
        if (itemStack.getItem() instanceof itemStorageBox) return false;
        // NBT付き不可
        if (itemStack.hasTagCompound()) return false;
        // Tool系不可
        if (itemStack.getItem().isDamageable()) return false;
        // エンチャント可能アイテム不可
        if (itemStack.isItemEnchanted()) return false;

        return true;
    }
}
