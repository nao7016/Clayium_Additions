package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerStorage extends Container {

    private final ItemStack storageBox; // このGUIで操作するストレージボックス
    private final InventoryPlayer playerInv;

    // 一時スロット（GUIに表示する1スロット）
    private final InventoryBasic StorageInventory = new InventoryBasic("storagebox", false, 1);

    public ContainerStorage(InventoryPlayer playerInv, ItemStack storageBox) {
        this.storageBox = storageBox;
        this.playerInv = playerInv;

        this.addSlotToContainer(new SlotStorage(this, StorageInventory, 0, 12, 35));

        // プレイヤーインベントリ
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // ホットバー
        for (int col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemStack = null;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();

            if (stack.getItem() instanceof itemStorageBox) return null;

            itemStorageBox box = getBoxItem();
            if (!box.canStore(stack)) return null;

            int storageSlotStart = 0;
            int storageSlotEnd = 1;
            int playerInvStart = storageSlotEnd;
            int playerInvEnd = playerInvStart + 36;
            if (index >= playerInvStart) { // プレイヤーインベントリ
                // クリックされたのがプレイヤー側なら、ストレージスロットへ移動
                if (!this.mergeItemStack(stack, storageSlotStart, storageSlotEnd, false)) {
                    return null; // 移動できなかった場合
                }
            } else {
                // クリックされたのがストレージスロット側なら、プレイヤーインベントリへ戻す
                if (!this.mergeItemStack(stack, playerInvStart, playerInvEnd, true)) {
                    return null; // 移動できなかった場合
                }
            }

            // スタックが空になったらスロットをクリア
            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack slotClick(int slotId, int mouseButton, int modifier, EntityPlayer player) {
        Slot slot = slotId >= 0 ? inventorySlots.get(slotId) : null;
        if (slot != null) {
            ItemStack stack = slot.getStack();
            if (stack != null && stack.getItem() instanceof itemStorageBox) return null;
        }
        return super.slotClick(slotId, mouseButton, modifier, player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        // GUIを閉じたときにスロット内のアイテムを登録
        if (!player.worldObj.isRemote && storageBox != null && storageBox.getItem() instanceof itemStorageBox) {
            ItemStack storageBox = player.getCurrentEquippedItem();
            ItemStack input = StorageInventory.getStackInSlot(0);

            itemStorageBox.setStoredItemToNBT(storageBox, input);

            player.inventory.markDirty();

            ItemStack stored = storageBox.copy();
            Item storedItem = itemStorageBox.getStoredItem(stored);
            // System.out.println(
            // "[StorageBox] GUI closed. Stored Item: " + (storedItem != null ? storedItem : "Empty")
            // + ", Count: "
            // + itemStorageBox.getStoredCount(stored));
        }
    }

    public ItemStack getStorageBox() {
        return storageBox;
    }

    public itemStorageBox getBoxItem() {
        ItemStack stack = getStorageBox();
        if (stack != null && stack.getItem() instanceof itemStorageBox) {
            return (itemStorageBox) stack.getItem();
        }
        return null;
    }

}
