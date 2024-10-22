package tkcy.simpleaddon.loaders.recipe;

import gregtech.api.unification.ore.OrePrefix;
import tkcy.simpleaddon.api.recipes.recipemaps.TKCYSARecipeMaps;

import static gregtech.api.unification.material.Materials.CarbonDioxide;
import static gregtech.api.unification.material.Materials.Charcoal;

public class CapabilityContainersTesting {

    public static void test() {

        TKCYSARecipeMaps.HEATING_RECIPES.recipeBuilder()
                .outputHeat(30)
                .input(OrePrefix.dust, Charcoal)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .duration(1000)
                .EUt(1)
                .buildAndRegister();

        TKCYSARecipeMaps.HEATING_RECIPES2.recipeBuilder()
                .inputHeat(30)
                .input(OrePrefix.dust, Charcoal)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .duration(1000)
                .EUt(1)
                .buildAndRegister();
    }
}
