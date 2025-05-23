package tkcy.tktech.loaders.recipe.parts;

import static gregtech.api.unification.ore.OrePrefix.*;
import static tkcy.tktech.api.unification.ore.TkTechOrePrefix.denseScrap;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.EnumFacing;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

import lombok.experimental.UtilityClass;
import tkcy.tktech.api.recipes.recipemaps.TkTechRecipeMaps;
import tkcy.tktech.api.unification.flags.TkTechMaterialFlags;
import tkcy.tktech.api.unification.materials.TkTechMaterials;
import tkcy.tktech.modules.toolmodule.ToolsModule;

@UtilityClass
public class PlatesHandler {

    public static void init() {
        plate.addProcessingHandler(PropertyKey.INGOT, PlatesHandler::primitiveProcessPlate);
    }

    private static final List<Material> excludeMaterials = new ArrayList<>() {

        {
            add(Materials.Wood);
            add(Materials.TreatedWood);
            add(Materials.Epoxy);
            add(Materials.ReinforcedEpoxyResin);
            add(Materials.Rubber);
            add(TkTechMaterials.Ceramic);
        }
    };

    private static void primitiveProcessPlate(OrePrefix orePrefix, Material material, IngotProperty ingotProperty) {
        int baseUse = 1 + (int) material.getMass() / 20;
        int denseScrapAmount = 0;

        if (material.hasFlag(TkTechMaterialFlags.IS_POLYMER) || excludeMaterials.contains(material)) return;

        denseScrapAmount = (int) (2 * ingot.getMaterialAmount(material) / denseScrap.getMaterialAmount(material));

        TkTechRecipeMaps.ANVIL_RECIPES.recipeBuilder()
                .input(ingot, material, 2)
                .output(orePrefix, material)
                .output(denseScrap, material, 9)
                .tool(ToolsModule.GtTool.HARD_HAMMER, baseUse, EnumFacing.UP)
                .failedOutputStack(denseScrap, material, denseScrapAmount)
                .buildAndRegister();

        if (!plateDouble.doGenerateItem(material)) return;

        TkTechRecipeMaps.ANVIL_RECIPES.recipeBuilder()
                .tool(ToolsModule.GtTool.HARD_HAMMER, 2 * baseUse, EnumFacing.UP)
                .input(orePrefix, material, 3)
                .output(OrePrefix.plateDouble, material)
                .output(denseScrap, material, 9)
                .failedOutputStack(denseScrap, material, denseScrapAmount * 2)
                .buildAndRegister();
    }
}
