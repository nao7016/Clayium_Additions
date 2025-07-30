package com.nao7016.ClayiumAdditions.common;

import com.nao7016.ClayiumAdditions.CAModMain;
import com.nao7016.ClayiumAdditions.Tags;
import com.nao7016.ClayiumAdditions.plugin.nei.NEIPluginClayiumAdditions;
import com.nao7016.ClayiumAdditions.recipe.CARecipes;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        CAModMain.LOG.info(Config.greeting);
        CAModMain.LOG.info("I am MyMod at version " + Tags.VERSION);

        CAItems.registerItems();
        CABlocks.registerBlocks();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {

    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        CARecipes.register();
        if (Loader.isModLoaded("NotEnoughItems")) {
            NEIPluginClayiumAdditions.registerNEI();
        }
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {

    }
}
