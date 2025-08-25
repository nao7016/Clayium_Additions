package com.nao7016.ClayiumAdditions;

import com.nao7016.ClayiumAdditions.network.storagebox.SBGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nao7016.ClayiumAdditions.common.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = CAModMain.MODID,
    version = Tags.VERSION,
    name = "Clayium Additions",
    dependencies = CAModMain.dependencies,
    acceptedMinecraftVersions = "[1.7.10]")
public class CAModMain {

    public static final String MODID = "clayiumadditions";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final String dependencies = "required-after:Forge@"
        + net.minecraftforge.common.ForgeVersion.majorVersion
        + '.' // majorVersion
        + net.minecraftforge.common.ForgeVersion.minorVersion
        + '.' // minorVersion
        + net.minecraftforge.common.ForgeVersion.revisionVersion
        + '.' // revisionVersion
        + net.minecraftforge.common.ForgeVersion.buildVersion
        + ",);" // buildVersion
        + "required-after:clayium@[0.4.6.36.hotfix2,);" // clayium
        + "after:etfuturum@[2.6.2,)" // Et Futurum Requiem
    ;

    public static final String ChannelName = "clayium_additions";
    public static final int ChannelFlagKey = 0;
    public static final int ChannelFlagGui = 1;


    @SidedProxy(
        clientSide = "com.nao7016.ClayiumAdditions.client.ClientProxy",
        serverSide = "com.nao7016.ClayiumAdditions.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(CAModMain.MODID)
    public static CAModMain instance;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new SBGuiHandler());
        LOG.info("Clayium Additions has loaded!");
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
