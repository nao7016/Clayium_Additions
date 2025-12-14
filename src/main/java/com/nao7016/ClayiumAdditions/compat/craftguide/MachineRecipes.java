package com.nao7016.ClayiumAdditions.compat.craftguide;

import net.minecraft.item.ItemStack;

import mods.clayium.block.CBlocks;
import uristqwerty.CraftGuide.api.*;

public class MachineRecipes extends CraftGuideAPIObject implements RecipeProvider {

    @Override
    public void generateRecipes(RecipeGenerator generator) {
        ItemStack bendingMachine = new ItemStack(CBlocks.blocksBendingMachine[4]);

        ConstructedRecipeTemplate bendingMachineRecipe = generator.buildTemplate(bendingMachine)
            .item()
            .nextColumn(1)
            .machineItem()
            .nextColumn(1)
            .nextSlotType(RecipeTemplateBuilder.TemplateBuilderSlotType.OUTPUT)
            .item()
            .finishTemplate();
    }
}
