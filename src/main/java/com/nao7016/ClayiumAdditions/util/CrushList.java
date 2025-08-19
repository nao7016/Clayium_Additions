package com.nao7016.ClayiumAdditions.util;

import net.minecraft.item.ItemStack;

public class CrushList {

    public final ItemStack input;
    public final ItemStack output;

    public CrushList(ItemStack input, ItemStack output, int stackSize) {
        this.input = input;
        this.output = output.copy();
        this.output.stackSize = stackSize;
    }

    public boolean matches(ItemStack stack) {
        return stack != null && stack.isItemEqual(input);
    }
}
