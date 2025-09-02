package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AutoCollect {

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        EntityItem item = event.item;
        int size = item.getEntityItem().stackSize;

        if (pickupItem(player, item.getEntityItem())) {
            event.setCanceled(true);
            float volume = (float) (0.2F + Math.random() * 0.2F);
            float pitch = (float) (0.7F + Math.random() * 0.6F);
            player.worldObj.playSoundAtEntity(item, "random.pop", volume, pitch);
            player.onItemPickup(item, size);
        }
    }

    private boolean pickupItem(EntityPlayer player, ItemStack item) {
        ItemStack[] inventory = player.inventory.mainInventory;

        for (ItemStack itemStack : inventory) {
            if (itemStack != null && itemStack.getItem() instanceof itemStorageBox
                && itemStorageBox.isAutoCollect(itemStack)) {
                ItemStack storageStack = itemStorageBox.getStoredItemStackAll(itemStack);
                if (storageStack != null && item.isItemEqual(storageStack)) {
                    itemStorageBox.addItemStack(itemStack, item);
                    item.stackSize = 0;
                    return true;
                }
            }
        }

        return false;
    }
}
