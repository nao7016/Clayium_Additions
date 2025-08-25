package com.nao7016.ClayiumAdditions.util.storagebox;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.nao7016.ClayiumAdditions.util.storagebox.SBGUIHandler.openInventory;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.*;

public class SBPlayerHandler {
    /**
     * [L]キー押下時
     *
     * @param isShiftKey boolean シフトキー押下時に true
     * @param player
     */
    public static void keyboardEvent(boolean isShiftKey, EntityPlayer player) {
        ItemStack CurrentStack = player.inventory.getCurrentItem();
        if (CurrentStack != null) {
            Item CurrentItem = CurrentStack.getItem();

            if (CurrentItem instanceof itemStorageBox) {
                if (isShiftKey) {
                    dropCurrentItem(CurrentStack, player);
                } else {
                    storageCurrentItem(CurrentStack, player);
                }
                return;
            }
        }
    }

    /**
     * [L]キー押下時のアイテム収納処理
     *
     * @param itemStack
     *            storagebox
     * @param player
     */
    public static void storageCurrentItem(ItemStack itemStack, EntityPlayer player) {
        World world = player.worldObj;
        Item sItem = getItem(itemStack);

        if (sItem == null) {
            openInventory(itemStack, player, world);
        } else {
            storageItem(itemStack, player);
        }
    }

    /**
     * [L]キー押下時のアイテムドロップ処理
     *
     * @param itemStack
     *            storagebox
     * @param player
     */
    public static void dropCurrentItem(ItemStack itemStack, EntityPlayer player) {
        dropCurrentItem(itemStack, player, 0);
    }

    /**
     * [L]キー押下時のアイテムドロップ処理
     *
     * @param itemStack
     *            storagebox
     * @param player
     */
    public static void dropCurrentItem(ItemStack itemStack, EntityPlayer player, int maxNum) {
        ItemStack sItemStack = getItemStack(itemStack, maxNum);

        if (sItemStack == null) return;

        do {
            if (maxNum >= 1) {
                int n = player.inventory.getFirstEmptyStack();
                if (n >= 0) {
                    player.inventory.setInventorySlotContents(n, sItemStack);
                    sItemStack = null;
                    break;
                }
            }
            if (player.inventory.addItemStackToInventory(sItemStack)) {
                player.inventory.markDirty();
            }
            break;
        } while(false);
        if (null != sItemStack) {
            if (sItemStack.stackSize > 0) {
                player.dropPlayerItemWithRandomChoice(sItemStack.copy(), false);
            }
        }
    }

    /**
     * アイテム収納処理
     *
     * @param	itemStack		収納対象アイテム
     * @param	player	実行ユーザー
     */
    private static void storageItem(ItemStack itemStack, EntityPlayer player) {
        ItemStack sItemStack = peekItemStackAll(itemStack);
        if (sItemStack == null) return;

        int size = 0;
        InventoryPlayer inventory = player.inventory;
        ItemStack[] aItemStacks = inventory.mainInventory;

        for (int i = 0; i < aItemStacks.length; i++) {
            if (aItemStacks[i] != null && sItemStack.isItemEqual(aItemStacks[i])) {
                size += aItemStacks[i].stackSize;
                aItemStacks[i] = null;
            }
        }

        sItemStack.stackSize = size;
        addItemStack(itemStack, sItemStack);
    }

    /**
     * アイテムのドロップ処理
     *
     * @param	player	実行ユーザー
     * @param	itemStack		ドロップするアイテム
     */
    public static void dropItem(EntityPlayer player, ItemStack itemStack) {
        if (isPlayer(player)) {
            if (player.inventory.addItemStackToInventory(itemStack)) {
                player.inventory.markDirty();
            } else {
                if (itemStack.stackSize > 0) {
                    player.dropPlayerItemWithRandomChoice(itemStack.copy(), false);
                }
            }
            itemStack.stackSize = 0;
        }
    }

    /**
     * プレイヤー判定
     *
     * @param	player	実行ユーザー
     * @return	プレイヤーであるか否か
     */
    private static boolean isPlayer(EntityPlayer player) {
        return FMLClientHandler.instance().getClient().thePlayer == player;
    }
}
