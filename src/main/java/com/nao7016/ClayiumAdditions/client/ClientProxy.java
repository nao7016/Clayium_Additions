package com.nao7016.ClayiumAdditions.client;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.nao7016.ClayiumAdditions.common.CAItems;
import com.nao7016.ClayiumAdditions.common.CommonProxy;
import com.nao7016.ClayiumAdditions.event.IDChecker;
import com.nao7016.ClayiumAdditions.item.storagebox.StorageKey;
import com.nao7016.ClayiumAdditions.item.storagebox.StorageRenderer;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForgeClient.registerItemRenderer(CAItems.storageBox, new StorageRenderer());
        MinecraftForgeClient.registerItemRenderer(CAItems.clayStorageBox, new StorageRenderer());
        StorageKey.set();

        if (!(Loader.isModLoaded("inventorytweaks"))) {
            MinecraftForge.EVENT_BUS.register(new IDChecker());
        }
    }
}
