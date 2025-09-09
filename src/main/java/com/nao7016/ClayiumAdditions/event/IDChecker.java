package com.nao7016.ClayiumAdditions.event;

import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * From Inventory Tweaks ID checker function
 */

public class IDChecker {

    @SubscribeEvent
    public void IDCheckerOnTooltip(ItemTooltipEvent event) {
        if (event.showAdvancedItemTooltips) {
            event.toolTip
                .add(1, EnumChatFormatting.GRAY + Item.itemRegistry.getNameForObject(event.itemStack.getItem()));
        }
    }

}
