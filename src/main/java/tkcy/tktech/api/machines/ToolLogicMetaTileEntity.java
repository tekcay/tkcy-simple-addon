package tkcy.tktech.api.machines;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import lombok.Getter;
import tkcy.tktech.api.recipes.logic.OnBlockRecipeLogic;
import tkcy.tktech.modules.toolmodule.ToolsModule;
import tkcy.tktech.modules.toolmodule.WorkingTool;

@Getter
@WorkingTool
public abstract class ToolLogicMetaTileEntity extends MetaTileEntity
                                              implements IOnAnyToolClick, IRightClickItemTransfer {

    private final OnBlockRecipeLogic logic;

    public ToolLogicMetaTileEntity(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.logic = initRecipeLogic();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        String tools = getWorkingGtTool()
                .stream()
                .map(ToolsModule.GtTool::toString)
                .collect(Collectors.joining(", "));

        EnumFacing enumFacing = getRecipeTriggerFacing();
        if (enumFacing == null) {
            tooltip.add(I18n.format("tktech.tool_machine.sneak_right_click_with_tool.tooltip.1", tools));
        } else {
            tooltip.add(I18n.format("tktech.tool_machine.sneak_right_click_with_tool.facing.tooltip",
                    enumFacing.getName().toUpperCase(), tools));
        }

        addExtraTooltip(stack, player, tooltip, advanced);
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public boolean showAnyToolClickTooltip() {
        return true;
    }

    @Override
    public void onAnyToolClick(ToolsModule.GtTool tool, boolean isPlayerSneaking, EnumFacing faceClick) {
        if (!isPlayerSneaking) return;
        this.logic.runToolRecipeLogic(tool, faceClick);
    }

    @Override
    public void onAnyToolClickTooltip(@NotNull List<String> tooltips) {
        tooltips.add(I18n.format("tktech.metatileentity.on_any_tool_click.sneak.invalidate.tooltip"));
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    protected void addExtraTooltip(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {}

    @Nullable
    protected EnumFacing getRecipeTriggerFacing() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    protected abstract SimpleOverlayRenderer getBaseRenderer();

    protected abstract List<ToolsModule.GtTool> getWorkingGtTool();

    protected abstract OnBlockRecipeLogic initRecipeLogic();
}
