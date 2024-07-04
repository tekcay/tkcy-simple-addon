package tkcy.simpleaddon.api.unification.materials.other;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static tkcy.simpleaddon.api.unification.materials.TKCYSAMaterials.*;

import gregtech.api.unification.material.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MethaneCracking {

    public static int register(int startId) {
        LightlySteamCrackedMethane = new Material.Builder(startId++, gregtechId("lightly_steam_cracked_methane"))
                .fluid()
                .components(Hydrogen, 3, CarbonMonoxide, 2, DistilledWater, 2)
                .colorAverage()
                .build();

        ModeratelySteamCrackedMethane = new Material.Builder(startId++, gregtechId("moderately_steam_cracked_methane"))
                .fluid()
                .components(Hydrogen, 6, CarbonMonoxide, 1, DistilledWater, 2)
                .colorAverage()
                .build();

        SeverelySteamCrackedMethane = new Material.Builder(startId++, gregtechId("severely_steam_cracked_methane"))
                .fluid()
                .components(Hydrogen, 4, CarbonDioxide, 1, DistilledWater, 4)
                .colorAverage()
                .build();

        LightlySteamCrackedMethane.setFormula("");
        ModeratelySteamCrackedMethane.setFormula("");
        SeverelySteamCrackedMethane.setFormula("");

        return startId;
    }
}
