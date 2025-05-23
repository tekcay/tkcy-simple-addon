package tkcy.tktech.loaders.recipe.handlers.harderstuff;

import static gregtech.api.GTValues.*;
import static tkcy.tktech.api.unification.materials.TkTechMaterials.CalciumSulfate;
import static tkcy.tktech.api.unification.materials.TkTechMaterials.MicaPulp;
import static tkcy.tktech.common.item.TkTechMetaItems.*;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import lombok.experimental.UtilityClass;
import tkcy.tktech.api.recipes.recipemaps.TkTechRecipeMaps;

@UtilityClass
public class HarderCoilsRecipes {

    public static void init() {
        // Mica based pulp

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Mica, 3)
                .input(OrePrefix.dust, Materials.RawRubber, 2)
                .output(OrePrefix.dust, MicaPulp, 5)
                .EUt(8)
                .duration(400)
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Mica, 3)
                .input(MetaItems.STICKY_RESIN)
                .output(OrePrefix.dust, MicaPulp, 4)
                .EUt(8)
                .duration(400)
                .buildAndRegister();

        // Mica based sheet

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, MicaPulp, 4)
                .input(OrePrefix.dust, Materials.Asbestos, 2)
                .output(MICA_SHEET, 5)
                .EUt(28)
                .duration(400)
                .buildAndRegister();

        // Mica insulator sheet

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .input(MICA_SHEET, 5)
                .input(OrePrefix.dust, Materials.SiliconDioxide, 3)
                .output(MICA_INSULATOR_SHEET, 5)
                .EUt(30)
                .duration(400)
                .buildAndRegister();

        // Mica insulator foil

        TkTechRecipeMaps.CLUSTER_MILL_RECIPES.recipeBuilder()
                .input(MICA_INSULATOR_SHEET)
                .output(MICA_INSULATOR_FOIL, 4)
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        // Coils

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[LV]).input(OrePrefix.wireGtDouble, Materials.Cupronickel, 8)
                .input(OrePrefix.foil, Materials.Bronze, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .input(OrePrefix.dust, CalciumSulfate, 4)
                .fluidInputs(Materials.TinAlloy.getFluid(GTValues.L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).duration(200)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[MV]).input(OrePrefix.wireGtDouble, Materials.Kanthal, 8)
                .input(OrePrefix.foil, Materials.Aluminium, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .input(OrePrefix.dust, CalciumSulfate, 4)
                .fluidInputs(Materials.Copper.getFluid(GTValues.L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL)).duration(300)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[HV]).input(OrePrefix.wireGtDouble, Materials.Nichrome, 8)
                .input(OrePrefix.foil, Materials.StainlessSteel, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .input(OrePrefix.dust, CalciumSulfate, 4)
                .fluidInputs(Materials.Aluminium.getFluid(GTValues.L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME)).duration(400)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[EV]).input(OrePrefix.wireGtDouble, Materials.RTMAlloy, 8)
                .input(OrePrefix.foil, Materials.VanadiumSteel, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .input(OrePrefix.dust, CalciumSulfate, 4)
                .fluidInputs(Materials.Nichrome.getFluid(GTValues.L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.RTM_ALLOY)).duration(500)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[IV]).input(OrePrefix.wireGtDouble, Materials.HSSG, 8)
                .input(OrePrefix.foil, Materials.TungstenCarbide, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .input(OrePrefix.dust, CalciumSulfate, 4)
                .fluidInputs(Materials.Tungsten.getFluid(GTValues.L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G)).duration(600)
                .buildAndRegister();
    }
}
