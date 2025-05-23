package tkcy.tktech.common.metatileentities.multiprimitive;

import static tkcy.tktech.api.predicates.TkTechPredicates.brickItemBus;
import static tkcy.tktech.api.predicates.TkTechPredicates.cokeBrick;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

import tkcy.tktech.api.machines.NoEnergyMultiController;
import tkcy.tktech.api.recipes.recipemaps.TkTechRecipeMaps;

public class PrimitiveRoastingOven extends NoEnergyMultiController {

    public PrimitiveRoastingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TkTechRecipeMaps.PRIMITIVE_ROASTING);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new PrimitiveRoastingOven(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "-X-")
                .aisle("XXX", "X#X", "-X-")
                .aisle("XXX", "XYX", "-X-")
                .where('X', cokeBrick()
                        .or(brickItemBus(true, 1))
                        .or(brickItemBus(false, 1)))
                .where('#', air())
                .where('-', any())
                .where('Y', selfPredicate())
                .build();
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.COKE_OVEN_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.COKE_BRICKS;
    }
}
