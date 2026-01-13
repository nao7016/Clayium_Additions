package com.nao7016.ClayiumAdditions.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.nao7016.ClayiumAdditions.item.*;
import com.nao7016.ClayiumAdditions.item.ItemClayBowl;
import com.nao7016.ClayiumAdditions.item.gadget.GadgetNightVision;
import com.nao7016.ClayiumAdditions.item.gadget.GadgetSpeed;
import com.nao7016.ClayiumAdditions.item.storagebox.itemClayStorageBox;
import com.nao7016.ClayiumAdditions.item.storagebox.itemMiningStorageBox;
import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.RawOreRegistry;
import mods.clayium.item.ItemGadgetHolder;

public class CAItems {

    public static Item clayEnergy;
    public static Item clayBowlEmpty;
    public static Item clayBowl;
    public static Item rawClayOre;
    public static Item clayHammer;
    public static Item clayMiningHammer;
    public static Item clay3x3Hammer;
    public static Item storageBox;
    public static Item clayStorageBox;
    public static Item miningStorageBox;
    public static ItemAddGadget addGadget;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        addGadget = (ItemAddGadget) (new ItemAddGadget()).setCreativeTab(CATabs.ca_tabs)
            .setUnlocalizedName("itemGadget")
            .setMaxStackSize(1);
        addGadget.addItemList("Speed0", 96, "gadget_speed0", 7);
        addGadget.addItemList("Speed1", 97, "gadget_speed1", 9);
        addGadget.addItemList("Speed2", 98, "gadget_speed2", 11);
        // addGadget.addItemList("NightVision", 112, "gadget_nightvision", 7);
        register(addGadget, "itemGadget");
        ItemGadgetHolder.addGadget(new GadgetSpeed());
        ItemGadgetHolder.addGadget(new GadgetNightVision());
        if (Config.cfgSplitEnergeticClay) {
            clayEnergy = register(new ItemClayEnergy(), "clay_energy");
        }
        if (Config.cfgModeSky) {
            clayBowlEmpty = register(new ItemClayBowlEmpty(), "clay_bowl_empty");
            clayBowl = register(new ItemClayBowl(), "clay_bowl");
            clayHammer = register(new ItemClayHammer(), "clay_hammer");
        }
        if (Config.cfgEtFuturum) {
            if (Loader.isModLoaded("etfuturum")) {
                rawClayOre = new ItemRawClayOre();
                register(rawClayOre, "raw_clay_ore");
                OreDictionary.registerOre("oreDenseClay", getMeta(rawClayOre, 1));
                OreDictionary.registerOre("oreLargeDenseClay", getMeta(rawClayOre, 2));
                RawOreRegistry.addOre("oreDenseClay", rawClayOre, 1);
                RawOreRegistry.addOre("oreLargeDenseClay", rawClayOre, 2);
            }
        }
        if (Config.cfgMiningHammer) {
            clayMiningHammer = register(new ItemClayMiningHammer(), "clay_mining_hammer");
            clay3x3Hammer = register(new Item3x3Hammer(), "clay_3x3_hammer");
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
