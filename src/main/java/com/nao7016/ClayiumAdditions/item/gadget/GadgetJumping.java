package com.nao7016.ClayiumAdditions.item.gadget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class GadgetJumping extends GadgetAddOrdinal {

    private int previousIndex = -1;

    public GadgetJumping() {
        super(new String[] { "Jumping1", "Jumping2", "Jumping3" });
    }

    @Override
    public void update(int itemIndex, Entity entity, boolean isRemote) {
        if (itemIndex == previousIndex) return;
        previousIndex = itemIndex;

        if (itemIndex >= 0 && entity instanceof EntityPlayer && !isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            player.addPotionEffect(new PotionEffect(Potion.jump.getId(), Integer.MAX_VALUE, itemIndex, false));
        } else if (entity instanceof EntityPlayer && !isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            player.removePotionEffect(Potion.jump.getId());
        }
    }
}
