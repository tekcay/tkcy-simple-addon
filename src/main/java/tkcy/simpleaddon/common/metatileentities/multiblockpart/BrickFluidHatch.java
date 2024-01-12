package tkcy.simpleaddon.common.metatileentities.multiblockpart;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch;

import tkcy.simpleaddon.api.capabilities.TKCYSAMultiblockAbility;

public class BrickFluidHatch extends MetaTileEntityFluidHatch {

    public BrickFluidHatch(ResourceLocation metaTileEntityId, boolean isExportHatch) {
        super(metaTileEntityId, 0, isExportHatch);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new BrickFluidHatch(metaTileEntityId, isExportHatch);
    }

    @Override
    public ICubeRenderer getBaseTexture() {
        return Textures.COKE_BRICKS;
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return isExportHatch ? TKCYSAMultiblockAbility.BRICK_FLUIDS_OUTPUT : TKCYSAMultiblockAbility.BRICK_FLUIDS_INPUT;
    }
}