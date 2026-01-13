package com.nao7016.ClayiumAdditions.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.nao7016.ClayiumAdditions.common.CATabs;
import com.nao7016.ClayiumAdditions.util.HarvestCoord3x3;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.item.IAdvancedTool;
import mods.clayium.item.IHarvestCoord;
import mods.clayium.util.UtilAdvancedTools;
import mods.clayium.util.UtilLocale;

public class Item3x3Hammer extends ItemPickaxe implements IAdvancedTool {

    private IHarvestCoord harvestCoord;

    public Item3x3Hammer() {
        super(ToolMaterial.IRON);
        this.setMaxDamage(1000);
        this.setCreativeTab(CATabs.ca_tabs);
        this.setUnlocalizedName("clay_3x3_hammer");
        this.setTextureName("clayiumadditions:3x3hammer");
        this.harvestCoord = new HarvestCoord3x3();
    }

    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        return super.getDigSpeed(stack, block, meta) * 2.0F;
    }

    @Override
    public IHarvestCoord getHarvestCoord() {
        return this.harvestCoord;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z,
        EntityLivingBase entity) {
        boolean result = super.onBlockDestroyed(stack, world, block, x, y, z, entity);
        stack.damageItem(UtilAdvancedTools.onBlockDestroyed(stack, world, block, x, y, z, entity), entity);
        return result;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        super.addInformation(stack, player, list, flag);
        List alist = UtilLocale.localizeTooltip(this.getUnlocalizedName(stack) + ".tooltip");
        if (alist != null) {
            list.addAll(alist);
        }
    }

}
