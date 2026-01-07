package com.nao7016.ClayiumAdditions.item;

import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

import com.nao7016.ClayiumAdditions.common.CABlocks;
import com.nao7016.ClayiumAdditions.common.CATabs;
import com.nao7016.ClayiumAdditions.recipe.CrushRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.block.CBlocks;
import mods.clayium.util.UtilLocale;

public class ItemClayHammer extends ItemPickaxe {

    protected float efficiencyOnClayBlocks = 40.0F;
    private float efficiencyOnClayOre = 15.0F;

    public ItemClayHammer() {
        super(ToolMaterial.STONE);
        this.setMaxDamage(300);
        this.setCreativeTab(CATabs.ca_tabs);
        this.setUnlocalizedName("clay_hammer");
        this.setTextureName("clayiumadditions:clay_hammer");
    }

    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (block.getMaterial() == Material.clay) {
            return this.efficiencyOnClayBlocks;
        } else if (block == CBlocks.blockClayOre || block == CABlocks.blockDeepslateClayOre) {
            return block.getHarvestLevel(meta) <= Objects.requireNonNull(stack.getItem())
                .getHarvestLevel(stack, block.getHarvestTool(meta)) ? this.efficiencyOnClayOre
                    : this.efficiencyOnClayOre * 100.0F / 30.0F;
        } else if (CrushRecipes.isHammerTarget(block)) {
            return ToolMaterial.IRON.getEfficiencyOnProperMaterial();
        } else {
            return ForgeHooks.isToolEffective(stack, block, meta) ? this.efficiencyOnProperMaterial
                : super.getDigSpeed(stack, block, meta);
        }
    }

    /*
     * @Override
     * public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_)
     * {
     * return false;
     * }
     * @Override
     * public boolean hasContainerItem(ItemStack stack) {
     * return true;
     * }
     * @Override
     * public ItemStack getContainerItem(ItemStack stack) {
     * ItemStack copy = stack.copy();
     * copy.setItemDamage(copy.getItemDamage() + 1);
     * if (copy.getItemDamage() >= copy.getMaxDamage()) {
     * return null;
     * }
     * return copy;
     * }
     */

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag) {
        super.addInformation(itemstack, player, list, flag);
        List alist = UtilLocale.localizeTooltip(this.getUnlocalizedName(itemstack) + ".tooltip");
        if (alist != null) {
            list.addAll(alist);
        }
    }
}
