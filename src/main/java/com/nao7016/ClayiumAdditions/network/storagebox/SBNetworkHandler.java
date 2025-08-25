package com.nao7016.ClayiumAdditions.network.storagebox;

import com.nao7016.ClayiumAdditions.CAModMain;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class SBNetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(CAModMain.ChannelName);
    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(SBPacketAction.Handler.class, SBPacketAction.class, id++, Side.SERVER);
    }
}
