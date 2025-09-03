package com.nao7016.ClayiumAdditions.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean cfgSplittedEnergeticClayEnabled;
    public static boolean cfgAutoWaterWheelEnabled;
    public static boolean cfgStorageBox;
    public static boolean cfgTransform;
    public static boolean cfgMiningHammer;
    public static boolean cfgModeSky;
    public static boolean cfgEtFuturum;
    public static boolean cfgAddMachines;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        cfgSplittedEnergeticClayEnabled = configuration.getBoolean(
            "EnableSplittedEnergeticClay",
            "item",
            true,
            "If true, Splitted Energetic Clay will be enabled.");
        cfgAutoWaterWheelEnabled = configuration
            .getBoolean("EnableAutoWaterWheel", "item", true, "If true, Auto Water Wheel will be enabled.");
        cfgStorageBox = configuration
            .getBoolean("EnableStorageBox", "item", true, "If true, Storage Box will be enabled.");
        cfgAddMachines = configuration.getBoolean(
            "EnableAdditionalMachines",
            "item",
            false,
            "If true, additional tiers Machines will be enabled. (ex. tier 6,7 Condenser) Mainly for modpacks.");
        cfgTransform = configuration.getBoolean(
            "EnableAdditionalTransformerRecipe",
            "recipe",
            true,
            "If true, additional Transformer recipes will be available.");
        cfgMiningHammer = configuration
            .getBoolean("EnableMiningHammer", "item", true, "If true, Clay Mining Hammer will be enabled.");
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
