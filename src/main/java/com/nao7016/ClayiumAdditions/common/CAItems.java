package com.nao7016.ClayiumAdditions.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.nao7016.ClayiumAdditions.item.itemClayBowl;
import com.nao7016.ClayiumAdditions.item.itemClayBowlEmpty;
import com.nao7016.ClayiumAdditions.item.itemClayEnergy;
import com.nao7016.ClayiumAdditions.item.itemClayHammer;
import com.nao7016.ClayiumAdditions.item.itemClayMiningHammer;
import com.nao7016.ClayiumAdditions.item.itemRawClayOre;
import com.nao7016.ClayiumAdditions.item.storagebox.itemClayStorageBox;
import com.nao7016.ClayiumAdditions.item.storagebox.itemMiningStorageBox;
import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.RawOreRegistry;

public class CAItems {

    public static Item clayEnergy;
    public static Item clayBowlEmpty;
    public static Item clayBowl;
    public static Item rawClayOre;
    public static Item clayHammer;
    public static Item clayMiningHammer;
    public static Item storageBox;
    public static Item clayStorageBox;
    public static Item miningStorageBox;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        if (Config.cfgSplittedEnergeticClay) {
            clayEnergy = register(new itemClayEnergy(), "clay_energy");
        }
        if (Config.cfgModeSky) {
            clayBowlEmpty = register(new itemClayBowlEmpty(), "clay_bowl_empty");
            clayBowl = register(new itemClayBowl(), "clay_bowl");
            clayHammer = register(new itemClayHammer(), "clay_hammer");
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
        if (Config.cfgMiningHammer) {
            clayMiningHammer = register(new itemClayMiningHammer(), "clay_mining_hammer");
        }
        if (Config.cfgStorageBox) {
            storageBox = register(new itemStorageBox(), "storage_box");
            clayStorageBox = register(new itemClayStorageBox(), "clay_storage_box");
            miningStorageBox = register(new itemMiningStorageBox(), "mining_storage_box");
        }
    }

    private static ItemStack getMeta(Item item, int meta) {
        return new ItemStack(item, 1, meta);
    }
}
