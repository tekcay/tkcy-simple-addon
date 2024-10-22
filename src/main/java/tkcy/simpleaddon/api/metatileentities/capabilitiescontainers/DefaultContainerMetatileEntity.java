package tkcy.simpleaddon.api.metatileentities.capabilitiescontainers;

import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.MetaTileEntity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import tkcy.simpleaddon.api.capabilities.DefaultContainer;

@Getter
public abstract class DefaultContainerMetatileEntity extends MetaTileEntity implements IDataInfoProvider {

    private DefaultContainer internContainer;

    protected DefaultContainerMetatileEntity(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    protected abstract void doSomething();

    @Override
    public void update() {
        super.update();
        if (this.isBlockRedstonePowered()) return;
        if (getInternContainer().isEmpty()) return;

        if (!getWorld().isRemote) {
            doSomething();
        }
    }

    @NotNull
    @Override
    public List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = new ObjectArrayList<>();
        list.add(new TextComponentTranslation("behavior.tricorder.value", getInternContainer().getValue(),
                getInternContainer().getBaseUnit()));
        return list;
    }
}
