package tkcy.tktech.common.metatileentities.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.items.itemhandlers.GTItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import tkcy.tktech.api.machines.*;
import tkcy.tktech.api.recipes.logic.*;
import tkcy.tktech.api.recipes.logic.containers.IRecipeLogicContainer;
import tkcy.tktech.api.recipes.logic.containers.InWorldRecipeLogic;
import tkcy.tktech.api.recipes.logic.containers.RecipeLogicsContainer;
import tkcy.tktech.api.recipes.logic.containers.ToolLogic;
import tkcy.tktech.api.recipes.logic.markers.IHideEnergyRecipeLogic;
import tkcy.tktech.api.recipes.recipemaps.TkTechRecipeMaps;
import tkcy.tktech.api.unification.ore.TkTechOrePrefix;
import tkcy.tktech.modules.toolmodule.ToolsModule;

public class MTeWoodWorkshop extends ToolLogicMetaTileEntity
                             implements IUnificationToolMachine, IOnSawClick {

    public MTeWoodWorkshop(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    protected List<ToolsModule.GtTool> getWorkingGtTool() {
        return new ArrayList<>() {

            {
                add(ToolsModule.GtTool.AXE);
                add(ToolsModule.GtTool.SAW);
            }
        };
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new GTItemStackHandler(this, 2);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(this, 2, this, false);
    }

    @Override
    protected SimpleOverlayRenderer getBaseRenderer() {
        return Textures.COKE_BRICKS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        IVertexOperation[] colouredPipeline = ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering())));
        getBaseRenderer().render(renderState, translation, colouredPipeline);
        ColourMultiplier multiplier = new ColourMultiplier(
                GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()));
        colouredPipeline = ArrayUtils.add(pipeline, multiplier);
        Textures.CRAFTING_TABLE.renderOriented(renderState, translation, pipeline, EnumFacing.SOUTH);
        Textures.CRAFTING_TABLE.renderOriented(renderState, translation, pipeline, EnumFacing.NORTH);
        Textures.CRAFTING_TABLE.renderOriented(renderState, translation, pipeline, EnumFacing.EAST);
        Textures.CRAFTING_TABLE.renderOriented(renderState, translation, pipeline, EnumFacing.WEST);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTeWoodWorkshop(metaTileEntityId);
    }

    @Override
    public @NotNull List<OrePrefix> getPartsOrePrefixes() {
        return new ArrayList<>() {

            {
                add(TkTechOrePrefix.strippedWood);
                add(OrePrefix.plank);
            }
        };
    }

    @Override
    protected OnBlockRecipeLogic initRecipeLogic() {
        return new Logic(this, null, TkTechRecipeMaps.WOOD_WORKSHOP_RECIPES);
    }

    @Override
    protected void addExtraTooltip(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("tktech.tool_machine.parts.tooltip", addPartsOrePrefixInformation()));
    }

    @Override
    public boolean onSawClick(EntityPlayer playerIn, EnumHand hand, EnumFacing wrenchSide,
                              CuboidRayTraceResult hitResult) {
        if (playerIn.isSneaking()) {
            ToolLogic toolLogic = getLogic().getToolLogic();
            if (toolLogic != null && toolLogic.getRecipeTool() == ToolsModule.GtTool.SAW) {
                playSawClickSound(playerIn);
            }
        }
        return true;
    }

    private static class Logic extends OnBlockRecipeLogic implements IHideEnergyRecipeLogic {

        public Logic(MetaTileEntity tileEntity, Supplier<IEnergyContainer> energyContainer,
                     RecipeMap<?>... recipeMaps) {
            super(tileEntity, energyContainer, recipeMaps);
        }

        @Override
        public @NotNull IRecipeLogicContainer setRecipeLogicContainer() {
            InWorldRecipeLogic inWorldRecipeLogic = new InWorldRecipeLogic.Builder(this)
                    .doesNeedInWorldBlock(mte -> mte.getPos().up())
                    .doesPlaceOutputBlock(mte -> mte.getPos().up())
                    .doesRemoveInputBlock()
                    .doesSpawnOutputItems()
                    .build();
            ToolLogic toolLogic = new ToolLogic(this);
            return new RecipeLogicsContainer(this, inWorldRecipeLogic, toolLogic);
        }

        @Override
        public boolean consumesEnergy() {
            return false;
        }
    }
}
