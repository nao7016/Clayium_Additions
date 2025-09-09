package com.nao7016.ClayiumAdditions.item.hammer;

import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

import com.nao7016.ClayiumAdditions.common.CABlocks;
import com.nao7016.ClayiumAdditions.common.CATabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.CBlocks;
import mods.clayium.util.UtilLocale;

public class itemClayMiningHammer extends ItemPickaxe {

    private float efficiencyOnClayOre = 40.0F;

    public itemClayMiningHammer() {
        super(ToolMaterial.IRON);
        this.setMaxDamage(400);
        this.setCreativeTab(CATabs.ca_tabs);
        this.setUnlocalizedName("clay_mining_hammer");
        this.setTextureName("clayiumadditions:clay_mining_hammer");
    }

    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (block == CBlocks.blockClayOre || block == CABlocks.blockDeepslateClayOre) {
            return block.getHarvestLevel(meta) <= Objects.requireNonNull(stack.getItem())
                .getHarvestLevel(stack, block.getHarvestTool(meta)) ? this.efficiencyOnClayOre
                    : this.efficiencyOnClayOre * 100.0F / 30.0F;
        } else {
            return ForgeHooks.isToolEffective(stack, block, meta) ? this.efficiencyOnProperMaterial
                : super.getDigSpeed(stack, block, meta);
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag) {
        super.addInformation(itemstack, player, list, flag);
        List alist = UtilLocale.localizeTooltip(this.getUnlocalizedName(itemstack) + ".tooltip");
        if (alist != null) {
            list.addAll(alist);
        }
    }
}
