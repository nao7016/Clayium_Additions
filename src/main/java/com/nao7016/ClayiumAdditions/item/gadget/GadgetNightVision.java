package com.nao7016.ClayiumAdditions.item.gadget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class GadgetNightVision extends GadgetAddOrdinal {

    private int previousIndex = -1;

    public GadgetNightVision() {
        super(new String[] { "NightVision" });
    }

    @Override
    public void update(int itemIndex, Entity entity, boolean isRemote) {
        if (itemIndex == previousIndex) return;
        previousIndex = itemIndex;

        if (itemIndex >= 0 && entity instanceof EntityPlayer && !isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE, 0, false));
        } else if (entity instanceof EntityPlayer && !isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            player.removePotionEffect(Potion.nightVision.getId());
        }
    }
}
