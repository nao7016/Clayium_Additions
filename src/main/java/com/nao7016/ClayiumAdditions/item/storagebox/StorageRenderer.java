package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class StorageRenderer extends RenderItem implements IItemRenderer {
    public StorageRenderer() {}

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.INVENTORY
            && item.getItem() == CAItems.storageBox
            && SBNBTUtil.peekItemStack(item) != null;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type != ItemRenderType.INVENTORY) return;
        if (item.getItem() != CAItems.storageBox) return;
        ItemStack sItemStack = SBNBTUtil.peekItemStack(item);
        if (sItemStack != null) {
            Minecraft mc = Minecraft.getMinecraft();
            renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), sItemStack,0, 0);
        }
    }
}
