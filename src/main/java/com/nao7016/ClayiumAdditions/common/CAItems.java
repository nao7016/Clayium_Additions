package com.nao7016.ClayiumAdditions.common;

import net.minecraft.item.Item;

import com.nao7016.ClayiumAdditions.item.itemClayEnergy;

import cpw.mods.fml.common.registry.GameRegistry;

public class CAItems {

    public static Item clayEnergy;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        clayEnergy = register(new itemClayEnergy(), "clay_energy");
    }
}
