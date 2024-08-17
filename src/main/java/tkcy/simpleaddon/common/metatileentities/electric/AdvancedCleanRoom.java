package tkcy.simpleaddon.common.metatileentities.electric;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.CleanroomLogic;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityCleanroom;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import tkcy.simpleaddon.api.machines.AdvancedCleanRoomLogic;

import java.util.ArrayList;

public class AdvancedCleanRoom extends MetaTileEntityCleanroom {

    private AdvancedCleanRoomLogic cleanroomLogic;
    private final int atmosphereGasPerProgress = 100;
    private final FluidStack atmosphereGasPerProgressStack;

    public AdvancedCleanRoom(ResourceLocation metaTileEntityId, FluidStack atmosphereGas) {
        super(metaTileEntityId);
        this.atmosphereGasPerProgressStack = new FluidStack(atmosphereGas, atmosphereGasPerProgress);
        this.cleanroomLogic = new AdvancedCleanRoomLogic(this);
    }


    @Override
    protected void initializeAbilities() {
        super.initializeAbilities();
        this.importFluids = new FluidTankList(false, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return super.autoAbilities().or(abilities(MultiblockAbility.IMPORT_FLUIDS));
    }

    public boolean drainFluid(boolean simulate) {
        FluidStack remainder = getImportFluids().drain(atmosphereGasPerProgress, simulate);
        return remainder != null && remainder.isFluidEqual(atmosphereGasPerProgressStack);
    }
}
