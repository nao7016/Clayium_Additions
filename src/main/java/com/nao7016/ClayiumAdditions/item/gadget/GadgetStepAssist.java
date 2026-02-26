package com.nao7016.ClayiumAdditions.item.gadget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class GadgetStepAssist extends GadgetAddOrdinal {

    public GadgetStepAssist() {
        super(new String[] { "StepAssist" });
    }

    @Override
    public void update(int itemIndex, Entity entity, boolean isRemote) {
        if (entity instanceof EntityPlayer player) {
            if (itemIndex >= 0) {
                if (player.isSneaking()) {
                    player.stepHeight = 0.0F;
                } else {
                    player.stepHeight = 1.002F;
                }
            } else {
                player.stepHeight = 0.5F;
            }
        }
    }
}
