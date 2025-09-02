package com.nao7016.ClayiumAdditions.network;

import net.minecraft.entity.player.EntityPlayer;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class StorageBoxPacketHandler implements IMessageHandler<StorageBoxPacket, IMessage> {

    @Override
    public IMessage onMessage(StorageBoxPacket message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        itemStorageBox.keyboardEvent(message.shift, player);
        return null;
    }
}
