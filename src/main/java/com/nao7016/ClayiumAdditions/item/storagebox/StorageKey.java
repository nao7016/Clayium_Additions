package com.nao7016.ClayiumAdditions.item.storagebox;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class StorageKey {

    public static KeyBinding StorageKey;

    public static void set() {
        StorageKey = new KeyBinding(
            "key.clayiumadditions.storagebox",
            Keyboard.KEY_L,
            "itemGroup.clayiumAdditionsTabs");
        ClientRegistry.registerKeyBinding(StorageKey);
        FMLCommonHandler.instance()
            .bus()
            .register(new StorageKeyEvent());
    }
}
