package tkcy.simpleaddon.common.metatileentities.multiprimitive;

import java.util.List;
import java.util.function.Function;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.impl.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.unification.material.Material;

import tkcy.simpleaddon.api.capabilities.TKCYSAMultiblockAbilities;
import tkcy.simpleaddon.api.metatileentities.MetaTileEntityStorageFormat;
import tkcy.simpleaddon.api.utils.StorageUtils;
import tkcy.simpleaddon.api.utils.TKCYSALog;
import tkcy.simpleaddon.api.utils.units.CommonUnits;
import tkcy.simpleaddon.common.metatileentities.storage.MetaTileEntityModulableCrateValve;
import tkcy.simpleaddon.modules.storagemodule.StorageModule;

@StorageModule.StorageModulable
public class MetaTileEntityMultiblockCrate extends MetaTileEntityMultiblockStorage<IItemHandler, ItemStack>
                                           implements MetaTileEntityStorageFormat<ItemStack> {

    private FilteredItemHandler filteredItemHandler;
    private IItemHandlerModifiable itemStackHandler;

    public MetaTileEntityMultiblockCrate(ResourceLocation metaTileEntityId, Material material, boolean isLarge) {
        super(metaTileEntityId, material, isLarge);
    }

    @Override
    protected void setLayerCapacity(boolean isLarge) {
        this.layerCapacity = (int) Math.pow(10, 6) * (isLarge ? 21 : 1);
    }

    @Override
    protected MetaTileEntityModulableCrateValve getValve(Material material) {
        return StorageModule.getCrateValve(material);
    }

    @Override
    protected void initializeInventory() {
        if (this.getMaterial() == null) return;
        super.initializeInventory();
        this.itemStackHandler = new FilteredItemHandler(this, 1);

        // this.filteredItemHandler = new FilteredItemHandler(this, this.totalCapacity);
        // this.filteredItemHandler.setFillPredicate(itemStack -> this.filteredItemHandler.getStackInSlot(0).isEmpty()
        // ||
        // this.filteredItemHandler.getStackInSlot(0).isItemEqual(itemStack));

        TKCYSALog.logger.info("this.itemStackHandler.getSlots() : " + this.itemStackHandler.getSlots());

        // this.exportItems = this.importItems = new
        // ItemHandlerList(getAbilities(TKCYSAMultiblockAbilities.CRATE_VALVE));
        this.exportItems = this.importItems = this.itemStackHandler;

        TKCYSALog.logger.info("getAbilities(TKCYSAMultiblockAbilities.CRATE_VALVE).size() : " +
                getAbilities(TKCYSAMultiblockAbilities.CRATE_VALVE).size());

        TKCYSALog.logger.info("this.itemInventory.getSlots() : " + this.itemInventory.getSlots());
        TKCYSALog.logger.info("this.importItems.getSlots() : " + this.importItems.getSlots());
        TKCYSALog.logger.info("this.exportItems.getSlots() : " + this.exportItems.getSlots());

        this.itemInventory = this.itemStackHandler;

        TKCYSALog.logger.info("this.itemInventory.getSlots() post : " + this.itemInventory.getSlots());
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityMultiblockCrate(metaTileEntityId, getMaterial(), isLarge);
    }

    @Override
    protected void updateFormedValid() {
        // this.filteredItemHandler.setSize(this.totalCapacity);
    }

    @Override
    protected Capability<IItemHandler> getCapability() {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    protected IItemHandler getHandler() {
        return getCapability().cast(this.itemInventory);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("tkcysa.multiblock.modulable_tank.tooltip"));
        tooltip.add(I18n.format(
                "tkcysa.multiblock.modulable_storage.layer_infos", getCapacityPerLayerFormatted(), getMaxSideLength()));
    }

    @Override
    public Function<ItemStack, String> getContentLocalizedNameProvider() {
        return ItemStack::getDisplayName;
    }

    @Override
    public Function<ItemStack, Integer> getContentAmountProvider() {
        return ItemStack::getCount;
    }

    @Override
    public StorageUtils<ItemStack> getStorageUtil() {
        return new StorageUtils<>(this);
    }

    @Override
    @Nullable
    public ItemStack getContent() {
        return this.itemInventory.getStackInSlot(0);
    }

    @Override
    public CommonUnits getBaseContentUnit() {
        return CommonUnits.liter;
    }

    @Override
    public String getPercentageTranslationKey() {
        return "tkcysa.multiblock.modulable_storage.fill.percentage";
    }

    @Override
    public String getCapacityTranslationKey() {
        return "tkcysa.multiblock.modulable_storage.capacity";
    }

    @Override
    public String getContentTextTranslationKey() {
        return "tkcysa.multiblock.modulable_storage.content";
    }
}