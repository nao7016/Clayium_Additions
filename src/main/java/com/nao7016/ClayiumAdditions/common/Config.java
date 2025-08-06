package com.nao7016.ClayiumAdditions.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";
    public static boolean cfgSplittedEnergeticClayEnabled;
    public static boolean cfgAutoWaterWheelEnabled;
    public static boolean cfgFishesTransform;
    public static boolean cfgCalciumChlorideTransform;
    public static boolean cfgModeSky;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");

        cfgSplittedEnergeticClayEnabled = configuration.getBoolean(
            "EnableSplittedEnergeticClay",
            "item",
            true,
            "If true, Splitted Energetic Clay will be enabled.");
        cfgAutoWaterWheelEnabled = configuration
            .getBoolean("EnableAutoWaterWheel", "item", true, "If true, Auto Water Wheel will be enabled.");
        cfgFishesTransform = configuration.getBoolean(
            "EnableTransformerFishRecipe",
            "recipe",
            true,
            "If true, you can craft fish with Transformer by chicken.");
        cfgCalciumChlorideTransform = configuration.getBoolean(
            "EnableTransformerCalciumChlorideRecipe",
            "recipe",
            true,
            "If true, Calcium Chloride can be transformed into Impure Calcium.");
        cfgModeSky = configuration.getBoolean(
            "SkyMode",
            "mode",
            false,
            "If true, added recipes for skyblock. This mode is mainly for modpacks.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
