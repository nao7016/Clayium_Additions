package com.nao7016.ClayiumAdditions.item.storagebox;

import org.lwjgl.input.Keyboard;

import com.nao7016.ClayiumAdditions.network.CANetwork;
import com.nao7016.ClayiumAdditions.network.StorageBoxPacket;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class StorageKeyEvent {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (StorageKey.StorageKey.isPressed()) {
            boolean isShift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
            CANetwork.INSTANCE.sendToServer(new StorageBoxPacket(isShift));
        }
    }
}
