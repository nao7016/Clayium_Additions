package com.nao7016.ClayiumAdditions.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import com.nao7016.ClayiumAdditions.item.itemClayHammer;
import com.nao7016.ClayiumAdditions.recipe.CrushRecipes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HammerEvent {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer player = event.harvester;
        if (player != null && player.getHeldItem() != null
            && player.getHeldItem()
                .getItem() instanceof itemClayHammer) {

            ItemStack drop = new ItemStack(event.block);
            ItemStack result = CrushRecipes.getResult(drop);

            if (result != null) {
                event.drops.clear();
                event.drops.add(result);
                player.getHeldItem()
                    .damageItem(1, player);
            }
        }
    }
}
