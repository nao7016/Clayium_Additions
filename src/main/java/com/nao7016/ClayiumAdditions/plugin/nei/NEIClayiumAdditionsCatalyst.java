package com.nao7016.ClayiumAdditions.plugin.nei;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.Config;

import codechicken.nei.recipe.CatalystInfo;
import codechicken.nei.recipe.RecipeCatalysts;

public class NEIClayiumAdditionsCatalyst {

    public static void registerHammerCatalysts() {
        if (Config.cfgModeSky) {
            registerCatalysts("hammer", CAItems.clayHammer);
        }
    }

    public static void registerCatalysts(String handlerId, Block[] blocks) {
        for (Block block : blocks) {
            registerCatalysts(handlerId, block);
        }
    }

    public static void registerCatalysts(String handlerId, Block block) {
        if (block != null) {
            Item item = Item.getItemFromBlock(block);
            registerCatalysts(handlerId, item);
        }
    }

    public static void registerCatalysts(String handlerId, Item[] items) {
        for (Item item : items) {
            registerCatalysts(handlerId, item);
        }
    }

    public static void registerCatalysts(String handlerId, Item item) {
        if (item != null) {
            RecipeCatalysts.addRecipeCatalyst(handlerId, new CatalystInfo(new ItemStack(item), 0));
        }
    }
}
