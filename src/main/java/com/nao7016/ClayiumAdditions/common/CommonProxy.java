package com.nao7016.ClayiumAdditions.common;

import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;

import com.nao7016.ClayiumAdditions.CAModMain;
import com.nao7016.ClayiumAdditions.Tags;
import com.nao7016.ClayiumAdditions.item.hammer.HammerEvent;
import com.nao7016.ClayiumAdditions.item.hammer.MiningHammerEvent;
import com.nao7016.ClayiumAdditions.item.storagebox.AutoCollect;
import com.nao7016.ClayiumAdditions.network.CANetwork;
import com.nao7016.ClayiumAdditions.plugin.EtOreIntegration;
import com.nao7016.ClayiumAdditions.plugin.InventoryBogoSorter;
import com.nao7016.ClayiumAdditions.plugin.nei.NEIPluginClayiumAdditions;
import com.nao7016.ClayiumAdditions.recipe.CARecipes;
import com.nao7016.ClayiumAdditions.util.UtilAddTier;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import mods.clayium.core.ClayiumCore;
import mods.clayium.gui.CreativeTab;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        CAModMain.LOG.info("Clayium Additions: ver" + Tags.VERSION);
        CANetwork.init();
        if (Config.cfgAddMachines) UtilAddTier.setAddTierManagers();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        CAItems.registerItems();
        CABlocks.registerBlocks();
        if (Config.cfgAddMachines) AddMachines.registerAddMachines();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        CARecipes.register();
        MinecraftForge.EVENT_BUS.register(new HammerEvent());
        MinecraftForge.EVENT_BUS.register(new MiningHammerEvent());
        MinecraftForge.EVENT_BUS.register(new AutoCollect());

        if (Loader.isModLoaded("NotEnoughItems")) {
            NEIPluginClayiumAdditions.registerNEI();
        }

        if (!ClayiumCore.cfgEnableFluidCapsule && ClayiumCore.creativeTabClayiumCapsule != null) {
            ((CreativeTab) ClayiumCore.creativeTabClayiumCapsule).setTabIconItem(Items.bucket);
        }

        if (Loader.isModLoaded("etfuturum")) {
            MinecraftForge.EVENT_BUS.register(new EtOreIntegration());
        }

        if (Loader.isModLoaded("bogosorter")) {
            InventoryBogoSorter.init();
        }
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {

    }
}
