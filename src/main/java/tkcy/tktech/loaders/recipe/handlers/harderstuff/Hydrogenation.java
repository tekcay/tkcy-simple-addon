package tkcy.tktech.loaders.recipe.handlers.harderstuff;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static tkcy.tktech.modules.PetroChemModule.desulfurizedFuels;
import static tkcy.tktech.modules.PetroChemModule.sulfuricLayers;

import gregtech.api.recipes.*;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;

import lombok.experimental.UtilityClass;
import tkcy.tktech.api.recipes.recipemaps.TkTechRecipeMaps;
import tkcy.tktech.api.utils.CollectionHelper;
import tkcy.tktech.api.utils.RecipeHelper;

@UtilityClass
public class Hydrogenation {

    public static void init() {
        removeAmmoniaRecipe();

        CollectionHelper.buildMap(sulfuricLayers, desulfurizedFuels)
                .forEach(Hydrogenation::oilDesulfurizationRecipes);

        miscHydrogenations();
    }

    private static void miscHydrogenations() {
        // N2 + 3 H2 -> NH3
        TkTechRecipeMaps.HYDROGENATION.recipeBuilder()
                .duration(20)
                .EUt(VA[HV])
                .notConsumable(OrePrefix.dust, Iron)
                .fluidInputs(Nitrogen.getFluid(50))
                .fluidInputs(Hydrogen.getFluid(150))
                .fluidOutputs(Ammonia.getFluid(50))
                .buildAndRegister();
    }

    private static void oilDesulfurizationRecipes(Material sulfuricLayer, Material desulfurized) {
        TkTechRecipeMaps.HYDROGENATION.recipeBuilder()
                .duration(20)
                .EUt(VA[MV])
                .fluidInputs(sulfuricLayer.getFluid(50))
                .fluidInputs(Hydrogen.getFluid(400))
                .fluidOutputs(desulfurized.getFluid(50))
                .fluidOutputs(HydrogenSulfide.getFluid(200))
                .buildAndRegister();
    }

    private static void removeAmmoniaRecipe() {
        RecipeMap<?> chemRecipeMap = RecipeMaps.CHEMICAL_RECIPES;
        RecipeHelper.tryToRemoveRecipeWithCircuitConfig(
                chemRecipeMap, 384, 1, Nitrogen.getFluid(1000), Hydrogen.getFluid(3000));

        chemRecipeMap = RecipeMaps.LARGE_CHEMICAL_RECIPES;
        RecipeHelper.tryToRemoveRecipeWithCircuitConfig(
                chemRecipeMap, 384, 1, Nitrogen.getFluid(1000), Hydrogen.getFluid(3000));

        RecipeHelper.tryToRemoveRecipeWithCircuitConfig(
                chemRecipeMap, VA[HV], 24, Nitrogen.getFluid(4000), Methane.getFluid(3000), Oxygen.getFluid(3000));
    }
}
