package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.CAModMain;

import java.io.*;

/*public class ChestKeyEventData {
    public boolean isShift = false;
    public boolean isCtrl = false;
    public int windowID = 0;

    public void read(byte[] bytes) {
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            input.readByte();
            this.isShift = input.readBoolean();
            this.isCtrl = input.readBoolean();
            this.windowID = input.readInt();
        } catch (IOException ignored) {}
    }

    public byte[] write() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream os = new DataOutputStream(baos)) {
            try {
                os.writeByte(CAModMain.ChannelFlagGui);
                os.writeBoolean(this.isShift);
                os.writeBoolean(this.isCtrl);
                os.writeInt(this.windowID);
            } catch (IOException ignored) {}
        } catch (IOException ignored) {}

        return baos.toByteArray();
    }
}*/
