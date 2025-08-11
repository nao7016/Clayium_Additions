package com.nao7016.ClayiumAdditions.common;

import com.nao7016.ClayiumAdditions.item.itemClayBowl;
import com.nao7016.ClayiumAdditions.item.itemClayBowlEmpty;
import net.minecraft.item.Item;

import com.nao7016.ClayiumAdditions.item.itemClayEnergy;

import cpw.mods.fml.common.registry.GameRegistry;

public class CAItems {

    public static Item clayEnergy;
    public static Item clayBowlEmpty;
    public static Item clayBowl;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        if(Config.cfgSplittedEnergeticClayEnabled) {
            clayEnergy = register(new itemClayEnergy(), "clay_energy");
        }
        if(Config.cfgModeSky) {
            clayBowlEmpty = register(new itemClayBowlEmpty(), "clay_bowl_empty");
            clayBowl = register(new itemClayBowl(), "clay_bowl");
        }
    }
}
