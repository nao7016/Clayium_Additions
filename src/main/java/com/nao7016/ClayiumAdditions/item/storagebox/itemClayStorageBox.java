package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.item.Item;

public class itemClayStorageBox extends itemStorageBox {
    public itemClayStorageBox() {
        super();
        setUnlocalizedName("clay_storage_box");
    }

    @Override
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

}
