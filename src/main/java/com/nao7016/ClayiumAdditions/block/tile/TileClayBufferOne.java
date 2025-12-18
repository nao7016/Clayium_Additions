package com.nao7016.ClayiumAdditions.block.tile;

import mods.clayium.block.tile.TileClayBuffer;
import net.minecraft.item.ItemStack;

public class TileClayBufferOne extends TileClayBuffer {
    public void initParams() {
        super.initParams();
        this.containerItemStacks = new ItemStack[1];
    }

    public void initParamsByTier(int tier) {
        super.initParamsByTier(tier);
        this.inventoryX = this.inventoryY = 1;

        int slotNum = this.inventoryX * this.inventoryY; // == 1
        int[] slots = new int[slotNum];
        int[] slots2 = new int[slotNum];

        for (int i = 0; i < slotNum; i++) {
            slots[i] = i;
            slots2[i] = slotNum - i - 1;
        }

        // 親が作成したリストを上書きして整合性を保つ
        this.listSlotsInsert.clear();
        this.listSlotsExtract.clear();
        this.listSlotsInsert.add(slots);
        this.listSlotsExtract.add(slots2);
        this.slotsDrop = slots;
    }
}
