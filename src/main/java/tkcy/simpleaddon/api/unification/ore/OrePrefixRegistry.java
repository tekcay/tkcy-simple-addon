package tkcy.simpleaddon.api.unification.ore;

import static gregtech.common.items.MetaItems.addOrePrefix;
import static tkcy.simpleaddon.api.unification.ore.TKCYSAOrePrefix.*;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrePrefixRegistry {

    public static void register() {
        addOrePrefix(TKCYSAOrePrefix.electrode);
        addOrePrefix(TKCYSAOrePrefix.anode);
        addOrePrefix(TKCYSAOrePrefix.cathode);
        addOrePrefix(curvedPlate);

        registerComponentsOres();
        registerToolOres();
    }

    private static void registerComponentsOres() {
        addOrePrefix(lvComponents);
        addOrePrefix(mvComponents);
        addOrePrefix(hvComponents);
        addOrePrefix(evComponents);
        addOrePrefix(ivComponents);
        addOrePrefix(luvComponents);
        addOrePrefix(zpmComponents);
        addOrePrefix(uvComponents);
    }

    private static void registerToolOres() {
        addOrePrefix(toolTipSolderingIron);
    }
}
