package tkcy.simpleaddon.common.metatileentities.multiprimitive;

import static gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType.STEEL_PIPE;
import static gregtech.common.blocks.MetaBlocks.BOILER_CASING;

import java.util.function.Function;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

import tkcy.simpleaddon.api.machines.NoEnergyMultiController;
import tkcy.simpleaddon.api.metatileentities.RepetitiveSide;
import tkcy.simpleaddon.api.recipes.TKCYSARecipeMaps;
import tkcy.simpleaddon.api.recipes.logic.NoEnergyParallelLogic;

public class GasRelease extends NoEnergyMultiController implements RepetitiveSide {

    private int height = 1;
    private final String heightMarker = "height";

    public GasRelease(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TKCYSARecipeMaps.GAS_RELEASE);
        this.recipeMapWorkable = new NoEnergyParallelLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new GasRelease(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.FRONT, RelativeDirection.UP)
                .aisle("I")
                .aisle("Y")
                .aisle("P").setRepeatable(getMinSideLength(), getMaxSideLength())
                .aisle("M")
                .where('I', abilities(MultiblockAbility.IMPORT_FLUIDS))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('P', states(getSideBlockBlockState()))
                .where('Y', selfPredicate())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.height = getHeight();
        this.recipeMapWorkable.setParallelLimit(this.getParallelNumber());
        this.recipeMapWorkable.applyParallelBonus(TKCYSARecipeMaps.GAS_RELEASE.recipeBuilder());
        initializeAbilities();
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
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
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger(this.heightMarker, this.height);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.height = data.getInteger(this.heightMarker);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.height);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.height = buf.readInt();
    }

    @Override
    public int getMinSideLength() {
        return 3;
    }

    @Override
    public int getMaxSideLength() {
        return 15;
    }

    @Override
    public IBlockState getSideBlockBlockState() {
        return BOILER_CASING.getState(STEEL_PIPE);
    }

    @Override
    public MetaTileEntity getMetatileEntity() {
        return this;
    }

    @Override
    public int getParallelNumber() {
        return this.height / 3;
    }

    @Override
    public Function<Integer, BlockPos> getRepetitiveDirection() {
        return pos -> this.getPos().up(pos);
    }
}
