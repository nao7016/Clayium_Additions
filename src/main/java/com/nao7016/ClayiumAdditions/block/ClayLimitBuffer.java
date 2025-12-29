package com.nao7016.ClayiumAdditions.block;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.block.tile.TileClayLimitBuffer;

import mods.clayium.block.ClayNoRecipeMachines;
import mods.clayium.util.UtilLocale;

public class ClayLimitBuffer extends ClayNoRecipeMachines {

    public ClayLimitBuffer(int tier) {
        this(tier, TileClayLimitBuffer.class);
    }

    public ClayLimitBuffer(int tier, Class<? extends TileClayLimitBuffer> tileClass) {
        super((String) null, "", tier, tileClass, 2);
        this.guiId = 11;
    }

    public List getTooltip(ItemStack itemStack) {
        List ret = UtilLocale.localizeTooltip("tooltip.LimitBuffer");
        ret.addAll(super.getTooltip(itemStack));
        return ret;
    }
}
