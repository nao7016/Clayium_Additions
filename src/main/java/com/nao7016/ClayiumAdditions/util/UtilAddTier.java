package com.nao7016.ClayiumAdditions.util;

import mods.clayium.util.UtilTier;

public class UtilAddTier extends UtilTier {

    public static void setAddTierManagers() {
        tierGeneric = UtilTier.TierManager.getMachineTierManager("MachineBase_Crafting");
        tierSmelter = UtilTier.TierManager.getMachineTierManager("MachineSmelter_Crafting");
        tierCACondenser = UtilTier.TierManager.getMachineTierManager("MachineCACondenser_Crafting");
        for (int tier = 0; tier <= 13; tier++) {
            // tier generic
            if (7 <= tier && tier < 10) {
                tierGeneric.get("multCraftTime").put(tier, 0.0625F);
                tierGeneric.get("multConsumingEnergy").put(tier, 25.0F);
            } else if (11 <= tier) {
                tierGeneric.get("multCraftTime").put(tier, 0.01F);
                tierGeneric.get("multConsumingEnergy").put(tier, 250.0F);
            }

            // tier smelter
            if (10 <= tier) {
                tierSmelter.get("multCraftTime").put(tier, 0.0025F);
                tierSmelter.get("multConsumingEnergy").put(tier, 560000.0F);
            }

            // tier ca condenser
            if (12 <= tier) {
                tierCACondenser.get("multCraftTime").put(tier, 0.01F);
                tierCACondenser.get("multConsumingEnergy").put(tier, 100.0F);
            }
        }
    }
}
