package com.nao7016.ClayiumAdditions.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import mods.clayium.item.IHarvestCoord;

public class HarvestCoord3x3 implements IHarvestCoord {

    public HarvestCoord3x3() {}

    @Override
    public List<Vec3> getHarvestedCoordList(ItemStack itemStack, int x, int y, int z, Vec3 xxVector, Vec3 yyVector,
        Vec3 zzVector) {
        List<Vec3> result = new ArrayList<>();

        for (int yy = -1; yy <= 1; ++yy) {
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = 0; zz <= 0; ++zz) {
                    result.add(
                        Vec3.createVectorHelper(
                            (double) x + xxVector.xCoord * (double) xx
                                + yyVector.xCoord * (double) yy
                                + zzVector.xCoord * (double) zz,
                            (double) y + xxVector.yCoord * (double) xx
                                + yyVector.yCoord * (double) yy
                                + zzVector.yCoord * (double) zz,
                            (double) z + xxVector.zCoord * (double) xx
                                + yyVector.zCoord * (double) yy
                                + zzVector.zCoord * (double) zz));
                }
            }
        }
        return result;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int i, int i1, int i2, int i3,
        float v, float v1, float v2) {
        return false;
    }
}
