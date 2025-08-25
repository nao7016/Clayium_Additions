package com.nao7016.ClayiumAdditions.util;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StorageBoxUtil {
    /**
     * LC + Stack + itemに分ける
     * @param stack ストレージボックス
     * @param appendItemNum アイテム数を表示する？
     * @return StringBuilder
     */
    public static StringBuilder calcItemNumByUnit(ItemStack stack, boolean appendItemNum) {
        StringBuilder builder = new StringBuilder("Empty");
        ItemStack sItemStack = itemStorageBox.getStoredItem(stack);
        if (sItemStack == null) return builder;

        Item sItem = sItemStack.getItem();
        if (sItem == null) return builder;

        builder.setLength(0);
        final int maxStackNum = sItemStack.getMaxStackSize();
        final int LCSlot = 54;
        final int LCNum = LCSlot * maxStackNum;
        int size = sItemStack.stackSize;
        int LC = size / LCNum;
        boolean isHigherUnit = false;
        // LC数の計算
        if (LC >= 1) {
            isHigherUnit = true;
            builder.append(LC).append("LC ");
            size -= LC * LCNum;
        }
        int Stack = size / maxStackNum;
        // stack数の計算
        if (Stack >= 1) {
            isHigherUnit = true;
            if (builder.length() >= 1) {
                builder.append("+ ");
            }
            builder.append(Stack).append("stacks ");
            size -= Stack * maxStackNum;
        }
        // 残りのアイテム数の計算
        if (size >= 1) {
            if (builder.length() >= 1) {
                builder.append("+ ");
            }
            builder.append(size).append("items ");
        }
        if (isHigherUnit && appendItemNum) {
            builder.append("( ").append(sItemStack.stackSize).append(" items)");
        }
        return builder;
    }
}
