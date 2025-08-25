package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.network.storagebox.SBGuiHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GUIStorage extends GuiContainer {
    private static final ResourceLocation rl = new ResourceLocation("textures/guis/storagebox/ItemSelect.png");
    private boolean keySent = false;

    public GUIStorage(ItemStack itemStack, InventoryPlayer player, World world) {
        super(new ContainerStorage(itemStack, player, world));
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString("Storage", 8, 20, 0x404040);
        fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(rl);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        boolean keyL  = Keyboard.isKeyDown(Keyboard.KEY_L);

        if (keyL && !keySent) {
            // GUI が開いている間、Shift/Ctrl/L キー操作を送信
            SBGuiHelper.sendGuiKeyEvent(this);
            keySent = true;
        } else if (!keyL) {
            keySent = false;
        }
    }

}
