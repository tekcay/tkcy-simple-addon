package tkcy.simpleaddon.api.unification;

import gregtech.api.unification.material.Material;

public class TKCYSAMaterials {

    public static void init() {
        // 4000 - 4200
        TKCYSAFirstDegreeMaterials.init();

        // 4201
        TKCYSASecondDegreeMaterials.init();

        TKCYSAMaterialFlagAddition.init();

        RoastingMaterials.init(4400);
    }

    // Chromite chain
    public static Material SodiumChromate;
    public static Material SodiumDichromate;
    public static Material ChromiumOxide;
    public static Material SodiumSulfate;
    public static Material Alumina;

    // Gold chain
    public static Material PreciousMetal;
    public static Material GoldAlloy;
    public static Material GoldLeach;
    public static Material CopperLeach;
    public static Material ChloroauricAcid;
    public static Material PotassiumBisulfate;
    public static Material PotassiumMetaBisulfite;
    public static Material PotassiumHydroxide;

    // Iron
    public static Material PigIron;

    // Tungsten Chain
    public static Material TungstenOxide;

    // Roasting
    public static Material Kesterite;
    public static Material Stannite;
    public static Material Arsenopyrite;
    public static Material MolybdenumTrioxide;

    public static Material RoastedTetrahedrite;
    public static Material RoastedCobaltite;
    public static Material RoastedGalena;
    public static Material SilverOxide;
    public static Material RoastedChalcopyrite;
    public static Material RoastedKesterite;
    public static Material RoastedStannite;
    public static Material RoastedArsenopyrite;
    public static Material RoastedBornite;
}
