package com.nao7016.ClayiumAdditions.compat;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import com.nao7016.ClayiumAdditions.common.CABlocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.clayium.block.ClayOre;
import mods.clayium.item.CItems;

public class EtOreIntegration {

    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent event) {
        if (event.block instanceof ClayOre) {
            int meta = event.blockMetadata;

            if (meta == 1 || meta == 2) {
                if (event.harvester != null && event.harvester.getHeldItem() != null) {
                    Item held = event.harvester.getHeldItem()
                        .getItem();
                    if (held == CItems.itemClayShovel || held == CItems.itemClayPickaxe) {
                        int multiplier = (held == CItems.itemClayShovel) ? 3 : 4;
                        if (!event.drops.isEmpty()) {
                            int fixMultiplier = Math.max(1, event.drops.get(0).stackSize / multiplier);

                            ItemStack drop = event.drops.get(0)
                                .copy();
                            drop.stackSize = fixMultiplier;

                            event.drops.clear();
                            event.drops.add(drop);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Block block = event.block;
        int meta = event.metadata;
        ItemStack heldItem = event.entityPlayer.getHeldItem();

        if (heldItem != null && block == CABlocks.blockDeepslateClayOre) {
            if (heldItem.getItem() == CItems.itemClayShovel) {
                float efficiencyOnClayOre = 12.0F;
                event.newSpeed = efficiencyOnClayOre;
            } else if (heldItem.getItem() == CItems.itemClayPickaxe) {
                float efficiencyOnClayOre = 32.0F;
                if (block.getHarvestLevel(meta) <= heldItem.getItem()
                    .getHarvestLevel(heldItem, block.getHarvestTool(meta))) {
                    event.newSpeed = efficiencyOnClayOre;
                } else {
                    event.newSpeed = efficiencyOnClayOre * 100.0F / 30.0F;
                }
            }
        }
    }
}
