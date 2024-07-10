package tkcy.simpleaddon.common.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.unification.material.Material;
import gregtech.client.model.MaterialStateMapper;
import gregtech.client.model.modelfactories.MaterialBlockModelLoader;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMaterialBase;
import gregtech.common.blocks.properties.PropertyMaterial;

import tkcy.simpleaddon.api.unification.iconset.TKCYSAMaterialIconType;

public abstract class BlockMaterialCasing extends BlockMaterialBase {
    private PropertyMaterial property;

    public static BlockMaterialCasing create(Material[] materials) {
        PropertyMaterial property = PropertyMaterial.create("variant", materials);
        return new BlockMaterialCasing() {

            @NotNull
            @Override
            public PropertyMaterial getVariantProperty() {
                return property;
            }
        };
    }

    @NotNull
    @Override
    public PropertyMaterial getVariantProperty() {
        return property;
    }

    private BlockMaterialCasing() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("casing");
        setHardness(5.0f);
        setResistance(10.0f);
        // setCreativeTab(GregTechAPI.TAB_GREGTECH_MATERIALS);
    }

    public BlockMaterialCasing(Material[] materials) {
        super(net.minecraft.block.material.Material.IRON);
        PropertyMaterial property = PropertyMaterial.create("variant", materials);
        setTranslationKey("casing");
        setHardness(5.0f);
        setResistance(10.0f);
        // setCreativeTab(GregTechAPI.TAB_GREGTECH_MATERIALS);
    }

    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public net.minecraft.block.material.Material getMaterial(@NotNull IBlockState state) {
        return net.minecraft.block.material.Material.IRON;
    }

    @NotNull
    @Override
    public SoundType getSoundType(@NotNull IBlockState state, @NotNull World world, @NotNull BlockPos pos,
                                  @Nullable Entity entity) {
        return SoundType.STONE;
    }

    @Override
    public String getHarvestTool(@NotNull IBlockState state) {
        return ToolClasses.WRENCH;
    }

    @Override
    public int getHarvestLevel(@NotNull IBlockState state) {
        return 1;
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn) {
        if (ConfigHolder.misc.debug) {
            tooltip.add("MetaItem Id: block" + getGtMaterial(stack).toCamelCaseString());
        }
    }

    @SideOnly(Side.CLIENT)
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new MaterialStateMapper(
                TKCYSAMaterialIconType.casing, s -> getGtMaterial(s).getMaterialIconSet()));
        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state),
                    MaterialBlockModelLoader.registerItemModel(
                            TKCYSAMaterialIconType.casing,
                            getGtMaterial(state).getMaterialIconSet()));
        }
    }
}
