package com.nao7016.ClayiumAdditions.compat.nei;

import com.nao7016.ClayiumAdditions.common.Config;

import codechicken.nei.api.API;

public class NEIPluginClayiumAdditions {

    public static void registerNEI() {
        NEIClayiumCatalyst.registerMachineCatalysts();
        NEIClayiumAdditionsCatalyst.registerHammerCatalysts();
        registerHammer();
    }

    private static void registerHammer() {
        if (Config.cfgModeSky) {
            API.registerRecipeHandler(new NEICrushHandler());
            API.registerUsageHandler(new NEICrushHandler());
        }
    }
}
