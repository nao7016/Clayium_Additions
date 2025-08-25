package com.nao7016.ClayiumAdditions.network.storagebox;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;
import com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EventHooks {
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        EntityItem item = event.item;
        int size = item.getEntityItem().stackSize;

        if (pickUpItem(player, item.getEntityItem())) {
            event.setCanceled(true);
            player.worldObj.playSoundAtEntity(item, "random.pop", 0.2F, (float) (((Math.random() - Math.random()) * 0.7 + 1) * 2));
            player.onItemPickup(item, size);
        }
    }

    private boolean pickUpItem(EntityPlayer player, ItemStack item) {
        ItemStack[] inventory = player.inventory.mainInventory;

        for (ItemStack itemStack : inventory) {
            if (itemStack != null && itemStack.getItem() instanceof itemStorageBox) {
                if (SBNBTUtil.isAutoCollect(itemStack)) {
                    ItemStack storageStack = SBNBTUtil.peekItemStackAll(itemStack);

                    if (storageStack != null && item.isItemEqual(storageStack)) {
                        SBNBTUtil.addItemStack(itemStack, item);
                        item.stackSize = 0;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
