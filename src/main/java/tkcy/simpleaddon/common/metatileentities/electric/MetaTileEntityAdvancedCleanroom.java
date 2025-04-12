package tkcy.simpleaddon.common.metatileentities.electric;

import java.util.*;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.*;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.ImageCycleButtonWidget;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.*;
import gregtech.api.util.LocalizationUtils;
import gregtech.api.util.TextComponentUtil;

import tkcy.simpleaddon.api.capabilities.TKCYSADataCodes;
import tkcy.simpleaddon.api.logic.AdvancedCleanroomLogic;
import tkcy.simpleaddon.api.metatileentities.cleanroom.AdvancedCleanroomType;
import tkcy.simpleaddon.api.metatileentities.cleanroom.IAdvancedCleanroomProvider;
import tkcy.simpleaddon.api.metatileentities.cleanroom.MetaTileEntityCleanroomBase;

public class MetaTileEntityAdvancedCleanroom extends MetaTileEntityCleanroomBase
                                             implements IAdvancedCleanroomProvider, IWorkable, IDataInfoProvider {

    private IMultipleTankHandler inputFluidInventory;
    private final AdvancedCleanroomLogic cleanroomLogic;
    private int cleanroomTypeIndex;

    public MetaTileEntityAdvancedCleanroom(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.cleanroomLogic = new AdvancedCleanroomLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAdvancedCleanroom(metaTileEntityId);
    }

    @Override
    protected void initializeAbilities() {
        super.initializeAbilities();
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
    }

    @Override
    protected void resetTileAbilities() {
        super.resetTileAbilities();
        this.inputFluidInventory = new FluidTankList(true);
    }

    @Override
    protected boolean doesHandleFilter() {
        return false;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.inputFluidInventory = new FluidTankList(true);
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return super.autoAbilities().or(abilities(MultiblockAbility.IMPORT_FLUIDS)).setMaxGlobalLimited(2);
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        super.addWarningText(textList);
    }

    @Override
    protected @NotNull Widget getFlexButton(int x, int y, int width, int height) {
        return new ImageCycleButtonWidget(x, y, width, height, GuiTextures.BUTTON_MULTI_MAP,
                AdvancedCleanroomType.ADVANCED_CLEANROOM_TYPES.size(),
                this::getCleanroomTypeIndex,
                this::setCleanroomTypeIndex)
                        .shouldUseBaseBackground().singleTexture()
                        .setTooltipHoverString(i -> LocalizationUtils
                                .format("tkcysa.multiblock.advanced_cleanroom.gas.header",
                                        getCleanroomType().getIntertGasMaterial().getLocalizedName(),
                                        gasAmountToDrain()));
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == TKCYSADataCodes.CLEANROOM_TYPE_INDEX) {
            cleanroomTypeIndex = buf.readByte();
            scheduleRenderUpdate();
        }
    }

    @Override
    public long energyToDrainWhenClean() {
        return getRoomVolume() / 2;
    }

    @Override
    public long energyToDrain() {
        return isClean() ? energyToDrainWhenClean() : getRoomVolume() * 2L;
    }

    @Override
    public int gasAmountToDrainWhenClean() {
        return getRoomVolume();
    }

    @Override
    public int gasAmountToDrain() {
        return isClean() ? gasAmountToDrainWhenClean() : getRoomVolume() * 2;
    }

    @Override
    public int getRoomVolume() {
        return getMaxProgress();
    }

    @Override
    public int getCleanroomTypeIndex() {
        return cleanroomTypeIndex;
    }

    @Override
    public void setCleanroomTypeIndex(int index) {
        this.cleanroomTypeIndex = index;
        if (!getWorld().isRemote) {
            writeCustomData(TKCYSADataCodes.CLEANROOM_TYPE_INDEX, buf -> buf.writeByte(index));
            markDirty();
        }
    }

    @Override
    public void addGasConsumptionInfos(List<ITextComponent> textComponents) {
        textComponents.add(TextComponentUtil.translationWithColor(
                TextFormatting.YELLOW,
                "tkcysa.multiblock.advanced_cleanroom.gas_consumption_infos",
                gasAmountToDrain(),
                getCleanroomType().getIntertGasMaterial().getLocalizedName()));
    }

    @Override
    public boolean drainGas(boolean simulate) {
        int gasToDrain = isClean() ? gasAmountToDrainWhenClean() : gasAmountToDrain();
        FluidStack fluidStackToDrain = getCleanroomType().getIntertGasMaterial().getFluid(gasToDrain);
        FluidStack drainedStack = inputFluidInventory.drain(fluidStackToDrain, false);
        return drainedStack != null && drainedStack.amount == gasToDrain;
    }
}
