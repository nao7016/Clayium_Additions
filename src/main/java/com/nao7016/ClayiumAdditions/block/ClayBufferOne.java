package com.nao7016.ClayiumAdditions.block;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.nao7016.ClayiumAdditions.block.tile.TileClayBufferOne;

import mods.clayium.block.ClayNoRecipeMachines;
import mods.clayium.util.UtilLocale;

public class ClayBufferOne extends ClayNoRecipeMachines {

    public ClayBufferOne(int tier) {
        this(tier, TileClayBufferOne.class);
    }

    public ClayBufferOne(int tier, Class<? extends TileClayBufferOne> tileClass) {
        super((String) null, "", tier, tileClass, 2);
        this.guiId = 11;
    }

    public List getTooltip(ItemStack itemStack) {
        List ret = UtilLocale.localizeTooltip("tooltip.BufferOne");
        ret.addAll(super.getTooltip(itemStack));
        return ret;
    }
}
