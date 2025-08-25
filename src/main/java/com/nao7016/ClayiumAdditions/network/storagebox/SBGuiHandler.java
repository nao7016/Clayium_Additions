package com.nao7016.ClayiumAdditions.network.storagebox;

import com.nao7016.ClayiumAdditions.item.storagebox.ContainerStorage;
import com.nao7016.ClayiumAdditions.item.storagebox.GUIStorage;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SBGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 21) {
            return new ContainerStorage(player.getCurrentEquippedItem(), player.inventory, world);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 21) {
            return new GUIStorage(player.getCurrentEquippedItem(), player.inventory, world);
        }
        return null;
    }
}
