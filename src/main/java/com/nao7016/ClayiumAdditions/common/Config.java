package com.nao7016.ClayiumAdditions.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    // item
    public static boolean cfgSplittedEnergeticClay;
    public static boolean cfgAutoWaterWheel;
    public static boolean cfgMiningHammer;
    public static boolean cfgStorageBox;
    // recipe
    public static boolean cfgTransform;
    // mode
    public static boolean cfgModeSky;
    // integration
    public static boolean cfgEtFuturum;


    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        cfgSplittedEnergeticClay = configuration.getBoolean(
            "EnableSplittedEnergeticClay",
            "item",
            true,
            "If true, Splitted Energetic Clay will be enabled.");
        cfgAutoWaterWheel = configuration.getBoolean(
            "EnableAutoWaterWheel",
            "item",
            true,
            "If true, Auto Water Wheel will be enabled.");
        cfgMiningHammer = configuration.getBoolean(
            "EnableMiningHammer",
            "item",
            true,
            "If true, Clay Mining Hammer will be enabled.");
        cfgStorageBox = configuration.getBoolean(
            "EnabledStorageBox",
            "item",
            true,
            "If true, Storage Box will be enabled.");

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

        cfgEtFuturum = configuration.getBoolean(
            "Et Futurum Requiem",
            "integration",
            true,
            "Added Deepslate Ores and Raw Ores.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
