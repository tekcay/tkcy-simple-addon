package tkcy.simpleaddon.api.unification.materials.other;

import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.DULL;
import static gregtech.api.unification.material.info.MaterialIconSet.SAND;
import static tkcy.simpleaddon.api.unification.materials.TKCYSAMaterials.Ceramic;
import static tkcy.simpleaddon.api.unification.materials.TKCYSAMaterials.MicaPulp;
import static tkcy.simpleaddon.api.utils.TKCYSAUtil.tkcysa;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MiscMaterials {

    public static int register(int startdId) {
        Ceramic = new Material.Builder(startdId++, tkcysa("ceramic"))
                .dust()
                .ingot()
                .liquid(new FluidBuilder().temperature(2500))
                .flags(GENERATE_ROUND, GENERATE_GEAR, GENERATE_ROD, GENERATE_PLATE, GENERATE_RING, GENERATE_SMALL_GEAR,
                        GENERATE_BOLT_SCREW, GENERATE_LONG_ROD)
                .color(0xe47a17)
                .iconSet(DULL)
                .colorAverage()
                .build();

        MicaPulp = new Material.Builder(startdId++, tkcysa("mica_pulp"))
                .dust(1)
                .color(0xf1cd91).iconSet(SAND)
                .build();

        return startdId;
    }
}
