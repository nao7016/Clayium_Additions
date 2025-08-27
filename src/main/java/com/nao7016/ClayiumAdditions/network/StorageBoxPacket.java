package com.nao7016.ClayiumAdditions.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class StorageBoxPacket implements IMessage {

    public boolean shift;

    public StorageBoxPacket() {}

    public StorageBoxPacket(boolean shift) {
        this.shift = shift;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        shift = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(shift);
    }
}
