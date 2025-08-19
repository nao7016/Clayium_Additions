package com.nao7016.ClayiumAdditions.plugin.nei;

import com.nao7016.ClayiumAdditions.common.Config;

public class NEIPluginClayiumAdditions {

    public static void registerNEI() {
        NEIClayiumCatalyst.registerMachineCatalysts();
        NEIClayiumAdditionsCatalyst.registerHammerCatalysts();
        registerHammer();
    }

    private static void registerHammer() {
        if (Config.cfgModeSky) {
            codechicken.nei.api.API.registerRecipeHandler(new NEICrushHandler());
            codechicken.nei.api.API.registerUsageHandler(new NEICrushHandler());
        }
    }
}
