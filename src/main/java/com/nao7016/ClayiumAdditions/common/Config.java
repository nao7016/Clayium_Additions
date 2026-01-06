package com.nao7016.ClayiumAdditions.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean cfgSplitEnergeticClay;
    public static boolean cfgAutoWaterWheel;
    public static boolean cfgMiningHammer;
    public static boolean cfgAddMachines;

    public static boolean cfgStorageBox;
    public static boolean cfgSBMoreDisplay;
    public static boolean cfgSBSIPrefix;

    public static boolean cfgTransform;
    public static boolean cfgQoLRecipe;

    public static boolean cfgModeSky;
    public static boolean cfgEtFuturum;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        cfgSplitEnergeticClay = configuration.getBoolean(
            "EnableSplitEnergeticClay",
            "item",
            true,
            "If true, Split Energetic Clay will be enabled.");
        cfgAutoWaterWheel = configuration
            .getBoolean("EnableAutoWaterWheel", "item", true, "If true, Auto Water Wheel will be enabled.");
        cfgMiningHammer = configuration
            .getBoolean("EnableMiningHammer", "item", true, "If true, Clay Mining Hammer will be enabled.");
        cfgAddMachines = configuration.getBoolean(
            "EnableAdditionalTieredMachines",
            "item",
            false,
            "If true, additional tiered Machines will be enabled. (ex. tier 6,7 Condenser) Mainly for modpacks.");
        cfgStorageBox = configuration
            .getBoolean("EnableStorageBox", "storagebox", true, "If true, Storage Box will be enabled.");
        cfgSBMoreDisplay = configuration.getBoolean(
            "EnableStorageBoxMoreDisplay",
            "storagebox",
            false,
            "If true, Storage Box show item amount and auto collection.");
        cfgSBSIPrefix = configuration.getBoolean(
            "UsingSIPrefixes",
            "storagebox",
            true,
            "If true, over 1,000 amount will be shorten and use SI prefixes.");
        cfgTransform = configuration.getBoolean(
            "EnableAdditionalTransformerRecipe",
            "recipe",
            true,
            "If true, additional Transformer recipes will be available.");
        cfgQoLRecipe = configuration
            .getBoolean("EnableQoLRecipes", "recipe", false, "If true, added some QoL recipes.");
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
