package com.nao7016.ClayiumAdditions.network.storagebox;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;
import com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil;
import com.nao7016.ClayiumAdditions.util.storagebox.SBPlayerHandler;
import com.nao7016.ClayiumAdditions.util.storagebox.ServerScheduler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class SBPacketAction implements IMessage {
    private ActionType action;
    private int windowId;
    private boolean shift;
    private boolean ctrl;

    public enum ActionType{
        KeyAction,
        GuiAction
    }

    public SBPacketAction() {}
    public SBPacketAction(ActionType action, int windowId, boolean shift, boolean ctrl) {
        this.action = action;
        this.windowId = windowId;
        this.shift = shift;
        this.ctrl = ctrl;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.action = ActionType.values()[buf.readInt()];
        this.windowId = buf.readInt();
        this.shift = buf.readBoolean();
        this.ctrl = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(action.ordinal());
        buf.writeInt(windowId);
        buf.writeBoolean(shift);
        buf.writeBoolean(ctrl);
    }


    public static class Handler implements IMessageHandler<SBPacketAction, IMessage> {
        @Override
        public IMessage onMessage(SBPacketAction message, final MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;

            ServerScheduler.schedule(() -> handleAction(message, player));
            return null;
        }

        private void handleAction(SBPacketAction message, EntityPlayer player) {
            if (player == null) return;

            if (message.windowId == -1) {
                handleKeyAction(message, player);
            } else {
                handleGuiAction(message, player);
            }
        }

        private void handleKeyAction(SBPacketAction message, EntityPlayer player) {
            ItemStack held = Objects.requireNonNull(player.inventory).getItemStack();

            if (held != null && held.getItem() instanceof itemStorageBox) {

                // GUIを開いていて、StorageBoxを持っている
                // 空っぽの StorageBox の場合は無視。
                if (SBNBTUtil.getItem(held) == null) return;
                // Shift+Ctrl+L を押下した。
                if (message.shift && message.ctrl) {
                    SBPlayerHandler.dropCurrentItem(held, player);
                } else if (!message.shift && !message.ctrl) {
                    // L のみ押下した
                    SBPlayerHandler.storageCurrentItem(held, player);
                }
            }
        }

        private void handleGuiAction(SBPacketAction message, EntityPlayer player) {
            Container container = Objects.requireNonNull(player).openContainer;
            if (container == null || container.windowId != message.windowId) return;

            ItemStack held = player.getCurrentEquippedItem();
            if(held == null || !(held.getItem() instanceof itemStorageBox)) return;

            @SuppressWarnings("rawtypes")
            List list = container.inventorySlots;

            if (message.shift) {
                itemStorageBox.dropToChest(held, list, message.ctrl);
            } else {
                itemStorageBox.storageFromChest(player, held, list);
            }
        }
    }
}
