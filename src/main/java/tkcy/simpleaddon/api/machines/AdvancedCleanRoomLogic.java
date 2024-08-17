package tkcy.simpleaddon.api.machines;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.CleanroomLogic;
import net.minecraftforge.fluids.FluidStack;
import tkcy.simpleaddon.common.metatileentities.electric.AdvancedCleanRoom;

public class AdvancedCleanRoomLogic extends CleanroomLogic {

    private final AdvancedCleanRoom advancedCleanRoom;

    public AdvancedCleanRoomLogic(AdvancedCleanRoom advancedCleanRoom) {
        super(advancedCleanRoom, GTValues.LV);
        this.advancedCleanRoom = advancedCleanRoom;
    }

    @Override
    protected boolean consumeEnergy(boolean simulate) {
        return super.consumeEnergy(simulate) && advancedCleanRoom.drainFluid(simulate);
    }
}
