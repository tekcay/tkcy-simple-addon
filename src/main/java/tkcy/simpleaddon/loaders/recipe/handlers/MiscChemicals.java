package tkcy.simpleaddon.loaders.recipe.handlers;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static tkcy.simpleaddon.api.TKCYSAValues.SECOND;
import static tkcy.simpleaddon.api.unification.materials.TKCYSAMaterials.*;

import tkcy.simpleaddon.api.recipes.TKCYSARecipeMaps;

public class MiscChemicals {

    public static void init() {
        // PotassiumHydroxide
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .EUt(100)
                .input(dust, RockSalt, 2)
                .fluidInputs(DistilledWater.getFluid(2000))
                .output(dust, PotassiumHydroxide, 2)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .buildAndRegister();

        // MetaBisulfite
        MIXER_RECIPES.recipeBuilder().duration(100)
                .EUt(100)
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .input(dust, PotassiumHydroxide)
                .output(dust, PotassiumMetaBisulfite)
                .buildAndRegister();

        // Sodium nitrite production
        // 2 NaOH + NO2 + NO -> 2 NaNO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .duration(SECOND * 60)
                .EUt(VA[LV])
                .notConsumable(DistilledWater.getFluid(4000))
                .input(dust, SodiumHydroxide, 2)
                .fluidInputs(NitrogenDioxide.getFluid(1000))
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidOutputs(SodiumNitriteSolution.getFluid(2000))
                .buildAndRegister();

        TKCYSARecipeMaps.DRYING.recipeBuilder()
                .duration(SECOND * 40)
                .EUt(VA[MV])
                .fluidInputs(SodiumNitriteSolution.getFluid(1000))
                .fluidOutputs(Steam.getFluid(200))
                .output(dust, SodiumNitrite)
                .buildAndRegister();

        // 2 NaOH + N2O3 -> 2 NaNO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .duration(SECOND * 60)
                .EUt(VA[LV])
                .notConsumable(DistilledWater.getFluid(4000))
                .input(dust, SodiumHydroxide, 2)
                .fluidInputs(LiquidDinitrogenTrioxide.getFluid(1000))
                .fluidOutputs(SodiumNitriteSolution.getFluid(2000))
                .buildAndRegister();

        // NO + NO2 -> N2O3
        VACUUM_RECIPES.recipeBuilder()
                .duration(SECOND * 60)
                .EUt(VA[MV])
                .fluidInputs(NitrogenDioxide.getFluid(1000))
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidOutputs(LiquidDinitrogenTrioxide.getFluid(1000))
                .buildAndRegister();
    }
}
