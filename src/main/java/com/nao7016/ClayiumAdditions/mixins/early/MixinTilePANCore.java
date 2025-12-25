package com.nao7016.ClayiumAdditions.mixins.early;

import static mods.clayium.util.crafting.CRecipes.i;

import java.util.Set;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.nao7016.ClayiumAdditions.common.CAItems;

import mods.clayium.block.tile.TilePANCore;

@Mixin(value = TilePANCore.class, remap = false)
public class MixinTilePANCore {

    @ModifyVariable(method = "refreshItemSet", at = @At("STORE"), name = "prohibiteds", require = 1)
    private Set<TilePANCore.ItemStackWithEnergy> addProhibited(Set<TilePANCore.ItemStackWithEnergy> prohibiteds) {

        for (int tier = 4; tier <= 12; tier++) {
            ItemStack energyClay = i(CAItems.clayEnergy, 1, tier);
            prohibiteds.add(new TilePANCore.ItemStackWithEnergy(energyClay, 0, 0));
        }

        return prohibiteds;
    }
}
