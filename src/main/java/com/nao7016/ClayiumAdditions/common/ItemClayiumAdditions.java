package com.nao7016.ClayiumAdditions.common;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import com.nao7016.ClayiumAdditions.item.itemClayEnergy0;

public class ItemClayiumAdditions {
    public static Item clayEnergy0;

    public static void registerItems() {
        clayEnergy0 = new itemClayEnergy0();
        GameRegistry.registerItem(clayEnergy0, "clay_energy_0");
    }
}
