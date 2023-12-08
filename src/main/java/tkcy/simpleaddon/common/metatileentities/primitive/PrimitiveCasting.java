package tkcy.simpleaddon.common.metatileentities.primitive;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import gregtech.api.GTValues;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.RenderUtil;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import tkcy.simpleaddon.api.machines.PrimitiveSingleBlock;
import tkcy.simpleaddon.api.recipes.TKCYSARecipeMaps;

public class PrimitiveCasting extends PrimitiveSingleBlock {

    public PrimitiveCasting(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TKCYSARecipeMaps.CASTING, Textures.COKE_OVEN_OVERLAY);
    }

    @Override
    public ICubeRenderer getBaseRenderer() {
        return Textures.COKE_BRICKS;
    }

    @Override
    protected SoundEvent getRecipeSound() {
        return SoundEvents.BLOCK_LAVA_EXTINGUISH;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);

        getBaseRenderer().render(renderState, translation, pipeline);
        Textures.COKE_OVEN_OVERLAY.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), false,
                false);
        Textures.STEAM_VENT_OVERLAY.renderSided(EnumFacing.UP, renderState,
                RenderUtil.adjustTrans(translation, EnumFacing.UP, 2), pipeline);

        renderer.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), primitiveLogic.isActive(),
                primitiveLogic.isWorkingEnabled());
    }

    @Override
    public void update() {
        super.update();
        if (isActive()) {
            final BlockPos pos = getPos();
            float x = pos.getX() + 0.5F;
            float z = pos.getZ() + 0.5F;
            final float y = pos.getY() + GTValues.RNG.nextFloat() * 0.375F;
            randomDisplayTick(x, y, z);

            if (getOffsetTimer() % 4 == 0) {
                getWorld().playSound(x, y, z, getRecipeSound(), SoundCategory.BLOCKS, 1.0F,
                        1.0F, false);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected void randomDisplayTick(float x, float y, float z) {
        getWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0, 0, 0);
        getWorld().spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
    }
}
