package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiStorage extends GuiContainer {

    private final ResourceLocation texture = new ResourceLocation("clayiumadditions:textures/guis/StorageBox.png");

    public GuiStorage(InventoryPlayer playerInv, ItemStack storageBox) {
        super(new ContainerStorage(playerInv, storageBox));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager()
            .bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Storage", 8, 20, 0x404040);
        fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }
}
