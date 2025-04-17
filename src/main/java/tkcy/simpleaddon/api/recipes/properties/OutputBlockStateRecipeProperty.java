package tkcy.simpleaddon.api.recipes.properties;

import java.util.Objects;
import java.util.function.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.properties.RecipeProperty;

import tkcy.simpleaddon.api.utils.BlockStateHelper;
import tkcy.simpleaddon.modules.RecipePropertiesKey;

public class OutputBlockStateRecipeProperty extends RecipeProperty<IBlockState>
                                            implements IRecipePropertyHelper<IBlockState> {

    public static final String KEY = RecipePropertiesKey.OUTPUT_BLOCK_STATE_KEY;
    private static OutputBlockStateRecipeProperty INSTANCE;

    private OutputBlockStateRecipeProperty() {
        super(KEY, IBlockState.class);
    }

    @NotNull
    public static OutputBlockStateRecipeProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutputBlockStateRecipeProperty();
        }
        return INSTANCE;
    }

    @Override
    public @NotNull NBTBase serialize(@NotNull Object value) {
        IBlockState blockState = castValue(value);
        ItemStack blockStack = Item.getItemFromBlock(blockState.getBlock()).getDefaultInstance();
        return blockStack.serializeNBT();
    }

    @Override
    public @NotNull Object deserialize(@NotNull NBTBase nbt) {
        ItemStack blockStack = new ItemStack((NBTTagCompound) nbt);
        return BlockStateHelper.itemStackToBlockState(blockStack);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {}

    @Override
    public Predicate<IBlockState> testSuppliedValue() {
        return Objects::nonNull;
    }

    @Override
    public IBlockState getDefaultValue() {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    public String getErrorMessage() {
        return "BlockStateRecipeProperty must be provided a not null IBlockState";
    }

    @Override
    public RecipeProperty<IBlockState> getProperty() {
        return this;
    }
}
