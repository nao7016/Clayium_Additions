package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public class SBGuiHandler implements IGuiHandler {

    public static final int StorageBoxGui = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == StorageBoxGui) {
            ItemStack held = player.getHeldItem();
            if (held != null && held.getItem() instanceof itemStorageBox) {
                return new ContainerStorage(player.inventory, held);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == StorageBoxGui) {
            ItemStack held = player.getHeldItem();
            if (held != null && held.getItem() instanceof itemStorageBox) {
                return new GuiStorage(player.inventory, held); // クライアント側GUI
            }
        }
        return null;
    }
}
