package tkcy.simpleaddon.common.metatileentities;

import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import tkcy.simpleaddon.api.capabilities.DefaultContainer;
import tkcy.simpleaddon.api.capabilities.helpers.AdjacentCapabilityHelper;
import tkcy.simpleaddon.api.metatileentities.capabilitiescontainers.DefaultContainerMetatileEntity;
import tkcy.simpleaddon.api.metatileentities.capabilitiescontainers.SupplierContainerMetatileEntity;

import java.util.List;

@Getter
public class BurnerMetatileEntity extends SupplierContainerMetatileEntity implements AdjacentCapabilityHelper {


    public BurnerMetatileEntity(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new BurnerMetatileEntity(metaTileEntityId);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    protected void doSomething() {

    }

    @Override
    public void tryToEmit(EnumFacing emittingSide) {

    }


    @Override
    public EnumFacing getEmittingFace() {
        return null;
    }
}
