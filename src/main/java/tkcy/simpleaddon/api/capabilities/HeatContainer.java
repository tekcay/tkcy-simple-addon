package tkcy.simpleaddon.api.capabilities;

import tkcy.simpleaddon.api.utils.units.CommonUnits;
import tkcy.simpleaddon.modules.capabilitiesmodule.CapabilityModule;

public interface HeatContainer extends DefaultContainer {

    @Override
    default CapabilityModule.ContainerType getContainerType() {
        return CapabilityModule.ContainerType.HEAT;
    }

    @Override
    default int getDefaultValue() {
        return 0;
    }

    @Override
    default CommonUnits getBaseUnit() {
        return CommonUnits.joule;
    }
}
