package tkcy.simpleaddon.api.unification;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static gregtech.api.util.GTUtility.gregtechId;
import static tkcy.simpleaddon.api.unification.materials.TKCYSAMaterials.*;

import gregtech.api.unification.material.Material;

public final class TKCYSAFirstDegreeMaterials {

    private TKCYSAFirstDegreeMaterials() {}

    public static void init() {
        PotassiumBisulfate = new Material.Builder(4008, gregtechId("potassium_bisulfate"))
                .dust()
                .iconSet(DULL)
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumMetaBisulfite = new Material.Builder(4009, gregtechId("potassium_metabisulfite"))
                .dust()
                .iconSet(DULL)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .colorAverage()
                .build();

        PotassiumHydroxide = new Material.Builder(4011, gregtechId("potassium_hydroxide"))
                .dust()
                .components(Potassium, 1, Oxygen, 1, Hydrogen, 1)
                .colorAverage()
                .build();

        IronSulfate = new Material.Builder(4018, gregtechId("iron_sulfate"))
                .dust()
                .components(Iron, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();
    }
}
