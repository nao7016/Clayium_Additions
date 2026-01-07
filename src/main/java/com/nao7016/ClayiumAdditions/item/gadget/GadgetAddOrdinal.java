package com.nao7016.ClayiumAdditions.item.gadget;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.nao7016.ClayiumAdditions.common.CAItems;

import mods.clayium.item.CItems;
import mods.clayium.item.IItemGadget;
import mods.clayium.item.gadget.GadgetOrdinal;

public abstract class GadgetAddOrdinal extends GadgetOrdinal implements IItemGadget {

    public List<String> itemNames;

    // アイテム名とリスト(+インデックス)を受け取るコンストラクタ
    public GadgetAddOrdinal(String... itemNames) {
        this.itemNames = Arrays.asList(itemNames);
    }

    // 粘土ガジェットかどうかを判定する
    @Override
    public boolean match(ItemStack itemStack, World world, Entity entity, int slot, boolean isCurrentItem) {
        return isGadget(itemStack) && ( /*
                                         * this.itemNames.contains(CItems.itemGadget.getItemName(itemStack))
                                         * ||
                                         */ this.itemNames.contains(CAItems.addGadget.getItemName(itemStack)));
    }

    // 元のメソッドではCItems.itemGadgetを対象にupdateを呼び出し
    // OverrideしてaddGadgetを対象にした
    @Override
    public void update(List<ItemStack> list, Entity entity, boolean isRemote) {
        int i = -1;

        for (ItemStack itemStack : list) {
            if (isGadget(itemStack)) {
                // i = Math.max(this.itemNames.indexOf(CItems.itemGadget.getItemName(itemStack)), i);
                i = Math.max(this.itemNames.indexOf(CAItems.addGadget.getItemName(itemStack)), i);
            }

            this.update(i, entity, isRemote);
        }
    }

    // 粘土ガジェットかどうかを判定するヘルパーメソッド
    private boolean isGadget(ItemStack itemStack) {
        return itemStack != null
            && (itemStack.getItem() == CItems.itemGadget || itemStack.getItem() == CAItems.addGadget);
    }

    // インデックス、Entity、isRemoteを受け取る抽象メソッド
    @Override
    public abstract void update(int itemIndex, Entity entity, boolean isRemote);
}
