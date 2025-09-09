package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.nao7016.ClayiumAdditions.common.Config;
import com.nao7016.ClayiumAdditions.util.UtilStorageBox;

public class StorageRenderer extends RenderItem implements IItemRenderer {

    private static final ResourceLocation icon = new ResourceLocation(
        "clayiumadditions",
        "textures/items/storage_box_1.png");

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
        String count = Config.cfgSBSIPrefix ? UtilStorageBox.calcNumberPrefix(itemStorageBox.getStoredCount(item))
            : String.valueOf(itemStorageBox.getStoredCount(item));
        if (stored != null) {
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();

            Minecraft mc = Minecraft.getMinecraft();
            renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), stored, 0, 0);
            if (Config.cfgSBMoreDisplay) {
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                if (itemStorageBox.isAutoCollect(item)) {
                    GL11.glScalef(0.5F, 0.5F, 1.0F);
                    renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, 0, 0);
                } else {
                    RenderHelper.disableStandardItemLighting();
                    drawOverlayIcon();
                    RenderHelper.enableGUIStandardItemLighting();
                }
                GL11.glEnable((GL11.GL_DEPTH_TEST));
            }

            GL11.glPopMatrix();

            if (Config.cfgSBMoreDisplay) {

                RenderHelper.disableStandardItemLighting();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                mc.fontRenderer.drawStringWithShadow(count, 16 - mc.fontRenderer.getStringWidth(count), 8, 0xFFFF00);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                RenderHelper.enableGUIStandardItemLighting();
            }
        }
    }

    private void drawOverlayIcon() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager()
            .bindTexture(icon);

        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(0, 8, 0, 0, 1);
        t.addVertexWithUV(8, 8, 0, 1, 1);
        t.addVertexWithUV(8, 0, 0, 1, 0);
        t.addVertexWithUV(0, 0, 0, 0, 0);
        t.draw();
    }
}
