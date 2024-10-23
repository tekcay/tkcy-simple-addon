package tkcy.simpleaddon.api.capabilities;

import tkcy.simpleaddon.api.utils.units.CommonUnits;
import tkcy.simpleaddon.modules.capabilitiesmodule.CapabilityModule;

public interface TemperatureContainer extends DefaultContainer {

    @Override
    default CapabilityModule.ContainerType getContainerType() {
        return CapabilityModule.ContainerType.TEMPERATURE;
    }

    @Override
    default int getDefaultValue() {
        return 298;
    }

    @Override
    default CommonUnits getBaseUnit() {
        return CommonUnits.kelvin;
    }
}
