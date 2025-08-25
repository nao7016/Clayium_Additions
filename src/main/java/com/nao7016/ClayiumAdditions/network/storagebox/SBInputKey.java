package com.nao7016.ClayiumAdditions.network.storagebox;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SBInputKey {
    public static KeyBinding key;
    private static boolean keyPressed = false;

    public static void init() {
        key = new KeyBinding("key.clayiumadditions.storagebox", Keyboard.KEY_L,"key.categories.clayiumadditions");
        ClientRegistry.registerKeyBinding(key);
        FMLCommonHandler.instance().bus().register(new SBInputKey());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (!keyPressed && key.isPressed()) {
            keyPressed = true;
            sendKeyPacket();
        } else if (!key.isPressed()) {
            keyPressed = false;
        }
    }

    private void sendKeyPacket() {
        boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        boolean ctrl = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);

        SBNetworkHandler.INSTANCE.sendToServer(new SBPacketAction(SBPacketAction.ActionType.KeyAction, 0, shift, ctrl));
    }


}
