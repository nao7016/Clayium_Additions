package com.nao7016.ClayiumAdditions.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean cfgSplittedEnergeticClay;
    public static boolean cfgAutoWaterWheel;
    public static boolean cfgMiningHammer;
    public static boolean cfgAddMachines;

    public static boolean cfgStorageBox;
    public static boolean cfgSBMoreDisplay;

    public static boolean cfgTransform;

    public static boolean cfgModeSky;
    public static boolean cfgEtFuturum;


    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        cfgSplittedEnergeticClay = configuration.getBoolean(
            "EnableSplittedEnergeticClay",
            "item",
            true,
            "If true, Splitted Energetic Clay will be enabled.");
        cfgAutoWaterWheel = configuration
            .getBoolean("EnableAutoWaterWheel", "item", true, "If true, Auto Water Wheel will be enabled.");
        cfgMiningHammer = configuration
            .getBoolean("EnableMiningHammer", "item", true, "If true, Clay Mining Hammer will be enabled.");
        cfgAddMachines = configuration.getBoolean(
            "EnableAdditionalMachines",
            "item",
            false,
            "If true, additional tiers Machines will be enabled. (ex. tier 6,7 Condenser) Mainly for modpacks.");
        cfgStorageBox = configuration
            .getBoolean("EnableStorageBox", "storagebox", true, "If true, Storage Box will be enabled.");
        cfgSBMoreDisplay = configuration.getBoolean("EnableStorageBoxMoreDisplay", "storagebox", false, "If true, Storage Box show item amount and auto collection.");
        cfgTransform = configuration.getBoolean(
            "EnableAdditionalTransformerRecipe",
            "recipe",
            true,
            "If true, additional Transformer recipes will be available.");
        cfgModeSky = configuration.getBoolean(
            "SkyMode",
            "mode",
            false,
            "If true, added recipes for skyblock. This mode is mainly for modpacks.");
        cfgEtFuturum = configuration
            .getBoolean("Et Futurum Requiem", "integration", true, "Added Deepslate Ores and Raw Ores.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
