package tkcy.tktech.api.unification;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static tkcy.tktech.api.unification.materials.TkTechMaterials.*;
import static tkcy.tktech.api.utils.TkTechUtil.tktech;

import gregtech.api.unification.material.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class TkTechFirstDegreeMaterials {

    public static void init() {
        PotassiumBisulfate = new Material.Builder(4008, tktech("potassium_bisulfate"))
                .dust()
                .iconSet(DULL)
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4)
                .colorAverage()
                .build();

        PotassiumMetaBisulfite = new Material.Builder(4009, tktech("potassium_metabisulfite"))
                .dust()
                .iconSet(DULL)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .colorAverage()
                .build();

        PotassiumHydroxide = new Material.Builder(4011, tktech("potassium_hydroxide"))
                .dust()
                .components(Potassium, 1, Oxygen, 1, Hydrogen, 1)
                .colorAverage()
                .build();

        IronSulfate = new Material.Builder(4018, tktech("iron_sulfate"))
                .dust()
                .components(Iron, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();
    }
}
