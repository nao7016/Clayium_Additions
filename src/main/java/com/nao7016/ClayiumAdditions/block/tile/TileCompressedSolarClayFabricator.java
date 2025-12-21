package com.nao7016.ClayiumAdditions.block.tile;

import mods.clayium.block.tile.TileSolarClayFabricator;
import mods.clayium.util.UtilTransfer;
import net.minecraft.item.ItemStack;

public class TileCompressedSolarClayFabricator extends TileSolarClayFabricator {
    protected int maxParallel = 8;
    protected int compressedCounter = 1;

    @Override
    public void initParams() {
        super.initParams();
        this.maxParallel = 8;
        this.compressedCounter = 1;
    }

    @Override
    public void initParamsByTier(int tier) {
        super.initParamsByTier(tier);
    }

    protected int getMaxParallel() {
        return Math.max(1, this.maxParallel ^ Math.max(1, this.compressedCounter));
    }

    public int getCompressedCounter() {
        return this.compressedCounter;
    }

    protected boolean canCraft(int tier, int size) {
        return tier >= 0 && tier <= this.acceptableTier
            ? UtilTransfer.canProduceItemStack(getCompressedClay(tier +1, size), this.containerItemStacks, 1, 2, this.getInventoryStackLimit()) >= 1
            : false;
    }

    @Override
    public boolean canProceedCraft() {
        for (int y = 255; y > this.yCoord; --y) {
            if (this.getWorldObj().getBlockLightOpacity(this.xCoord, y, this.zCoord) > 0) {
                return false;
            }
        }
        if (this.containerItemStacks[2] == null) {
            if (this.containerItemStacks[0] == null) return false;
            int tier = this.getTierOfCompressedClay(this.containerItemStacks[0], false);
            int size = Math.min(this.containerItemStacks[0].stackSize, getMaxParallel());
            return canCraft(tier, size);
        } else {
            int tier = getTierOfCompressedClay(this.containerItemStacks[2]);
            int size = this.containerItemStacks[2].stackSize;
            return canCraft(tier, size);
        }
    }

    @Override
    public void proceedCraft() {
        if (this.containerItemStacks[2] == null) {
            int tier = this.getTierOfCompressedClay(this.containerItemStacks[0]);
            int size = Math.min(this.containerItemStacks[0].stackSize, getMaxParallel());
            this.machineTimeToCraft = (long) (Math.pow((double) this.baseCraftTime, (double) tier * (double) this.multCraftTime));
            this.containerItemStacks[2] = this.containerItemStacks[0].splitStack(size);
            if (this.containerItemStacks[0].stackSize <= 0) {
                this.containerItemStacks[0] = null;
            }
        }

        ++this.machineCraftTime;
        this.isDoingWork = true;

        int tier2 = getTierOfCompressedClay(this.containerItemStacks[2]);
        int size2 = this.containerItemStacks[2].stackSize;
        this.clayEnergy = (long) (Math.pow(10.0D, (double) (tier2 + 1)) * (double) size2 * (double) this.machineCraftTime / (double) this.machineTimeToCraft);
        if (this.machineCraftTime >= this.machineTimeToCraft) {
            this.clayEnergy = 0L;
            ItemStack result = getCompressedClay(getTierOfCompressedClay(this.containerItemStacks[2]) + 1, this.containerItemStacks[2].stackSize);
            this.containerItemStacks[2] = null;
            UtilTransfer.produceItemStack(result, this.containerItemStacks, 1, 2, this.getInventoryStackLimit());
            this.machineCraftTime = 0L;
            if (this.externalControlState > 0) {
                --this.externalControlState;
                if (this.externalControlState == 0) {
                    this.externalControlState = -1;
                }
            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return slot == 0 ? getTierOfCompressedClay(itemStack) >= 0 && getTierOfCompressedClay(itemStack) <= this.acceptableTier : true;
    }
}
