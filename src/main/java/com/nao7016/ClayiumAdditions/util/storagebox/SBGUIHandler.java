package com.nao7016.ClayiumAdditions.util.storagebox;

import com.nao7016.ClayiumAdditions.CAModMain;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SBGUIHandler {

    public static int containerID = 21;
    /**
     * 収納アイテム選択GUIの表示
     *
     * @param itemStack
     *            storagebox
     * @param player
     *            実行ユーザー
     * @param world
     *            実行ワールド
     */
    public static void openInventory(ItemStack itemStack, EntityPlayer player, World world) {
        if(!world.isRemote) {
            FMLNetworkHandler.openGui(player, CAModMain.instance, containerID, world, player.inventory.currentItem, 0, 0);
        }
    }
}
