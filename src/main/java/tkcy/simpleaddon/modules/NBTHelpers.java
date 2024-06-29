package tkcy.simpleaddon.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

public class NBTHelpers {

    public static NBTTagList serializeItemOutputs(NonNullList<ItemStack> itemOutputs) {
        NBTTagList itemOutputsList = new NBTTagList();
        for (ItemStack itemOutput : itemOutputs) {
            itemOutputsList.appendTag(itemOutput.writeToNBT(new NBTTagCompound()));
        }
        return itemOutputsList;
    }

    public static NBTTagList serializeFluidOutputs(List<FluidStack> fluidOutputs) {
        NBTTagList fluidOutputsList = new NBTTagList();
        for (FluidStack fluidOutput : fluidOutputs) {
            fluidOutputsList.appendTag(fluidOutput.writeToNBT(new NBTTagCompound()));
        }
        return fluidOutputsList;
    }

    public static NonNullList<ItemStack> deserializeItemOutputs(@NotNull NBTTagCompound compound,
                                                                @NotNull NBTLabel nbtLabel) {
        NBTTagList itemOutputsList = compound.getTagList(nbtLabel.name(), Constants.NBT.TAG_COMPOUND);
        NonNullList<ItemStack> itemOutputs = NonNullList.create();
        for (int i = 0; i < itemOutputsList.tagCount(); i++) {
            itemOutputs.add(new ItemStack(itemOutputsList.getCompoundTagAt(i)));
        }
        return itemOutputs;
    }

    public static List<FluidStack> deserializeFluidOutputs(@NotNull NBTTagCompound compound,
                                                           @NotNull NBTLabel nbtLabel) {
        NBTTagList fluidOutputsList = compound.getTagList(nbtLabel.name(), Constants.NBT.TAG_COMPOUND);
        List<FluidStack> fluidOutputs = new ArrayList<>();
        for (int i = 0; i < fluidOutputsList.tagCount(); i++) {
            fluidOutputs.add(FluidStack.loadFluidStackFromNBT(fluidOutputsList.getCompoundTagAt(i)));
        }
        return fluidOutputs;
    }
}