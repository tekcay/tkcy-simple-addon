package tkcy.simpleaddon.api.unification.materials;

import gregtech.api.unification.material.Material;

import tkcy.simpleaddon.api.unification.MaterialsFormula;
import tkcy.simpleaddon.api.unification.RoastingMaterials;
import tkcy.simpleaddon.api.unification.TKCYSAFirstDegreeMaterials;
import tkcy.simpleaddon.api.unification.iconset.MaterialIconAddition;
import tkcy.simpleaddon.api.unification.materials.chains.*;
import tkcy.simpleaddon.api.unification.materials.other.MiscMaterials;
import tkcy.simpleaddon.api.unification.properties.PropertiesAddition;

public class TKCYSAMaterials {

    public static void init() {
        // 4000 - 4200
        TKCYSAFirstDegreeMaterials.init();

        // 4201
        int id = 4200;
        id = RoastingMaterials.init(id);
        id = AluminiumChainMaterials.init(id);
        id = GoldChainMaterials.init(id);
        id = ChromiteChainMaterials.init(id);
        id = ZincChainMaterials.init(id);
        id = GermaniumChainMaterials.init(id);
        id = FluorineChainMaterials.init(id);
        id = ManganeseChainMaterials.register(id);
        id = SmallChains.init(id);

        id = AlloysMaterials.init(id);

        id = MiscMaterials.register(id);

        MaterialsFormula.init();

        PropertiesAddition.init();

        MaterialIconAddition.init();
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

    // FluorineChain
    public static Material PotassiumBifluoride;
    public static Material Fluorite;

    /**
     * Used in
     * Fireproofing:
     * Fire-Resistant Materials: Calcium sulfate can contribute to the fire resistance of certain materials, making it
     * useful in applications where fireproofing is essential.
     */
    public static Material CalciumSulfate;
    public static Material LithiumFluoride;
    public static Material SodiumFluoride;
    public static Material PotassiumFluoride;
    public static Material LithiumHydroxide;
    public static Material HydrogenFluoride;

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

    // Zinc chain
    public static Material ZincSulfate;
    public static Material ZincLeachingSolution;

    // Germanium chain
    public static Material ZincLeachingResidue;
    public static Material GermanicAcid;
    public static Material IronSulfate;
    public static Material GermaniumSulfide;
    public static Material GermaniumOxide;
    public static Material GermaniumTetrachloride;
    public static Material Argyrodite;

    // BauxiteChain
    public static Material PotassiumAluminate;
    public static Material SodiumAluminate;
    public static Material TreatedPotassiumAluminate;
    public static Material TreatedSodiumAluminate;
    public static Material BauxiteResidue;
    public static Material PotashTreatedBauxite;
    public static Material SodaTreatedBauxite;
    public static Material AluminiumFluoride;
    public static Material DriedTreatedSodiumAluminate;
    public static Material DriedTreatedPotassiumAluminate;
    public static Material Cryolite;
    public static Material HexafluorosilicAcid;
    public static Material AluminiumHydroxide;

    // Manganese chain
    public static Material TreatedSpessartine;
    public static Material OxalicAcid;
    public static Material CrudeOxalicAcid;
    public static Material DryOxalicAcid;
    public static Material CrudeDimethylOxalate;
    public static Material CrudeDiethylOxalate;
    public static Material DimethylOxalate;
    public static Material DiethylOxalate;

    // Alloys
    public static Material GalvanizedSteel;
    public static Material Monel;
    public static Material BT6;
    public static Material Mangalloy;
    public static Material Talonite;
    public static Material HastelloyN;
    public static Material Inconel600;
    public static Material Inconel690;
    public static Material TC4;

    // Misc
    public static Material Ceramic;
    public static Material MicaPulp;
}
