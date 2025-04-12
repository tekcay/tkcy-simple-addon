package tkcy.simpleaddon.api.machines;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import tkcy.simpleaddon.api.recipes.builders.ToolRecipeBuilder;
import tkcy.simpleaddon.api.recipes.logic.ToolRecipeLogic;
import tkcy.simpleaddon.modules.toolmodule.ToolsModule;
import tkcy.simpleaddon.modules.toolmodule.WorkingTool;

@WorkingTool
public abstract class ToolLogicMetaTileEntity extends MetaTileEntity {

    protected final ToolRecipeLogic logic;
    protected final RecipeMap<ToolRecipeBuilder> recipeMap;

    public ToolLogicMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<ToolRecipeBuilder> recipeMap) {
        super(metaTileEntityId);
        this.recipeMap = recipeMap;
        this.logic = new ToolRecipeLogic(this, recipeMap);
    }

    /**
     * This is used for recipe trimming during
     * {@link ToolRecipeLogic#prepareRecipe(Recipe, IItemHandlerModifiable, IMultipleTankHandler)}.
     * </br>
     * As we want to spawn items instead of transferring to output inventory, we use -1 (see
     * {@link Recipe#trimRecipeOutputs(Recipe, RecipeMap, int, int)})
     */
    @Override
    public int getItemOutputLimit() {
        if (doOutputInWorld()) {
            return -1;
        } else return this.recipeMap.getMaxOutputs();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("tkcya.tool_machine.sneak_right_click_with_tool.tooltip.1", getWorkingGtTool()));
        addExtraTooltip(stack, player, tooltip, advanced);
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    protected ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player).build(getHolder(), player);
    }

    protected boolean doOutputInWorld() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    protected abstract SimpleOverlayRenderer getBaseRenderer();

    protected abstract ToolsModule.GtTool getWorkingGtTool();

    protected abstract ModularUI.Builder createUITemplate(EntityPlayer entityPlayer);

    protected void addExtraTooltip(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {}
}
