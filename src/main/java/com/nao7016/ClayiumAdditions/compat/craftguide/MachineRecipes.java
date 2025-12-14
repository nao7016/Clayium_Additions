package com.nao7016.ClayiumAdditions.compat.craftguide;

import net.minecraft.item.ItemStack;

import mods.clayium.block.CBlocks;
import uristqwerty.CraftGuide.api.ConstructedRecipeTemplate;
import uristqwerty.CraftGuide.api.CraftGuideAPIObject;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplateBuilder;

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
