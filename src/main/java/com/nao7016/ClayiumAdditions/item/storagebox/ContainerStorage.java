package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
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
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            if (index > 0) { // プレイヤーインベントリ
                registerItem(stack);
                slot.putStack(null);
            }
        }
        return null;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        // GUIを閉じたときにスロット内のアイテムを登録
        if (!player.worldObj.isRemote) {
            ItemStack storageBox = player.getCurrentEquippedItem();
            ItemStack input = StorageInventory.getStackInSlot(0);

            if (storageBox != null && storageBox.getItem() instanceof itemStorageBox && input != null) {
                itemStorageBox.setStoredItem(storageBox, input.copy(), input.stackSize);
                player.inventory.markDirty();
            }
        }
    }

    /** アイテムを登録 */
    private void registerItem(ItemStack input) {
        ItemStack stored = itemStorageBox.getStoredItem(storageBox);
        int count = itemStorageBox.getStoredCount(storageBox);

        if (stored == null) {
            // 初回登録
            itemStorageBox.setStoredItem(storageBox, input.copy(), input.stackSize);
        } else {
            if (ItemStack.areItemStacksEqual(stored, input)) {
                // 同じアイテム → 加算
                itemStorageBox.setStoredItem(storageBox, stored, count + input.stackSize);
            }
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
