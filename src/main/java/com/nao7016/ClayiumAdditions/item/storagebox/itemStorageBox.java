package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.common.CATabs;
import net.minecraft.item.Item;

public class itemStorageBox extends Item {
    public itemStorageBox() {
        super();
        setUnlocalizedName("storage_box");
        setCreativeTab(CATabs.ca_tabs);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }
}
