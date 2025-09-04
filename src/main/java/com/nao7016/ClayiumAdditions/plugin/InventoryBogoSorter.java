package com.nao7016.ClayiumAdditions.plugin;

import java.util.List;

import net.minecraft.inventory.IInventory;

import com.cleanroommc.bogosorter.BogoSortAPI;
import com.cleanroommc.bogosorter.api.IPosSetter;
import com.nao7016.ClayiumAdditions.util.StorageBoxUtil;

import mods.clayium.block.tile.InventoryMultiPage;
import mods.clayium.block.tile.InventoryOffsetted;
import mods.clayium.block.tile.TileMetalChest;
import mods.clayium.gui.container.ContainerNormalInventory;
import mods.clayium.gui.container.ContainerTemp;

public class InventoryBogoSorter {

    public static void init() {
        BogoSortAPI.INSTANCE.addCompat(ContainerNormalInventory.class, ((container, builder) -> {

            // ContainerNormalInventory -> ContainerTemp.tileを取得
            List<Object> l = StorageBoxUtil.findPrivateValue(ContainerTemp.class, container, IInventory.class);
            if (l.size() != 1 || !(l.get(0) instanceof InventoryMultiPage multiPage)) return;
            // InventoryOffsetted.inventoryを取得
            List<Object> l2 = StorageBoxUtil.findPrivateValue(InventoryOffsetted.class, multiPage, IInventory.class);
            if (l2.size() != 1 || !(l2.get(0) instanceof TileMetalChest)) return;

            if (container.drawInventoryName()) {
                builder.addSlotGroup(0, container.inventorySlots.size() - 36, container.rowSizeCallback());
            } else {
                builder.addSlotGroup(0, container.inventorySlots.size() - 36, container.rowSizeCallback())
                    .buttonPosSetter(IPosSetter.TOP_RIGHT_VERTICAL);
            }
        }));

        BogoSortAPI.INSTANCE.addPlayerSortButtonPosition(ContainerNormalInventory.class, null);
    }

}
