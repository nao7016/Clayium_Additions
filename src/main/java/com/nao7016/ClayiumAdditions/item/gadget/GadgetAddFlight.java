package com.nao7016.ClayiumAdditions.item.gadget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class GadgetAddFlight extends GadgetAddOrdinal {

    public GadgetAddFlight() {
        super(new String[] { "AddFlight" });
    }

    @Override
    public void update(int itemIndex, Entity entity, boolean isRemote) {
        if (entity instanceof EntityPlayer player) {
            if (itemIndex == -1 && !player.capabilities.isCreativeMode) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
            } else if (itemIndex == 0 && !player.capabilities.isCreativeMode) {
                player.capabilities.allowFlying = true;
                if (player.motionY >= 0.0D) {
                    player.fallDistance = 0.0F;
                }
                player.sendPlayerAbilities();
            }
        }
    }
}
