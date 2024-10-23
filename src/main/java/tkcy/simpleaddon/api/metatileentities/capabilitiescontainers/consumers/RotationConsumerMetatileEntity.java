package tkcy.simpleaddon.api.metatileentities.capabilitiescontainers.consumers;

import net.minecraft.util.ResourceLocation;

import lombok.Getter;
import tkcy.simpleaddon.api.capabilities.RotationContainer;

public abstract class RotationConsumerMetatileEntity extends ConsumerContainerMetatileEntity {

    @Getter
    private RotationContainer heatContainer;

    protected RotationConsumerMetatileEntity(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }
}