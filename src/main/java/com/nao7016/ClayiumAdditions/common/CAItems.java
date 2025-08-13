package com.nao7016.ClayiumAdditions.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.nao7016.ClayiumAdditions.item.itemClayBowl;
import com.nao7016.ClayiumAdditions.item.itemClayBowlEmpty;
import com.nao7016.ClayiumAdditions.item.itemClayEnergy;
import com.nao7016.ClayiumAdditions.item.itemRawClayOre;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.RawOreRegistry;

public class CAItems {

    public static Item clayEnergy;
    public static Item clayBowlEmpty;
    public static Item clayBowl;
    public static Item rawClayOre;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        if (Config.cfgSplittedEnergeticClayEnabled) {
            clayEnergy = register(new itemClayEnergy(), "clay_energy");
        }
        if (Config.cfgModeSky) {
            clayBowlEmpty = register(new itemClayBowlEmpty(), "clay_bowl_empty");
            clayBowl = register(new itemClayBowl(), "clay_bowl");
        }
        if (Config.cfgEtFuturum) {
            if (Loader.isModLoaded("etfuturum")) {
                rawClayOre = new itemRawClayOre();
                register(rawClayOre, "raw_clay_ore");
                OreDictionary.registerOre("oreDenseClay", getMeta(rawClayOre, 1));
                OreDictionary.registerOre("oreLargeDenseClay", getMeta(rawClayOre, 2));
                RawOreRegistry.addOre("oreDenseClay", rawClayOre, 1);
                RawOreRegistry.addOre("oreLargeDenseClay", rawClayOre, 2);
            }
        }
    }

    private static ItemStack getMeta(Item item, int meta) {
        return new ItemStack(item, 1, meta);
    }
}
