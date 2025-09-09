package com.nao7016.ClayiumAdditions.util;

import java.lang.ref.WeakReference;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import mods.clayium.block.tile.TileClayCraftingTable;
import mods.clayium.util.UtilDirection;
import mods.clayium.util.UtilTransfer;

public class UtilInventory {

    public static class EnhancedSelector extends UtilTransfer.InventorySelector {

        public WeakReference<IInventory> doubleChest;
        public boolean doubleFirst;

        @Override
        public IInventory selectInventoryToInsertTo(World world, int fromX, int fromY, int fromZ,
            ForgeDirection direction) {
            TileEntity tile = UtilDirection.getTileEntity(world, fromX, fromY, fromZ, direction);
            if (tile instanceof IInventory to && !(tile instanceof TileClayCraftingTable)) {
                if (tile instanceof TileEntityChest) {
                    checkDoubleChest(world, tile, 1, 0);
                    checkDoubleChest(world, tile, -1, 0);
                    checkDoubleChest(world, tile, 0, 1);
                    checkDoubleChest(world, tile, 0, -1);
                }
            }
            return null;
        }

        void checkDoubleChest(World world, TileEntity tile, int dx, int dz) {
            this.checkDoubleChest(world, tile.xCoord, tile.yCoord, tile.zCoord, dx, dz);
        }

        void checkDoubleChest(World world, int x, int y, int z, int dx, int dz) {
            TileEntity tile = world.getTileEntity(x + dx, y, z + dz);
            if (tile instanceof TileEntityChest) {
                doubleChest = new WeakReference<>((IInventory) tile);
                doubleFirst = dx + dz < 0;
            }
        }
    }
}
