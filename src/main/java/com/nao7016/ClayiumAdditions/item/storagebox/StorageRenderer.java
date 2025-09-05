package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.common.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.nao7016.ClayiumAdditions.util.StorageBoxUtil;

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

        ItemStack stored = itemStorageBox.getStoredItemStackAll(item);
        String count = StorageBoxUtil.calcNumberPrefix(itemStorageBox.getStoredCount(item));
        if (stored != null) {
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();

            Minecraft mc = Minecraft.getMinecraft();
            renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), stored, 0, 0);
            if (Config.cfgSBMoreDisplay) {
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glScalef(0.5F, 0.5F, 1.0F);
                renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, 0, 0);
                GL11.glEnable((GL11.GL_DEPTH_TEST));
            }

            GL11.glPopMatrix();

            if (Config.cfgSBMoreDisplay) {

                RenderHelper.disableStandardItemLighting();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                mc.fontRenderer.drawStringWithShadow(count, 16 - mc.fontRenderer.getStringWidth(count), 8, 0xFFFF00);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                RenderHelper.enableStandardItemLighting();
            }
        }
    }
}
