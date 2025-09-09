package com.nao7016.ClayiumAdditions.item.hammer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import com.nao7016.ClayiumAdditions.common.CABlocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.clayium.block.CBlocks;

public class MiningHammerEvent {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer player = event.harvester;
        if (player != null && player.getHeldItem() != null
            && player.getHeldItem()
                .getItem() instanceof itemClayMiningHammer) {

            ItemStack held = player.getHeldItem();

            if (event.block == CBlocks.blockClayOre || event.block == CABlocks.blockDeepslateClayOre) {
                event.drops.clear();
                event.drops.add(new ItemStack(event.block, 1, event.blockMetadata));
                held.damageItem(1, player);
            }
        }
    }

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        ItemStack held = event.getPlayer() != null ? event.getPlayer()
            .getHeldItem() : null;
        if (held == null || !(held.getItem() instanceof itemClayMiningHammer)) return;

        if (event.block == CBlocks.blockClayOre || event.block == CABlocks.blockDeepslateClayOre) {
            event.setExpToDrop(0);
        }
    }
}
