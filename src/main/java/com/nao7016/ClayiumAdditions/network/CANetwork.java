package com.nao7016.ClayiumAdditions.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class CANetwork {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("clayiumadditions");

    public static void init() {
        INSTANCE.registerMessage(StorageBoxPacketHandler.class, StorageBoxPacket.class, 0, Side.SERVER);
    }
}
