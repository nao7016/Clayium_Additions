package com.nao7016.ClayiumAdditions.network.storagebox;

import com.nao7016.ClayiumAdditions.network.storagebox.SBPacketAction.ActionType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.lwjgl.input.Keyboard;

public class SBGuiHelper {

    /**
     * GUI上でキー操作が発生したときに呼び出す
     * @param guiContainer 操作対象のGUI
     */
    public static void sendGuiKeyEvent(GuiContainer guiContainer) {
        if (guiContainer == null) return;

        Container container = guiContainer.inventorySlots;
        if (container == null) return;

        boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        boolean ctrl  = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);

        // windowId を GUI の windowId に設定
        int windowId = container.windowId;

        // サーバに送信
        SBNetworkHandler.INSTANCE.sendToServer(
            new SBPacketAction(ActionType.GuiAction, windowId, shift, ctrl)
        );
    }
}
