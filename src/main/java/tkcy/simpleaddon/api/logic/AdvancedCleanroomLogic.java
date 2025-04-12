package tkcy.simpleaddon.api.logic;

import gregtech.api.capability.impl.CleanroomLogic;
import gregtech.api.metatileentity.MetaTileEntity;

import tkcy.simpleaddon.api.metatileentities.cleanroom.IAdvancedCleanroomProvider;

public class AdvancedCleanroomLogic extends CleanroomLogic {

    private final MetaTileEntity metaTileEntity;

    public AdvancedCleanroomLogic(MetaTileEntity metaTileEntity) {
        super(metaTileEntity, 1);
        this.metaTileEntity = metaTileEntity;
    }

    @Override
    public boolean consumeEnergy(boolean simulate) {
        return super.consumeEnergy(simulate) && consumeGas(simulate);
    }

    protected boolean consumeGas(boolean simulate) {
        return ((IAdvancedCleanroomProvider) metaTileEntity).drainGas(simulate);
    }
}
