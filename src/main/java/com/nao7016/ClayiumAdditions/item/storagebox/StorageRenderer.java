package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class StorageRenderer extends RenderItem implements IItemRenderer {

    public StorageRenderer() {}

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.INVENTORY && item.getItem() instanceof itemStorageBox
            && itemStorageBox.getStoredItemStack(item) != null;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type != ItemRenderType.INVENTORY || !(item.getItem() instanceof itemStorageBox)) return;

        ItemStack stored = itemStorageBox.getStoredItemStack(item);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        RenderHelper.enableGUIStandardItemLighting();

        if (stored != null) {
            Minecraft mc = Minecraft.getMinecraft();
            renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), stored, 0, 0);
        }

        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }
}
