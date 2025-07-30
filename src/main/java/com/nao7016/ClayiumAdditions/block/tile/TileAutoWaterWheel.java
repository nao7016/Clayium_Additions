package com.nao7016.ClayiumAdditions.block.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import mods.clayium.block.tile.TileClayMachines;
import mods.clayium.block.tile.TileWaterWheel;
import mods.clayium.util.UtilDirection;

public class TileAutoWaterWheel extends TileWaterWheel {

    private static final Random random = new Random();

    @Override
    public void func_145845_h() {
        if (!this.worldObj.isRemote && this.progressEfficiency < 500) {
            this.progressEfficiency = 1000;
            this.setSyncFlag(); // クライアントと同期
        }
        if (!this.worldObj.isRemote && random.nextInt(40) < this.countSurroundingWater()) {
            this.progress += this.progressEfficiency * 4;
            this.setSyncFlag();
            if (this.progress >= 100000) {
                this.progress -= this.progress;
                this.progressEfficiency -= random.nextInt(5) == 0 ? 1 : 0;
                this.emitEnergy();
            } else if (this.progress >= progressMax) {
                this.progress -= progressMax;
                this.progressEfficiency -= random.nextInt(5) == 0 ? 1 : 0;
                this.emitEnergy();
            }
        }
    }

    @Override
    public void emitEnergy() {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = UtilDirection.getTileEntity(this.worldObj, this.xCoord, this.yCoord, this.zCoord, dir);
            if (te instanceof TileClayMachines) {
                TileClayMachines machine = (TileClayMachines) te;
                int targetTier = machine.getTier();

                // ここで独自の条件：Tier 2以上のマシンのみにエネルギーを渡す
                if (targetTier == baseTier - 1
                    && machine.clayEnergy < 5.0 * Math.pow(4.0, Math.max(this.baseTier, 1.0))) {
                    machine.clayEnergy += (long) Math.pow(4.0, Math.max(this.baseTier, 1.0));
                    machine.setSyncFlag();
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.progress = tag.getInteger("Progress");
        this.progressEfficiency = tag.getInteger("ProgressEfficiency");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Progress", this.progress);
        tag.setInteger("ProgressEfficiency", this.progressEfficiency);
    }

    @Override
    public int getProgressIcon() {
        return this.progress * 10 / progressMax / 2 == 0 ? 0 : 1;
    }
}
