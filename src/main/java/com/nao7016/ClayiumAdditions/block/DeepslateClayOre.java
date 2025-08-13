package com.nao7016.ClayiumAdditions.block;

import net.minecraft.block.Block;

import mods.clayium.block.ClayOre;

public class DeepslateClayOre extends ClayOre {

    public DeepslateClayOre() {
        super();
        this.setBlockTextureName("clayiumadditions:deepslateclayore");
        this.setHardness(4.5F);
        this.setResistance(7.5F);
        this.setStepSound(Block.soundTypeStone);

        this.setHarvestLevel("pickaxe", 1);
    }
}
